package com.demo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.demo.bean.RobotBlog;
import com.demo.bean.RobotBlogCommont;
import com.demo.bean.RobotBlogTheme;
import com.demo.bean.RobotUser;
import com.demo.dao.BlogDao;
import com.demo.service.BlogService;
import com.demo.service.DemoService;
import com.demo.util.Logs;

@Service
public class BlogServiceImpl implements BlogService {
	
	@Autowired
	private BlogDao blogDao ;
	@Autowired
	private DemoService demoService;
	
	public List<RobotBlog> getBlogs(RobotUser user ,long theme_id ,String key ,int page ,int length) {
		long author_id = user != null ?user.getUserId() : 0  ;
		int type = 0 ;

		if (theme_id != -1) {//查询全部
			RobotBlogTheme theme = this.getRobotBlogThemeById(theme_id);
			if (theme != null) {
				type = theme.getType();
				if (type != 0 && theme.getUserId() != author_id) {
					//私有主题，并且非主题所有者操作
					type = 0;
					theme_id = 0;
					author_id = 0;
				} else if (type != 0 && theme.getUserId() == author_id) {
					//私有主题，所有者操作
					type = theme.getType();
					author_id = theme.getUserId();
				} else {
					author_id = 0 ;
				}
			} else if (author_id != 0){
				type = -1;
			}
		} else {
			author_id = 0 ;
		}
		
			
		List<RobotBlog> list = this.getBlogs(type, author_id, theme_id, key, page, length);
		
		return list;
	}
	
	public long deleteRobotBlogThemeTitle(long id, long userid) {
		StringBuffer log = new StringBuffer("BlogServiceImpl deleteRobotBlogTheme  id:"+id+"   userid:"+userid);
		try {
			RobotUser user = demoService.getRobotUserById(userid);
			if (user == null || (!user.isAuthor() && !user.isSuper())) {
				log.append(" user is null or user is not author or user is not super");
				Logs.geterrorLogger().error(log.toString());
				return -4;
			}
			
			RobotBlogTheme theme = this.getRobotBlogThemeById(id);
			if (theme == null || (theme.getUserId() != userid && !user.isSuper())) {
				log.append(" theme is not this user ");
				Logs.geterrorLogger().error(log.toString());
				return -5;
			}
			
			long res = this.updateRobotBlogThemeStatus(id, -1);
			if (res > 0) {
				res = this.updateRobotBlogStatusByThemeId(id, -1);
			}
			Logs.geterrorLogger().error(log.append("delete theme success...").toString());
		} catch (Exception e) {
			log.append(" error ...");
			Logs.geterrorLogger().error(log.toString(),e);
			return -6;
		}
		
		return 0;
	}
	
	
	public RobotBlog getBlogById(long id){
		return RobotBlog.convert(blogDao.getBlogById(id));
	}
	public List<RobotBlog> getBlogs(int type ,long author_id ,long theme_id ,String key,int page ,int length){
		return RobotBlog.convertList(blogDao.getBlogs(type, author_id, theme_id, key, page, length));
	}
	
	public RobotBlogTheme getRobotBlogThemeById(long id){
		return RobotBlogTheme.convert(blogDao.getRobotBlogThemeById(id));
	}

	public long insertRobotBlog(RobotBlog blog) {
		return blogDao.insertRobotBlog(blog);
	}
	public long updateRobotBlog(RobotBlog blog){
		return blogDao.updateRobotBlog(blog);
	}
	public long insertRobotBlogTheme(RobotBlogTheme theme) {
		return blogDao.insertRobotBlogTheme(theme);
	}

	public List<RobotBlogTheme> getPublicRobotBlogTheme() {
		return RobotBlogTheme.convertList(blogDao.getPublicRobotBlogTheme());
	}

	public List<RobotBlogTheme> getPrivateRobotBlogTheme(long userid, int type) {
		return RobotBlogTheme.convertList(blogDao.getPrivateRobotBlogTheme(userid, type));
	}
	public long updateRobotBlogThemeTitle(long id ,String title) {
		return blogDao.updateRobotBlogThemeTitle(id, title);
	}
	public long updateRobotBlogStatus(long id, int status) {
		return blogDao.updateRobotBlogStatus(id, status);
	}
	public long updateRobotBlogStatusByThemeId(long theme_id, int status) {
		return blogDao.updateRobotBlogStatusByThemeId(theme_id, status);
	}
	public long updateRobotBlogThemeStatus(long id, int status) {
		return blogDao.updateRobotBlogThemeStatus(id, status);
	}

	public long insertRobotBlogCommont(RobotBlogCommont commont) {
		return blogDao.insertRobotBlogCommont(commont);
	}

	public List<RobotBlogCommont> getRobotBlogCommontByBlogId(long blogId) {
		return RobotBlogCommont.convertList(blogDao.getRobotBlogCommontByBlogId(blogId));
	}
	public RobotBlogCommont getRobotBlogCommontById(long id) {
		List<Map<String,Object>> list = blogDao.getRobotBlogCommontById(id);
		if (CollectionUtils.isEmpty(list))
			return null;
		return RobotBlogCommont.convert(list.get(0));
	}

	public long updateRobotBlogCommontStatusById(long id, int status) {
		return blogDao.updateRobotBlogCommontStatusById(id,status);
	}

}
