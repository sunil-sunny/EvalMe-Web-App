package com.group18.asdc.controller;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.group18.asdc.entities.BasicQuestionData;
import com.group18.asdc.entities.QuestionType;

@RequestMapping("/questionpage")
@Controller
public class QuestionManagerController {

	private Logger log = Logger.getLogger(QuestionManagerController.class.getName());

	@ModelAttribute("question")
	public BasicQuestionData bean() {
		return new BasicQuestionData();
	}

	@GetMapping("/getHome")
	public String getCourseQuestionPage() {
		return "QuestionPageHome";
	}

	@GetMapping("/getCreateQuestionHome")
	public String getcreateQuestionPage(Model model) {
		return "QuestionCreateHome";
	}

	@PostMapping("/getQuestionConfirm")
	public String getQuestionConfirmPage(@ModelAttribute("question") BasicQuestionData basicQuestionData,Model model) {

		log.info("confirming questions based on type");

		System.out.println(basicQuestionData.getQuestionType());
		model.addAttribute("BasicQuestion", basicQuestionData);
		if (basicQuestionData.getQuestionType().equalsIgnoreCase(QuestionType.numericType)
				|| basicQuestionData.getQuestionType().equalsIgnoreCase(QuestionType.freeText)) {
			return "NumericOrTextQuestion";
		}
		if (basicQuestionData.getQuestionType().equalsIgnoreCase(QuestionType.multipleChooseMore)
				|| basicQuestionData.getQuestionType().equalsIgnoreCase(QuestionType.multipleChooseOne)) {

			return "MultipleChoiceQuestion";
		}

		return "error";
	}

}
