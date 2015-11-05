package com.demo.dao.impl;

import java.util.List;
import java.util.Map;

import com.demo.dao.WeixinDao;
import com.demo.util.Db;

public class WeixinDaoImpl implements WeixinDao {
	public long insertWeixinUser(String fromId){
		String sql = "insert into weixin_user (from_id,created_date) VALUES (?,NOW())";
		return Db.executeUpdate(sql, new Object[]{fromId});
	}
	
	public List<Map<String,Object>> getWeixinUserByFromid(String fromid,int deleteFlag){
		String sql ="select * from weixin_user where from_id = ? and delete_flag=?";
		return Db.executeQuery(sql, new Object[]{fromid,deleteFlag});
	}
	
	public long updateWeixinUserInfo(String name,long phone,long id){
		String sql ="update weixin_user set real_name=?  ,phone=? where  id = ?";
		return Db.executeUpdate(sql, new Object[]{name,phone,id});
	}
}
