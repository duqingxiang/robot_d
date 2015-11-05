package com.demo.util.pay;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.demo.bean.RobotShopOrder;
import com.demo.util.Constants;
import com.demo.util.DoubleUtil;
import com.demo.util.MD5Facade;
import com.demo.util.alipay.AlipaySubmit;

public class PayUtil {

	
	public static void getNowpayPostContent(HttpServletRequest request,RobotShopOrder order){
		
		
		String appId = Constants.NOWPAY_APP_ID;
		String appKey = Constants.NOWPAY_APP_KEY;
		//商品名称
		String mhtOrderName = "LonelyRobots";
		//消费金额
		String mhtOrderAmt = (int)DoubleUtil.multiply(order.getPrice(), 100)+"";
		//消费信息
		String mhtOrderDetail = "http://lonelyrobots.cn/detail/order/"+order.getId()+".do";
		
		String fundcode = "WP001";
		String mhtOrderNo =order.getId()+"";
		//交易类型  01普通消费
		String mhtOrderType = "01";
		//币种类型 156人民币
		String mhtCurrencyType = "156";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		//订单开始时间
		String mhtOrderStartTime = dateFormat.format(new Date());
		
		String notifyUrl = "http://lonelyrobots.cn/pay/nowNotify.do";
		String frontNotifyUrl ="http://lonelyrobots.cn/pay/nowFontNotify.do"; 
		String mhtCharset = "UTF-8";
		String deviceType = "02";
		String payChannelType = "";
		String mhtReserved = "";
		
		//做MD5签名
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("appId", appId);
		dataMap.put("mhtOrderNo", mhtOrderNo);
		dataMap.put("mhtOrderName", mhtOrderName);
		dataMap.put("mhtCurrencyType", mhtCurrencyType);
		dataMap.put("mhtOrderAmt", mhtOrderAmt);
		dataMap.put("mhtOrderDetail", mhtOrderDetail);
		dataMap.put("mhtOrderType", mhtOrderType);
		dataMap.put("mhtOrderStartTime", mhtOrderStartTime);
		dataMap.put("notifyUrl", notifyUrl);
		dataMap.put("frontNotifyUrl", frontNotifyUrl);
		dataMap.put("mhtCharset", mhtCharset);
		dataMap.put("payChannelType", payChannelType);
		//商户保留域， 可以不用填。 如果商户有需要对每笔交易记录一些自己的东西，可以放在这个里面
		dataMap.put("mhtReserved", mhtReserved);
		String mhtSignature = MD5Facade.getFormDataParamMD5(dataMap, appKey, "UTF-8");
		
		request.setAttribute("appId", appId);
		request.setAttribute("mhtOrderNo", mhtOrderNo);
		request.setAttribute("mhtOrderName", mhtOrderName);
		request.setAttribute("mhtCurrencyType", mhtCurrencyType);
		request.setAttribute("mhtOrderAmt", mhtOrderAmt);
		request.setAttribute("mhtOrderDetail", mhtOrderDetail);
		request.setAttribute("mhtOrderType", mhtOrderType);
		request.setAttribute("mhtOrderStartTime", mhtOrderStartTime);
		request.setAttribute("notifyUrl", notifyUrl);
		request.setAttribute("frontNotifyUrl", frontNotifyUrl);
		request.setAttribute("mhtCharset", mhtCharset);
		
		request.setAttribute("mhtSignType", "MD5");
		request.setAttribute("mhtSignature", mhtSignature);
		request.setAttribute("funcode", fundcode);
		request.setAttribute("deviceType", deviceType);
		request.setAttribute("payChannelType", payChannelType);
		request.setAttribute("mhtReserved", mhtReserved);
		
	}
	
	
	public static void getAlipayPostContent(HttpServletRequest request,RobotShopOrder order){
		
		String payment_type = "1";
		String notify_url = "http://www.lonelyrobots.cn/pay/alipayNotify.do";
		String return_url = "http://www.lonelyrobots.cn/pay/alipayReturn.do";
		String out_trade_no = order.getId()+"";
		String subject = "LonelyRobots";
		String price = order.getPrice()+"";
		String quantity = "1";
		String logistics_fee = "0.00";
		String logistics_type = "EXPRESS";
		String logistics_payment = "SELLER_PAY";
		String body = order.getContent();
		String show_url ="http://lonelyrobots.cn/detail/order/"+order.getId()+".do";
		String receive_name = order.getReceivedName();
		String receive_address = order.getReceivedAddress();
		String receive_mobile = order.getReceivedPhone();
		
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "create_partner_trade_by_buyer");
        sParaTemp.put("partner", Constants.ALIPAY_PARTNER);
        sParaTemp.put("seller_email", Constants.ALIPAY_SELLER_EMAIL);
        sParaTemp.put("_input_charset", Constants.ALIPAY_INPUT_CHARSET);
		sParaTemp.put("payment_type", payment_type);
		sParaTemp.put("notify_url", notify_url);
		sParaTemp.put("return_url", return_url);
		sParaTemp.put("out_trade_no", out_trade_no);
		sParaTemp.put("subject", subject);
		sParaTemp.put("price", price);
		sParaTemp.put("quantity", quantity);
		sParaTemp.put("logistics_fee", logistics_fee);
		sParaTemp.put("logistics_type", logistics_type);
		sParaTemp.put("logistics_payment", logistics_payment);
		sParaTemp.put("body", body);
		sParaTemp.put("show_url", show_url);
		sParaTemp.put("receive_name", receive_name);
		sParaTemp.put("receive_address", receive_address);
		sParaTemp.put("receive_mobile", receive_mobile);
		
		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"get","确认");
		request.setAttribute("sHtmlText", sHtmlText);
	}
}
