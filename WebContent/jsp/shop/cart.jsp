<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.demo.util.*,com.demo.bean.RobotUser,com.demo.bean.RobotShopGood"%>
<html lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"
	name="viewport">
<meta name="description" content="">
<meta name="author" content="">
<title>Lonely Robots</title>
<%
	request.setCharacterEncoding("utf-8");
%>
<link href="/css/bootstrap.css" media="all" rel="stylesheet">
<link href="/css/site.css" media="all" rel="stylesheet">
<style>
.red {
	color: red;
}

.td1_width {
	width: 20px;
}
.td2_width {
	width: 150px;
}
.td3_width {
	width:15%;
}
.td4_width {
	width:25%;
}
.td4_width span {
	color: #9c9c9c;
	font-size:13px;
}
.td5_width {
	width:10%;
}
.td5_width span {
	color: #f40;
	font-size:15px;
}
.td6_width {
	width:15%;
}
.td6_width input{
	width:100px;
}
.img_css {
	height: 100px;
  	width: 100px;
}

</style>
</head>
<body class="body_css">
	<div class=".container-fluid">
		<div class="row">
			<div class="col-md-1"></div>
			<div class="col-md-10">
				<%@include file="shopBanner.jsp" %>
				
				<div class="header_title"	style="background-image: url(/img/bg3.jpg); background-repeat: repeat-y;margin-bottom:25px;">
					<div class="container">
						<div class="row">
							<div class="col-xs-12">
								<span class="title_tag"><a href="">购物车</a></span>
								
							</div>
						</div>
					</div>
				
				</div>
				
				<div class="panel panel-primary">
					<div class="panel-heading">
						购物列表
					</div>
					<div class="panel-body">
						<table class="table table-hover">
							<%
								List<RobotShopGood> goodList = (List<RobotShopGood>)request.getAttribute("goodList");
								if (!CollectionUtils.isEmpty(goodList)) {
									for (RobotShopGood good : goodList) {%>
										
										<tr>
											<td class="td1_width">
												<span><input type="checkbox" class="goodCheck" onclick="sumGoods()" value="<%=good.getId()%>"/></span>
											</td>
											<td class="td2_width">
												<img class="img-thumbnail img_css" src="<%=good.getGoodPicUrl() %>" />
											</td>
											<td class="td3_width" style="vertical-align: middle;">
												<span><a href="/detail/good/<%=good.getId() %>.do"><%=good.getGoodName() %></a></span>
											</td>
											<td class="td4_width" style="vertical-align: middle;">
												<span><%=good.getGoodTag() %></span>
											</td>
											<td class="td5_width" style="vertical-align: middle;">
												<span class="goodPrice"><%=good.getIsDiscount() ==1 ?good.getGoodDisPrice() :good.getGoodPrice() %></span>
											</td>
											<td class="td6_width" style="vertical-align: middle;">
												<input type="text" class="goodAmount" value="1"/>
											</td>
											<td class="td5_width" style="vertical-align: middle;">
												<span class="goodSum"><%=good.getIsDiscount() ==1 ?good.getGoodDisPrice() :good.getGoodPrice() %></span>
											</td>
											<td style="vertical-align: middle;">
												<a href="javascript:void(0);" onclick="outCart(this,<%=good.getId() %>)">删除</a>
											</td>
										</tr>
										
							<%}} else {%>
								<tr><td colspan="8" class="text-center">亲，还没有选购商品哟~</td></tr>
							<%}%>
							
							
						</table>
							
					</div>
					
					
				</div>
				<form id="orderForm" method="post" action="/shop/toPay.do">
					<div class="panel panel-primary">
						<div class="panel-heading">
							收货信息
						</div>
						<div class="panel-body">
							<input type="hidden" name="good_content" id="good_content"/>
							<div class="form-inline">
								<div class="form-group">
									<label for="received_name">收货人姓名：</label> 
									<input type="text" class="form-control" name="received_name" id="received_name" placeholder="姓名">
								</div>
								<div class="form-group">
									<label for="received_phone">收货人手机：</label> 
									<input type="text" class="form-control" name="received_phone" id="received_phone" placeholder="手机">
								</div>
								<div class="form-group">
									<label for="received_address">收货人地址：</label> 
									<input type="text" class="form-control" name="received_address" id="received_address" style="min-width: 400px;" placeholder="地址">
								</div>
							</div>
							<div class="form-group">
								<label for="received_remarks">买家备注：</label>
		   						<input type="text" class="form-control" name="received_remarks" id="received_remarks" placeholder="Text">
							</div>
							<br/>
							<div class="form-inline">
								<div class="form-group">
									<label for="received_name">支付方式：</label> 
									<label class="radio-inline"> 
										<input type="radio" name="payChannel" id="inlineRadio1" value="1">
										<img alt="支付宝" src="/img/shop/alipay.png">
									</label> 
									<label class="radio-inline"> 
										<input type="radio" name="payChannel" id="inlineRadio2" value="2"> 
										<img alt="第三方支付" src="/img/shop/nowpay_mini.jpg">
									</label>
								</div>
							</div>
						</div>
					</div>
					<div class="cart_title"  background-repeat: repeat-y;margin-bottom:25px;">
						<div class="container">
							<div class="row">
								<div class="col-xs-12 ">
									<span>
										<button type="button" id="confirmBtn" style="width:50%;" class="btn btn-primary">确认订单</button>
									</span>
									
									<span class="cart_title_tag">总计：<a href="javascript:void(0)" id="showSum">0</a></span>
									
								</div>
							</div>
						</div>
					
					</div>
						
				</form>
								
				<br/>
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