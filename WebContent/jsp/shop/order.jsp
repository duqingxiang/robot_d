<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.demo.util.*,com.demo.bean.RobotUser,com.demo.bean.RobotShopOrder"%>
<html lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"
	name="viewport">
<meta name="description" content="">
<meta name="author" content="">
<title>Lonely Robots</title>

<link href="/css/bootstrap.css" media="all" rel="stylesheet">
<link href="/css/site.css" media="all" rel="stylesheet">
<link href="/css/shop/shop.css" media="all" rel="stylesheet">
<style>
.red {
	color: red;
}

</style>
</head>
<body class="body_css">
	<div class=".container-fluid">
		<div class="row">
			<div class="col-md-1"></div>
			<div class="col-md-10">
				<%@include file="shopBanner.jsp" %>
					<%
						RobotShopOrder order =(RobotShopOrder) request.getAttribute("order");
						if (order != null) {
							String status = "";
							switch (order.getStatus()) {
								case 0 : status="待支付";break;
								case 1 : status="已支付，等待发货";break;
								case 2 : status="已发货，等待收货";break;
								case 3 : status="订单完成";break;
								case -1 : status="订单已取消";break;
								
							
							}
						%>
						
							<div class="jumbotron text-center">
							  <h1>请确认您要支付的订单信息</h1>
							  <p>付款金额：<%=order.getPrice() %></p>
							  <p>收货人姓名：<%=order.getReceivedName() %></p>
							  <p>收货人手机：<%=order.getReceivedPhone() %></p>
							  <p>收货人地址：<%=order.getReceivedAddress() %></p>
							  <p>买家备注：<%=order.getReceivedRemarks() %></p>
							  <p>订单状态：<%=status %></p>
							  <% if (order.getStatus() == 0){%>
							  	<p><a class="btn btn-primary btn-lg" href="javascript:void(0)" onclick="toPay()" role="button">确认支付</a></p>
							  <%} else{%>
								 <p><a class="btn btn-primary btn-lg" href="/shop/index.do" role="button">返回首页</a></p>
							  <%} %>
							</div>
							
							
							<% if(order.getType() == 1) {//alipay%>
								<div style="display:none;">
									<%= request.getAttribute("sHtmlText") !=null ? request.getAttribute("sHtmlText").toString() : "" %>
								</div>
							<% }else if (order.getType() == 2) {//nowpay%>
								<div style="display:none;">
									<form id="payForm" action="http://api.ipaynow.cn" METHOD=POST target="_blank">
										功能码：<input type=text name="funcode" value="WP001" readonly/><br>
										应用ID:<input type=text name="appId" value="${appId}" readonly/><br>
										商户订单号：<input type=text name="mhtOrderNo" value="${mhtOrderNo}" readonly/><br>
										商户订单名称：<input type=text name="mhtOrderName" value="${mhtOrderName}" readonly/><br>
										币种：<input type=text name="mhtCurrencyType" value="156" readonly/><br>
										金额：<input type=text name="mhtOrderAmt" value="${mhtOrderAmt}" readonly/><br>
										订单详情：<input type=text name="mhtOrderDetail" value="${mhtOrderDetail}" readonly/><br>
										订单类型：<input type=text name="mhtOrderType" value="${mhtOrderType}" readonly/><br>
										订单时间：<input type=text name="mhtOrderStartTime" value="${mhtOrderStartTime}" readonly/><br>
										后台通知URL：<input type=text name="notifyUrl" value="${notifyUrl}" readonly/><br>
										前台通知URL：<input type=text name="frontNotifyUrl" value="${frontNotifyUrl}" readonly/><br>
										字符集：<input type=text name="mhtCharset" value="UTF-8" readonly/><br>
										设备类型：<input type=text name="deviceType" value="02" readonly/><br>
										现在支付网关号：<input type=text name="payChannelType" value="${payChannelType}"><br>
										商户保留域：<input type=text name="mhtReserved" value="${mhtReserved}"><br>
										签名类型：<input type=text name="mhtSignType" value="MD5" readonly/><br>
										数字签名：<input type=text name="mhtSignature" value="${mhtSignature}" readonly/><br>
										
										<button type=submit>确认订单</button>
									</form>
								
								</div>
							<%} %>
							
							
							
						<%} else {%>
							<div class="jumbotron text-center">
							  <h1>您所查看的订单不存在！</h1>
							  <p><a class="btn btn-primary btn-lg" href="/shop/index.do" role="button">返回首页</a></p>
							</div>
						
						<%}%>
					
					
				
				<%@ include file="footer.jsp" %>
			</div>
			<div class="col-md-1"></div>
		</div>
	</div>

</body>

	<script type="text/javascript" src="/js/jquery-2.1.3.js"></script>
	<script type="text/javascript" src="/js/bootstrap.js"></script>
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/js/shop/good.js"></script>
	<script>
	</script>

</html>