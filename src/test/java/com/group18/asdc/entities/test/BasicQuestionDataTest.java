package com.group18.asdc.entities.test;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.group18.asdc.entities.BasicQuestionData;

public class BasicQuestionDataTest {

	@Test
	public void getQuestionTitle() {
		BasicQuestionData getTitleQuestionData = new BasicQuestionData();
		getTitleQuestionData.setQuestionTitle("Java and Data Structures");
		assertTrue(getTitleQuestionData.getQuestionTitle().equals("Java and Data Structures"));
	}

	@Test
	public void setQuestionTitle() {
		BasicQuestionData setTitleQuestionData = new BasicQuestionData();
		setTitleQuestionData.setQuestionTitle("Java and Data Structures");
		assertTrue(setTitleQuestionData.getQuestionTitle().equals("Java and Data Structures"));
	}

	@Test
	public void getQuestionText() {
		BasicQuestionData getQuestionTextData = new BasicQuestionData();
		getQuestionTextData.setQuestionText("How proficient are you in Java, on a scale of 1 to 10?");
		assertTrue(getQuestionTextData.getQuestionText().equals("How proficient are you in Java, on a scale of 1 to 10?"));
	}

	@Test
	public void setQuestionText() {
		BasicQuestionData setQuestionTextData = new BasicQuestionData();
		setQuestionTextData.setQuestionText("How proficient are you in Java, on a scale of 1 to 10?");
		assertTrue(setQuestionTextData.getQuestionText().equals("How proficient are you in Java, on a scale of 1 to 10?"));
	}

	@Test
	public void getQuestionType() {
		BasicQuestionData getQuestionTypeData = new BasicQuestionData();
		getQuestionTypeData.setQuestionType("freetext");
		assertTrue(getQuestionTypeData.getQuestionType().equals("freetext"));
	}

	@Test
	public void setQuestionType() {
		BasicQuestionData setQuestionTypeData = new BasicQuestionData();
		setQuestionTypeData.setQuestionType("freetext");
		assertTrue(setQuestionTypeData.getQuestionType().equals("freetext"));
	}
}