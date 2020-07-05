package com.group18.asdc.service.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.group18.asdc.entities.Course;
import com.group18.asdc.entities.SurveyQuestion;
import com.group18.asdc.service.SurveyService;

@SpringBootTest
public class SurveyServiceImplTest {

	@Test
	public void addQuestionToSurveyTest() {

	}

	@Test
	public void getAllSavedSurveyQuestionsTest() {
		SurveyService surveyService=new SurveyServiceImplMock();
		List<SurveyQuestion> allQuestions = surveyService.getAllSavedSurveyQuestions(new Course());
		assertNotEquals(0, allQuestions.size());
	}

	@Test
	public void saveSurveyQuestionsTest() {
		SurveyService surveyService=new SurveyServiceImplMock();
		List<SurveyQuestion> surveyQuestions = new ArrayList<SurveyQuestion>();
		surveyQuestions.add(new SurveyQuestion());
		boolean isSaved = surveyService.saveSurveyQuestions(surveyQuestions);
		assertTrue(isSaved);
	}

	public void publishSurveyTest() {

	}

	public void removeQuestionFromSurveyTest() {

	}

	public void getCurrentListOfSurveyQuestionsTest() {

	}

	public void isSurveyPublishedForCourseTest() {

	}
}
