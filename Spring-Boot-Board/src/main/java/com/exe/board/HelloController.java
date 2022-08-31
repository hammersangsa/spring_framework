package com.exe.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//@RestController = @Controller + @ResponseBody
//react, vue, angular에서 데이터를 JSON으로 처리하기 위해 사용
@Controller
public class HelloController {
//text;responsebody
	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		return "Hello World!";
		//return bbs/list
	}
	
}
