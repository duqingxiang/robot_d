package com.demo.dao;

import java.util.List;
import java.util.Map;

public interface WeixinDao {

	public long insertWeixinUser(String fromId);
	
	public List<Map<String,Object>> getWeixinUserByFromid(String fromid,int deleteFlag);
	
	public long updateWeixinUserInfo(String name,long phone,long id);
}
