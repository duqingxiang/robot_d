<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="com.demo.util.*" %>
<html lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport">
<meta name="description" content="">
<meta name="author" content="">
<title>Lonely Robots</title>

<link href="/css/bootstrap.css" media="all" rel="stylesheet">
<link href="/css/timeline/font-awesome.min.css" media="all" rel="stylesheet">
<style>
body {
	/* background-image: linear-gradient(to right, #8DD4FE 0, #5A5499 135%); */
	background-image:url("/img/bg4.jpg");
	background-repeat:round;
	padding: 5em 0; }
</style>
</head>
<body>
<%
	String forward = request.getParameter("forward");
%>
<input type="hidden" id="forwardUrl" value="<%=forward%>"/>
<div class="container">
	<div class="row">
		<div class="col-md-4"></div>
	  	<div class="col-md-4">
	  	<br>
		<br>
		<br>
		<br>
		<br>
	  	<ul class="nav nav-tabs">
		  <li role="presentation" class="active"><a class="change-tab" target_div="login_div" href="javascirpt:void(0);">登录</a></li>
		  <li role="presentation"><a class="change-tab" target_div="reg_div" href="javascirpt:void(0);">注册</a></li>
		  <li role="presentation"><a class="change-tab" href="/">首页</a></li>
		</ul>
		<br>
		<div id="login_div" class="loginClass">
			  <div class="form-group">
			    <label for="login_name">Username</label>
			    <input type="email" class="form-control" id="login_name" placeholder="Enter Username">
			  </div>
			  <div class="form-group">
			    <label for="login_password">Password</label>
			    <input type="password" class="form-control" id="login_password" placeholder="Password">
			  </div>
			  <div class="form-group" align="right">
				<label style="color:red" id="login_tag"></label>
			  </div>
			  <div class="form-group" align="right">
			  	<button type="submit" class="btn btn-primary" id="login_submit">登录</button>
			  </div>
		</div>
		<div id="reg_div" class="loginClass" style="display:none;">
			  <div class="form-group">
			    <label for="reg_name">Username</label>
			    <input type="email" class="form-control" id="reg_name" placeholder="Enter Username">
			  </div>
			  <div class="form-group">
			    <label for="reg_name">NickName</label>
			    <input type="text" class="form-control" id="nick_name" placeholder="Enter NickName">
			  </div>
			  <div class="form-group">
			    <label for="reg_password">Password</label>
			    <input type="password" class="form-control" id="reg_password" placeholder="Password">
			  </div>
			  <div class="form-group">
			    <label for="reg_password_again">Password Again</label>
			    <input type="password" class="form-control" id="reg_password_again" placeholder="Password Again">
			  </div>
			  <div class="form-group" align="right">
				<label style="color:red" id="reg_tag"></label>
			  </div>
			  <div class="form-group" align="right">
			  	<button type="submit" class="btn btn-primary" id="reg_submit">注册</button>
			  </div>
		</div>
			
		</div>
		<div class="col-md-4"></div>
	</div>
</div>


<script type="text/javascript" src="/js/timeline/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/js/timeline/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/common.js"></script>


</body></html>