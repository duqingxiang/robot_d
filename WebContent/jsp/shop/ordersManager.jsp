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
	width:15%;
}
.td5_width span {
	color: #f40;
	font-size:15px;
}
.td6_width {
	width:10%;
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
								<span class="title_tag"><a href="">订单管理</a></span>
								
							</div>
						</div>
					</div>
				
				</div>
				<div class="panel panel-primary">
					<div class="panel-body">
					
						<%
							int nowPage = request.getAttribute("page") != null ? StringUtil.toInteger(request.getAttribute("page").toString(), 0) : 0;
							int status = request.getAttribute("status") != null ? StringUtil.toInteger(request.getAttribute("status").toString(), 0) : -999;
							int admin = request.getAttribute("admin") != null ? StringUtil.toInteger(request.getAttribute("admin").toString(), 0) : 0;
							
							String adminStr = admin == 1 ? "&admin=1" : "";
							
							String beforeUrl="/shop/ordersManager.do?p="+(nowPage-1)+adminStr+"&s="+status;
							String nextUrl="/shop/ordersManager.do?p="+(nowPage+1)+adminStr+"&s="+status;
							
						%>

						<form class="form-inline" action="/shop/ordersManager.do">
						  <div class="form-group">
						    <label for="exampleInputName2">状态</label>
						    <select name="s" class="form-control">
						    	<option <%if(status == -999){%>selected=selected<%} %> value="-999">全部</option>
						    	<option <%if(status == 0){%>selected=selected<%} %> value="0">待支付</option>
						    	<option <%if(status == 1){%>selected=selected<%} %> value="1">待发货</option>
						    	<option <%if(status == 2){%>selected=selected<%} %> value="2">待收货</option>
						    	<option <%if(status == 3){%>selected=selected<%} %> value="3">完成</option>
						    	<option <%if(status == -1){%>selected=selected<%} %> value="-1">关闭</option>
						    </select>
						  </div>
						  <input type="hidden" name="p" value="<%=nowPage %>"/>
						  <% if(admin==1 && user.isSuper()) {%>
							  <input type="hidden" name="admin" value="<%=admin %>"/>
						  <%} %>
						  <button type="submit" class="btn btn-success">查询</button>
						</form>
						<table class="table table-hover">
							<tr>
								<th>订单ID</th>
								<th>最后更新时间</th>
								<th>价格</th>
								<th>收货人姓名</th>
								<th>收货人手机</th>
								<th>状态</th>
								<th>操作</th>
							</tr>
							<%
								List<RobotShopOrder> ordersList = (List<RobotShopOrder>)request.getAttribute("ordersList");
								if (CollectionUtils.isEmpty(ordersList)){%>
									<tr><td colspan="7" class="text-center">暂无订单数据</td></tr>
								<%} else {
									for (RobotShopOrder order : ordersList) {
										String statusStr = "";
										String dateStr = DateUtil.dateUtil2String(order.getModifyDate(), "yyyy-MM-dd HH:mm:ss");
										switch (order.getStatus()) {
											case 0 : statusStr="待支付";break;
											case 1 : statusStr="待发货";break;
											case 2 : statusStr="待收货";break;
											case 3 : statusStr="完成";break;
											case -1 : statusStr="已关闭";break;
										}
									
								%>
								<tr>
									<td><%=order.getId() %></td>
									<td><%=dateStr %></td>
									<td><%=order.getPrice() %></td>
									<td><%=order.getReceivedName() %></td>
									<td><%=order.getReceivedPhone() %></td>
									<td><%=statusStr %></td>
									<td>
										<button type="button" class="btn btn-info" onclick="toDetail(<%=order.getId()%>)">详情</button>
										<% if(order.getStatus() == 0){%>
											<a class="btn btn-success" href="/shop/toPay.do?order_id=<%=order.getId()%>" target="_blank">支付</a>
											<button type="button" class="btn btn-danger" onclick="closeOrder(<%=order.getId()%>)">关闭</button>
										<%} %>
										<% if(order.getStatus() == 1 && order.getAutoSend() == 0&& admin==1 && user.isSuper()) {%>
											<button type="button" class="btn btn-warning" onclick="sendOrder(<%=order.getId()%>)">发货</button>
										<%} %>
									</td>
								</tr>
								
							<%	}}
							%>
							
							
						</table>
						<nav>
							<ul class="pager">
								<% if (nowPage >0){%>
									<li class="previous"><a href="<%=beforeUrl%>"><span
											aria-hidden="true">&larr;</span> Older</a></li>
								<%} %>
								<li class="next"><a href="<%=nextUrl%>">Newer <span
										aria-hidden="true">&rarr;</span></a></li>
							</ul>
						</nav> 
					</div>
					
					
				</div>
				
				<br/>
				<%@ include file="footer.jsp" %>
				<div class="modal fade" id="detailDiv" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="detailDivTitle">详情</h4>
							</div>
							<div class="modal-body">
								
								
								
								<table class="table table-hover">
									<thead>
										<tr>
											<th>商品名称</th>
											<th>数量</th>
											<th>单价</th>
											<th>总价</th>
										</tr>
									</thead>
									<tbody id="detailBody">
									</tbody>
								</table>
								
								
								
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-1"></div>
		</div>
	</div>

</body>

	<script type="text/javascript" src="/js/jquery-2.1.3.js"></script>
	<script type="text/javascript" src="/js/bootstrap.js"></script>
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/js/shop/good.js"></script>

</html>