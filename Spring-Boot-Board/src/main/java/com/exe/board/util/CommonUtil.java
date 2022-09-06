package com.exe.board.util;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Component;

@Component
public class CommonUtil {

	public String markdown(String markdown) {
		
		Parser parser = Parser.builder().build();
		
		Node doc = parser.parse(markdown);
		
		HtmlRenderer htmlRenderer = HtmlRenderer.builder().build();
		
		return htmlRenderer.render(doc);
		
	}
	
}
