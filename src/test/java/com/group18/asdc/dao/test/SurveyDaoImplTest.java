package com.group18.asdc.dao.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.group18.asdc.dao.SurveyDao;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.SurveyQuestion;

@SpringBootTest
public class SurveyDaoImplTest {

	@Test
	public void getAllSavedSurveyQuestionsTest() {
		SurveyDao surveyDao = new SurveyDaoImplMock();
		List<SurveyQuestion> allQuestions = surveyDao.getAllSavedSurveyQuestions(new Course());
		assertNotEquals(0, allQuestions.size());
	}

	@Test
	public void saveSurveyQuestionsTest() {
		SurveyDao surveyDao = new SurveyDaoImplMock();
		List<SurveyQuestion> surveyQuestions = new ArrayList<SurveyQuestion>();
		surveyQuestions.add(new SurveyQuestion());
		boolean isSaved = surveyDao.saveSurveyQuestions(surveyQuestions);
		assertTrue(isSaved);
	}

}
