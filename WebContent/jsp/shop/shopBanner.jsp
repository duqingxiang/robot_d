<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="com.demo.util.*,java.util.*,com.demo.bean.RobotUser"%>
<%@page import="org.springframework.util.CollectionUtils"%>
<%
	RobotUser user = (RobotUser) request.getAttribute("user") ;
%>
<nav class="navbar navbar-default fixed_banner">
	<div class="container-fluid">
		<!-- <div class="navbar-header">
			<a class="navbar-brand" href="#">Lonely Robots</a>
		</div> -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only"></span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">Lonely Robots</a>
		</div>

		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="/shop/index.do">全部</a></li>
				<li><a href="/shop/index.do?t=1">书籍</a></li>
				<li><a href="/blog/toDetail.do?id=1000333" target="_blank">流程简介</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">相关 <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="/" target="_blandk">首页</a></li>
						<li><a href="/map.do" target="_blandk">地图(Map)</a></li>
						<li><a href="/uploadManager.do" target="_blandk">资源(Resource)</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="/blog/index.do" target="_blandk">博客(Blog)</a></li>
					</ul></li>
			</ul>

			<% 
				String key = request.getAttribute("key") != null ? request.getAttribute("key").toString() : "";
				int type = request.getAttribute("type") != null ? StringUtil.toInteger(request.getAttribute("type").toString(), 1) :1;
			%>
			<ul class="nav navbar-nav navbar-right">
				<li>
					<form class="navbar-form navbar-left" role="search" method="post" action="/shop/index.do">
						<input type="hidden" name="t" value="<%=type %>"/>
						<div class="form-group">
							<input type="text" class="form-control" name="s" value="<%=key %>" placeholder="Search">
						</div>
						<button type="submit" class="btn btn-default">Submit</button>
					</form>
				</li>
				<li class="dropdown">
				
					<% if (user == null) {//未登录%>
						<a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false">游客<span class="caret"></span></a>
					<%} else {//已登录%>
						<a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false"><%=StringUtil.isEmpty(user.getNickName()) ? user.getEmail() : user.getNickName() %><span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="/shop/ordersManager.do" target="_blank">我的订单</a></li>
							<!-- <li><a href="#">Another action</a></li>
							<li><a href="#">Something else here</a></li> -->
							<% if (user.isSuper()) {%>
								<li role="separator" class="divider"></li>
								<li><a href="/shop/goodsManager.do">商品管理</a></li>
								<li><a href="/shop/ordersManager.do?admin=1">订单管理</a></li>
							<%} %>
							
							
							<li role="separator" class="divider"></li>
							<li><a href="javascript:void(0);" id="logout_btn">退出</a></li>
						</ul>
					<%} %>
				</li>
				<li><a target="_blank" href="/shop/cart.do"><span
						class="glyphicon glyphicon-shopping-cart"></span>&nbsp;&nbsp;购物车</a></li>
			</ul>
		</div>
	</div>
</nav>