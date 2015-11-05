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
					boolean error = request.getAttribute("error") != null ? (Boolean) request.getAttribute("error") : true;
					if (error) {//错误的商品显示
						%>
					
					<div class="jumbotron text-center">
					  <h1>该商品不存在！</h1>
					  <p>您所浏览的商品不存在，或已经下架，请您重新选择相关产品</p>
					  <p><a class="btn btn-primary btn-lg" href="/shop/index.do" role="button">返回首页</a></p>
					</div>
					
					
						
				<%} else {//正确的显示
				
					RobotShopGood good = (RobotShopGood)request.getAttribute("good");
				%>
					
					<div class="row">
						<div class="col-md-4">
							<a href="javascript:void(0)" class="thumbnail">
						    	<img alt="<%=good.getGoodName() %>" style="max-height: 418px;" src="<%=good.getGoodPicUrl()%>">
						    </a>
							
						</div>
						<div class="col-md-8">
							<table>
								<tr>
									<td colspan="2">
										<span class="good_title"><%=good.getGoodName() %>1</span>
									</td>
								</tr>
								<tr class="good_tr">
									<td colspan="2">
										<span class="good_title_simple"><%=good.getGoodTag() %></span>
									</td>
								</tr>
								<%
								
								double price =  good.getGoodPrice(); 
								if (good.getIsDiscount() == 1) {
									price = good.getGoodDisPrice();
								%>
									<tr class="good_tr">
										<td class="good_td_tag"><span>原价:</span></td>
										<td class="good_td_price_delete"><span>￥<%=good.getGoodPrice() %></span></td>
									</tr>
								<%} %>
								<tr class="good_tr">
									<td class="good_td_tag"><span>价格:</span></td>
									<td class="good_td_price"><span>￥<%=price %></span><%if(good.getIsOut() == 1){%><span class="good_td_price_out">缺货</span><%}else{%><span class="good_td_price_in">有货</span><%} %></td>
								</tr>
								<tr class="good_tr">
									<td class="good_td_tag"><span>运费:</span></td>
									<td class="good_td"><span>顺丰快递，货到付款</span></td>
								</tr>
								<tr class="good_tr">
									<td colspan="2"><button type="button" onclick="inCart(<%=good.getId() %>)" class="btn btn-success">加入购物车</button></td>
								</tr>
							</table>
						</div>
					</div>
					<div class="panel panel-info">
						<div class="panel-heading">商品详情</div>
						<div class="panel-body">
							<%=good.getGoodContent() %>
						</div>
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