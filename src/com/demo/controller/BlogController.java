package com.demo.controller;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.demo.bean.RobotBlog;
import com.demo.bean.RobotBlogCommont;
import com.demo.bean.RobotBlogTheme;
import com.demo.bean.RobotUser;
import com.demo.service.BlogService;
import com.demo.service.DemoService;
import com.demo.util.CookieUtil;
import com.demo.util.HttpSessionUtil;
import com.demo.util.StringUtil;

@Controller
@RequestMapping(value = "/blog/*.do")
public class BlogController extends MultiActionController  {
	
	@Autowired
	private BlogService blogService;
	@Autowired
	private DemoService demoService;
	
	public String  index(HttpServletRequest request,HttpServletResponse response){
		
		long theme_id = StringUtil.toLong(request.getParameter("t"), -1L); 
		String searchKey = request.getParameter("s");
		int page = StringUtil.toInteger(request.getParameter("p"), 0);

		RobotUser user = HttpSessionUtil.getCookieUserBean(request);
		List<RobotBlog> blogList = blogService.getBlogs(user, theme_id, searchKey, (page*20), 20);
		
		List<RobotBlogTheme> themeList = blogService.getPublicRobotBlogTheme();
		List<RobotBlogTheme> privateThemes = null;
		if (user != null && user.isAuthor()) { //判断作家，添加作家私有主题
			privateThemes = blogService.getPrivateRobotBlogTheme(user.getUserId(), 1);
			if ( !CollectionUtils.isEmpty(privateThemes) ) 
				themeList.addAll(privateThemes);
		}
		request.setAttribute("blogList", blogList);
		request.setAttribute("themeList", themeList);
		request.setAttribute("privateThemeList", privateThemes);
		request.setAttribute("user", user);
		request.setAttribute("search_k", searchKey);
		request.setAttribute("search_t", theme_id);
		request.setAttribute("page", page);
		return "/blog/main";
	}
	
	public String publishBlog(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String title = request.getParameter("title");
		long theme_id = StringUtil.toLong(request.getParameter("theme_id"), 0L);
		int type = StringUtil.toInteger(request.getParameter("type"), -1);
		String shortContent = request.getParameter("shortContent");
		String content = request.getParameter("content");
		
		if (StringUtil.isEmpty(title) || theme_id == 0 || type == -1 || StringUtil.isEmpty(content)) {
			out.print(-1);
			return null ;
		}
		
		RobotUser user = HttpSessionUtil.getCookieUserBean(request);
		if (user == null) {
			out.print(-2);
			return null;
		}
		
		if ( !user.isAuthor() ) {
			out.print(-3);
			return null;
		}
		
		RobotBlogTheme theme = blogService.getRobotBlogThemeById(theme_id);
		if (theme == null || (type == 1 && theme.getUserId() != user.getUserId()) || (type == 0 && theme.getType() == 1)) {
			out.print(-4);
			return null;
		}


		RobotBlog blog = new RobotBlog();
		blog.setTitle(title);
		blog.setType(type);
		blog.setAuthorId(user.getUserId());
		blog.setThemeId(theme_id);
		blog.setShortContent(shortContent);
		blog.setContent(content);
		long res = blogService.insertRobotBlog(blog);
		if (res <=0) {
			out.print(-5);
			return null;
		}
		out.print(0);
		return null;
	}
	
	public String toDetail(HttpServletRequest request ,HttpServletResponse response) {
		
		RobotUser user = HttpSessionUtil.getCookieUserBean(request);
		request.setAttribute("user", user);
		long id = StringUtil.toLong(request.getParameter("id"), 0L);
		RobotBlog blog = blogService.getBlogById(id);
		boolean errorFlag = false ;
		if (blog == null) {
			errorFlag = true;
		} else {
			if (blog.getType() == 1 && ( user == null || user.getUserId() != blog.getAuthorId()))
				errorFlag = true;
		}
		
		if (errorFlag) {
			blog = new RobotBlog();
			blog.setTitle("该文章不存在，或为私有文章不能阅读！");
			blog.setNickName("系统");
			blog.setCreatedDate(new Date());
			blog.setShortContent("请联系提供链接者或管理员以获取正确的地址，有问题请联系duqingxiang92@163.com,非常感谢。");
		}
		
		List<RobotBlogCommont> commontList = blogService.getRobotBlogCommontByBlogId(blog.getId());
		
		request.setAttribute("blog", blog);
		request.setAttribute("commontList", commontList);
		return "/blog/content";
	}
	
	public String toView(HttpServletRequest request ,HttpServletResponse response) throws Exception {
		
		RobotUser user = HttpSessionUtil.getCookieUserBean(request);
		Cookie titleCookie = CookieUtil.getCookie(request, "robot_blog_title");
		Cookie shortCookie = CookieUtil.getCookie(request, "robot_blog_short");
		Cookie contentCookie = CookieUtil.getCookie(request, "robot_blog_content");
		
		String title = titleCookie != null ? URLDecoder.decode(titleCookie.getValue(),"utf-8") : "";
		String shortContent = shortCookie != null ? URLDecoder.decode(shortCookie.getValue(),"utf-8") : "";
		String content = contentCookie != null ? URLDecoder.decode(contentCookie.getValue(),"utf-8") : "";
		
		CookieUtil.deleteCookie(request, response, titleCookie);
		CookieUtil.deleteCookie(request, response, shortCookie);
		CookieUtil.deleteCookie(request, response, contentCookie);
		
		RobotBlog blog = new RobotBlog();
		blog.setTitle(title);
		blog.setNickName(user.getNickName());
		blog.setCreatedDate(new Date());
		blog.setShortContent(shortContent);
		blog.setContent(content);
		request.setAttribute("blog", blog);
		return "/blog/content";
	}
	
	
	public String addTheme(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String title = request.getParameter("title");
		int type = StringUtil.toInteger(request.getParameter("type"), -1);
		int style = StringUtil.toInteger(request.getParameter("style"), 0);
		
		if (StringUtil.isEmpty(title) || (type !=0 && type !=1) ) {
			out.print(-1);
			return null ;
		}
		
		RobotUser user = HttpSessionUtil.getCookieUserBean(request);
		if (user == null) {
			out.print(-2);
			return null;
		}
		
		if ( !user.isAuthor() || (type == 0 && !user.isSuper())) {
			out.print(-3);
			return null;
		}
		
		String styleStr = "panel-default";
		switch (style) {
			case 1:styleStr = "panel-primary";break;
			case 2:styleStr = "panel-success";break;
			case 3:styleStr = "panel-info";break;
			case 4:styleStr = "panel-warning";break;
			case 5:styleStr = "panel-danger";break;
		}
		
		RobotBlogTheme theme = new RobotBlogTheme();
		theme.setUserId(user.getUserId());
		theme.setTitle(title);
		theme.setType(type);
		theme.setStyle(styleStr);
		
		long res = blogService.insertRobotBlogTheme(theme);
		if (res <=0 ) {
			out.print(-4);
			return null;
		}
		out.print(0);
		return null;
	}
	
	public String updateTheme(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		long id = StringUtil.toLong(request.getParameter("id"), 0L);
		String title = request.getParameter("titie");
		if (id == 0 || StringUtil.isEmpty(title)) {
			out.print(-1);
			return null;
		}
		RobotUser user = HttpSessionUtil.getCookieUserBean(request);
		if (user == null) {
			out.print(-2);
			return null;
		}
		
		if ( !user.isAuthor() ||  user.getIsSuper() != 1) {
			out.print(-3);
			return null;
		}
		
		RobotBlogTheme theme = blogService.getRobotBlogThemeById(id);
		if (theme == null) {
			out.print(-4);
			return null;
		}
		
		long res = blogService.updateRobotBlogThemeTitle(id, title);
		if (res <= 0) {
			out.print(-5);
			return null;
		}
		out.print(0);
		return null;
	}
	
	public String deleteTheme(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		long id = StringUtil.toLong(request.getParameter("id"), 0L);
		if (id == 0) {
			out.print(-1);
			return null;
		}
		
		RobotUser user = HttpSessionUtil.getCookieUserBean(request);
		if (user == null) {
			out.print(-2);
			return null;
		}
		
		if ( !user.isAuthor() &&  !user.isSuper()) {
			out.print(-3);
			return null;
		}
		long res = blogService.deleteRobotBlogThemeTitle(id,user.getUserId());
		out.print(res);
		return null;
	}
	
	public String toUpdate(HttpServletRequest request,HttpServletResponse response){
		RobotUser user = HttpSessionUtil.getCookieUserBean(request);
		
		long id = StringUtil.toLong(request.getParameter("id"), 0L);
		RobotBlog blog = blogService.getBlogById(id);
		boolean errorFlag = false ;
		if (user == null) {
			errorFlag = true;
		}else{
			
			List<RobotBlogTheme> themeList = blogService.getPublicRobotBlogTheme();
			List<RobotBlogTheme> privateThemes = null;
			if (user != null && user.isAuthor()) { //判断作家，添加作家私有主题
				privateThemes = blogService.getPrivateRobotBlogTheme(user.getUserId(), 1);
				if ( !CollectionUtils.isEmpty(privateThemes) ) 
					themeList.addAll(privateThemes);
			}
			request.setAttribute("themeList", themeList);
			
			
			if (blog == null) {
				errorFlag = true;
			} else {
				if (blog.getType() == 1 && ( user == null || user.getUserId() != blog.getAuthorId()))
					errorFlag = true;
			}
		}
		
		if (errorFlag) {
			blog = new RobotBlog();
			blog.setTitle("用户未登录或权限异常");
			blog.setNickName("系统");
			blog.setCreatedDate(new Date());
			blog.setShortContent("请联系提供链接者或管理员以获取正确的地址，有问题请联系duqingxiang92@163.com,非常感谢。");
		}
		request.setAttribute("errorFlag", errorFlag);
		request.setAttribute("blog", blog);
		return "/blog/edit";
	}
	
	public String updateBlog(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String title = request.getParameter("title");
		long theme_id = StringUtil.toLong(request.getParameter("theme_id"), 0L);
		int type = StringUtil.toInteger(request.getParameter("type"), -1);
		String shortContent = request.getParameter("shortContent");
		String content = request.getParameter("content");
		long id = StringUtil.toLong(request.getParameter("id"), 0L);
		
		if (id == 0 || StringUtil.isEmpty(title) || theme_id == 0 || type == -1 || StringUtil.isEmpty(content)) {
			out.print(-1);
			return null ;
		}
		
		RobotUser user = HttpSessionUtil.getCookieUserBean(request);
		if (user == null) {
			out.print(-2);
			return null;
		}
		
		if ( !user.isAuthor() ) {
			out.print(-3);
			return null;
		}
		
		RobotBlogTheme theme = blogService.getRobotBlogThemeById(theme_id);
		if (theme == null || (type == 1 && theme.getUserId() != user.getUserId()) || (type == 0 && theme.getType() == 1)) {
			out.print(-4);
			return null;
		}

		RobotBlog defBlog = blogService.getBlogById(id);
		if (defBlog == null || (defBlog.getAuthorId() != user.getUserId() && !user.isSuper())) {
			out.print(-5);
			return null;
		}
		RobotBlog blog = new RobotBlog();
		blog.setId(id);
		blog.setTitle(title);
		blog.setType(type);
		blog.setAuthorId(user.getUserId());
		blog.setThemeId(theme_id);
		blog.setShortContent(shortContent);
		blog.setContent(content);
		long res = blogService.updateRobotBlog(blog);
		if (res <=0) {
			out.print(-6);
			return null;
		}
		out.print(0);
		return null;
	}
	
	public String deleteBlog(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		long id = StringUtil.toLong(request.getParameter("id"), 0L);
		if (id == 0) {
			out.print(-1);
			return null;
		}
		
		RobotUser user = HttpSessionUtil.getCookieUserBean(request);
		if (user == null) {
			out.print(-2);
			return null;
		}
		
		if ( !user.isAuthor() &&  !user.isSuper()) {
			out.print(-3);
			return null;
		}
		RobotBlog blog = blogService.getBlogById(id);
		if (blog == null || blog.getAuthorId() != user.getUserId()) {
			out.print(-4);
			return null;
		}
		long res = blogService.updateRobotBlogStatus(id, -1);
		if (res <= 0) {
			out.print(-5);
			return null;
		}
		out.print(0);
		return null;
	}
	public String saveCommont(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String content = request.getParameter("content");
		long blog_id = StringUtil.toLong(request.getParameter("blog_id"),0L);
		if (StringUtil.isEmpty(content) || blog_id == 0) {
			out.print(-1);
			return null;
		}
		long repty_user_id = StringUtil.toLong(request.getParameter("repty_user_id"), 0L);
		RobotUser user = HttpSessionUtil.getCookieUserBean(request);
		if (user == null) {
			out.print(-2);
			return null;
		}
		
		RobotBlogCommont commont = new RobotBlogCommont();
		commont.setBlogId(blog_id);
		commont.setUserId(user.getUserId());
		if (repty_user_id != 0) {
			RobotUser reptyUser = demoService.getRobotUserById(repty_user_id);
			if (reptyUser != null) {
				content = "<p>回复@"+ reptyUser.getNickName() + "</p>" +content;
				commont.setReplyUserId(repty_user_id);
			}
		}
		commont.setContent(content);
		long res = blogService.insertRobotBlogCommont(commont);
		if (res <= 0) {
			out.print(-5);
			return null;
		}
		out.print(0);
		return null;
	}
	public String deleteCommont(HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		long commont_id = StringUtil.toLong(request.getParameter("id"),0L);
		if (commont_id == 0) {
			out.print(-1);
			return null;
		}
		RobotUser user = HttpSessionUtil.getCookieUserBean(request);
		if (user == null) {
			out.print(-2);
			return null;
		}
		
		RobotBlogCommont commont = blogService.getRobotBlogCommontById(commont_id);
		if (commont == null) {
			out.print(-3);
			return null;
		}
		
		if (commont.getUserId() != user.getUserId() && !user.isSuper()) {
			out.print(-4);
			return null;
		}
		
		long res = blogService.updateRobotBlogCommontStatusById(commont_id, -1);
		if (res <= 0) {
			out.print(-5);
			return null;
		}
		out.print(0);
		return null;
	}
}
