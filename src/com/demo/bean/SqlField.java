package com.demo.bean;

import com.alibaba.fastjson.JSONObject;
import com.demo.util.StringUtil;

public class SqlField {

	private String name;
	private long type;
	private int length;
	private int decimals;
	private String default_value;
	private String comment;
	private boolean isKey;
	private boolean allowNull;
	private boolean autoIncrement;
	private boolean unsigned;
	private boolean zerofill;
	private SqlType sqlType;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getType() {
		return type;
	}
	public void setType(long type) {
		this.type = type;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getDecimals() {
		return decimals;
	}
	public void setDecimals(int decimals) {
		this.decimals = decimals;
	}
	public String getDefault_value() {
		return default_value;
	}
	public void setDefault_value(String default_value) {
		this.default_value = default_value;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public boolean isKey() {
		return isKey;
	}
	public void setKey(boolean isKey) {
		this.isKey = isKey;
	}
	public boolean isAllowNull() {
		return allowNull;
	}
	public void setAllowNull(boolean allowNull) {
		this.allowNull = allowNull;
	}
	public boolean isAutoIncrement() {
		return autoIncrement;
	}
	public void setAutoIncrement(boolean autoIncrement) {
		this.autoIncrement = autoIncrement;
	}
	public boolean isUnsigned() {
		return unsigned;
	}
	public void setUnsigned(boolean unsigned) {
		this.unsigned = unsigned;
	}
	public boolean isZerofill() {
		return zerofill;
	}
	public void setZerofill(boolean zerofill) {
		this.zerofill = zerofill;
	}
	
	public SqlType getSqlType() {
		return sqlType;
	}
	public void setSqlType(SqlType sqlType) {
		this.sqlType = sqlType;
	}
	public JSONObject initField(JSONObject json,SqlType type){
		JSONObject res = new JSONObject();
		if (json == null) {
			res.put("code", -1);
			return res;
		}
			
		if (type == null) {
			res.put("code", -2);
			return res;
		}
		if (json.get("name") != null) this.setName(json.get("name").toString());
		if (json.get("type") != null) this.setType(StringUtil.toLong(json.get("type").toString(), 0L));
		if (json.get("length") != null) this.setLength(StringUtil.toInteger(json.get("length").toString(), -1));
		if (json.get("decimals") != null) this.setDecimals(StringUtil.toInteger(json.get("decimals").toString(), -1));
		if (json.get("default_value") != null) this.setDefault_value(json.get("default_value").toString());
		if (json.get("comment") != null) this.setComment(json.get("comment").toString());
		
		if (json.get("key") != null) this.setKey(json.getBooleanValue("key"));
		if (json.get("allow_null") != null) this.setAllowNull(json.getBooleanValue("allow_null"));
		if (json.get("auto_increment") != null) this.setAutoIncrement(json.getBooleanValue("auto_increment"));
		if (json.get("unsigned") != null) this.setUnsigned(json.getBooleanValue("unsigned"));
		if (json.get("zerofill") != null) this.setZerofill(json.getBooleanValue("zerofill"));
		
		this.setSqlType(type);
		
		if (StringUtil.isEmpty(this.name)) {
			res.put("code", -3);
			return res;
		}
		res.put("code", 1);
		return res;
	}
	/**
	 * 
	 * 
		CREATE TABLE `555` (
		  `id` bigint(32) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT '33',
			`id1` bigint(32) unsigned zerofill NOT NULL DEFAULT '1' COMMENT '33',
		  PRIMARY KEY (`id`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	 * @return
	 */
	public String getFieldSql(){
		
		StringBuffer sb = new StringBuffer(",&hh;");
		sb.append("  `"+this.getName()+"`");
		sb.append(" "+this.getSqlType().getTigs());
		if ((this.getSqlType().getType() == 1 || this.getSqlType().getType() == 3) 
				&& this.getLength() != -1) {
			sb.append("("+this.getLength()+")");
		}
		
		if (this.getSqlType().getType() == 2 && this.getLength() != -1 
				&& this.getDecimals() != -1) {
			sb.append("("+this.getLength()+","+this.getDecimals()+")");
		}
		
		if (this.getSqlType().getType() == 1 || this.getSqlType().getType() == 2) {
			if (this.isUnsigned()) {//无符号
				sb.append(" unsigned");
			}
			
			if (this.isZerofill()) {//补零
				sb.append(" zerofill");
			}
		}
		
		
		if (!this.isAllowNull()) {//非空
			sb.append(" NOT NULL");
		}
		
		//非自增列才可以有默认值
		if (!this.isAutoIncrement() && !StringUtil.isEmpty(this.getDefault_value())) {
			sb.append(" DEFAULT '"+this.getDefault_value()+"'");
		}
		
		//主键才可以自增
		if (this.isKey() && this.isAutoIncrement()) {
			sb.append(" AUTO_INCREMENT");
		}
		
		//备注
		if (!StringUtil.isEmpty(this.getComment())) {
			sb.append(" COMMENT '"+this.getComment()+"'");
		}
		
		return sb.toString();
	}
	
}
