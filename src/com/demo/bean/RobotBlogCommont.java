package com.demo.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.demo.util.StringUtil;

public class RobotBlogCommont {

	private long id ;
	private long blogId;
	private long userId;
	private long replyUserId;
	private int type;
	private int status;
	private String content;
	private Date createdDate;
	
	private String nickName;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getBlogId() {
		return blogId;
	}
	public void setBlogId(long blogId) {
		this.blogId = blogId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getReplyUserId() {
		return replyUserId;
	}
	public void setReplyUserId(long replyUserId) {
		this.replyUserId = replyUserId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public String getSimpleContent(){
		String c = StringUtil.isEmpty(this.content) ? "":this.content.replaceAll("\"", "'");
		return c;
	}
	public static RobotBlogCommont convert(Map<String,Object> map){
		if ( map == null )
			return null ;
		
		long id=0;
		if(map.get("id")!=null){
			id =StringUtil.toLong(map.get("id").toString());
			if(id == 0){
				return null;
			}
		}
		
		RobotBlogCommont blog = new RobotBlogCommont();
		blog.setId(id);
		if(map.get("blog_id") != null) blog.setBlogId(StringUtil.toLong(map.get("blog_id").toString(), 0L));
		if(map.get("user_id") != null) blog.setUserId(StringUtil.toLong(map.get("user_id").toString(), 0L));
		if(map.get("reply_user_id") != null) blog.setReplyUserId(StringUtil.toLong(map.get("reply_user_id").toString(), 0L));
		if(map.get("type") != null) blog.setType(StringUtil.toInteger(map.get("type").toString(), 0));
		if(map.get("status") != null) blog.setStatus(StringUtil.toInteger(map.get("status").toString(), 0));
		if(map.get("content") != null) blog.setContent(map.get("content").toString());
		if(map.get("created_date")!=null) blog.setCreatedDate((Date)map.get("created_date"));
		
		if(map.get("nick_name") != null) blog.setNickName(map.get("nick_name").toString());
		
		return blog;
	}
	
	public static List<RobotBlogCommont> convertList(List<Map<String,Object>> list) {
		if (CollectionUtils.isEmpty(list))
			return null;
		List<RobotBlogCommont> resultList = new ArrayList<RobotBlogCommont>();
		for (Map<String,Object> map : list) {
			RobotBlogCommont blog = convert(map);
			resultList.add(blog);
		}
		return resultList;
	} 
}
