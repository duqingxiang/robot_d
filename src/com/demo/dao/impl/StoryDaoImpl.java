package com.demo.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.demo.dao.StoryDao;
import com.demo.util.Db;

public class StoryDaoImpl implements StoryDao{

	public List<Map<String,Object>> getMemorysList(int type ,int startIndex ,int pageLength){
		
		String limit = "";
		String typeStr ="";
		List<Object> param = new ArrayList<Object>();
		param.add(1);
		
		if(type != 999){
			typeStr = " and type = ? ";
			param.add(type);
		}
		
		if(startIndex != 0 || pageLength != 0){
			limit = " limit ?,? ";
			param.add(startIndex);
			param.add(pageLength);
		}
			
		String sql = "select * from memorys where 1=? "+typeStr+limit;
		return Db.executeQuery(sql, param.toArray()) ;
	}
}
