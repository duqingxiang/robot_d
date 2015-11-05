<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="com.demo.util.*" %>
<html lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport">
<meta name="description" content="">
<meta name="author" content="">
<title>Lonely Robots</title>
<%

	long browseCount = StringUtil.toLong(request.getAttribute("browseCount").toString(), 0L);
	long ipCount = StringUtil.toLong(request.getAttribute("ipCount").toString(), 0L);
	long sessionidCount = StringUtil.toLong(request.getAttribute("sessionidCount").toString(), 0L);

%>

<link href="/css/bootstrap.css" media="all" rel="stylesheet">
<link href="/css/timeline/font-awesome.min.css" media="all" rel="stylesheet">
<link href="/css/styles.css" media="all" rel="stylesheet">
<style>
body {
	background-image: linear-gradient(to right, #8DD4FE 0, #5A5499 135%);
	padding: 5em 0; }
</style>
</head>
<body>
<nav class="navbar navbar-inverse  navbar-fixed-top">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="/">Lonely Robots</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="/">首页 <span class="sr-only">(current)</span></a></li>
        <li><a href="#" data-toggle="modal" data-target="#myModal" >关于</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">相关 <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="http://lonelyrobots.cn:8088" target="_blank">布词</a></li>
            <li><a href="/timeline.do">时间轴</a></li>
            <!-- 分割线 -->
            <!-- <li class="divider"></li> -->

          </ul>
        </li>
      </ul>


    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>

<div class="container">
	<div class="jumbotron">
	  <h1>Hello, Robots!</h1>
	  <p>...</p>
	  <p>访问次数：<%=browseCount %></p>
	  <p>IP访问次数：<%=ipCount %></p>
	  <p>Session访问次数：<%=sessionidCount %></p>
	</div>
</div>


<!-- 时间轴 -->
<div class="timeline animated">
	
	<div class="timeline-row active">
		<div class="timeline-time">2015-03-17</div>
		<div class="timeline-icon">
			<div class="bg-primary"><i class="fa fa-pencil"></i></div>
		</div>
		<div class="panel timeline-content">
			<div class="panel-body">
				<h2>搭建框架，bootstrap</h2>
				<p>这是个Demo...</p>
			</div>
		</div>
	</div>
	
	<div class="timeline-row active">
		<div class="timeline-time">2015-03-18</div>
		<div class="timeline-icon">
			<div class="bg-warning"><i class="fa fa-quote-right"></i></div>
		</div>
		<div class="panel timeline-content">
			<div class="panel-body">
				<h2>增加时间轴，简单流量统计</h2>
				<p>效果还可以，美工做的不好啊。。</p>
			</div>
		</div>
	</div>
	
	<div class="timeline-row active">
		<div class="timeline-time">2015-03-18</div>
		<div class="timeline-icon">
			<div class="bg-info"><i class="fa fa-camera"></i></div>
		</div>
		<div class="panel timeline-content">
			<div class="panel-body">
				<h2>时间轴图片测试</h2>
				<img class="img-responsive" src="/img/timeline/robots.jpg">
				<p>this is a test.</p>
			</div>
		</div>
	</div>
	
</div>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">About</h4>
      </div>
      <div class="modal-body">
        <p>Lonely Robots 团队起始于某破烂大学的某个宿舍...</p>
        <p>兄弟几人创建至今仍然一事无成...</p>
        <p>时至今日，他们终于...</p>
        <p>买了一双滑板鞋！</p>
        <p>我滴滑板鞋，时尚时尚最时尚。。。</p>
        <hr>
        <p>Author:Robot_d</p>
        <p>Email:duqingxiang92@163.com</p>
        <p>QQ:2410508062</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <!-- <button type="button" class="btn btn-primary">Save changes</button> -->
      </div>
    </div>
  </div>
</div>

<script type="text/javascript" src="/js/timeline/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/js/timeline/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/timeline/main.js"></script>


</body></html>