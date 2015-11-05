package com.demo.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.demo.bean.Memorys;
import com.demo.service.StoryService;

public class StoryController extends MultiActionController {

	private StoryService storyService ;
	
	public void setStoryService(StoryService storyService) {
		this.storyService = storyService;
	}


	public String test(HttpServletRequest request , HttpServletResponse response) throws Exception{
		
		PrintWriter out = response.getWriter() ;
		out.print("Story begin soon...");
		return null ;
	}
	
	public String photos(HttpServletRequest request , HttpServletResponse response) throws Exception{
		
		response.setCharacterEncoding("utf-8");
		return "/story/touchPhotos_1" ;
	}
	public String timeline(HttpServletRequest request , HttpServletResponse response) throws Exception{
		
		List<Memorys> memorysList = storyService.getMemorysList(999 , 0, 0);
		
		request.setAttribute("memorysList", memorysList);
		return "/story/timeline" ;
	}
	public String waterfull(HttpServletRequest request , HttpServletResponse response) throws Exception{
		
		List<Memorys> memorysList = storyService.getMemorysList(2 , 0, 0);
		
		request.setAttribute("memorysList", memorysList);
		return "/story/waterFullPhotos" ;
	}
}
