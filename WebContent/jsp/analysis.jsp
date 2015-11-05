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
<body>
	<div class="row">
		<div class="col-md-1"></div>
		<div class="col-md-10">
		
			<p>各位萌萌哒吃货小主们，你们好～</p>
			<p>不知道大家喜欢什么样的零食呐~\(≧▽≦)/~ </p>
			<p>各位小主动动你可爱的小手，告诉伦家你最喜欢的是什么样的零食呀，分享会让零食更美味哦～</p>
			<form action="/submitAnalysis.do" method="post" id="contentForm">
				<div class="form-group">
			   		<label for="exampleInputEmail1">请在小框框里写一下哟~！</label>
			    	<textarea class="form-control" name="content" id="content" rows="3"></textarea>
			  	</div>
			  	<div class="form-group">
			   		<label for="phone">还有你的手机号呢~！</label>
			   		<br/>
			   		<label for="phone">填写手机号可能会随机发红包哦！~</label>
			    	<input type="text" class="form-control" name="phone" id="phone" placeholder="手机号" >
			  	</div>
			  	<div class="form-group text-center">
			  		<input type="button" onclick="submitForm()" class="btn btn-primary" value="提交">
			  	</div>
			</form>

		</div>
		<div class="col-md-1"></div>
	</div>

	<script type="text/javascript" src="/js/jquery-2.1.3.js"></script>
	<script type="text/javascript" src="/js/bootstrap.js"></script>
	<script type="text/javascript">
	
		function submitForm(){
			var c = $("#content").val();
			if (c == null || c == "" || c.trim().length <= 0) {
				alert("还没有填写内容哦！~");
				return ;
			}
			var p = $("#phone").val();
			if (p == null || p == "" || p.trim().length <= 0) {
				alert("还没有填写手机号哦！~");
				return ;
			}
			$("#contentForm").submit();
		}
	</script>
</body>
</html>