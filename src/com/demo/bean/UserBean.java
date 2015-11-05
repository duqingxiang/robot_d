package com.demo.bean;

import java.util.Date;
import java.util.Map;

import com.demo.util.StringUtil;


public class UserBean {

	private long userId;
	private String userName;
	private String password;
	private String realName;
	private String qq;
	private String weixin;
	private String email;
	private long phone;
	private String ownSign;
	private int deleteFlag;
	private Date createdDate;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	public String getOwnSign() {
		return ownSign;
	}
	public void setOwnSign(String ownSign) {
		this.ownSign = ownSign;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getWeixin() {
		return weixin;
	}
	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}
	public static   UserBean convert(Map<String, Object> map) {
		if(map == null){
			return null;
		}
		long id=0;
		if(map.get("user_id")!=null){
			id =StringUtil.toLong(map.get("user_id").toString(),0l);
			if(id == 0){
				return null;
			}
		}
		
		UserBean bean = new UserBean();
		bean.setUserId(id);
		if(map.get("user_name")!=null) bean.setUserName(map.get("user_name").toString());
		if(map.get("password_md5")!=null) bean.setPassword(map.get("password_md5").toString());
		if(map.get("phone")!=null) bean.setPhone(StringUtil.toLong(map.get("phone").toString(), 0l));
		if(map.get("real_name")!=null) bean.setRealName(map.get("real_name").toString());
		if(map.get("email")!=null) bean.setEmail(map.get("email").toString());
		if(map.get("qq")!=null) bean.setQq(map.get("qq").toString());
		if(map.get("weixin")!=null) bean.setWeixin(map.get("weixin").toString());
		if(map.get("own_sign")!=null) bean.setOwnSign(map.get("own_sign").toString());
		if(map.get("delete_flag")!=null) bean.setDeleteFlag(StringUtil.toInteger(map.get("delete_flag").toString(), 0));
		if(map.get("created_date")!=null) bean.setCreatedDate((Date)map.get("created_date"));
		
		return bean;
	}
	
}
