package com.group18.asdc.dao.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.group18.asdc.entities.QuestionMetaData;

@SpringBootTest
public class ViewQuestionsDaoImplTest {


	@Test
	public void getAllQuestionsTest() {
		
		ViewQuestionsDaoImplMock theViewQuestionsDaoImplMock=new ViewQuestionsDaoImplMock();
		List<QuestionMetaData> theQuestionList=theViewQuestionsDaoImplMock.getAllQuestions();
		assertEquals(2, theQuestionList.size());
		
	}

	@Test
	public void getAllQuestionsSortByDateTest() {
		ViewQuestionsDaoImplMock theViewQuestionsDaoImplMock=new ViewQuestionsDaoImplMock();
		List<QuestionMetaData> theQuestionList=theViewQuestionsDaoImplMock.getAllQuestionsSortByDate();
		assertEquals(2, theQuestionList.size());
		
	}

	
	@Test
	public void getAllQuestionsSortByTitleTest() {
		ViewQuestionsDaoImplMock theViewQuestionsDaoImplMock=new ViewQuestionsDaoImplMock();
		List<QuestionMetaData> theQuestionList=theViewQuestionsDaoImplMock.getAllQuestionsSortByTitle();
		assertEquals(2, theQuestionList.size());
		
	}

}
