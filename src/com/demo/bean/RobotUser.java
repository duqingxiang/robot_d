package com.demo.bean;

import java.util.Date;
import java.util.Map;

import com.demo.util.StringUtil;

public class RobotUser {

	private long userId ;
	private String email ;
	private String nickName;
	private String loginPwd ;
	private String loginCookie ;
	private int isActive ;
	private int isSuper ;
	private int isAuthor ;
	private String phone ;
	private String headPath ;
	private int settingFlag ;
	private Date createdDate ;
	private Date modifyDate ;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getLoginPwd() {
		return loginPwd;
	}
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
	public String getLoginCookie() {
		return loginCookie;
	}
	public void setLoginCookie(String loginCookie) {
		this.loginCookie = loginCookie;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public int getIsSuper() {
		return isSuper;
	}
	public void setIsSuper(int isSuper) {
		this.isSuper = isSuper;
	}
	public int getIsAuthor() {
		return isAuthor;
	}
	public void setIsAuthor(int isAuthor) {
		this.isAuthor = isAuthor;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getHeadPath() {
		return headPath;
	}
	public void setHeadPath(String headPath) {
		this.headPath = headPath;
	}
	public int getSettingFlag() {
		return settingFlag;
	}
	public void setSettingFlag(int settingFlag) {
		this.settingFlag = settingFlag;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	public boolean isAuthor(){
		if (this.isAuthor == 1)
			return true;
		return false;
	}
	
	public boolean isSuper() {
		if (this.isSuper == 1)
			return true;
		return false;
	}
	
	public static RobotUser convert(Map<String,Object> map){
		if ( map == null )
			return null ;
		
		long id=0;
		if(map.get("user_id")!=null){
			id =StringUtil.toLong(map.get("user_id").toString());
			if(id == 0){
				return null;
			}
		}
		
		RobotUser user = new RobotUser();
		user.setUserId(id);
		if(map.get("email") != null) user.setEmail(map.get("email").toString());
		if(map.get("nick_name") != null) user.setNickName(map.get("nick_name").toString());
		if(map.get("login_pwd") != null) user.setLoginPwd(map.get("login_pwd").toString());
		if(map.get("login_cookies") != null) user.setLoginCookie(map.get("login_cookies").toString());
		if(map.get("is_active") != null) user.setIsActive(StringUtil.toInteger(map.get("is_active").toString(), 0));
		if(map.get("is_super") != null) user.setIsSuper(StringUtil.toInteger(map.get("is_super").toString(), 0));
		if(map.get("is_author") != null) user.setIsAuthor(StringUtil.toInteger(map.get("is_author").toString(), 0));
		if(map.get("phone") != null) user.setPhone(map.get("phone").toString());
		if(map.get("head_path") != null) user.setHeadPath(map.get("head_path").toString());
		if(map.get("setting_flag") != null) user.setSettingFlag(StringUtil.toInteger(map.get("setting_flag").toString(), 0));
		if(map.get("created_date")!=null) user.setCreatedDate((Date)map.get("created_date"));
		if(map.get("modify_date")!=null) user.setModifyDate((Date)map.get("modify_date"));
		return user;
	}
	
}
