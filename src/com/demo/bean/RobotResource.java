package com.demo.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.demo.util.StringUtil;

public class RobotResource {

	private long id ;
	private long userId;
	private String name ;
	private int type ;
	private String realPath;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getRealPath() {
		return realPath;
	}
	public void setRealPath(String realPath) {
		this.realPath = realPath;
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
	
	public static RobotResource convert(Map<String,Object> map){
		if ( map == null )
			return null ;
		
		long id=0;
		if(map.get("id")!=null){
			id =StringUtil.toLong(map.get("id").toString());
			if(id == 0){
				return null;
			}
		}
		
		RobotResource res = new RobotResource();
		res.setId(id);
		if(map.get("user_id") != null) res.setUserId(StringUtil.toLong(map.get("user_id").toString(), 0L));
		if(map.get("name") != null) res.setName(map.get("name").toString());
		if(map.get("type") != null) res.setType(StringUtil.toInteger(map.get("type").toString(),0));
		if(map.get("real_path") != null) res.setRealPath(map.get("real_path").toString());
		if(map.get("net_path") != null) res.setNetPath(map.get("net_path").toString());
		if(map.get("mini_path") != null) res.setMiniPath(map.get("mini_path").toString());
		if(map.get("created_date") != null) res.setCreatedDate((Date)map.get("created_date"));
		
		return res ;
	}
	
	public static List<RobotResource> convertToList(List<Map<String,Object>> list){
		if (CollectionUtils.isEmpty(list))
			return null ;
		List<RobotResource> resultList = new ArrayList<RobotResource>();
		for (Map<String,Object> map : list) {
			RobotResource res = convert(map);
			resultList.add(res);
		}
		return resultList ;
	}
}
