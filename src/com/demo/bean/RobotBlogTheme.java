package com.demo.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.demo.util.StringUtil;

public class RobotBlogTheme {
	
	private long id;
	private long userId;
	private int type;
	private int status;
	private String title;
	private String style;
	private Date createdDate;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public static RobotBlogTheme convert(Map<String,Object> map){
		if ( map == null )
			return null ;
		
		long id=0;
		if(map.get("id")!=null){
			id =StringUtil.toLong(map.get("id").toString());
			if(id == 0){
				return null;
			}
		}
		
		RobotBlogTheme theme = new RobotBlogTheme();
		theme.setId(id);
		if(map.get("user_id") != null) theme.setUserId(StringUtil.toLong(map.get("user_id").toString(),0L));
		if(map.get("type") != null) theme.setType(StringUtil.toInteger(map.get("type").toString(), 0));
		if(map.get("status") != null) theme.setStatus(StringUtil.toInteger(map.get("status").toString(), 0));
		if(map.get("title") != null) theme.setTitle(map.get("title").toString());
		if(map.get("style") != null) theme.setStyle(map.get("style").toString());
		if(map.get("created_date")!=null) theme.setCreatedDate((Date)map.get("created_date"));
		return theme;
	}
	
	public static List<RobotBlogTheme> convertList(List<Map<String,Object>> list) {
		if (CollectionUtils.isEmpty(list))
			return null;
		List<RobotBlogTheme> resultList = new ArrayList<RobotBlogTheme>();
		for (Map<String,Object> map : list) {
			RobotBlogTheme theme = convert(map);
			resultList.add(theme);
		}
		return resultList;
	} 

}
