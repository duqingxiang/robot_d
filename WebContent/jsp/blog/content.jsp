<%@page import="org.springframework.util.CollectionUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="com.demo.util.*,java.util.*,com.demo.bean.RobotUser,com.demo.bean.RobotBlog,com.demo.bean.RobotBlogTheme,com.demo.bean.RobotBlogCommont"%>
<html lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"
	name="viewport">
<meta name="description" content="">
<meta name="author" content="">
<%
	RobotUser user = (RobotUser) request.getAttribute("user");
%>
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
<body class="">
	<div class="wrap">

		<div class="header"
			style="background-image: url(/img/timeline/robots.jpg); background-repeat: repeat-y;">

			<div class="container">
				<div class="row">
					<div class="col-xs-12">
						<div class="logotxt">
							<h1>
								<a href="">This is My Blog</a>
							</h1>
						</div>
						<h2 class="text-center">
							<p>
								<a class="btn btn-warning btn-lg" href="/blog/index.do" role="button">返回</a>
							</p>
						</h2>
					</div>
				</div>
			</div>
		</div>
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-12">
					<% 
						RobotBlog blog = (RobotBlog)request.getAttribute("blog");
					%>
					<div class="panel panel-primary">
						<div class="panel-body">
							<div class="text-center"><h2><%=blog.getTitle() %></h2></div>
							<div class="text-center"><span>【作者:<%=blog.getNickName() %>】【日期:<%=DateUtil.dateUtil2String(blog.getCreatedDate(), "yyyy-MM-dd HH:mm:ss") %>】</span></div>
							<br>
							<div class="text-center"><%=StringUtil.isEmpty(blog.getShortContent()) ?"没有简述哦~~~" :blog.getShortContent() %></div>
							<br>
							<p><%=StringUtil.isEmpty(blog.getContent()) ?"" :blog.getContent() %></p>
						</div>
					</div>
					<% List<RobotBlogCommont> commontList = (List<RobotBlogCommont>)request.getAttribute("commontList");
					   if(CollectionUtils.isEmpty(commontList)){%>
						   <div class="panel panel-primary">
								<div class="panel-heading">@_@</div>
								<div class="panel-body">
									还没有评论哦~~
								</div>
							</div>
					<%} else {
						int index =1;
						for(RobotBlogCommont commont : commontList) {%>
						<div class="panel panel-primary">
								<div class="panel-heading">第<%=index %>名：<%=commont.getNickName() %>
									<a onclick="showInput(<%=commont.getUserId() %>)" style="color:#fff;margin-left:25px;">回复Ta</a>
									<%if (user != null && (user.isSuper() || user.getUserId() == commont.getUserId())) {%>
										<a onclick="deleteCommont(<%=commont.getId() %>)" style="color:#fff;margin-left:25px;">删除</a>
									<%} %>
								</div>
								<div class="panel-body">
									<%=commont.getContent() %>
								</div>
							</div>	
					<%	index++;}
					%>
					
					<%}%>
					<div class="text-center" style="background-color:#337ab7"><h2><a onclick="showInput(0)" style="color:#fff;">~~! 俺要评论！~~</a></h2></div>
					<br>
					<div class="panel panel-primary" id="commont_input" style="display:none;">
						<div class="panel-heading">评论</div>
						<div class="panel-body">
						<input type="hidden" id="commont_blog_id" value="<%=blog.getId() %>">
						<input type="hidden" id="commont_blog_repty_id" value="" />
							<div>
								<script id="editor" type="text/plain" style=" height: 250px;"></script>
							</div>
							<div style="float:right;">
								<button type="button" class="btn btn-success" id="commontSave">保存</button>
							    <button type="button" class="btn btn-default" onclick="closeInput()">关闭</button>
							</div>
						</div>
					</div>
					
				</div>
			</div>
		</div>

	</div>
	<%@ include file="../footer.jsp" %>
	<script type="text/javascript" src="/js/jquery-2.1.3.js"></script>
	<script type="text/javascript" src="/js/bootstrap.js"></script>
	<script type="text/javascript" src="/js/blog/blog.js"></script>
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" charset="utf-8" src="/js/ueditor/ueditor.config.blog.js?ran=<%=System.currentTimeMillis()%>"></script>
	<script type="text/javascript" charset="utf-8" src="/js/ueditor/ueditor.all.min.js"></script>
	<script type="text/javascript" charset="utf-8" src="/js/ueditor/zh-cn.js"></script>
	<script>
		var ue = UE.getEditor('editor');
	</script>
	
	
</body>
</html>