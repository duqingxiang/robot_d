<%@page import="org.springframework.util.CollectionUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.demo.util.*,com.demo.bean.SqlType"%>
<html lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"
	name="viewport">
<meta name="description" content="">
<meta name="author" content="">

<title>Lonely Robots-在线生成建表SQL</title>

<link href="/css/bootstrap.css" media="all" rel="stylesheet">
<!-- <link href="/css/site.css" media="all" rel="stylesheet"> -->
<style>
.red{
	color:red;
}
</style>
</head>
<body class="">

		<div class="container">
			<div class="row">
				<div class="page-header">
				  <h1>在线生成建表SQL</h1>
				</div>
				<div class="col-xs-12">
					<div class="panel panel-primary">
						<!-- Default panel contents -->
						<div class="panel-heading">已添加字段</div>
						<div class="panel-body">
							<p>表名:<input type="text" id="table_name" /></p>
							<table class="table table-hover">
								<tr>
									<th>名称</th>
									<th>类型</th>
									<th>长度</th>
									<th>小数位</th>
									<th>是否为空</th>
									<th>默认值</th>
									<th>自增</th>
									<th>无符号</th>
									<th>补位</th>
									<th>主键</th>
									<th>备注</th>
									<th style="width: 10%;">操作</th>
								</tr>
								<tbody id="field_list">
									
								</tbody>
							</table>
							<div class="text-right">
								<a href="javascript:void(1)" class="btn btn-primary create_table">创建</a>
							</div>
							<div id="show_sql_tigs" class="alert alert-success">
								根据SQL规则，在生成时可能会将部分字段的属性去掉，不会在生成的SQL中出现。
							</div>
							<div id="show_sql">
							
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-xs-12">
					<div class="panel panel-primary">
						<!-- Default panel contents -->
						<div class="panel-heading">字段信息</div>
						<div class="panel-body">
							<div class="form-horizontal">
								<div class="form-group">
									<label for="field_name" class="col-sm-2 control-label">名称</label>
									<div class="col-sm-8">
										<input type="text" class="form-control" id="field_name"
											placeholder="名称">
									</div>
								</div>
								<div class="form-group">
									<label for="inputPassword3" class="col-sm-2 control-label">类型</label>
									<div class="col-sm-8">
										<select class="form-control" id="field_type">
											<option value="-1">请选择</option>
											<%
												List<SqlType> typeList = (List<SqlType>)request.getAttribute("typeList");
												if (!CollectionUtils.isEmpty(typeList)) {
													for (SqlType type : typeList) {%>
												
													<option value="<%=type.getId()%>"><%=type.getTigs() %></option>	
											<%	}} %>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="length" class="col-sm-2 control-label">长度</label>
									<div class="col-sm-8">
										<input type="text" class="form-control" id="field_length"
											placeholder="长度">
									</div>
								</div>
								<div class="form-group">
									<label for="decimals" class="col-sm-2 control-label">小数位</label>
									<div class="col-sm-8">
										<input type="text" class="form-control" id="field_decimals"
											placeholder="小数位">
									</div>
								</div>
								<div class="form-group">
									<label for="length" class="col-sm-2 control-label">默认值</label>
									<div class="col-sm-8">
										<input type="text" class="form-control" id="field_default"
											placeholder="默认值">
									</div>
								</div>
								<div class="form-group">
									<label for="length" class="col-sm-2 control-label">备注</label>
									<div class="col-sm-8">
										<input type="text" class="form-control" id="field_comment"
											placeholder="备注">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-8">
										<div class="checkbox">
											<label> <input type="checkbox" id="field_key">主键</label>
											<label> <input type="checkbox" id="field_allow_null">允许为空</label>
											<label> <input type="checkbox" id="field_auto_increment">自增</label>
											<label> <input type="checkbox" id="field_unsigned">无符号</label>
											<label> <input type="checkbox" id="field_zerofill">补位</label>
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-8">
										<input type="hidden" id="rowIndex" value=""/>
										<button type="button" class="btn btn-primary field_add">增加</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			
		</div>
		
	
		
	

	<script type="text/javascript" src="/js/jquery-2.1.3.js"></script>
	<script type="text/javascript" src="/js/bootstrap.js"></script>
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/js/tool/sql.js"></script>
</body>
</html>