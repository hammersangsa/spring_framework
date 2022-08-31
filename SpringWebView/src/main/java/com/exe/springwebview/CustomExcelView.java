package com.exe.springwebview;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractJExcelView;

import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class CustomExcelView extends AbstractJExcelView{

	protected void buildExcelDocument(Map<String, Object> model, 
			WritableWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.addHeader("Content-Disposition", 
				"attachment;fileName=sales.xls");
		
		WritableSheet sheet = 
				workbook.createSheet("판매보고서", 0);
		
		sheet.addCell(new Label(0,0,"년도"));
		sheet.addCell(new Label(1, 0, "판매량"));
		
		//임의 값
		for(int i=2001;i<2015;i++) {
			sheet.addCell(new Label(0, i-2000, String.format("%d", i)));
			sheet.addCell(new Label(1, i-2000, String.format("%d", i+100000)));
		}
	}

}
