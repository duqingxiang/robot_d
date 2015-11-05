package com.demo.listener;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.demo.bean.UserBean;

public class UserOnlionListener implements HttpSessionAttributeListener {

	/**
     * 
     * 
     * web.xml 需要配置
     * 
      <listener>
	    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
	  </listener>
	  <listener>
	    <listener-class>com.demo.listener.UserOnlionListener</listener-class>
	  </listener>
     * 
     * 
     * 
     * 
     */
	
	
	 /**
	     * 存放在线用户列表
	     */
	    public static List<Long> onLineUserList = new LinkedList<Long>();
	     
	    /**
	     * 根据用户ID，查询用户是否在线
	     * @param userId  用户ID
	     * @return  true:表示用户在线   false:表示用户离线
	     */
	    public static boolean findUserOnLine(long userId){
	        return onLineUserList.contains(userId);
	    }
	     
	 
	    /**
	     * 用户登录时候，把用户的信息存到userSession里
	     * UserSession类的结构很简单，只有userId,userName两个属性
	     */
	    @Override
	    public void attributeAdded(HttpSessionBindingEvent se) {
	        if ("uBean".equals(se.getName())){
	            /**
	             * 用户上线的话，把用户的ID，添加到onLineUserList里
	             */
	            long userId = ((UserBean)se.getValue()).getUserId();
	            onLineUserList.add(userId);
	            System.out.println("用户ID："+userId + " 上线了。。。");
	        }
	    }
	 
	    @Override
	    public void attributeRemoved(HttpSessionBindingEvent se) {
	        if ("uBean".equals(se.getName())){
	            /**
	             * 用户下线的话，把用户的ID，从onLineUserList中移除
	             */
	            long userId = ((UserBean)se.getValue()).getUserId();
	            onLineUserList.remove(userId);
	            System.out.println("用户ID："+userId + " 下线了。。。");
	        }
	    }
	 
	    @Override
	    public void attributeReplaced(HttpSessionBindingEvent se) {
	        // TODO Auto-generated method stub
	         
	    }


}
