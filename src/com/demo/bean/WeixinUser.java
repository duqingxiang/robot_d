package com.demo.bean;

import java.util.Date;
import java.util.Map;

import com.demo.util.StringUtil;

public class WeixinUser {

	private long id;
	private String fromId;
	private String realName;
	private long phone;
	private int deleteFlag;
	private int roleType;
	private Date createdDate;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFromId() {
		return fromId;
	}
	public void setFromId(String fromId) {
		this.fromId = fromId;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public int getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public int getRoleType() {
		return roleType;
	}
	public void setRoleType(int roleType) {
		this.roleType = roleType;
	}
	public static   WeixinUser convert(Map<String, Object> map) {
		if(map == null){
			return null;
		}
		long id=0;
		if(map.get("id")!=null){
			id =StringUtil.toLong(map.get("id").toString(),0l);
			if(id == 0){
				return null;
			}
		}
		
		WeixinUser user = new WeixinUser();
		user.setId(id);
		if(map.get("from_id")!=null) user.setFromId(map.get("from_id").toString());
		if(map.get("real_name")!=null) user.setRealName(map.get("real_name").toString());
		if(map.get("phone")!=null) user.setPhone(StringUtil.toLong(map.get("phone").toString(), 0l));
		if(map.get("delete_flag")!=null) user.setDeleteFlag(StringUtil.toInteger(map.get("delete_flag").toString(), 0));
		if(map.get("role_type")!=null) user.setRoleType(StringUtil.toInteger(map.get("role_type").toString(), 0));
		if(map.get("created_date")!=null) user.setCreatedDate((Date)map.get("created_date"));
		
		return user;
	}
	
}
