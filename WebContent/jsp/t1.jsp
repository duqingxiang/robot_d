<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.demo.util.*,com.demo.bean.RobotUser"%>
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

</style>
</head>
<body class="">
	<div class="row">
		<div class="col-md-1"></div>
		<div class="col-md-10">

			<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
				<!-- Indicators -->
				<ol class="carousel-indicators">
					<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
					<li data-target="#carousel-example-generic" data-slide-to="1"></li>
					<!-- <li data-target="#carousel-example-generic" data-slide-to="2"></li> -->
				</ol>

				<!-- Wrapper for slides -->
				<div class="carousel-inner" role="listbox">
					<div class="item active">
						<img src="/img/bg1.jpg" width="100%" alt="...">
						<div class="carousel-caption">...</div>
					</div>
					<div class="item">
						<img src="/img/bg2.jpg" width="100%" alt="...">
						<div class="carousel-caption">...</div>
					</div>
					
				</div>

				<!-- Controls -->
				<a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev"> 
					<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					<span class="sr-only">Previous</span>
				</a> 
				<a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next"> 
					<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					<span class="sr-only">Next</span>
				</a>
			</div>

		</div>
		<div class="col-md-1"></div>
	</div>

	<script type="text/javascript" src="/js/jquery-2.1.3.js"></script>
	<script type="text/javascript" src="/js/bootstrap.js"></script>
</body>
</html>