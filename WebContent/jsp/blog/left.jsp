<%@page import="org.springframework.util.CollectionUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.demo.util.*,com.demo.bean.RobotUser,com.demo.bean.RobotBlog,com.demo.bean.RobotBlogTheme"%>
<div class="panel panel-primary"
	style="font-size: 16px; min-height: 200px;">
	<div style="padding-left: 20px;">
		<h3>主题列表</h3>
	</div>
	<table class="table table-hover">
		<tr>
			<td><a class="td_padding" href="/blog/index.do?t=-1">全部</a></td>
		</tr>
		<% if(user != null && user.isAuthor()) {%>
			<tr>
				<td><a class="td_padding" href="/blog/index.do?t=0">我的</a></td>
			</tr>
		<%} %>
		<% 
			List<RobotBlogTheme> themeList = (List<RobotBlogTheme> )request.getAttribute("themeList");
			if ( !CollectionUtils.isEmpty(themeList) ) {
				for (RobotBlogTheme theme :themeList) {%>
						<tr>
							<td><a class="td_padding" href="/blog/index.do?t=<%=theme.getId()%>"><%=theme.getTitle() %></a></td>
						</tr>
				<%}
			}
		%>
	</table>

</div>