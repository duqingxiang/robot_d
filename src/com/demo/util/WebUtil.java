package com.demo.util;



import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;


public class WebUtil {
	
    /**
     * 获取客户端ip
     * @param request
     * @return ip 地址
     * @ test  just a test word
     */
    public static String getClientAddress(HttpServletRequest request) {
    	String address = request.getHeader("X-Forwarded-For");
    	if(StringUtil.isEmpty(address)){
    		address = request.getHeader("X-Real-IP");
    	}
    	
        if (!StringUtil.isEmpty(address)) {
        	String[] ips = address.split(",");
        	if(ips!=null && ips.length>0){
        		address = ips[0];
        	}
            return address;
        }
        return request.getRemoteAddr();
    }
    
 
	public static Object getBean(javax.servlet.http.HttpServletRequest request,String bean) {
		return org.springframework.web.context.support.WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext()).getBean(bean);
	}
	
	public static Object getBean(String beanId){
		WebApplicationContext wac =  ContextLoader.getCurrentWebApplicationContext();  
	    return wac.getBean(beanId);
	}
	
	  
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Integer.toBinaryString(i)

	}
	

}
