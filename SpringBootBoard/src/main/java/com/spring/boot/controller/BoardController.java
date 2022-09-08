package com.spring.boot.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
//controller+responsebody
import org.springframework.web.servlet.ModelAndView;

import com.spring.boot.dto.BoardDTO;
import com.spring.boot.service.BoardService;
import com.spring.boot.util.MyPage;
//bc-(bs-bsi-bm)-(mapper에 맞는 method 찾음)
@RestController
public class BoardController {
	
	@Resource
	private BoardService boardService;
	
	@Autowired
	MyPage myPage;
	
	@GetMapping("/")
	public ModelAndView index() throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("index");
		
		return mav;
	}

	@GetMapping("/created.action")
	public ModelAndView created() throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("bbs/created");
		
		return mav;
		
	}
	
	@PostMapping("/created.action")
	public ModelAndView created_ok(BoardDTO dto,HttpServletRequest request) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		int maxNum = boardService.maxNum();
		
		dto.setNum(maxNum + 1);
		dto.setIpAddr(request.getRemoteAddr());
		
		boardService.insertData(dto);
		
		mav.setViewName("redirect:/list.action");
		
		return mav;
		
	}
	
	@GetMapping("/list.action")
	public ModelAndView list(HttpServletRequest request) throws Exception{
		
		String pageNum = request.getParameter("pageNum");
		
		int currentPage = 1;
		
		if(pageNum!=null) {
			currentPage = Integer.parseInt(pageNum);
		}
		
		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");
		
		if(searchValue==null) {
			searchKey = "subject";
			searchValue = "";
		}else {
			if(request.getMethod().equalsIgnoreCase("GET")) {
				searchValue = URLDecoder.decode(searchValue, "UTF-8");
			}
		}
		
		int dataCount = boardService.getDataCount(searchKey, searchValue);
		int numPerPage = 5;
		int totalPage = myPage.getPageCount(numPerPage, dataCount);
		
		if(currentPage>totalPage) {
			currentPage = totalPage;
		}
		
		int start = (currentPage-1)*numPerPage+1;
		int end = currentPage*numPerPage;
		
		List<BoardDTO> lists =
				boardService.getLists(start, end, searchKey, searchValue);
		
		String param = "";
		if(searchValue!=null&&!searchValue.equals("")) {
			param = "searchKey=" + searchKey;
			param+= "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
		}
		
		String listUrl = "/list.action";
		
		if(!param.equals("")) {
			listUrl += "?" + param;
		}
		
		String pageIndexList = 
				myPage.pageIndexList(currentPage, totalPage, listUrl);
		
		String articleUrl = "/article.action?pageNum=" + currentPage;
		
		if(!param.equals("")) {
			articleUrl += "&" + param;
		}
		
		ModelAndView mav = new ModelAndView();
		//Forwarding Data
		mav.addObject("lists", lists);
		mav.addObject("pageIndexList", pageIndexList);
		mav.addObject("articleUrl", articleUrl);
		mav.addObject("dataCount", dataCount);
		mav.addObject("pageNum", currentPage);
		
		mav.setViewName("bbs/list");
		
		return mav;
	}
	
	@GetMapping("/article.action")
	public ModelAndView article(HttpServletRequest request) throws Exception{
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");
		
		if(searchValue!=null&&!searchValue.equals("")) {
			searchValue = URLDecoder.decode(searchValue, "UTF-8");
		}
		
		boardService.updateHitCount(num);
		
		BoardDTO dto = boardService.getReadData(num);
		
		if(dto==null) {
			
			ModelAndView mav = new ModelAndView();
			
			mav.setViewName("redirect:/list.action?pageNum=" + pageNum);
			
			return mav;
		}
		
		int lineSu = dto.getContent().split("\n").length;
		
		//dto.setContent(dto.getContent().replace("\n", "<br/>"));
		
		String param = "pageNum=" + pageNum;
		
		if(searchValue!=null&&!searchValue.equals("")) {
			param += "&searchKey=" + searchKey;
			param += "&searchValue=" +URLEncoder.encode(searchValue, "UTF-8");
		}

		ModelAndView mav = new ModelAndView();
		
		mav.addObject("dto", dto);
		mav.addObject("params",param);
		mav.addObject("lineSu",lineSu);
		mav.addObject("pageNum",pageNum);
		
		mav.setViewName("bbs/article");

		return mav;
	}
	
	@GetMapping("/updated.action")
	public ModelAndView updated(HttpServletRequest request) throws Exception{
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");
		
		if(searchValue!=null&&!searchValue.equals("")) {
			searchValue = URLDecoder.decode(searchValue, "UTF-8");
		}
		
		BoardDTO dto = boardService.getReadData(num);

		String param = "pageNum=" + pageNum;
		
		if(searchValue!=null&&!searchValue.equals("")) {
			param += "&searchKey=" + searchKey;
			param += "&searchValue=" +URLEncoder.encode(searchValue, "UTF-8");
		}
		
		if(dto==null) {
			
			ModelAndView mav = new ModelAndView();
			
			mav.setViewName("redirect:/list.action?pageNum=" + pageNum);
			
			return mav;
		}
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("dto", dto);
		mav.addObject("pageNum", pageNum);
		mav.addObject("params", param);
		mav.addObject("searchKey", searchKey);
		mav.addObject("searchValue", searchValue);
		
		mav.setViewName("bbs/updated");
		
		return mav;
		
	}
	
	@PostMapping("/updated_ok.action")
	public ModelAndView updated_ok(BoardDTO dto,HttpServletRequest request) throws Exception{
		
		String pageNum = request.getParameter("pageNum");
		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");
		
		if(searchValue!=null&&!searchValue.equals("")) {
			searchValue = URLDecoder.decode(searchValue, "UTF-8");
		}

		dto.setContent(dto.getContent().replaceAll("<br/>", "\r\n"));
		boardService.updateData(dto);
		
		String param = "pageNum=" + pageNum;
		
		if(searchValue!=null&&!searchValue.equals("")) {
			param += "&searchKey=" + searchKey;
			param += "&searchValue=" +URLEncoder.encode(searchValue, "UTF-8");
		}
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("redirect:/list.action?" + param);
		
		return mav;
		
	}
	
	@GetMapping("/deleted_ok.action")
	public ModelAndView deleted_ok(HttpServletRequest request) throws Exception{
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");
		
		if(searchValue!=null&&!searchValue.equals("")) {
			searchValue = URLDecoder.decode(searchValue, "UTF-8");
		}
		
		boardService.deleteData(num);
		
		String param = "pageNum=" + pageNum;
		
		if(searchValue!=null&&!searchValue.equals("")) {
			param += "&searchKey=" + searchKey;
			param += "&searchValue=" +URLEncoder.encode(searchValue, "UTF-8");
		}
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("redirect:/list.action?" + param);
		
		return mav;
	}
	
}
