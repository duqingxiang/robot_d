<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="com.demo.util.*" %>
<html lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport">
<meta name="description" content="">
<meta name="author" content="">
<title>Lonely Robots</title>

<link href="/css/bootstrap.css" media="all" rel="stylesheet">
<link href="/css/timeline/font-awesome.min.css" media="all" rel="stylesheet">
<style>
body {
	/* background-image: linear-gradient(to right, #8DD4FE 0, #5A5499 135%); */
	background-image:url("/img/bg4.jpg");
	background-repeat:round;
	padding: 5em 0; }
</style>
</head>
<body>
<%
	String forward = request.getParameter("forward");
%>
<input type="hidden" id="forwardUrl" value="<%=forward%>"/>
<div class="container">
	<div class="row">
		<div class="col-md-4"></div>
	  	<div class="col-md-4">
	  	<br>
		<br>
		<br>
		<div id="login_div" class="loginClass">
				<h1>猜红包...</h1>
			  <div class="form-group">
			    <label for="hongbao_url">URL</label>
			    <input type="text" class="form-control" id="hongbao_url" placeholder="Enter url">
			  </div>
			  <div class="form-group">
			    <label for="hongbao_phone">PHONE</label>
			    <input type="text" class="form-control" id="hongbao_phone" placeholder="phone">
			  </div>
			  <div class="form-group">
			    <label for="hongbao_max">MAX</label>
			    <input type="text" class="form-control" id="hongbao_max" placeholder="max">
			  </div>
			  <div class="form-group" align="right">
				<label style="color:red" id="hongbao_tag"></label>
			  </div>
			  <div class="form-group" align="right">
			  	<button type="submit" class="btn btn-primary" id="hongbao_sub">领取</button>
			  </div>
		</div>
			
		</div>
		<div class="col-md-4"></div>
	</div>
</div>


<script type="text/javascript" src="/js/timeline/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/js/timeline/bootstrap.min.js"></script>
<script>
	$("#hongbao_sub").click(function(){
		
		var url = $("#hongbao_url").val();
		var phone = $("#hongbao_phone").val();
		var max = $("#hongbao_max").val();
		
		var u = "/caihongbao.do";
		var param = {url:url,phone:phone,max:max};
		$.post(u,param,function(data){
			var res=eval("("+data+")");
			if (res != null) {
				var code = res.code;
				if (code == 1) {
					alertTag("成功！    "+res.message);
				} else if (code == 2) {
					alertTag("失败！    "+res.message);
				}else if (code == -1) {
					alertTag("url为空！");
				} else if (code == -2) {
					alertTag("phone为空!");
				} else if (code == -3) {
					alertTag("max为空");
				} else {
					alertTag("领取失败");
				}
			}
			
		});
	});
	function alertTag(str){
		$("#hongbao_tag").html(str);
	}

</script>

</body></html>