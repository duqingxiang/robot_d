package com.demo.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.demo.bean.RobotBlog;
import com.demo.bean.RobotBlogCommont;
import com.demo.bean.RobotBlogTheme;
import com.demo.dao.BlogDao;
import com.demo.util.Db;
import com.demo.util.StringUtil;

@Repository
public class BlogDaoImpl implements BlogDao {
	
	public long insertRobotBlog(RobotBlog blog) {
		String sql = "insert into robot_blog (type,author_id,theme_id,title,short_content,content,created_date) VALUES (?,?,?,?,?,?,now());";
		return Db.executeUpdate(sql, new Object[]{blog.getType(),blog.getAuthorId(),blog.getThemeId(),blog.getTitle(),blog.getShortContent(),blog.getContent()}) ;
	}
	
	public long updateRobotBlog(RobotBlog blog){
		String sql = "update robot_blog set type=?,theme_id=?,title=?,short_content=?,content=? where id = ?";
		return Db.executeUpdate(sql, new Object[]{blog.getType(),blog.getThemeId(),blog.getTitle(),blog.getShortContent(),blog.getContent(),blog.getId()});
	}
	
	public Map<String,Object> getBlogById(long id){
		String sql = "select a.*,u.nick_name nick_name from robot_blog a  left join robot_user u on a.author_id = u.user_id where a.status = 0 and a.id = ? ";
		List<Map<String,Object>> list = Db.executeQuery(sql, new Object[]{id});
		if (CollectionUtils.isEmpty(list))
			return null;
		return list.get(0);
	}
	
	public List<Map<String,Object>> getBlogs(int type ,long author_id ,long theme_id ,String key,int page ,int length){
		StringBuffer sql = new StringBuffer(" select a.*,u.nick_name nick_name,t.style style from robot_blog a ");
		sql.append(" left join robot_user u on a.author_id = u.user_id ");
		sql.append(" left join robot_blog_theme t on a.theme_id = t.id where a.status = 0 ");
		List<Object> params = new ArrayList<Object>();
		if (type != -1) {
			sql.append(" and a.type = ? ");
			params.add(type);
		}
		
		if (author_id != 0) {
			sql.append(" and a.author_id = ? ");
			params.add(author_id);
		}
		
		if (theme_id != -1 && theme_id !=0) {
			sql.append(" and a.theme_id = ? ");
			params.add(theme_id);
		}
		
		if ( !StringUtil.isEmpty(key) ) {
			sql.append(" and a.title like '%"+key+"%'");
		}
		
		sql.append(" order by id desc limit ?,?");
		params.add(page);
		params.add(length);
		return Db.executeQuery(sql.toString(), params.toArray()) ;
	}
	
	public long updateRobotBlogStatus(long id,int status) {
		String sql = "update robot_blog set status = ? where id = ?";
		return Db.executeUpdate(sql, new Object[]{status,id});
	}
	
	public long updateRobotBlogStatusByThemeId(long theme_id ,int status) {
		String sql = "update robot_blog set status = ? where theme_id = ?";
		return Db.executeUpdate(sql, new Object[]{status,theme_id});
	}

	
	public long insertRobotBlogTheme(RobotBlogTheme theme) {
		String sql = "insert into robot_blog_theme (user_id,type,title,style) values (?,?,?,?)";
		return Db.executeUpdate(sql, new Object[]{theme.getUserId(),theme.getType(),theme.getTitle(),theme.getStyle()});
	}
	
	public long updateRobotBlogThemeTitle(long id ,String title) {
		String sql = "update robot_blog_theme set title = ? where id = ?";
		return Db.executeUpdate(sql, new Object[]{title,id});
	}
	
	public long updateRobotBlogThemeStatus(long id,int status) {
		String sql = "update robot_blog_theme set status = ? where id = ?";
		return Db.executeUpdate(sql, new Object[]{status,id});
	}
	
	public Map<String,Object> getRobotBlogThemeById(long id) {
		String sql = "select * from robot_blog_theme where status= 0 and id = ?";
		List<Map<String,Object>> list = Db.executeQuery(sql, new Object[]{id});
		if (CollectionUtils.isEmpty(list))
			return null ;
		return list.get(0);
	}
	
	public List<Map<String,Object>> getPublicRobotBlogTheme() {
		String sql = "select * from robot_blog_theme where status= 0 and type = ?";
		return Db.executeQuery(sql, new Object[]{0});
	}
	
	public List<Map<String,Object>> getPrivateRobotBlogTheme(long userid ,int type) {
		String sql = "select * from robot_blog_theme where status= 0 and user_id = ? and type = ?";
		return Db.executeQuery(sql, new Object[]{userid,type});
	}
	
	public long insertRobotBlogCommont(RobotBlogCommont commont){
		String sql = "insert into robot_blog_commont (blog_id,user_id,reply_user_id,type,status,content) VALUES (?,?,?,?,?,?)";
		return Db.executeUpdate(sql, new Object[]{commont.getBlogId(),commont.getUserId(),commont.getReplyUserId(),commont.getType(),commont.getStatus(),commont.getContent()});
	}
	
	public List<Map<String,Object>> getRobotBlogCommontByBlogId(long blogId){
		String sql = "select c.*,u.nick_name from robot_blog_commont c left join robot_user u on c.user_id = u.user_id where blog_id = ? and status=0";
		return Db.executeQuery(sql, new Object[]{blogId});
	}
	
	public List<Map<String,Object>> getRobotBlogCommontById(long id){
		String sql = "select * from robot_blog_commont where id = ?";
		return Db.executeQuery(sql, new Object[]{id});
	}
	
	public long updateRobotBlogCommontStatusById(long id ,int status){
		String sql = "update robot_blog_commont set status= ? where id= ?";
		return Db.executeUpdate(sql, new Object[]{status,id});
	}
}
