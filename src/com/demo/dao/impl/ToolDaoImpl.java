package com.demo.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.demo.dao.ToolDao;
import com.demo.util.Db;
@Repository
public class ToolDaoImpl implements ToolDao {

	public List<Map<String,Object>> getSqlTypes(){
		String sql = "select * from sql_type where status != ?";
		return Db.executeQuery(sql, new Object[]{-1});
	}
}
