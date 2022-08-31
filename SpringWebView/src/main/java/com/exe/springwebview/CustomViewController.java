package com.exe.springwebview;

import java.io.FileOutputStream;
import java.io.InputStream;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomViewController {

	@RequestMapping(value = "/simpleCustomView.action")
	public ModelAndView customView() {

		ModelAndView mav = new ModelAndView();
		
		//mav.setViewName("simpleCustomView"); //jsp
		
		//Class ���Ϸ� ������ �ѱ�
		mav.setView(new SimpleCustomView());
		mav.addObject("message","SimpleCustomView class�Դϴ�");
		
		return mav;
	}
	
	@RequestMapping(value = "pdfView.action")
	public ModelAndView getPdfView() {
		
		ModelAndView mav = new ModelAndView();
		
		mav.setView(new CustomPdfView());
		mav.addObject("message","PDF FILE");
		
		return mav;
	}
	
	@RequestMapping(value = "excelView.action")
	public ModelAndView getExcelfView() {
	
		ModelAndView mav = new ModelAndView();
		
		mav.setView(new CustomExcelView());
	
	
		return mav;
	}
	//����ġ�� �⺻ ���� ���ε� ���
	@RequestMapping(value = "upload.action", method = RequestMethod.POST)
	public String upload(MultipartHttpServletRequest request) throws Exception{
		
		String path = request.getSession()
				.getServletContext().getRealPath("/WEB-INF/files");
		
		MultipartFile file = request.getFile("upload");
		
		if(file!=null && file.getSize()>0) {
			
			try {
				
				InputStream is = file.getInputStream();
				
				FileOutputStream fos = 
						new FileOutputStream(path + 
								"/" + file.getOriginalFilename());
				/*
				int data;
				byte[] buffer = new byte[4096];
				
				while((data=is.read(buffer,0,buffer.length))!=-1) {
					fos.write(buffer,0,data);
				}
				*/
				//spring framework ��ü�� ���� ���ε� ��ƿ
				FileCopyUtils.copy(is, fos);
				
				is.close();
				fos.close();
				
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			
		}
		return "uploadResult";
	}
	
	@RequestMapping(value = "/download.action")
	public ModelAndView download() {
		
		ModelAndView mav = new ModelAndView();
		
		mav.setView(new DownloadView());
		
		return mav;
		
	}
	
	
}
