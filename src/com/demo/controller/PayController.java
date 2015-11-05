package com.demo.controller;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.demo.bean.RobotShopOrder;
import com.demo.service.PayService;
import com.demo.service.ShopService;
import com.demo.util.Logs;
import com.demo.util.StringUtil;
import com.demo.util.alipay.AlipayNotify;

@Controller
@RequestMapping(value = "/pay/*.do")
public class PayController extends MultiActionController {

	
	private static String appId = "1435659688772285";
	private static String appKey = "QIsDeGmrSe2G6iAswDwvQpkPTxO9lEOJ";
	@Autowired
	private PayService payService;
	@Autowired
	private ShopService shopService;
	
	public String index(HttpServletRequest request ,HttpServletResponse response){
		
		
		
		return null;
	}
	
	
	
	public String nowNotify(HttpServletRequest request ,HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		BufferedReader reader = request.getReader();
		StringBuilder reportBuilder = new StringBuilder();
		
		String tempStr = "";
		while((tempStr = reader.readLine()) != null) {
			reportBuilder.append(tempStr);
		}
		
		Logs.geterrorLogger().error("PayController nowpay notify  report:"+reportBuilder.toString());
		
		String report = reportBuilder.toString();
		if (StringUtil.isEmpty(report)) {
			Logs.geterrorLogger().error("PayController nowpay notify  report error");
		}
		
		StringBuffer log = new StringBuffer("PayController nowpay notify \n");
		Map<String,String> paramMap = new HashMap<String,String>();
		for (String str : report.split("&")) {
			if (StringUtil.isEmpty(str))
				continue;
			String [] param = str.split("=");
			String key = param[0];
			String value = StringUtil.UrlDecoder(param[1]);
			log.append(key+"=="+value+"\n");
			paramMap.put(key, value);
		}
		String notifyStatus = paramMap.get("tradeStatus");
		long order_id = paramMap.get("mhtOrderNo") != null ? StringUtil.toLong(paramMap.get("mhtOrderNo"), 0L):0;
		RobotShopOrder order = shopService.getRobotShopOrderById(order_id);
		
		if (order == null) {
			log.append(" order is null ");
			Logs.geterrorLogger().error(log.toString());
			return null;
		}
		
		if (!"A001".equals(notifyStatus)) {
			log.append(" notify status is error ");
			Logs.geterrorLogger().error(log.toString());
			return null;
		}
		long res = -1 ;
		if (order != null && "A001".equals(notifyStatus)) {
			res = shopService.updateRobotShopOrderStatus(order_id, 1);
		}
		
		if (res <=0) {
			log.append(" update error ");
			Logs.geterrorLogger().error(log.toString());
			return null;
		}
		
		response.getOutputStream().write("success=Y".getBytes());
		log.append(" notify update success ");
		Logs.geterrorLogger().error(log.toString());
		return null;
	}
	public String nowFontNotify(HttpServletRequest request ,HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String funcode= request.getParameter("funcode");
		String appId= request.getParameter("appId");
		String mhtOrderNo= request.getParameter("mhtOrderNo");
		String mhtCharset= request.getParameter("mhtCharset");
		String tradeStatus= request.getParameter("tradeStatus");
		String mhtReserved= request.getParameter("mhtReserved");
		String signType= request.getParameter("signType");
		String signature= request.getParameter("signature");
		
		StringBuffer log = new StringBuffer(" PayController nowpay fontNotify \n");
		log.append("funcode--->"+funcode+"\n");
		log.append("appId--->"+ appId+"\n");
		log.append("mhtOrderNo--->"+ mhtOrderNo+"\n");
		log.append("mhtCharset--->"+ mhtCharset+"\n");
		log.append("tradeStatus--->"+tradeStatus+"\n");
		log.append("mhtReserved--->"+mhtReserved+"\n");
		log.append("signType--->"+signType+"\n");
		log.append("signature--->"+signature+"\n");
		Logs.geterrorLogger().error(log.toString());
		
		out.print("支付成功！");
		return null;
	}
	
	public String alipayNotify(HttpServletRequest request ,HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			Logs.geterrorLogger().error(" notify    :"+name+"------>"+valueStr);
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//商户订单号
		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//支付宝交易号
		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//交易状态
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

		Logs.geterrorLogger().error(" PayController  alipayNotify  order_id:"+out_trade_no +"    alipay_no:"+trade_no+"  trade_status:"+trade_status);
		
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		if(AlipayNotify.verify(params)){//验证成功
			if(trade_status.equals("WAIT_BUYER_PAY")){
				//该判断表示买家已在支付宝交易管理中产生了交易记录，但没有付款
				Logs.geterrorLogger().error(out_trade_no+"-------->1");
					//判断该笔订单是否在商户网站中已经做过处理
						//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
						//如果有做过处理，不执行商户的业务程序
					
					out.println("success");	
				} else if(trade_status.equals("WAIT_SELLER_SEND_GOODS")){//支付成功
					//该判断表示买家已在支付宝交易管理中产生了交易记录且付款成功，但卖家没有发货
					long id = StringUtil.toLong(out_trade_no,0L);
					RobotShopOrder order = shopService.getRobotShopOrderById(id);
					if (order == null || order.getStatus() != 0) {
						Logs.geterrorLogger().error(out_trade_no+"-------->pay success but  order is null or status error!!");
						return null;
					}
					long res = shopService.updateRobotShopOrderStatusOrderid(id, 1, trade_no);
					if (res < 0) {
						Logs.geterrorLogger().error(out_trade_no+"-------->pay success but  order update status error!!");
						return null;
					}
					Logs.geterrorLogger().error(out_trade_no+"-------->pay success and update status success");
					out.println("success");	//请不要修改或删除
				} else if(trade_status.equals("WAIT_BUYER_CONFIRM_GOODS")){
					//该判断表示卖家已经发了货，但买家还没有做确认收货的操作
					long id = StringUtil.toLong(out_trade_no,0L);
					RobotShopOrder order = shopService.getRobotShopOrderById(id);
					if (order == null || order.getStatus() != 1) {
						Logs.geterrorLogger().error(out_trade_no+"-------->send success  but order is null or status error!!");
						return null;
					}
					long res = shopService.updateRobotShopOrderStatus(id, 2);
					if (res < 0) {
						Logs.geterrorLogger().error(out_trade_no+"-------->send success  but order update status error!!");
						return null;
					}
					Logs.geterrorLogger().error(out_trade_no+"-------->send success  and order update status success!!");
					out.println("success");	//请不要修改或删除
				} else if(trade_status.equals("TRADE_FINISHED")){
					//该判断表示买家已经确认收货，这笔交易完成
					long id = StringUtil.toLong(out_trade_no,0L);
					RobotShopOrder order = shopService.getRobotShopOrderById(id);
					if (order == null || order.getStatus() != 2) {
						Logs.geterrorLogger().error(out_trade_no+"-------->confirm success  but order is null or status error!!");
						return null;
					}
					long res = shopService.updateRobotShopOrderStatus(id, 3);
					if (res < 0) {
						Logs.geterrorLogger().error(out_trade_no+"-------->confirm success  but order update status error!!");
						return null;
					}
					Logs.geterrorLogger().error(out_trade_no+"-------->confirm success  and order update status success!!");
					
					out.println("success");	//请不要修改或删除
				} else {
					out.println("success");	//请不要修改或删除
				}

			//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

			//////////////////////////////////////////////////////////////////////////////////////////
		}else{//验证失败
			Logs.geterrorLogger().error("PayController alipayNotify  veryfy failed!!!");
			out.println("fail");
		}
		return null;
		
	}
	
	public String alipayReturn(HttpServletRequest request ,HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		//获取支付宝GET过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			Logs.geterrorLogger().error(" return    :"+name+"------>"+valueStr);
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//商户订单号
		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//支付宝交易号
		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//交易状态
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		//计算得出通知验证结果
		boolean verify_result = AlipayNotify.verify(params);
		
		if(verify_result){//验证成功
			if(trade_status.equals("WAIT_SELLER_SEND_GOODS")){
				Logs.geterrorLogger().error("PayController return  "+out_trade_no+"------->pay success!!");
			}
			
			//该页面可做页面美工编辑
			out.println("验证成功<br />");
			
		}else{
			//该页面可做页面美工编辑
			out.println("验证失败");
		}
		
		
		return null;
	}
	
}
