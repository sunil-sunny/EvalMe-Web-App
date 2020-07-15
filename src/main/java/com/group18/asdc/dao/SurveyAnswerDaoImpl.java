package com.group18.asdc.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.group18.asdc.database.ConnectionManager;
import com.group18.asdc.database.SQLMethods;
import com.group18.asdc.util.SurveyDataBaseQueries;

public class SurveyAnswerDaoImpl implements SurveyAnswerDao {

    private Logger logger = Logger.getLogger(SurveyAnswerDao.class.getName());

    @Override
    public ArrayList fetchAnswersForSurvey(ArrayList valueList) {
        logger.log(Level.INFO, "Fetching answers for survey from Database survey=" + valueList.get(0));
        SQLMethods sqlImplementation = null;
        ArrayList answerList = new ArrayList<>();
        try {
            Connection connection = ConnectionManager.getInstance().getDBConnection();
            sqlImplementation = new SQLMethods(connection);
            answerList = sqlImplementation.selectQuery(SurveyDataBaseQueries.GET_SURVEY_ANSWERS_DATA.toString(),
                    valueList);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQL Exception while fetching answers for survey ", e);
        } finally {
            if (sqlImplementation != null) {
                sqlImplementation.cleanup();
            }
        }
        return answerList;
    }

}