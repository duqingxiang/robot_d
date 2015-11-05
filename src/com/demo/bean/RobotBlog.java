package com.demo.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.demo.util.StringUtil;

public class RobotBlog {

	private long id ;
	private int type;
	private int status;
	private long authorId;
	private long themeId;
	private String title;
	private String shortContent;
	private String content;
	private Date createdDate;
	
	private String nickName;
	private String style;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public long getAuthorId() {
		return authorId;
	}
	public void setAuthorId(long authorId) {
		this.authorId = authorId;
	}
	public long getThemeId() {
		return themeId;
	}
	public void setThemeId(long themeId) {
		this.themeId = themeId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getShortContent() {
		return shortContent;
	}
	public void setShortContent(String shortContent) {
		this.shortContent = shortContent;
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
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	
	public String getSimpleContent(){
		String c = StringUtil.isEmpty(this.content) ? "":this.content.replaceAll("\"", "'");
		return c;
	}
	public static RobotBlog convert(Map<String,Object> map){
		if ( map == null )
			return null ;
		
		long id=0;
		if(map.get("id")!=null){
			id =StringUtil.toLong(map.get("id").toString());
			if(id == 0){
				return null;
			}
		}
		
		RobotBlog blog = new RobotBlog();
		blog.setId(id);
		if(map.get("type") != null) blog.setType(StringUtil.toInteger(map.get("type").toString(), 0));
		if(map.get("status") != null) blog.setStatus(StringUtil.toInteger(map.get("status").toString(), 0));
		if(map.get("author_id") != null) blog.setAuthorId(StringUtil.toLong(map.get("author_id").toString(), 0L));
		if(map.get("theme_id") != null) blog.setThemeId(StringUtil.toLong(map.get("theme_id").toString(), 0L));
		if(map.get("title") != null) blog.setTitle(map.get("title").toString());
		if(map.get("short_content") != null) blog.setShortContent(map.get("short_content").toString());
		if(map.get("content") != null) blog.setContent(map.get("content").toString());
		if(map.get("created_date")!=null) blog.setCreatedDate((Date)map.get("created_date"));
		
		if(map.get("nick_name") != null) blog.setNickName(map.get("nick_name").toString());
		if(map.get("style")!=null) blog.setStyle(map.get("style").toString());
		
		return blog;
	}
	
	public static List<RobotBlog> convertList(List<Map<String,Object>> list) {
		if (CollectionUtils.isEmpty(list))
			return null;
		List<RobotBlog> resultList = new ArrayList<RobotBlog>();
		for (Map<String,Object> map : list) {
			RobotBlog blog = convert(map);
			resultList.add(blog);
		}
		return resultList;
	} 
}
