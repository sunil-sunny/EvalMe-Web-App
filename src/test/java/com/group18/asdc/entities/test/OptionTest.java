package com.group18.asdc.entities.test;

import org.junit.Test;
import com.group18.asdc.entities.Option;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OptionTest{

	@Test
	public void getDisplayText() {
		Option option = new Option();
		option.setDisplayText("Beginner");
		assertTrue(option.getDisplayText().equals("Beginner"));
	}

	@Test
	public void setDisplayText() {
		Option option = new Option();
		option.setDisplayText("Beginner");
		assertTrue(option.getDisplayText().equals("Beginner"));
	}

	@Test
	public void getStoredData() {
		Option option = new Option();
		option.setStoredData(1);
		assertTrue(option.getStoredData()==1);
	}

	@Test
	public void setStoredData() {
		Option option = new Option();
		option.setStoredData(1);
		assertTrue(option.getStoredData()==1);
	}
}
