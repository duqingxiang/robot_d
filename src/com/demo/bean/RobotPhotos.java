package com.demo.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.demo.util.StringUtil;

public class RobotPhotos {

	private long id ;
	private long userId;
	private long positionId;
	private String netPath;
	private String miniPath;
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
	public long getPositionId() {
		return positionId;
	}
	public void setPositionId(long positionId) {
		this.positionId = positionId;
	}
	public String getNetPath() {
		return netPath;
	}
	public void setNetPath(String netPath) {
		this.netPath = netPath;
	}
	public String getMiniPath() {
		return miniPath;
	}
	public void setMiniPath(String miniPath) {
		this.miniPath = miniPath;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public static RobotPhotos convert(Map<String,Object> map){
		if ( map == null )
			return null ;
		
		long id=0;
		if(map.get("id")!=null){
			id =StringUtil.toLong(map.get("id").toString());
			if(id == 0){
				return null;
			}
		}
		
		RobotPhotos pho = new RobotPhotos();
		pho.setId(id);
		if(map.get("user_id") != null) pho.setUserId(StringUtil.toLong(map.get("user_id").toString(), 0L));
		if(map.get("position_id") != null) pho.setPositionId(StringUtil.toLong(map.get("position_id").toString(), 0L));
		if(map.get("net_path") != null) pho.setNetPath(map.get("net_path").toString());
		if(map.get("mini_path") != null) pho.setMiniPath(map.get("mini_path").toString());
		if(map.get("created_date") != null) pho.setCreatedDate((Date)map.get("created_date"));
		
		return pho ;
	}
	
	public static List<RobotPhotos> convertToList(List<Map<String,Object>> list){
		if (CollectionUtils.isEmpty(list))
			return null ;
		List<RobotPhotos> resultList = new ArrayList<RobotPhotos>();
		for (Map<String,Object> map : list) {
			RobotPhotos pos = convert(map);
			resultList.add(pos);
		}
		return resultList ;
	}
	
	
	
}
