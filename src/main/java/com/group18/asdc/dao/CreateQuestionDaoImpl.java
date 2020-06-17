package com.group18.asdc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.logging.Logger;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.database.ConnectionManager;
import com.group18.asdc.entities.BasicQuestionData;
import com.group18.asdc.entities.MultipleChoiceQuestion;
import com.group18.asdc.entities.Option;
import com.group18.asdc.entities.User;
import com.group18.asdc.service.UserService;
import com.group18.asdc.util.DataBaseQueriesUtil;

public class CreateQuestionDaoImpl implements CreateQuestionDao {

	Logger log = Logger.getLogger(CreateQuestionDaoImpl.class.getName());

	@Override
	public boolean isQuestionTitleExists(BasicQuestionData theBasicQuestionData) {

		Connection connection = null;
		PreparedStatement thePreparedStatement = null;
		ResultSet theResultSet = null;
		boolean isQuestionTitleExists = false;

		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			thePreparedStatement = connection.prepareStatement(DataBaseQueriesUtil.isQuestionTitle);
			thePreparedStatement.setString(1, theBasicQuestionData.getQuestionTitle());
			theResultSet = thePreparedStatement.executeQuery();
			if (theResultSet.next()) {
				isQuestionTitleExists = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (theResultSet != null) {
					theResultSet.close();
				}
				if (connection != null) {
					connection.close();
				}
				if (thePreparedStatement != null) {
					thePreparedStatement.close();
				}
				log.info("closing connection after having a check if question title exists or not");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return isQuestionTitleExists;
	}

	@Override
	public boolean createQuestionTitle(BasicQuestionData basicQuestionData) {

		Connection connection = null;
		PreparedStatement thePreparedStatement = null;

		boolean isQuestionTitleCreated = false;
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			thePreparedStatement = connection.prepareStatement(DataBaseQueriesUtil.createQuestionTitle);
			thePreparedStatement.setString(1, basicQuestionData.getQuestionTitle().toLowerCase());
			int theResultSet = thePreparedStatement.executeUpdate();
			if (theResultSet != 0) {
				isQuestionTitleCreated = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (connection != null) {
					connection.close();
				}
				if (thePreparedStatement != null) {
					thePreparedStatement.close();
				}
				log.info("closing connection after creating question title");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return isQuestionTitleCreated;
	}

	@Override
	public boolean createNumericOrTextQuestion(BasicQuestionData theBasicQuestionData, User theUser) {
		Connection connection = null;
		PreparedStatement thePreparedStatement = null;
		boolean isQuestionCreated = false;

		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			thePreparedStatement = connection.prepareStatement(DataBaseQueriesUtil.createQuestion);
			thePreparedStatement.setString(1, theUser.getBannerId());
			int questionTypeId = this.getIdForQuestionType(theBasicQuestionData.getQuestionType());
			int questionTitleId = this.getIdForQuestionTitle(theBasicQuestionData.getQuestionTitle());

			if ((questionTypeId == 0) || (questionTitleId == 0)) {
				isQuestionCreated = false;
			} else {
				thePreparedStatement.setInt(2, questionTypeId);
				thePreparedStatement.setInt(3, questionTitleId);
				thePreparedStatement.setString(4, theBasicQuestionData.getQuestionText().toLowerCase());
				Timestamp currentTimestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
				thePreparedStatement.setTimestamp(5, currentTimestamp);
				int createQuestionResult = thePreparedStatement.executeUpdate();
				if (createQuestionResult > 0) {
					isQuestionCreated = true;
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {

				if (connection != null) {
					connection.close();
				}
				if (thePreparedStatement != null) {
					thePreparedStatement.close();
				}
				log.info("closing connection after creating a numeric or text question");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return isQuestionCreated;
	}

	@Override
	public boolean createMultipleChoiceQuestion(MultipleChoiceQuestion theMultipleChoiceQuestion, User theUser) {
		Connection connection = null;
		PreparedStatement thePreparedStatement = null;
		ResultSet theResultSet = null;
		boolean isQuestionCreated = false;
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			// save basic question first and opions later
			BasicQuestionData theBasicQuestionData = new BasicQuestionData();
			theBasicQuestionData.setQuestionText(theMultipleChoiceQuestion.getQuestionText());
			theBasicQuestionData.setQuestionTitle(theMultipleChoiceQuestion.getQuestionTitle());
			theBasicQuestionData.setQuestionType(theMultipleChoiceQuestion.getQuestionType());
			boolean isBasicQuestionCreated = this.createNumericOrTextQuestion(theBasicQuestionData, theUser);
			// System.out.println("Basic question created in dao :"+isBasicQuestionCreated);
			if (isBasicQuestionCreated) {
				// System.out.println("Basic question created");
				int questionId = this.getQuestionId(theBasicQuestionData);
				// System.out.println("question id is "+questionId);
				if (questionId != 0) {
					for (Option theOption : theMultipleChoiceQuestion.getOptionList()) {

						thePreparedStatement = connection.prepareStatement(DataBaseQueriesUtil.createOptions);
						thePreparedStatement.setInt(1, questionId);
						thePreparedStatement.setString(2, theOption.getDisplayText());
						thePreparedStatement.setInt(3, theOption.getStoredData());
						// System.out.println("Saving option :"+theOption.getDisplayText()+"
						// "+theOption.getStoredData());
						int createdResult = thePreparedStatement.executeUpdate();
						// System.out.println("option saved result is"+createdResult);
						if (createdResult == 0) {
							//System.out.println("option not inserted and closing ps");
							isQuestionCreated = false;
							break;
						} else {
							isQuestionCreated = true;
							//System.out.println("option inserted and closing ps");
							if (thePreparedStatement != null) {
								thePreparedStatement.close();
							}
						}
					}
				}
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			try {
				if (theResultSet != null) {
					theResultSet.close();
				}
				if (connection != null) {
					connection.close();
				}
				if (thePreparedStatement != null) {
					thePreparedStatement.close();
				}
				log.info("closing connection after creating multiple choice question");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return isQuestionCreated;
	}

	@Override
	public int getIdForQuestionTitle(String questionTitle) {
		Connection connection = null;
		PreparedStatement thePreparedStatement = null;
		ResultSet theResultSet = null;
		int titleId = 0;
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			thePreparedStatement = connection.prepareStatement(DataBaseQueriesUtil.isQuestionTitle);
			thePreparedStatement.setString(1, questionTitle.toLowerCase());
			theResultSet = thePreparedStatement.executeQuery();
			if (theResultSet.next()) {
				titleId = theResultSet.getInt("qtitleid");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (theResultSet != null) {
					theResultSet.close();
				}
				if (connection != null) {
					connection.close();
				}
				if (thePreparedStatement != null) {
					thePreparedStatement.close();
				}
				log.info("closing connection after getting title id");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return titleId;
	}

	@Override
	public int getIdForQuestionType(String questionType) {

		Connection connection = null;
		PreparedStatement thePreparedStatement = null;
		ResultSet theResultSet = null;
		int typeId = 0;

		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			thePreparedStatement = connection.prepareStatement(DataBaseQueriesUtil.getQuestionTypeId);
			thePreparedStatement.setString(1, questionType);
			theResultSet = thePreparedStatement.executeQuery();
			if (theResultSet.next()) {
				typeId = theResultSet.getInt("questiontypeid");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (theResultSet != null) {
					theResultSet.close();
				}
				if (connection != null) {
					connection.close();
				}
				if (thePreparedStatement != null) {
					thePreparedStatement.close();
				}
				log.info("closing connection after getting title id");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return typeId;
	}

	@Override
	public int getQuestionId(BasicQuestionData theBasicQuestionData) {
		Connection connection = null;
		PreparedStatement thePreparedStatement = null;
		ResultSet theResultSet = null;
		UserService theUserService = SystemConfig.getSingletonInstance().getTheUserService();
		int id = 0;
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			thePreparedStatement = connection.prepareStatement(DataBaseQueriesUtil.getQuestionId);
			thePreparedStatement.setString(1, theUserService.getCurrentUser().getBannerId());
			thePreparedStatement.setInt(2, this.getIdForQuestionType(theBasicQuestionData.getQuestionType()));
			thePreparedStatement.setInt(3, this.getIdForQuestionTitle(theBasicQuestionData.getQuestionTitle()));
			thePreparedStatement.setString(4, theBasicQuestionData.getQuestionText().toLowerCase());
			theResultSet = thePreparedStatement.executeQuery();
			if (theResultSet.next()) {
				id = theResultSet.getInt(1);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {

			try {
				if (theResultSet != null) {
					theResultSet.close();
				}
				if (connection != null) {
					connection.close();
				}
				if (thePreparedStatement != null) {
					thePreparedStatement.close();
				}
				log.info("closing connection after getting question id");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return id;
	}

}
