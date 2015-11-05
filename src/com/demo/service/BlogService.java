package com.demo.service;

import java.util.List;
import java.util.Map;

import com.demo.bean.RobotBlog;
import com.demo.bean.RobotBlogCommont;
import com.demo.bean.RobotBlogTheme;
import com.demo.bean.RobotUser;

public interface BlogService {

	public long insertRobotBlog(RobotBlog blog);
	public long updateRobotBlog(RobotBlog blog);
	public RobotBlog getBlogById(long id);
	public List<RobotBlog> getBlogs(RobotUser user ,long theme_id ,String key ,int page ,int length);
	public long updateRobotBlogStatus(long id,int status);
	public long updateRobotBlogStatusByThemeId(long theme_id ,int status) ;

	public long insertRobotBlogTheme(RobotBlogTheme theme);
	public long deleteRobotBlogThemeTitle(long id,long userid);
	public long updateRobotBlogThemeTitle(long id ,String title) ;
	public long updateRobotBlogThemeStatus(long id,int status);
	public List<RobotBlogTheme> getPublicRobotBlogTheme();
	public List<RobotBlogTheme> getPrivateRobotBlogTheme(long userid ,int type);
	public RobotBlogTheme getRobotBlogThemeById(long id);
	
	public long insertRobotBlogCommont(RobotBlogCommont commont);
	public List<RobotBlogCommont> getRobotBlogCommontByBlogId(long blogId);
	public RobotBlogCommont getRobotBlogCommontById(long id);
	public long updateRobotBlogCommontStatusById(long id ,int status);
}
