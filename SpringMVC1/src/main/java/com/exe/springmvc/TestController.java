package com.exe.springmvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller("test.controller")
public class TestController {

	/*
	 * @RequestMapping(value = "/test/param.action",method = RequestMethod.GET)
	 * public String processGetRequest() {
	 * 
	 * System.out.println("GET방식 request");
	 * 
	 * return "paramResult"; }
	 * 
	 * @RequestMapping(value = "/test/param.action",method = RequestMethod.POST)
	 * public String processPostRequest() {
	 * 
	 * System.out.println("POST방식 request");
	 * 
	 * return "paramResult"; }
	 */
	@RequestMapping(value = "/test/param.action",
			method = {RequestMethod.GET,RequestMethod.POST})
	public String processRequest(PersonDTO dto, String name, 
			HttpServletRequest request) {
		
		System.out.println("Get/Post 방식 Request");
		System.out.println(name);
		System.out.println(request.getParameter("phone"));
		
		System.out.println(dto);
		System.out.println("name : " + dto.getName());
		System.out.println("phone : " + dto.getPhone());
		System.out.println("email : " + dto.getEmail());
		
		return "paramResult";
	}
	
	//mav로 넘기는 방법
	@RequestMapping(value = "/test/mav.action",
			method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView mavRequest(PersonDTO dto) {
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("dto",dto);
		mav.setViewName("paramResult");
		
		return mav;
	}
	//자기 자신으로
	@RequestMapping(value = "/test/redirect.action",
			method = {RequestMethod.GET,RequestMethod.POST})
	public String mavRedirectRequest() {
		
		//return "redirect:/";
		//hello.action을 찾아감
		return "redirect:/hello.action";
		//hello.jsp 찾아감
		//return "hello";
	}
		
		
	
	
	
	
	
	
	
	
	
	
	
	
}
