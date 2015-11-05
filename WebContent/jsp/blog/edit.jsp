<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.demo.util.*,com.demo.bean.RobotUser,com.demo.bean.RobotBlog,com.demo.bean.RobotBlogTheme"%>
<%@page import="org.springframework.util.CollectionUtils"%>
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

.td_padding {
	padding-left: 20px;
}
</style>
</head>
<body class="">
	<div class="wrap">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-12">
					<% 
						RobotBlog blog = (RobotBlog)request.getAttribute("blog");
						boolean errorFlag = request.getAttribute("errorFlag") != null? (Boolean)request.getAttribute("errorFlag") :true;
					%>
					<div class="panel panel-primary">
						<div class="panel-body">
							<div class="text-center"><h2 id="viewTitle"><%=blog.getTitle() %></h2></div>
							<div class="text-center"><span>【作者:<%=blog.getNickName() %>】【日期:<%=DateUtil.dateUtil2String(blog.getCreatedDate(), "yyyy-MM-dd HH:mm:ss") %>】</span></div>
							<br>
							<div class="text-center" id="viewShort"><%=StringUtil.isEmpty(blog.getShortContent()) ?"没有简述哦~~~" :blog.getShortContent() %></div>
							<br>
							<div id="viewContent">
								<%=StringUtil.isEmpty(blog.getContent()) ?"" :blog.getContent() %>
							</div>
							
							<div class="text-center" >
								<button type="button" class="btn btn-primary" id="updateBtn">编辑</button>
								<a type="button" class="btn btn-default" href="/blog/index.do">返回</a>
							</div>
						</div>
					</div>
					<input type="hidden" id="contentTemp" value="<%=blog.getSimpleContent() %>" />
					<input type="hidden" id="updateBlogId" value="<%=blog.getId() %>" />
					<% if (!errorFlag) {%>
						<div class="panel panel-primary" id="editPanel" style="display:none;">
							<div class="panel-class">
							<br/>
								<div class="form-inline">
										<div class="form-group">
											<label for="updateBlogTitle">标题:</label> 
											<input type="text" class="form-control" id="updateBlogTitle" placeholder="Title" value="<%=blog.getTitle() %>" style="width:350px;">
										</div>
										
										<div class="form-group">
											<label for="updateBlogTheme">主题</label>
											<select class="form-control" id="updateBlogTheme" style="width:200px;">
											<% 
												List<RobotBlogTheme> themeMainList = (List<RobotBlogTheme> )request.getAttribute("themeList");
												if ( !CollectionUtils.isEmpty(themeMainList) ) {
													for (RobotBlogTheme theme :themeMainList) {%>
															<option <%if(theme.getId() == blog.getThemeId()){%>selected=selected<%} %> value="<%=theme.getId() %>"><%=theme.getTitle() %></option>
													<%}
												}
											%>	
											</select>
										</div>
										<div class="form-group">
											<label for="updateBlogType">状态</label>
											<select class="form-control" id="updateBlogType" style="width:200px;">
												<option <%if(blog.getType() == 0){%>selected=selected<%} %> value="0">公开</option>
												<option <%if(blog.getType() == 1){%>selected=selected<%} %> value="1">私有</option>									
											</select>
										</div>
									</div>
									<br>
									<div class="form-inline">
										<div class="form-group">
											<label for="updateBlogShort">简述:</label> 
											<input type="text" class="form-control" id="updateBlogShort" placeholder="Title" value="<%=blog.getShortContent() %>" style="width:825px;">
										</div>
										<div class="form-group">
											<button type="button" class="btn btn-primary" id="updateBlogView">预览</button>
											<button type="button" class="btn btn-success" id="updateBlogSave">保存</button>
											<a type="button" class="btn btn-default" href="/blog/index.do">返回</a>
										</div>
									</div>
									<br/>
									<div>
										<script id="editor" type="text/plain" style=" height: 250px;"></script>
									</div>
							</div>
						</div>
					<%} %>
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
	<% if (!errorFlag) {%>
		var ue = UE.getEditor('editor');
	<%}%>
	</script>
</body>
</html>