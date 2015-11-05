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
<%
	long browseCount = StringUtil.toLong(
			request.getAttribute("browseCount").toString(), 0L);
	long ipCount = StringUtil.toLong(request.getAttribute("ipCount")
			.toString(), 0L);
	long sessionidCount = StringUtil.toLong(
			request.getAttribute("sessionidCount").toString(), 0L);
	RobotUser user = request.getAttribute("user") !=null ?(RobotUser)request.getAttribute("user") :null ;
%>
<title>Lonely Robots</title>

<link href="/css/bootstrap.css" media="all" rel="stylesheet">
<link href="/css/site.css" media="all" rel="stylesheet">
<style>
body {
	padding: 10em 0;
}
.red{
	color:red;
}
</style>
</head>
<body class="">
	<div class="row">
		<div class="col-xs-12 text-right">
			<div class="logotxt_1">
				<% if (user == null) {%>
					<a type="button" class="btn btn-link btn-lg" href="#" data-toggle="modal"
										data-target="#loginModal" role="button">Login</a>
					<a type="button" class="btn btn-link btn-lg" href="#" data-toggle="modal"
										data-target="#regModal" role="button">Register</a>
				<%}else{%>
					<a type="button" class="btn btn-link btn-lg"><%=user.getEmail() %></a>
					<a type="button" class="btn btn-link btn-lg" id="logout_btn">Logout</a>
				<%} %>
				
			</div>
		</div>
	</div>
	<div class="wrap">

		<div class="header"
			style="background-image: url(/img/timeline/robots.jpg); background-repeat: repeat-y;">

			<div class="container">
				<div class="row">
					<div class="col-xs-12">
						<div class="logotxt">
							<h1>
								<a href="">Hello World</a>
							</h1>
						</div>
						<h2 class="text-center">
							<p>
								<a class="btn btn-warning btn-lg" href="#" data-toggle="modal"
									data-target="#myModal" role="button" style="width: 120px;">Contect Us</a>
								<a class="btn btn-warning btn-lg" style="width: 120px;"href="/blog/index.do" target="_blank" role="button">Blog</a>
								<a class="btn btn-warning btn-lg" style="width: 120px;"href="/shop/index.do" target="_blank" role="button">Shop</a>
								<a class="btn btn-warning btn-lg" style="width: 120px;"href="/map.do" target="_blank" role="button">Map</a>
								<a class="btn btn-warning btn-lg" style="width: 120px;"href="/uploadManager.do" target="_blank" role="button">Resource</a>
							</p>
						</h2>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12 text-center">
				<h4>&nbsp;</h4>
				<a type="button" class="btn btn-primary btn-lg">PV:<%=browseCount%></a>
				<a type="button" class="btn btn-info  btn-lg">UV:<%=sessionidCount%></a>
				<a type="button" class="btn btn-success btn-lg">IP:<%=ipCount%></a>
			</div>
		</div>
		<main class="main" role="main">
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12 col-md-6 col-lg-6">
					<article class="post tag-ad">
						<section class="post-featured-image">
							<a class="thumbnail" href="http://www.lovermemorys.com/"
								target="_blank" onclick=""> <img src="/img/team/robots.jpg"
								width="800" height="600" alt="Lonely Robots"
								style="display: block;" data-original="/img/team/robots.jpg">
							</a>
						</section>
						<header class="post-header">
							<h2 class="post-title">
								<a href="http://www.lovermemorys.com/" onclick=""
									target="_blank">Lonely Robots团队</a>
							</h2>
						</header>

					</article>
				</div>
				<div class="col-xs-12 col-md-6 col-lg-6">
					<article class="post tag-ad">
						<section class="post-featured-image">
							<a class="thumbnail" href="http://www.lovermemorys.com/"
								target="_blank" onclick=""> <img src="/img/team/robots1.jpg"
								width="800" height="600" alt="Lonely Robots"
								style="display: block;" data-original="/img/team/robots1.jpg">
							</a>
						</section>
						<header class="post-header">
							<h2 class="post-title">
								<a href="http://www.lovermemorys.com/" onclick=""
									target="_blank">忧伤的故事</a>
							</h2>
						</header>

					</article>
				</div>

			</div>
			<br/><br/>
			<div class="row">
				<div class="col-xs-1"></div>
				<div class="col-xs-10">

					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">相关产品</h3>
						</div>
						<div class="panel-body">
							<table class="table table-bordered table-hover">
								<thead>
									<tr>
										<th>#</th>
										<th>产品</th>
										<th>价格</th>
										<th>作者</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<th scope="row">1</th>
										<td>项目、问题、解决方案咨询</td>
										<td>￥20/小时</td>
										<td>@all</td>
									</tr>
									<tr>
										<th scope="row">2</th>
										<td>java程序源码</td>
										<td>￥100-1000/个</td>
										<td>@all</td>
									</tr>
									<tr>
										<th scope="row">3</th>
										<td>中、小型项目框架构建</td>
										<td>面议</td>
										<td>@all</td>
									</tr>
									<tr>
										<th scope="row">4</th>
										<td>中、小型项目开发</td>
										<td>面议</td>
										<td>@all</td>
									</tr>
								</tbody>
							</table>


						</div>
					</div>
				</div>
				<div class="col-xs-1"></div>

			</div>
		</div>
		</main>
	</div>
	<footer>
        <div class="container">
            <div class="row">
                <div class="col-xs-12">
                    <div class="">
                        <p class="text-center">如果你对Lonely Robots团队，或对他们的产品感兴趣请联系我们</p>
                        <p class="text-center">请注明和说明你的来意</p>
                        <p class="text-center">©2015 Lonely Robots  联系方式：2410508062@qq.com </p>
                    </div>
                </div>
            </div>
        </div>
    </footer>		
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">About</h4>
				</div>
				<div class="modal-body">
					<h2><a href="/blog/toDetail.do?id=1000322" target="_blank">网站简介</a></h2>
					<hr>
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

	<div class="modal fade" id="loginModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="loginLabel">Login</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="login_name">Username</label> <input type="email"
							class="form-control" id="login_name" placeholder="Enter Username">
					</div>
					<div class="form-group">
						<label for="login_password">Password</label> <input
							type="password" class="form-control" id="login_password"
							placeholder="Password">
					</div>
					<div class="form-group" align="right">
						<label style="color: red" id="login_tag"></label>
					</div>
				</div>

				<div class="modal-footer">
					<button type="submit" class="btn btn-primary" id="login_submit">Login</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="regModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="regLabel">Register</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="reg_name">Username</label> <input type="email"
							class="form-control" id="reg_name" placeholder="Enter Username">
					</div>
					<div class="form-group">
						<label for="reg_name">NickName</label> <input type="text"
							class="form-control" id="nick_name" placeholder="Enter NickName">
					</div>
					<div class="form-group">
						<label for="reg_password">Password</label> <input type="password"
							class="form-control" id="reg_password" placeholder="Password">
					</div>
					<div class="form-group">
						<label for="reg_password_again">Password Again</label> <input
							type="password" class="form-control" id="reg_password_again"
							placeholder="Password Again">
					</div>
					<div class="form-group" align="right">
						<label style="color: red" id="reg_tag"></label>
					</div>

				</div>

				<div class="modal-footer">
					<button type="submit" class="btn btn-primary" id="reg_submit">Register</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="/js/jquery-2.1.3.js"></script>
	<script type="text/javascript" src="/js/bootstrap.js"></script>
	<script type="text/javascript" src="/js/common.js"></script>
</body>
</html>