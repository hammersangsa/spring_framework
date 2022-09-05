package com.exe.board.user;

import javax.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequestMapping("/user")
@RequiredArgsConstructor
@Controller
public class UserController {

	private final UserService userService;
	
	@GetMapping("/signup")
	public String signup(UserCreateForm userCreateForm) {
		
		return "signup_form";
	}
	
	@PostMapping("/signup")
	public String signup(@Valid UserCreateForm userCreateForm, 
			BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "signup_form";
		}
		
		//비밀번호 확인
		if(!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
			//컬럼,오류코드,오류메세지
			bindingResult.rejectValue("password2", 
					"passwordInCorrect","패스워드가 일치하지 않습니다");
			
			return "signup_form";
		}
		
		try {
			
			userService.create(userCreateForm.getUserName(),
					userCreateForm.getEmail(), userCreateForm.getPassword1());
			
		} catch (DataIntegrityViolationException e) {
			
			e.printStackTrace();
			
			bindingResult.reject("signupFailed","이미 등록된 계정입니다");
			
			return "signup_form";
		} catch (Exception e) {
			
			e.printStackTrace();
			
			bindingResult.reject("signupFailed", e.getMessage());
			
			return "signup_form";
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String login() {
		
		return "login_form";
	}
	
}
