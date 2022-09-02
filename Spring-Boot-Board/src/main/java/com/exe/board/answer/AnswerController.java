package com.exe.board.answer;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.exe.board.question.Question;
import com.exe.board.question.QuestionService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {

	private final QuestionService questionService;
	private final AnswerService answerService;
	
	@PostMapping("/create/{id}")
	public String createAnswer(Model model,
			@PathVariable("id") Integer id,
			@Valid AnswerForm answerForm, BindingResult bindResult) {
		
		Question question = questionService.getQuestion(id);
		
		if(bindResult.hasErrors()) {
			//값을 받아야함
			model.addAttribute("question", question);
			
			return "question_detail";
		}
		
		answerService.create(question, answerForm.getContent());
		
		return String.format("redirect:/question/detail/%s", id);
	}
}
