package com.demo.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.demo.bean.RobotShopGood;
import com.demo.bean.RobotShopOrder;
import com.demo.bean.RobotUser;
import com.demo.service.ShopService;
import com.demo.util.HttpSessionUtil;
import com.demo.util.StringUtil;
import com.demo.util.pay.PayUtil;

@Controller

public class DetailController extends MultiActionController {
	
	@Autowired
	private ShopService shopService;
	
	@RequestMapping(value = "/detail/good/{id}.do")
	public String good(@PathVariable Long id,HttpServletRequest request ,HttpServletResponse response)throws Exception{
		//获取用户
		RobotUser user = HttpSessionUtil.getCookieUserBean(request);
		request.setAttribute("user", user);
		
		boolean error = false;
		//获取商品
		RobotShopGood good = shopService.getRobotShopGoodById(id);
		//商品不存在
		if (good == null) {
			error = true;
			request.setAttribute("error", error);
			return "/shop/goods";
		}
		
		
		//游客浏览下架商品返回
		if ((user == null && good.getGoodStatus() == -1) 
			|| (user != null && !user.isSuper() && good.getGoodStatus() == -1)) {
			
			error = true;
			request.setAttribute("error", error);
			return "/shop/goods";
		}
		
		//更新产品浏览次数
		shopService.updateRobotShopGoodToCount(good.getId(), 0);
		
		request.setAttribute("error", error);
		request.setAttribute("good", good);
		return "/shop/goods";
	}
	
	@RequestMapping(value = "/detail/order/{id}.do")
	public String order(@PathVariable Long id,HttpServletRequest request ,HttpServletResponse response)throws Exception{
		//用户
		RobotUser user = HttpSessionUtil.getCookieUserBean(request);
		request.setAttribute("user", user);
		if (user == null) {
			return "redirect:/about.do?forward="+StringUtil.UrlEncoder(request.getRequestURI());
		}
		//订单列表跳转
		RobotShopOrder order = shopService.getRobotShopOrderById(id);
		int payType = order != null ? order.getType() : 1;
		
		request.setAttribute("order", order);
		if (order == null || order.getStatus() != 0 || user.getUserId() != order.getUserId()) {//无订单，非支付页面，不需要支付表单
			return "/shop/order";
		} 
		
		
		if (payType == 1) {//支付宝
			PayUtil.getAlipayPostContent(request, order);
		} else if (payType == 2) {//现在支付
			PayUtil.getNowpayPostContent(request, order);
		}
		
		return "/shop/order";
		
	}
	
	@RequestMapping(value = "/detail/duqingxiang.do")
	public String self(@PathVariable Long id,HttpServletRequest request ,HttpServletResponse response)throws Exception{
		
		return "/shop/order";
		
	}
}
