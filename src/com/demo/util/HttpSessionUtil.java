package com.demo.util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.demo.bean.RobotUser;
import com.demo.bean.UserBean;

public class HttpSessionUtil {

	public static UserBean getUserBean(HttpServletRequest request){
		HttpSession session = request.getSession();
		UserBean uBean = (UserBean)session.getAttribute("uBean");
		return uBean;
	}
	public static void setUserBean(HttpServletRequest request,UserBean bean){
		HttpSession session = request.getSession();
		session.setAttribute("uBean",bean);
	}
	
	public static void putUserBean(HttpServletRequest request,HttpServletResponse response ,RobotUser user){
		CookieUtil.setCookie(request, response, Constants.SESSION_COOKIE_NAME, user.getLoginCookie(), -1);
		XMemcachedUtil.put(user.getLoginCookie(), JSON.toJSONString(user),1000 * 60 * 60);
	}
	
	public static void removeUserBean(HttpServletRequest request,HttpServletResponse response ,RobotUser user){
		CookieUtil.setCookie(request, response, Constants.SESSION_COOKIE_NAME, "", -1);
		XMemcachedUtil.remove(user.getLoginCookie());
	}
	
	public static RobotUser getCookieUserBean(HttpServletRequest request){
		String cookie = CookieUtil.getCookieValue(request, Constants.SESSION_COOKIE_NAME);
		
		if(StringUtil.isEmpty(cookie))
			return null ;
		
		
		String userStr =(String) XMemcachedUtil.get(cookie);
		RobotUser user =(RobotUser)JSON.parseObject(userStr,RobotUser.class);
		if(user == null)
			return null ;
		
		Date now = new Date();
		if(now.after(DateUtil.getDateInMinuteAgo(user.getModifyDate(), 60))){//是否超时
			Logs.geterrorLogger().error("HttpSessionUtil getCookieUserBean user login out of time  userid:"+user.getUserId()+" cookie:"+cookie);
			return null ;
		}
		
		return user;
	}
	
}
