package com.demo.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.demo.bean.SqlType;

public interface ToolService {

	public List<SqlType> getSqlTypes();
	
	public Map<Long,SqlType> getStaticSqlTypeMap();
	
	public JSONObject createSql(String str,String table_name);
}
