package com.exe.board.question;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {

	//private final QuestionRepository questionRepository;
	private final QuestionService questionService;
	
	//GetMapping,PostMapping
	@RequestMapping("/list")
	public String list(Model model) {
		
		//List<Question> lists = questionRepository.findAll();
		List<Question> lists = questionService.getList();
		
		model.addAttribute("lists", lists);
		
		return "question_list";
	}
	
	@RequestMapping(value = "/detail/{id}")
	public String detail(Model model,@PathVariable("id") Integer id) {
		
		Question question = questionService.getQuestion(id);
		
		model.addAttribute("question",question);
		
		return "question_detail";
	}
	
	@GetMapping("/create")
	public String questionCreate() {
		
		return "question_form";
	}
	
	@PostMapping("/create")
	public String questionCreate(@RequestParam String subject, 
			@RequestParam String content) {
		
		//ют╥б
		questionService.create(subject, content);
		
		return "redirect:/question/list";
	}
	
	
	
	
	
	
	
	
	
	
	
}
