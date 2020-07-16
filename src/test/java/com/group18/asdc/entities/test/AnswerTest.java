package com.group18.asdc.entities.test;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import com.group18.asdc.entities.Answer;

public class AnswerTest {

	@Test
	public void setAnswer() {
		Answer answer = new Answer();
		answer.setAnswer("Beginner");
		assertTrue(answer.getAnswers().equals("Beginner"));
	}

	@Test
	public void getAnswers() {
		Answer answer = new Answer();
		answer.setAnswer("Beginner");
		assertTrue(answer.getAnswers().equals("Beginner"));
	}

	@Test
	public void setBannerId() {
		Answer answer = new Answer();
		answer.setBannerId("B00842470");
		assertTrue(answer.getBannerId().equals("B00842470"));
	}

	@Test
	public void getBannerId() {
		Answer answer = new Answer();
		answer.setBannerId("B00842470");
		assertTrue(answer.getBannerId().equals("B00842470"));
	}

	@Test
	public void setAnswerId() {
		Answer answer = new Answer();
		answer.setAnswerId(4);
		assertTrue(answer.getAnswerId()==4);
	}

	@Test
	public void getAnswerId() {
		Answer answer = new Answer();
		answer.setAnswerId(4);
		assertTrue(answer.getAnswerId()==4);
	}

	@Test
	public void setSurveyQuestionId() {
		Answer answer = new Answer();
		answer.setSurveyQuestionId(4);
		assertTrue(answer.getSurveyQuestionId()==4);
	}

	@Test
	public void getSurveyQuestionId() {
		Answer answer = new Answer();
		answer.setSurveyQuestionId(4);
		assertTrue(answer.getSurveyQuestionId()==4);
	}
}
