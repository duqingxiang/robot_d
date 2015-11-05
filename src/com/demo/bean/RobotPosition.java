package com.demo.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.demo.util.StringUtil;

public class RobotPosition {

	private long id ;
	private long userId;
	private int type ;
	private String years;
	private String positionX;
	private String positionY;
	private String imgPath;
	private String content;
	private String tags;
	private Date createdDate ;
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
	public String getYears() {
		return years;
	}
	public void setYears(String years) {
		this.years = years;
	}
	public String getPositionX() {
		return positionX;
	}
	public void setPositionX(String positionX) {
		this.positionX = positionX;
	}
	public String getPositionY() {
		return positionY;
	}
	public void setPositionY(String positionY) {
		this.positionY = positionY;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	
	public static RobotPosition convert(Map<String,Object> map){
		if ( map == null )
			return null ;
		
		long id=0;
		if(map.get("id")!=null){
			id =StringUtil.toLong(map.get("id").toString());
			if(id == 0){
				return null;
			}
		}
		
		RobotPosition pos = new RobotPosition();
		pos.setId(id);
		if(map.get("user_id") != null) pos.setUserId(StringUtil.toLong(map.get("user_id").toString(), 0L));
		if(map.get("type") != null) pos.setType(StringUtil.toInteger(map.get("type").toString(),0));
		if(map.get("years") != null) pos.setYears(map.get("years").toString());
		if(map.get("position_x") != null) pos.setPositionX(map.get("position_x").toString());
		if(map.get("position_y") != null) pos.setPositionY(map.get("position_y").toString());
		if(map.get("img_path") != null) pos.setImgPath(map.get("img_path").toString());
		if(map.get("content") != null) pos.setContent(map.get("content").toString());
		if(map.get("tags") != null) pos.setTags(map.get("tags").toString());

		if(map.get("created_date") != null) pos.setCreatedDate((Date)map.get("created_date"));
		
		return pos ;
	}
	
	public static List<RobotPosition> convertToList(List<Map<String,Object>> list){
		if (CollectionUtils.isEmpty(list))
			return null ;
		List<RobotPosition> resultList = new ArrayList<RobotPosition>();
		for (Map<String,Object> map : list) {
			RobotPosition pos = convert(map);
			resultList.add(pos);
		}
		return resultList ;
	}
}
