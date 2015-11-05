package com.demo.bean;

import java.util.Date;
import java.util.Map;

import com.demo.util.StringUtil;

public class WeixinUserGroup {

	private long id;
	private long userId;
	private long companyId;
	private long departmentId;
	private int status;
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
	public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	public long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(long departmentId) {
		this.departmentId = departmentId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	
	public static   WeixinUserGroup convert(Map<String, Object> map) {
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
		
		WeixinUserGroup group = new WeixinUserGroup();
		group.setId(id);

		if(map.get("user_id")!=null) group.setUserId(StringUtil.toLong(map.get("user_id").toString(),0l));
		if(map.get("company_id")!=null) group.setCompanyId(StringUtil.toLong(map.get("company_id").toString(),0l));
		if(map.get("department_id")!=null) group.setDepartmentId(StringUtil.toLong(map.get("department_id").toString(),0l));
		if(map.get("status")!=null) group.setStatus(StringUtil.toInteger(map.get("status").toString(), 0));
		if(map.get("created_date")!=null) group.setCreatedDate((Date)map.get("created_date"));
		
		return group;
	}
}
