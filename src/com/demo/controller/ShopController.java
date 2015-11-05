package com.demo.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.bean.RobotShopGood;
import com.demo.bean.RobotShopOrder;
import com.demo.bean.RobotUser;
import com.demo.service.ShopService;
import com.demo.util.CookieUtil;
import com.demo.util.HttpSessionUtil;
import com.demo.util.PageUtil;
import com.demo.util.StringUtil;
import com.demo.util.pay.PayUtil;

@Controller
@RequestMapping(value = "/shop/*.do")
public class ShopController extends MultiActionController {

	@Autowired
	private ShopService shopService;
	
	/**
	 * 首页
	 * @param request
	 * @param response
	 * @return
	 */
	public String index(HttpServletRequest request ,HttpServletResponse response){
		
		RobotUser user = HttpSessionUtil.getCookieUserBean(request);
		
		String key = request.getParameter("s");
		int page = StringUtil.toInteger(request.getParameter("p"), 1);
		int type = StringUtil.toInteger(request.getParameter("t"), -1);
		PageUtil p = new PageUtil();
		p.setPage(page);
		p.setPageLength(20);
		List<RobotShopGood> goodList = shopService.getRobotShopGoods(key,0,type, p.getPageIndex(), p.getPageLength());
		
		request.setAttribute("user", user);
		request.setAttribute("goodList", goodList);
		request.setAttribute("page", page);
		request.setAttribute("type", type);
		request.setAttribute("key", key);
		
		return "/shop/index";
	}
	
	public String getGoodMore(HttpServletRequest request ,HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String key = request.getParameter("s");
		int page = StringUtil.toInteger(request.getParameter("p"), 1);
		int type = StringUtil.toInteger(request.getParameter("t"), -1);
		PageUtil p = new PageUtil();
		p.setPage(page);
		p.setPageLength(20);
		List<RobotShopGood> goodList = shopService.getRobotShopGoods(key,0,type, p.getPageIndex(), p.getPageLength());

		JSONArray array = null;
		if (!CollectionUtils.isEmpty(goodList)) {
			array = new JSONArray();
			for (RobotShopGood good : goodList) {
				double price = good.getIsDiscount() == 1 ? good.getGoodDisPrice() : good.getGoodPrice();
				JSONObject g = new JSONObject();
				g.put("id", good.getId());
				g.put("name", good.getGoodName());
				g.put("price", price);
				g.put("url", good.getGoodPicUrl());
				array.add(g);
			}
		}
		
		JSONObject json = new JSONObject();
		int size  = CollectionUtils.isEmpty(goodList) ? 0 : goodList.size();
		json.put("size", size);
		json.put("array", array);
		
		out.print(json.toJSONString());
		return null;
	}
	
	/**
	 * 购物车
	 */
	public String cart(HttpServletRequest request ,HttpServletResponse response){
		RobotUser user = HttpSessionUtil.getCookieUserBean(request);
		if (user == null) {
			return "redirect:/about.do?forward="+StringUtil.UrlEncoder("/shop/cart.do");
		}
		Cookie cookie = CookieUtil.getCookie(request, "robot_shop_cart");
		String idsCookies = null;
		if (cookie != null) {
			idsCookies = cookie.getValue();
		}
		
		StringBuffer ids = new StringBuffer();
		if (!StringUtil.isEmpty(idsCookies)) {
			String [] idsArray = idsCookies.split("-");
			if (idsArray.length > 0) {
				for (int i=0 ;i<idsArray.length ;i++) {
					if (i == idsArray.length-1) {
						ids.append(idsArray[i]);
					} else {
						ids.append(idsArray[i]+",");
					}
				}
			}
		}
		List<RobotShopGood> goodList = null ;
		if (!StringUtil.isEmpty(ids.toString())) {
			goodList = shopService.getRobotShopGoodsByIds(ids.toString());
		}
		int size = goodList != null ?goodList.size():0 ;
		request.setAttribute("goodList", goodList);
		request.setAttribute("user", user);
		return "/shop/cart";
	}
	
	/**
	 * 产品管理
	 * @param request
	 * @param response
	 * @return
	 */
	public String goodsManager(HttpServletRequest request ,HttpServletResponse response){
		RobotUser user = HttpSessionUtil.getCookieUserBean(request);
		
		//未登录或无权限
		if (user == null || !user.isSuper()) {
			 return "redirect:/";
		}
		
		int page = StringUtil.toInteger(request.getParameter("p"), 1);
		PageUtil p = new PageUtil();
		p.setPage(page);
		p.setPageLength(20);
		List<RobotShopGood> goodList = shopService.getRobotShopGoods(null, -999,-1, p.getPageIndex(), p.getPageLength());
		
		request.setAttribute("user", user);
		request.setAttribute("goodList", goodList);
		request.setAttribute("page", page);
		return "/shop/goodsManager";
	}
	
	/**
	 * 新增产品
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String goodsAdd(HttpServletRequest request ,HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		RobotUser user = HttpSessionUtil.getCookieUserBean(request);
		if (user == null) {
			out.print(-1);
			return null;
		}
		
		if (!user.isSuper()) {
			out.print(-2);
			return null;
		}
		
		String goodName = request.getParameter("good_name");
		double goodPrice = StringUtil.toDouble(request.getParameter("good_price"), 0D);
		int isDiscount = StringUtil.toInteger(request.getParameter("is_discount"), 0);
		double goodDisPrice = StringUtil.toDouble(request.getParameter("good_dis_price"), 0D);
		String goodPicUrl = request.getParameter("good_pic_url");
		String goodTag = request.getParameter("good_tag");
		String goodContent = request.getParameter("good_content");
		int goodType = StringUtil.toInteger(request.getParameter("good_type"), 0);
		if (StringUtil.isEmpty(goodName) || StringUtil.isEmpty(goodPicUrl) || StringUtil.isEmpty(goodTag)
				|| StringUtil.isEmpty(goodContent) || goodPrice == 0 || (isDiscount==1&&goodDisPrice==0)) {
			
			out.print(-3);
			return null;
		}
		
		RobotShopGood good = new RobotShopGood();
		good.setOwnerId(user.getUserId());
		good.setGoodName(goodName);
		good.setGoodPrice(goodPrice);
		good.setGoodDisPrice(goodDisPrice);
		good.setGoodTag(goodTag);
		good.setGoodPicUrl(goodPicUrl);
		good.setGoodContent(goodContent);
		good.setGoodType(goodType);
		good.setIsDiscount(isDiscount);
		good.setIsOut(0);
		good.setGoodStatus(0);
		
		long res = shopService.insertRobotShopGood(good);
		if (res <= 0) {
			out.print(-4);;
		}
		out.print(1);
		return null;
	}
	
	/**
	 * 获取商品相信信息（修改使用）
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String goodsForUpdate(HttpServletRequest request ,HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		JSONObject json = new JSONObject();
		long id = StringUtil.toLong(request.getParameter("id"), 0L);
		
		RobotUser user = HttpSessionUtil.getCookieUserBean(request);
		if (user == null) {
			json.put("result", -1);
			out.print(json.toJSONString());
			return null;
		}
		
		if (!user.isSuper()) {
			json.put("result", -2);
			out.print(json.toJSONString());
			return null;
		}
		RobotShopGood good = shopService.getRobotShopGoodById(id);
		if (good == null) {
			json.put("result", -3);
			out.print(json.toJSONString());
			return null;
		}
		json.put("result", 1);
		json.put("good", good);
		
		out.print(json.toJSONString());
		return null;
	}
	
	/**
	 * 修改商品
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String goodsUpdate(HttpServletRequest request ,HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		RobotUser user = HttpSessionUtil.getCookieUserBean(request);
		if (user == null) {
			out.print(-1);
			return null;
		}
		
		if (!user.isSuper()) {
			out.print(-2);
			return null;
		}
		
		long goodId = StringUtil.toLong(request.getParameter("good_id"), 0L);
		String goodName = request.getParameter("good_name");
		double goodPrice = StringUtil.toDouble(request.getParameter("good_price"), 0D);
		int isDiscount = StringUtil.toInteger(request.getParameter("is_discount"), 0);
		double goodDisPrice = StringUtil.toDouble(request.getParameter("good_dis_price"), 0D);
		String goodPicUrl = request.getParameter("good_pic_url");
		String goodTag = request.getParameter("good_tag");
		String goodContent = request.getParameter("good_content");
		int goodType = StringUtil.toInteger(request.getParameter("good_type"), 0);
		if (goodId ==0 || StringUtil.isEmpty(goodName) || StringUtil.isEmpty(goodPicUrl) || StringUtil.isEmpty(goodTag)
				|| StringUtil.isEmpty(goodContent) || goodPrice == 0 || (isDiscount==1&&goodDisPrice==0)) {
			
			out.print(-3);
			return null;
		}
		
		
		RobotShopGood good = new RobotShopGood();
		good.setId(goodId);
		good.setGoodName(goodName);
		good.setGoodPrice(goodPrice);
		good.setGoodDisPrice(goodDisPrice);
		good.setGoodTag(goodTag);
		good.setGoodPicUrl(goodPicUrl);
		good.setGoodContent(goodContent);
		good.setIsDiscount(isDiscount);
		good.setGoodType(goodType);
		
		long res = shopService.updateRobotShopGoodSimple(good);
		if (res <= 0) {
			out.print(-4);;
		}
		out.print(1);
		return null;
	}
	
	public String goodsOnOff(HttpServletRequest request ,HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		RobotUser user = HttpSessionUtil.getCookieUserBean(request);
		if (user == null) {
			out.print(-1);
			return null;
		}
		
		if (!user.isSuper()) {
			out.print(-2);
			return null;
		}
		
		long id = StringUtil.toLong(request.getParameter("id"), 0L);
		int status = StringUtil.toInteger(request.getParameter("status"), -999);
		
		if (id == 0 || status == -999 || (status != 0 && status != -1 && status != -2)) {
			out.print(-3);
			return null;
		}
		
		
		long res = shopService.updateRobotShopGoodStatus(id, status);
		if (res <= 0) {
			out.print(-4);;
		}
		out.print(1);
		return null;
	}
	
	public String toPay(HttpServletRequest request ,HttpServletResponse response) throws Exception{

		//用户
		RobotUser user = HttpSessionUtil.getCookieUserBean(request);
		request.setAttribute("user", user);
		
		//初始化订单
		RobotShopOrder order = null;
		int payType = 1 ;
		long order_id = StringUtil.toLong(request.getParameter("order_id"), 0L);
		
		if (order_id == 0) {//购物车下单
			
			String good_content = request.getParameter("good_content");
			String received_name = request.getParameter("received_name");
			String received_phone = request.getParameter("received_phone");
			String received_address = request.getParameter("received_address");
			String received_remarks = request.getParameter("received_remarks");
			payType = StringUtil.toInteger(request.getParameter("payChannel"), 1);
			
			
			order = new RobotShopOrder();
			order.setUserId(user.getUserId());
			order.setContent(good_content);
			order.setReceivedName(received_name);
			order.setReceivedPhone(received_phone);
			order.setReceivedAddress(received_address);
			order.setReceivedRemarks(received_remarks);
			order.setType(payType);
			order.setStatus(0);
			//生成订单
			order = shopService.createRobotShopOrder(order);
		} else { //订单列表跳转
			order = shopService.getRobotShopOrderById(order_id);
			payType = order != null ? order.getType() : payType;
		}
		
		request.setAttribute("order", order);
		if (order == null || order.getStatus() != 0) {//无订单，非支付页面，不需要支付表单
			return "/shop/order";
		} 
		
		
		if (payType == 1) {//支付宝
			PayUtil.getAlipayPostContent(request, order);
		} else if (payType == 2) {//现在支付
			PayUtil.getNowpayPostContent(request, order);
		}
		
		return "/shop/order";
	}
	
	public String ordersManager(HttpServletRequest request ,HttpServletResponse response){
		RobotUser user = HttpSessionUtil.getCookieUserBean(request);
		if (user == null) {
			return "redirect:/about.do?forward="+StringUtil.UrlEncoder("/shop/ordersManager.do");
		}
		
		int page = StringUtil.toInteger(request.getParameter("p"), 1);
		int status = StringUtil.toInteger(request.getParameter("s"), -999);
		int admin = StringUtil.toInteger(request.getParameter("admin"), 0);
		PageUtil p = new PageUtil();
		p.setPage(page);
		p.setPageLength(20);
		
		long userid = user.getUserId() ; 
		if (user.isSuper() && admin == 1) {
			userid = 0 ;
		} else {
			admin = 0 ;
		}
		
		List<RobotShopOrder> ordersList = shopService.getRobotShopOrder(userid, status, p.getPageIndex(), p.getPageLength());
		
		request.setAttribute("user", user);
		request.setAttribute("admin", admin);
		request.setAttribute("ordersList", ordersList);
		request.setAttribute("page", page);
		request.setAttribute("status", status);
		return "/shop/ordersManager";
	}
	
	public String orderDetail(HttpServletRequest request ,HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		JSONObject json = new JSONObject();
		
		RobotUser user = HttpSessionUtil.getCookieUserBean(request);
		if (user == null) {
			json.put("resultCode", -1);
			out.print(json.toJSONString());
			return null;
		}
		long id = StringUtil.toLong(request.getParameter("id"), 0L);
		
		RobotShopOrder order = shopService.getRobotShopOrderById(id);
		if (order == null || (order.getUserId() != user.getUserId() && !user.isSuper())) {
			json.put("resultCode", -2);
			out.print(json.toJSONString());
			return null;
		}
		
		JSONArray array = shopService.getRobotShopOrderDetail(order.getContent());
		json.put("resultCode", 1);
		json.put("array", array);
		out.print(json.toJSONString());
		return null;
	}
	
	public String closeOrder(HttpServletRequest request ,HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		long id = StringUtil.toLong(request.getParameter("id"), 0L);
		RobotUser user = HttpSessionUtil.getCookieUserBean(request);
		if (user == null) {
			out.print(-1);
			return null;
		}
		
		RobotShopOrder order = shopService.getRobotShopOrderById(id);
		if (order == null || (order.getUserId() != user.getUserId() && !user.isSuper())) {
			out.print(-2);
			return null;
		}
		
		if (order.getStatus() != 0) {
			out.print(-3);
			return null;
		}
		
		long res = shopService.updateRobotShopOrderStatus(id, -1);
		if (res <= 0) {
			out.print(-4);
			return null;
		}
		
		out.print(1);
		return null;
	}
	
	public String sendOrder(HttpServletRequest request ,HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		long id = StringUtil.toLong(request.getParameter("id"), 0L);
		RobotUser user = HttpSessionUtil.getCookieUserBean(request);
		if (user == null) {
			out.print(-1);
			return null;
		}
		if (!user.isSuper()) {
			out.print(-2);
			return null;
		}
		RobotShopOrder order = shopService.getRobotShopOrderById(id);
		if (order == null || order.getStatus() != 1) {
			out.print(-3);
			return null;
		}
		
		long res = -4 ;
		if (order.getType() == 2) {
			res = shopService.updateRobotShopOrderStatus(order.getId(), 3);
		}else {
			res = shopService.updateRobotShopOrderToAutosend(id);
		}
		
		if (res <= 0) {
			out.print(-4);
			return null;
		}
		
		//更新购买次数
		shopService.updateRobotShopGoodToCountBuy(order);
		
		out.print(1);
		return null;
	}
	
}
