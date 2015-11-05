<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.demo.util.*,com.demo.bean.RobotUser,com.demo.bean.RobotBlog,com.demo.bean.RobotBlogTheme"%>
<%@page import="org.springframework.util.CollectionUtils"%>
<div class="header"	style="background-image: url(/img/timeline/robots.jpg); background-repeat: repeat-y;">

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
						<a class="btn btn-warning btn-lg" href="/" role="button">首页</a>
					</p>
				</h2>
			</div>
		</div>
	</div>
</div>

<nav class="navbar navbar-default">
	<div class="container-fluid">
		<ul class="nav navbar-nav navbar-left">
			<li>
				<div class="navbar-form navbar-left">
				<% if (user != null &&( user.isAuthor() || user.getIsSuper() == 1 )){%>
					<button type="button" class="btn btn-primary" id="publisBlog">发布文章</button>
					<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#themeDialog">编辑主题</button>
				<%} %>
				</div>
			</li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li>
				
			</li>
			<li>
				<form class="navbar-form navbar-left" role="search" method="post" action="/blog/index.do">
					<div class="form-group">
						<select name="t" class="form-control">
						<%
							String key = request.getAttribute("search_k") != null ?request.getAttribute("search_k").toString() :"";
							long search_t = request.getAttribute("search_t") != null ?StringUtil.toLong(request.getAttribute("search_t").toString(), -1L):-1;
							int nowPage = request.getAttribute("page") != null ? StringUtil.toInteger(request.getAttribute("page").toString(), 0) : 0;
							String beforeUrl="/blog/index.do?t="+search_t+"&s="+key+"&p="+(nowPage-1);
							String nextUrl="/blog/index.do?t="+search_t+"&s="+key+"&p="+(nowPage+1);
						%>
							<option <%if(search_t == -1){%>selected=selected<%} %> value="-1">全部</option>
							<% if(user != null && user.isAuthor()) {%>
									<option <%if(search_t == 0){%>selected=selected<%} %> value="0">我的</option>
							<%} %>
							<% 
								
								List<RobotBlogTheme> themeList1 = (List<RobotBlogTheme> )request.getAttribute("themeList");
								if ( !CollectionUtils.isEmpty(themeList1) ) {
									for (RobotBlogTheme theme :themeList1) {%>
											<option <%if(search_t == theme.getId()){%>selected=selected<%} %> value="<%=theme.getId()%>"><%=theme.getTitle() %></option>
									<%}
								}
							%>
						</select> <input type="text" class="form-control" name="s" placeholder="Search" value="<%=key%>">
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
			</li>
			<li class="dropdown">
				<% 
					String userNickName = "游客";
					if (user != null) {
						userNickName = user.getNickName();
					}
				%>
				<a href="#" class="dropdown-toggle"
				data-toggle="dropdown" role="button" aria-haspopup="true"
				aria-expanded="false"><%=userNickName %><span class="caret"></span></a>
				
				<% if(user != null) {%>
					<ul class="dropdown-menu">
						<!-- <li><a href="#">编辑Blog</a></li>
						<li><a href="#">编辑主题</a></li>
						<li><a href="#">Something else here</a></li>
						<li role="separator" class="divider"></li> -->
						<li><a href="#" id="logout_btn">退出</a></li>
					</ul>
				<%} else {%>
					<ul class="dropdown-menu">
						<li><a href="/about.do?forward=<%=StringUtil.UrlEncoder("/blog/index.do")%>">登录</a></li>
					</ul>
				<%}%>
				</li>
		</ul>
	</div>
</nav>


<% if (user != null) {%>
	<div class="modal fade" id="themeDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="themeDialogLabel">主题编辑</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="theme_title">标签:</label> 
						<input type="text" class="form-control" id="theme_title" placeholder="标签">
					</div>
					<div class="form-group">
						<label for="theme_type">模式</label> 
						<select class="form-control" id="theme_type">
							<option value="1">私有</option>
							<option value="0">公开</option>
						</select>
					</div>
					<div class="form-group">
						<label for="theme_style">样式</label> 
						<select class="form-control" id="theme_style">
							<option value="1">深蓝</option>
							<option value="2">绿色</option>
							<option value="3">天蓝</option>
							<option value="4">橘黄</option>
							<option value="5">红色</option>
						</select>
					</div>
					<div class="form-group" align="right">
						<label style="color: red" id="theme_tag"></label>
					</div>
					<div class="form-group" align="right">
						<button type="submit" class="btn btn-primary" id="theme_add">添加</button>
					</div>
					<div style="height: 170px;overflow: auto;">
						<table class="table table-hover">
							<tr>
								<th>#</th>
								<th>标签</th>
								<th>模式</th>
								<th>样式</th>
								<th>操作</th>
							</tr>
							
							<% List<RobotBlogTheme> themeShows = (List<RobotBlogTheme>)request.getAttribute("privateThemeList");
							   if (user.getIsSuper() == 1)
								   themeShows = (List<RobotBlogTheme>)request.getAttribute("themeList");
							   if (!CollectionUtils.isEmpty(themeShows)) {
								  	int count = 1 ;
								   	for (RobotBlogTheme theme : themeShows) {
								  		String style = "default";
								  		if (!StringUtil.isEmpty(theme.getStyle()) && theme.getStyle().indexOf("-") >-1)
								  			style = theme.getStyle().substring(theme.getStyle().indexOf("-")+1);
								  %>
								  
								  <tr>
								  	<td><%=count %></td>
								  	<td><%=theme.getTitle() %></td>
								  	<td><%=theme.getType()== 1 ?"私有" :"公开" %></td>
								  	<td><button type="button" class="btn btn-<%=style%>">&nbsp;&nbsp;</button></td>
								  	<td>
								  		<!-- <button type="button" class="btn btn-success">修改</button> -->
								  		<button type="button" class="btn btn-danger" onclick="deleteTheme(<%= theme.getId() %>)">删除</button>
								  	</td>
								  </tr>
								  
							 <%}}%>
							
							
						</table>
					</div>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>	
<%}%>