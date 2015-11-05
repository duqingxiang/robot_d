package com.demo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.bean.SqlField;
import com.demo.bean.SqlType;
import com.demo.dao.ToolDao;
import com.demo.service.ToolService;
import com.demo.util.StringUtil;
@Service
public class ToolServiceImpl implements ToolService {
	
	private Map<Long,SqlType> sqlTypeMap;
	
	@Autowired
	private ToolDao toolDao;

	@Override
	public List<SqlType> getSqlTypes() {
		return SqlType.convertList(toolDao.getSqlTypes());
	}
	
	public Map<Long,SqlType> getStaticSqlTypeMap(){
		if (sqlTypeMap == null) {
			List<SqlType> list = this.getSqlTypes();
			if (CollectionUtils.isEmpty(list))
				return null;
			sqlTypeMap = new HashMap<Long,SqlType>();
			for (SqlType type : list) {
				sqlTypeMap.put(type.getId(), type);
			}
		}
		
		return sqlTypeMap;
	}
	
	public JSONObject createSql(String str,String table_name) {
		JSONObject res = new JSONObject();
		Map<Long,SqlType> typeMap = this.getStaticSqlTypeMap();
		
		if (StringUtil.isEmpty(table_name)) {
			res.put("code", -51);//表名为空
			return res;
		}
		
		int autoCount = 0;
		List<String> keyList = new ArrayList<String>();
		StringBuffer fieldContent = new StringBuffer();
		JSONArray jsonArray = (JSONArray) JSONArray.parse(str);
		if (jsonArray.size() <= 0) {
			res.put("code", -52);//无字段信息
			return res;
		}
		for (Object obj : jsonArray) {
			String fieldStr = obj.toString();
			JSONObject json = JSONObject.parseObject(fieldStr);
			if (json == null)
				continue;
			
			long fieldTypeId = json.get("type") != null ? StringUtil.toLong(json.get("type").toString(),-1L) : -1;
			if (fieldTypeId == -1) {
				res.put("code", -1);//类型异常
				return res;
			}
			
			SqlField field = new SqlField();
			SqlType type = typeMap.get(fieldTypeId);
			JSONObject initRes = field.initField(json, type);
			
			if (initRes.getLong("code") != 1) {
				res.put("code", initRes.getLong("code"));//2-字段类型异常 3-字段名异常
				return res;
			}
			if (field.isAutoIncrement() && autoCount >=1 ) {
				res.put("code", -4);//有且只有一个列
				return res;
			}
			
			if (field.isKey()) {//增加主键
				String keyName = "`"+field.getName()+"`";
				keyList.add(keyName);
			}
			fieldContent.append(field.getFieldSql());
			autoCount++;
		}
		
		String keyString = this.getKeysString(keyList);
		String contentString = fieldContent.toString().substring(1, fieldContent.toString().length());
		
		StringBuffer sql = new StringBuffer("CREATE TABLE `"+table_name+"` (");
		sql.append(contentString);
		if (!StringUtil.isEmpty(keyString)) {
			sql.append(",&hh;PRIMARY KEY ("+keyString+")");
		}
		sql.append("&hh;) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='"+table_name+"';");


		String sqlStr = sql.toString().replaceAll("&hh;", "<br/>");
		res.put("code", 1);
		res.put("sql", sqlStr);
		return res;
	}
	
	private String getKeysString(List<String> list){
		if (CollectionUtils.isEmpty(list))
			return null;
		StringBuffer sb = new StringBuffer();
		for (String str : list) {
			sb.append(str+",");
		}
		return sb.toString().substring(0, sb.toString().length()-1);
	}
	
}
