package com.demo.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.demo.util.StringUtil;

public class SqlType {

	private long id;
	private String tigs;
	private int status;
	private int type;
	private Date createdDate;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTigs() {
		return tigs;
	}
	public void setTigs(String tigs) {
		this.tigs = tigs;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public static List<SqlType> convertList(List<Map<String,Object>> list) {
		if (CollectionUtils.isEmpty(list))
			return null;
		List<SqlType> result = new ArrayList<SqlType>();
		for (Map<String,Object> map : list)
			result.add(convert(map));
		return result;
	}
	
	public static SqlType convert(Map<String,Object> map){
		if ( map == null )
			return null ;
		
		long id=0;
		if(map.get("id")!=null){
			id =StringUtil.toLong(map.get("id").toString());
			if(id == 0){
				return null;
			}
		}
		
		SqlType type = new SqlType();
		type.setId(id);
		if(map.get("tigs") != null) type.setTigs(map.get("tigs").toString());
		if(map.get("status") != null) type.setStatus(StringUtil.toInteger(map.get("status").toString(),0));
		if(map.get("type") != null) type.setType(StringUtil.toInteger(map.get("type").toString(),0));
		if(map.get("created_date") != null) type.setCreatedDate((Date)map.get("created_date"));
		
		return type ;
	}
	
}
