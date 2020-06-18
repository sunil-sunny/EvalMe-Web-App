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
	public boolean createNumericOrTextQuestion(BasicQuestionData theBasicQuestionData, User theUser) {
		Connection connection = null;
		PreparedStatement thePreparedStatement = null;
		boolean isQuestionCreated = false;
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			thePreparedStatement = connection.prepareStatement(DataBaseQueriesUtil.createQuestion);
			thePreparedStatement.setString(1, theUser.getBannerId());
			int questionTypeId = this.getIdForQuestionType(theBasicQuestionData.getQuestionType());

			if (questionTypeId == 0) {
				isQuestionCreated = false;
			} else {
				thePreparedStatement.setInt(2, questionTypeId);
				thePreparedStatement.setString(3, theBasicQuestionData.getQuestionTitle().toLowerCase());
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
		PreparedStatement preparedStatementForQuestionCreation = null;
		PreparedStatement preparedStatementForOptionCreation = null;
		ResultSet theResultSet = null;
		boolean isQuestionCreated = false;
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			connection.setAutoCommit(false);
			// save basic question first and opions later
			preparedStatementForQuestionCreation = connection.prepareStatement(DataBaseQueriesUtil.createQuestion,
					PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatementForQuestionCreation.setString(1, theUser.getBannerId());
			int questionTypeId = this.getIdForQuestionType(theMultipleChoiceQuestion.getQuestionType());
			if (questionTypeId == 0) {
				isQuestionCreated = false;
			} else {
				preparedStatementForQuestionCreation.setInt(2, questionTypeId);
				preparedStatementForQuestionCreation.setString(3,
						theMultipleChoiceQuestion.getQuestionTitle().toLowerCase());
				preparedStatementForQuestionCreation.setString(4,
						theMultipleChoiceQuestion.getQuestionText().toLowerCase());
				Timestamp currentTimestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
				preparedStatementForQuestionCreation.setTimestamp(5, currentTimestamp);
				preparedStatementForQuestionCreation.executeUpdate();
				theResultSet = preparedStatementForQuestionCreation.getGeneratedKeys();
				if (theResultSet.next()) {

					long id = theResultSet.getLong(1);
					int questionId = (int) id;
					System.out.println(questionId);
					for (Option theOption : theMultipleChoiceQuestion.getOptionList()) {

						preparedStatementForOptionCreation = connection
								.prepareStatement(DataBaseQueriesUtil.createOptions);
						preparedStatementForOptionCreation.setInt(1, questionId);
						preparedStatementForOptionCreation.setString(2, theOption.getDisplayText());
						preparedStatementForOptionCreation.setInt(3, theOption.getStoredData());
						// System.out.println("Saving option :"+theOption.getDisplayText()+"
						// "+theOption.getStoredData());
						int createdResult = preparedStatementForOptionCreation.executeUpdate();
						// System.out.println("option saved result is"+createdResult);
						if (createdResult == 0) {
							// System.out.println("option not inserted and closing ps");
							isQuestionCreated = false;
							break;
						} else {
							isQuestionCreated = true;
							// System.out.println("option inserted and closing ps");
							if (preparedStatementForOptionCreation != null) {
								preparedStatementForOptionCreation.close();
							}
						}
					}
				}
			}
			if (isQuestionCreated) {
				connection.commit();
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
				if (preparedStatementForQuestionCreation != null) {
					preparedStatementForQuestionCreation.close();
				}
				if (preparedStatementForOptionCreation != null) {
					preparedStatementForOptionCreation.close();
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
	public boolean isQuestionExists(BasicQuestionData theBasicQuestionData) {
		Connection connection = null;
		PreparedStatement thePreparedStatement = null;
		ResultSet theResultSet = null;
		UserService theUserService = SystemConfig.getSingletonInstance().getTheUserService();
		boolean isQuestionExists = false;
		try {
			connection = ConnectionManager.getInstance().getDBConnection();
			thePreparedStatement = connection.prepareStatement(DataBaseQueriesUtil.getQuestionId);
			thePreparedStatement.setString(1, theUserService.getCurrentUser().getBannerId());
			thePreparedStatement.setInt(2, this.getIdForQuestionType(theBasicQuestionData.getQuestionType()));
			thePreparedStatement.setString(3, theBasicQuestionData.getQuestionTitle().toLowerCase());
			thePreparedStatement.setString(4, theBasicQuestionData.getQuestionText().toLowerCase());
			theResultSet = thePreparedStatement.executeQuery();
			if (theResultSet.next()) {
				isQuestionExists = true;
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
		return isQuestionExists;
	}

}
