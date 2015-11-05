<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="com.demo.bean.RobotUser,com.demo.bean.RobotShopGood"%>
<html lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"
	name="viewport">
<meta name="description" content="">
<meta name="author" content="">

<title>Lonely Robots</title>

<link href="/css/bootstrap.css" media="all" rel="stylesheet">
<link href="/css/site.css" media="all" rel="stylesheet">
<style>
.red {
	color: red;
}

.td_padding {
	padding-left: 20px;
}
</style>
</head>
<body class="body_css">
	<div class=".container-fluid">
		<div class="row">
			<div class="col-md-1"></div>
			<div class="col-md-10">
				<%@include file="shopBanner.jsp" %>
				<div class="header"	style="background-image: url(/img/bg3.jpg); background-repeat: repeat-y;margin-bottom:25px;">
					<div class="container">
						<div class="row">
							<div class="col-xs-12">
								<div class="logotxt">
									<h1>
										<a href="">Sun Shine</a>
									</h1>
								</div>
							</div>
						</div>
					</div>
				</div>
			
				<div class="panel panel-primary">
					<div class="panel-body">
						<div class="row" id="goodListContent">
						
						<% 
							List<RobotShopGood> goodList =(List<RobotShopGood>) request.getAttribute("goodList");
							if (!CollectionUtils.isEmpty(goodList)) {
								for (RobotShopGood good : goodList) {%>
								<div class="col-xs-6 col-md-3">
								    <div class="thumbnail">
								    	<img style="width:100%;height: 200px;" src="<%=good.getGoodPicUrl() %>" alt="...">
								    	<div class="caption">
									        <h3><a href="/detail/good/<%=good.getId()%>.do" target="_blank"><%=good.getGoodName() %></a></h3>
									        <p>￥<%=good.getIsDiscount() == 0?good.getGoodPrice():good.getGoodDisPrice() %></p>
									        <p>
									        	<a href="/detail/good/<%=good.getId()%>.do" target="_blank" class="btn btn-success" role="button"><span class="glyphicon glyphicon-heart"></span>&nbsp;查看</a>
									        	<a href="javascript:void(0)" onclick="inCart(<%=good.getId()%>)" class="btn btn-primary" role="button">加入购物车</a>
									        </p>
								      	</div>	
								    </div>
								  </div>	
									
									
						<%}}%>
						  
						  
						</div>
						<%
							int pages = request.getAttribute("page") != null ? StringUtil.toInteger(request.getAttribute("page").toString(), 1) :1;
							int goodCount = CollectionUtils.isEmpty(goodList) ? 0 : goodList.size();
						%>
						<% if (goodCount >= 20){%>
							<input type="hidden" id="indexPages" value="<%=pages %>"/>
							<input type="hidden" id="moreType" value="<%=type %>" />
							<input type="hidden" id="moreKey" value="<%=key %>" />
							<nav>
							  <ul class="pager">
							    <li><a href="javascript:void(0)" onclick="moreGoods()">More</a></li>
							  </ul>
							</nav>
						<%} %>
						
						
						
						
						
						
					</div>
		
				</div>
				
				
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