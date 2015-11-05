package com.demo.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.bean.SqlType;
import com.demo.service.ToolService;
import com.demo.util.StringUtil;

@Controller
@RequestMapping(value = "/tool/*.do")
public class ToolController extends MultiActionController {
	@Autowired
	private ToolService toolService;
	
	public String  sql(HttpServletRequest request,HttpServletResponse response){

		List<SqlType> typeList = toolService.getSqlTypes();
		request.setAttribute("typeList", typeList);
		return "/tool/sql";
	}
	
	public String createTableSql(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String fieldStr = request.getParameter("array");
		String table_name = request.getParameter("table_name");
		JSONObject res = toolService.createSql(fieldStr,table_name);
		out.print(res.toJSONString());
		return null;
	}
	
}
