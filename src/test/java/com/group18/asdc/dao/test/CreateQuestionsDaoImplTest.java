package com.group18.asdc.dao.test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.group18.asdc.entities.BasicQuestionData;
import com.group18.asdc.entities.FreeTextQuestion;
import com.group18.asdc.entities.MultipleChoiceQuestion;
import com.group18.asdc.entities.NumericQuestion;
import com.group18.asdc.entities.User;

@SpringBootTest
public class CreateQuestionsDaoImplTest {

	/*
	 * @BeforeAll public void getCourseMock() { this.theCreateQuestionsDaoImplMock =
	 * new CreateQuestionsDaoImplMock(); }
	 */

	@Test
	public void isQuestionTitleExistsTest() {

		BasicQuestionData theBasicQuestionData = new BasicQuestionData();
		theBasicQuestionData.setQuestionText("what is serialization in java?");
		theBasicQuestionData.setQuestionTitle("java");
		theBasicQuestionData.setQuestionType("freetext");
		CreateQuestionsDaoImplMock theCreateQuestionsDaoImplMock = new CreateQuestionsDaoImplMock();
		boolean isCourseTitleExists = theCreateQuestionsDaoImplMock.isQuestionTitleExists(theBasicQuestionData);
		assertTrue(isCourseTitleExists);
	}

	@Test
	public void createQuestionTitleTest() {

		BasicQuestionData theBasicQuestionData = new BasicQuestionData();
		theBasicQuestionData.setQuestionText("what is serialization in java?");
		theBasicQuestionData.setQuestionTitle("java");
		theBasicQuestionData.setQuestionType("freetext");
		CreateQuestionsDaoImplMock theCreateQuestionsDaoImplMock=new CreateQuestionsDaoImplMock();
		boolean isCourseTitleExists = theCreateQuestionsDaoImplMock.createQuestionTitle(theBasicQuestionData);
		assertTrue(isCourseTitleExists);
	}

	@Test
	public void createNumericQuestionTest() {

		NumericQuestion theNumericQuestion = new NumericQuestion();
		User theUser = new User();
		CreateQuestionsDaoImplMock theCreateQuestionsDaoImplMock=new CreateQuestionsDaoImplMock();
		boolean isQuestionCreated = theCreateQuestionsDaoImplMock.createNumericQuestion(theNumericQuestion,
				theUser);
		assertTrue(isQuestionCreated);
	}

	@Test
	public void createFreeTextQuestionTest() {

		FreeTextQuestion theFreeTextQuestion = new FreeTextQuestion();
		User theUser = new User();
		CreateQuestionsDaoImplMock theCreateQuestionsDaoImplMock=new CreateQuestionsDaoImplMock();
		boolean isQuestionCreated = theCreateQuestionsDaoImplMock.createFreeTextQuestion(theFreeTextQuestion,
				theUser);
		assertTrue(isQuestionCreated);
	}

	@Test
	public void createMultipleChoiceQuestionTest() {

		MultipleChoiceQuestion theMultipleChoiceQuestion = new MultipleChoiceQuestion();
		User theUser = new User();
		CreateQuestionsDaoImplMock theCreateQuestionsDaoImplMock=new CreateQuestionsDaoImplMock();
		boolean isQuestionCreated = theCreateQuestionsDaoImplMock
				.createMultipleChoiceQuestion(theMultipleChoiceQuestion, theUser);
		assertTrue(isQuestionCreated);
	}

	@Test
	public void getIdForQuestionTitleTest() {
		CreateQuestionsDaoImplMock theCreateQuestionsDaoImplMock=new CreateQuestionsDaoImplMock();
		int id = theCreateQuestionsDaoImplMock.getIdForQuestionTitle("java");

		boolean gotQuestionTitle=false;
		if(id>0) {
			gotQuestionTitle=true;
		}
		assertFalse(gotQuestionTitle);

	}

	@Test
	public void getIdForQuestionTypeTest() {

		CreateQuestionsDaoImplMock theCreateQuestionsDaoImplMock=new CreateQuestionsDaoImplMock();
		int id = theCreateQuestionsDaoImplMock.getIdForQuestionType("freetext");
		boolean gotQuestionTitle=false;
		if(id>0) {
			gotQuestionTitle=true;
		}
		assertFalse(gotQuestionTitle);

	}

}
