package com.demo.service;

import com.demo.bean.WeixinUser;

public interface WeixinService {

	public WeixinUser getWeixinUserByFromid(String fromid);
	
	public long insertWeixinUser(String fromid);
	
	public String updateWeixinUserInfo(String content,long userid);
	
	public String joinGroup(String content ,String fromUserId);
	
}
