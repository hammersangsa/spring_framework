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
		
		//��й�ȣ Ȯ��
		if(!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
			//�÷�,�����ڵ�,�����޼���
			bindingResult.rejectValue("password2", 
					"passwordInCorrect","�н����尡 ��ġ���� �ʽ��ϴ�");
			
			return "signup_form";
		}
		
		try {
			
			userService.create(userCreateForm.getUserName(),
					userCreateForm.getEmail(), userCreateForm.getPassword1());
			
		} catch (DataIntegrityViolationException e) {
			
			e.printStackTrace();
			
			bindingResult.reject("signupFailed","�̹� ��ϵ� �����Դϴ�");
			
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
