package com.demo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.demo.bean.WeixinUser;
import com.demo.qyh.ParamesAPI.ParamesAPI;
import com.demo.qyh.encryption.AesException;
import com.demo.qyh.encryption.WXBizMsgCrypt;
import com.demo.service.WeixinService;
import com.demo.util.Logs;
import com.demo.util.MD5Util;
import com.demo.util.StringUtil;
import com.demo.util.WeixinConstants;

public class WeixinController  extends MultiActionController  {

	private WeixinService weixinService;
	public void setWeixinService(WeixinService weixinService){
		this.weixinService = weixinService;
	}
	
	
	
	/**
	 * @author jeyy - 验证企业号的oauth请求
	 * @param request
	 * @param response
	 * @return sEchoStr
	 */
	public void verfy(HttpServletRequest request,  HttpServletResponse response)
			throws Exception {
		response.setCharacterEncoding("UTF-8");
		try {

			String sToken = ParamesAPI.token;
			String sCorpID = ParamesAPI.corpId;
			String sEncodingAESKey = ParamesAPI.encodingAESKey;

			/*
			 * 假定公众平台上开发者设置的Token 1. 验证回调URL 点击验证时，企业收到类似请求： GET
			 * /cgi-bin/wxpush?msg_signature
			 * =5c45ff5e21c57e6ad56bac8758b79b1d9ac89fd3
			 * &timestamp=1409659589&nonce=263014780
			 * &echostr=P9nAzCzyDtyTWESHep1vC5X9xho
			 * %2FqYX3Zpb4yKa9SKld1DsH3Iyt3tP3zNdtp
			 * %2B4RPcs8TgAE7OaBO%2BFZXvnaqQ%3D%3D HTTP/1.1 Host:
			 * qy.weixin.qq.com 接收到该请求时，企业应1.先验证签名的正确性 2. 解密出echostr原文。
			 * 以上两步用VerifyURL完成
			 */

			// 微信加密签名
			String sVerifyMsgSig = request.getParameter("msg_signature");
			// 时间戳
			String sVerifyTimeStamp = request.getParameter("timestamp");
			// 随机数
			String sVerifyNonce = request.getParameter("nonce");
			// 随机字符串
		 
			String sVerifyEchoStr = request.getParameter("echostr") ;


			String sEchoStr; // 需要返回的明文
			PrintWriter out = response.getWriter();
			WXBizMsgCrypt wxcpt;
			try {
				wxcpt = new WXBizMsgCrypt(sToken, sEncodingAESKey, sCorpID);
				sEchoStr = wxcpt.VerifyURL(sVerifyMsgSig, sVerifyTimeStamp,
						sVerifyNonce, sVerifyEchoStr);
				System.out.println("fanhui---->"+sEchoStr);
				// 验证URL成功，将sEchoStr返回
				out.print(sEchoStr);
			} catch (AesException e1) {
				e1.printStackTrace();
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	
	public String verfy_d(HttpServletRequest request,HttpServletResponse response)throws Exception {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String signature  = StringUtil.UrlDecoder(request.getParameter("msg_signature"));
		String timestamp  = StringUtil.UrlDecoder(request.getParameter("timestamp"));
		String nonce  = StringUtil.UrlDecoder(request.getParameter("nonce"));
		String echostr  = StringUtil.UrlDecoder(request.getParameter("echostr"));
		System.out.println("---WeixinController   verfy_d---->"+signature);
		System.out.println("---WeixinController   verfy_d---->"+timestamp);
		System.out.println("---WeixinController   verfy_d---->"+nonce);
		System.out.println("---WeixinController   verfy_d---->"+echostr);
		if(validateWeixin_d(signature,timestamp,nonce,ParamesAPI.token)){
			 out.print(echostr);
			 return null;
		 }
		
		return null;
	}
	
	
	private String MY_ID = "gh_7478555a1bcd";
	public String callback(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		/*String signature  = request.getParameter("signature");
		String timestamp  = request.getParameter("timestamp");
		String nonce  = request.getParameter("nonce");
		String echostr  = request.getParameter("echostr");
		if(validateWeixin(signature,timestamp,nonce)){
			 out.print(echostr);
			 return null;
		 }
		out.print("123456");*/
		System.out.println("------------>weixinController   start");
		String result = readPostXml(request.getInputStream());
		out.print(result);
		return null;
	
	}
	private String readPostXml(InputStream inputStream){
		SAXReader saxReader = new SAXReader();
		Document doc = null;
		try {
			doc = saxReader.read(inputStream);
		}  catch (DocumentException e) {
			e.printStackTrace();
		}
		String msgContent = "";
		String event = "";
		String eventKey = "";
		List<Element> listElement = null;
		if(doc != null && doc.getRootElement()!=null){
			listElement = doc.getRootElement().elements();
			if (listElement != null) {
				for (Element element : listElement) {
					if("MsgType".equals( element.getName())){
						msgContent = element.getTextTrim();
					}
				}
			}
		}
		if("text".equalsIgnoreCase(msgContent)){
			return messagePush(listElement);
		}else{
			return null;
		}
	}
	
	private String messagePush(List<Element> listElement) {
		StringBuffer result = new StringBuffer();;
		String fromUserName = "";
		String content = "";
		
		for (Element element : listElement) {
			if("FromUserName".equals(element.getName())){
				fromUserName = element.getTextTrim();
			}
			if("Content".equals(element.getName())){
				content = element.getTextTrim();
			}
		}
		Logs.geterrorLogger().error("------content----> "+content);
		String message ="";
		
		WeixinUser user = weixinService.getWeixinUserByFromid(fromUserName);
		if(user==null){
			weixinService.insertWeixinUser(fromUserName);
			message = WeixinConstants.WEIXIN_USERINFO_NOEXIST;
			return message(fromUserName,message).toString();
		}
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String dateStr = sdf.format(new Date());
		if(!StringUtil.isEmpty(content)&&content.indexOf("_")!=-1 ){
			String key = content.split("_")[0];
			if("join".equals(key)){//加入
				if(StringUtil.isEmpty(user.getRealName())||user.getPhone()==0){
					message = WeixinConstants.WEIXIN_USERINFO_NOEXIST;
					return message(fromUserName,message).toString();
				}
				message =  WeixinConstants.WEIXIN_OPERATION_NOOPENUP;
			}else if("out".equals(key)){//退出
				if(StringUtil.isEmpty(user.getRealName())||user.getPhone()==0){
					message = WeixinConstants.WEIXIN_USERINFO_NOEXIST;
					return message(fromUserName,message).toString();
				}
				message =  WeixinConstants.WEIXIN_OPERATION_NOOPENUP;
			}else if("update".equals(key)){//更新个人信息
				message =weixinService.updateWeixinUserInfo(content.split("_")[1], user.getId());
			}else if("pass".equals(key)&&user.getRoleType()>=1){//批准
				if(StringUtil.isEmpty(user.getRealName())||user.getPhone()==0){
					message = WeixinConstants.WEIXIN_USERINFO_NOEXIST;
					return message(fromUserName,message).toString();
				}
				message =  WeixinConstants.WEIXIN_OPERATION_NOOPENUP;
			}else{
				message = WeixinConstants.WEIXIN_OPERATION_NOEXIST+"，您输入的命令为："+content;
			}	
			
		}else{
			if("info".equals(content)){
				if(StringUtil.isEmpty(user.getRealName())||user.getPhone()==0){
					message = WeixinConstants.WEIXIN_USERINFO_NOEXIST;
					return message(fromUserName,message).toString();
				}
				StringBuffer info = new StringBuffer();
				info.append("&#x0A;姓名："+user.getRealName());
				info.append("&#x0A;手机号："+user.getPhone());
				message= info.toString();
			}else{
				message = "杜庆祥是个大帅哥！哈哈哈哈！"+dateStr;
			}
		}
		result = message(fromUserName,message);
		return result.toString();
	}
	/**
	 * 推送消息
	 * @param fromUserName
	 * @return
	 */
	private StringBuffer message(String fromUserName,String message) {
		StringBuffer result = new StringBuffer();
		result.append("<xml>"); 
		result.append("<ToUserName>"+fromUserName+"</ToUserName>"); 
		result.append("<FromUserName>"+MY_ID+"</FromUserName>"); 
		result.append("<CreateTime>"+System.currentTimeMillis()+"</CreateTime>"); 
		result.append("<MsgType>text</MsgType>"); 
		result.append("<Content>"+message+"</Content>"); 
		result.append("<FuncFlag>0</FuncFlag>"); 
		result.append("</xml>"); 
		return result;
	}
	private boolean validateWeixin(String signature, String timestamp,String nonce) {
		
		boolean result = false;
		String[] signatureStr = new String[]{"dqxtoken",timestamp,nonce};
		Arrays.sort(signatureStr);
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < signatureStr.length; i++){
		 sb. append(signatureStr[i]);
		}
		String localSignature = MD5Util.encodeBySha1(sb.toString());
		if(signature.equalsIgnoreCase(localSignature)){
			result = true;
		}
		return result;
	}
	
	private boolean validateWeixin_d(String signature, String timestamp,String nonce,String token) {
		
		boolean result = false;
		String[] signatureStr = new String[]{token,timestamp,nonce};
		Arrays.sort(signatureStr);
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < signatureStr.length; i++){
		 sb. append(signatureStr[i]);
		}
		String localSignature = MD5Util.encodeBySha1(sb.toString());
		if(signature.equalsIgnoreCase(localSignature)){
			result = true;
		}
		return result;
	}
	
	
	public static void main(String[] args) {
		String a = "update_name:杜庆祥；phone:18630342780";
		String [] arr = a.split("；");
		System.out.println(arr[0].split(":")[0]);
	}
}
