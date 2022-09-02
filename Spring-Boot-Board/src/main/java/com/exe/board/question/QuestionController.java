package com.exe.board.question;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exe.board.answer.AnswerForm;

import lombok.RequiredArgsConstructor;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {

	//private final QuestionRepository questionRepository;
	private final QuestionService questionService;
	
	//GetMapping,PostMapping
	@RequestMapping("/list")
	public String list(Model model, @PageableDefault Pageable pageable) {
		
		//List<Question> lists = questionRepository.findAll();
		Page<Question> paging = questionService.getList(pageable);
		
		model.addAttribute("paging", paging);
		
		return "question_list";
	}
	
	@RequestMapping("/detail/{id}")
	public String detail(Model model,@PathVariable("id") Integer id,
			AnswerForm answerForm) {
		
		Question question = questionService.getQuestion(id);
		
		model.addAttribute("question",question);
		
		return "question_detail";
	}
	
	@GetMapping("/create")
	public String questionCreate(QuestionForm questionForm) {
		
		return "question_form";
	}
	//framework의 autobinding
	//bindingResult엔 @valid 필요
	@PostMapping("/create")
	public String questionCreate(@Valid QuestionForm questionForm, 
			BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "question_form";
		}
		//입력
		questionService.create(questionForm.getSubject(),
				 questionForm.getContent());
		
		return "redirect:/question/list";
	}
	
}
