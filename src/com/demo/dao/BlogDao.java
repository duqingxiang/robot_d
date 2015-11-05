package com.demo.dao;

import java.util.List;
import java.util.Map;

import com.demo.bean.RobotBlog;
import com.demo.bean.RobotBlogCommont;
import com.demo.bean.RobotBlogTheme;
import com.demo.util.Db;

public interface BlogDao {
	public long insertRobotBlog(RobotBlog blog);
	public long updateRobotBlog(RobotBlog blog);
	public Map<String,Object> getBlogById(long id);
	public List<Map<String,Object>> getBlogs(int type ,long author_id ,long theme_id ,String key,int page ,int length);
	public long updateRobotBlogStatus(long id,int status);
	public long updateRobotBlogStatusByThemeId(long theme_id ,int status) ;

	public long insertRobotBlogTheme(RobotBlogTheme theme);
	public long updateRobotBlogThemeTitle(long id ,String title) ;
	public long updateRobotBlogThemeStatus(long id,int status);
	public Map<String,Object> getRobotBlogThemeById(long id);
	public List<Map<String,Object>> getPublicRobotBlogTheme();
	public List<Map<String,Object>> getPrivateRobotBlogTheme(long userid ,int type);

	public long insertRobotBlogCommont(RobotBlogCommont commont);
	public List<Map<String,Object>> getRobotBlogCommontByBlogId(long blogId);
	public List<Map<String,Object>> getRobotBlogCommontById(long id);
	public long updateRobotBlogCommontStatusById(long id ,int status);
}
