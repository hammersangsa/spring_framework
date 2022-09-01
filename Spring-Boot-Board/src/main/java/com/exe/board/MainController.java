package com.exe.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	@RequestMapping("/sbb")
	@ResponseBody
	public String hello() {
		return "메인 페이지";
	}

	@RequestMapping("/")
	public String home() {
		return "redirect:/question/list";
	}
	
	
	
}
