<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.demo.util.*,com.demo.bean.RobotUser,com.demo.bean.RobotBlog,com.demo.bean.RobotBlogTheme"%>
<html lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"
	name="viewport">
<meta name="description" content="">
<meta name="author" content="">
<%
	RobotUser user = (RobotUser) request.getAttribute("user") ;
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

		<%@ include file="banner.jsp"%>
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-3">
					<%@ include file="left.jsp"%>
				</div>
				<div class="col-md-9">
					<% if (user != null &&( user.isAuthor() || user.getIsSuper() == 1 )){%>
						<div class="panel panel-primary" id="editPanel" style="display:none;">
							<div class="panel-body">
								<div class="form-inline">
									<div class="form-group">
										<label for="blogTitle">标题:</label> 
										<input type="text" class="form-control" id="blogTitle" placeholder="Title" style="width:350px;">
									</div>
									
									<div class="form-group">
										<label for="blogTheme">主题</label>
										<select class="form-control" id="blogTheme" style="width:200px;">
										<% 
											List<RobotBlogTheme> themeMainList = (List<RobotBlogTheme> )request.getAttribute("themeList");
											if ( !CollectionUtils.isEmpty(themeMainList) ) {
												for (RobotBlogTheme theme :themeMainList) {%>
														<option value="<%=theme.getId()%>"><%=theme.getTitle() %></option>
												<%}
											}
										%>	
										</select>
									</div>
									<div class="form-group">
										<label for="blogTheme">状态</label>
										<select class="form-control" id="blogType" style="width:200px;">
											<option value="0">公开</option>
											<option value="1">私有</option>									
										</select>
									</div>
								</div>
								<br>
								<div class="form-inline">
									<div class="form-group">
										<label for="blogShort">简述:</label> 
										<input type="text" class="form-control" id="blogShort" placeholder="Title" style="width:825px;">
									</div>
								</div>
								<br>
								<div>
									<script id="editor" type="text/plain" style=" height: 250px;"></script>
								</div>
								
								<div class="text-right">
									<button type="button" class="btn btn-primary" id="blogView">预览</button>
									<button type="button" class="btn btn-success" id="blogSave">保存</button>
									<button type="button" class="btn btn-default" id="blogClose">关闭</button>
								</div>
							</div>
						</div>
					<%} %>
					
					<% 
						List<RobotBlog> blogList =(List<RobotBlog>) request.getAttribute("blogList");
						if (!CollectionUtils.isEmpty(blogList)){
							for (RobotBlog blog : blogList) {%>
								<div class="panel <%=StringUtil.isEmpty(blog.getStyle())?"panel-primary" :blog.getStyle()%>">
									<div class="panel-body">
										<span><%=DateUtil.dateUtil2String(blog.getCreatedDate(), "yyyy-MM-dd HH:mm:ss") %></span>
										<% if (user != null && user.isAuthor() &&( user.getUserId() == blog.getAuthorId() || user.getIsSuper() == 1)){%>
											<a href="javascript:void(0);" onclick="javascript:deleteBlog(<%=blog.getId() %>);"><span style="float: right;" class="text-right glyphicon glyphicon-remove" aria-hidden="true">delete</span></a>
											<a href="/blog/toUpdate.do?id=<%=blog.getId() %>"><span style="float: right;padding-right: 20px;" class="text-right glyphicon glyphicon-pencil" aria-hidden="true">edit</span></a>
										<%} %>
										
										<a href="/blog/toDetail.do?id=<%=blog.getId()%>"><h3><%=blog.getTitle() %></h3></a>
										<p>【作者:<%=blog.getNickName() %>】</p>
										<p><%=StringUtil.isEmpty(blog.getShortContent()) ?"没有简述哦~~~" :blog.getShortContent() %></p>
									</div>
								</div>
							<%}
						}
					%>
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
		</div>

	</div>
	<%@ include file="../footer.jsp" %>
	<script type="text/javascript" src="/js/jquery-2.1.3.js"></script>
	<script type="text/javascript" src="/js/bootstrap.js"></script>
	<script type="text/javascript" charset="utf-8" src="/js/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="/js/ueditor/ueditor.all.min.js"></script>
	<script type="text/javascript" charset="utf-8" src="/js/ueditor/zh-cn.js"></script>
	<script type="text/javascript" src="/js/blog/blog.js"></script>
	<script type="text/javascript" src="/js/common.js"></script>
	<script>
	<% if (user != null && (user.isAuthor() || user.getIsSuper() == 1)) {%>
		var ue = UE.getEditor('editor');
	<%}%>
	</script>
</body>
</html>