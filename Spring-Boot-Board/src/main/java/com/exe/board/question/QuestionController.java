package com.exe.board.question;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import com.exe.board.answer.AnswerForm;
import com.exe.board.user.SiteUser;
import com.exe.board.user.UserService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {

	//private final QuestionRepository questionRepository;
	private final QuestionService questionService;
	private final UserService userService;
	
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
	//GET으로 create하고 저장하는 순간(자기 자신을 받아오므로) POST로 보낸다, 
	//이후 update 시 GET의 create뜬 후 GET방식의 modify로 보낸다
	//POST로 
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/create")
	public String questionCreate(QuestionForm questionForm) {
		
		return "question_form";
	}
	//framework의 autobinding
	//bindingResult엔 @valid 필요
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create")
	public String questionCreate(@Valid QuestionForm questionForm, 
			BindingResult bindingResult, Principal principal) {
		
		if(bindingResult.hasErrors()) {
			return "question_form";
		}
		
		SiteUser siteUser = userService.getUser(principal.getName());
		//입력
		questionService.create(questionForm.getSubject(),
				 questionForm.getContent(),siteUser);
		
		return "redirect:/question/list";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/modify/{id}")
	public String questionModify(QuestionForm questionForm, 
			@PathVariable("id") Integer id,Principal principal) {
		
		Question question = questionService.getQuestion(id);
		
		if(!question.getAuthor().getUserName().equals(principal.getName())) {
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"수정 권한이 없습니다.");
		}
		
		questionForm.setSubject(question.getSubject());
		questionForm.setContent(question.getContent());
		
		return "question_form";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/modify/{id}")
	public String questionModify(@Valid QuestionForm questionForm, 
			BindingResult bindResult, @PathVariable("id") Integer id, Principal principal) {
		
		if(bindResult.hasErrors()) {
			return "question_form";
		}
		
		Question question = questionService.getQuestion(id);
		
		if(!question.getAuthor().getUserName().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"수정 권한이 없습니다.");
		}
		
		questionService.modify(question, questionForm.getSubject(), 
				questionForm.getContent());
		
		return String.format("redirect:/question/detail/%s", id);		
	}
	
}
