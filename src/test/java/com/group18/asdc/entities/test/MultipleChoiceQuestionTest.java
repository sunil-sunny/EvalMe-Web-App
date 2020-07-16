package com.group18.asdc.entities.test;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import com.group18.asdc.entities.MultipleChoiceQuestion;
import com.group18.asdc.entities.Option;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MultipleChoiceQuestionTest {

	@Test
	public void getOptionList() {
		Option option = new Option();
		option.setStoredData(1);
		option.setDisplayText("Beginner");
		Option anotherOption = new Option();
		anotherOption.setStoredData(2);
		anotherOption.setDisplayText("Proficient");
		List<Option> optionList = new ArrayList<Option>();
		optionList.add(option);
		optionList.add(anotherOption);
		MultipleChoiceQuestion getMultipleChoiceQuestion = new MultipleChoiceQuestion();
		getMultipleChoiceQuestion.setOptionList(optionList);
		assertTrue(getMultipleChoiceQuestion.getOptionList().equals(optionList));
	}

	@Test
	public void setOptionList() {
		Option option = new Option();
		option.setStoredData(1);
		option.setDisplayText("Beginner");
		Option anotherOption = new Option();
		anotherOption.setStoredData(2);
		anotherOption.setDisplayText("Proficient");
		List<Option> anotherOptionList = new ArrayList<Option>();
		anotherOptionList.add(option);
		anotherOptionList.add(anotherOption);
		MultipleChoiceQuestion setMultipleChoiceQuestion = new MultipleChoiceQuestion();
		setMultipleChoiceQuestion.setOptionList(anotherOptionList);
		assertTrue(setMultipleChoiceQuestion.getOptionList().equals(anotherOptionList));
	}
}