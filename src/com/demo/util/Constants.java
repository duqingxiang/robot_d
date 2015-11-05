package com.demo.util;

public class Constants {
	public static boolean SHOW_SQL = StringUtil.toInteger(IniReader.getInstance(1).getValue("DB", "show_sql"), 0) == 1;//0不打印sql，1是打印sql
	public static String SESSION_COOKIE_NAME = "robot_session_id_m";
	
	public static String UPLOAD_REAL_PATH =IniReader.getInstance(1).getValue("path", "real_path");
	public static String UPLOAD_NET_PATH =IniReader.getInstance(1).getValue("path", "net_path");
	public static String UPLOAD_MINI_PATH=IniReader.getInstance(1).getValue("path", "mini_path");
	
	/**
	 * 现在支付  相关参数
	 */
	public static String NOWPAY_APP_ID = "1435659688772285";
	public static String NOWPAY_APP_KEY = "QIsDeGmrSe2G6iAswDwvQpkPTxO9lEOJ";
	
	/**
	 * 阿里支付 相关参数
	 */
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String ALIPAY_PARTNER = "2088202198891693";
	// 收款支付宝账号
	public static String ALIPAY_SELLER_EMAIL = "363867842@qq.com";
	// 商户的私钥
	public static String ALIPAY_KEY = "clbwk588wowi7ehjs6zvh9vmhavxr36c";
	// 调试用，创建TXT日志文件夹路径
	public static String ALIPAY_LOG_PATH = "D:\\";
	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String ALIPAY_INPUT_CHARSET = "utf-8";
	// 签名方式 不需修改
	public static String ALIPAY_SIGN_TYPE = "MD5";
	
}
