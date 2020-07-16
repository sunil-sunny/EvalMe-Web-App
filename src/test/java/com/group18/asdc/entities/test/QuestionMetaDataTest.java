package com.group18.asdc.entities.test;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.Test;

import com.group18.asdc.entities.BasicQuestionData;
import com.group18.asdc.entities.QuestionMetaData;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuestionMetaDataTest {

	@Test
	public void getQuestionId() {
		QuestionMetaData getQuestionIdData = new QuestionMetaData();
		getQuestionIdData.setQuestionId(256);
		assertTrue(getQuestionIdData.getQuestionId()==256);
	}
	
	@Test
	public void setQuestionId() {
		QuestionMetaData getQuestionIdData = new QuestionMetaData();
		getQuestionIdData.setQuestionId(256);
		assertTrue(getQuestionIdData.getQuestionId()==256);
	}
	
	@Test
	public void getBasicQuestionData() {
		BasicQuestionData getBasicQuestionData = new BasicQuestionData();
		getBasicQuestionData.setQuestionText("Describe an experience you had when working with Java.");
		getBasicQuestionData.setQuestionTitle("Java and Data Structures");
		getBasicQuestionData.setQuestionType("freetext");
		QuestionMetaData getQuestionMetaData = new QuestionMetaData();
		getQuestionMetaData.setBasicQuestionData(getBasicQuestionData);
		assertTrue(getQuestionMetaData.getBasicQuestionData().equals(getBasicQuestionData));
	}

	@Test
	public void setBasicQuestionData() {
		BasicQuestionData setBasicQuestionData = new BasicQuestionData();
		setBasicQuestionData.setQuestionText("Describe an experience you had when working with Java.");
		setBasicQuestionData.setQuestionTitle("Java and Data Structures");
		setBasicQuestionData.setQuestionType("freetext");
		QuestionMetaData setQuestionMetaData = new QuestionMetaData();
		setQuestionMetaData.setBasicQuestionData(setBasicQuestionData);
		assertTrue(setQuestionMetaData.getBasicQuestionData().equals(setBasicQuestionData));
	}

	@Test
	public void getCreationDateTime() {
		Date date = new Date();
		long datetime = date.getTime();
		Timestamp timestamp = new Timestamp(datetime);
		QuestionMetaData questionMetaData = new QuestionMetaData();
		questionMetaData.setCreationDateTime(timestamp);
	}

	@Test
	public void setCreationDateTime() {
		Date date = new Date();
		long datetime = date.getTime();
		Timestamp timestamp = new Timestamp(datetime);
		QuestionMetaData questionMetaData = new QuestionMetaData();
		questionMetaData.setCreationDateTime(timestamp);
	}

}
