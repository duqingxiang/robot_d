<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.demo.util.*,com.demo.bean.RobotUser,com.demo.bean.RobotResource,com.demo.bean.RobotPosition,com.demo.bean.RobotPhotos"%>
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
body {
	/* background-image: linear-gradient(to right, #8DD4FE 0, #5A5499 135%); */
	/* background-image: url(/img/timeline/robots.jpg);  
	 background-repeat: repeat-y;
	 
	 background-size: 100% 106%;
	padding: 10em 0;
	  */
}
.red{
	color:red;
}
</style>
</head>
<body class="">
	<h3>坐标管理</h2>
	<%
	
		List<RobotPosition> list =(List<RobotPosition>) request.getAttribute("positions") ;
	
	%>
	<table class="table table-hover">
  		<thead>
  			<tr>
  				<th></th>
  				<th>Id</th>
  				<th>Type</th>
  				<th>X</th>
  				<th>Y</th>
  				<th>Tag</th>
  				<th></th>
  			</tr>
  			<% if (list != null && list.size() > 0) {
  				for (RobotPosition pos : list){
  			%>
  				<tr>
  					<td><img style="width: 100px;" src="<%=pos.getImgPath()%>"/></td>
  					<td><%=pos.getId() %></td>
  					<td><%=pos.getType() %></td>
  					<td><%=pos.getPositionX() %></td>
  					<td><%=pos.getPositionY() %></td>
  					<td><%=pos.getTags() %></td>
  					<td>
  						<a type="button" pos-id="<%=pos.getId()%>" class="deletePosition btn btn-link btn-lg" href="#" >Delete</a>
  						
  						<a type="button" class="editPositionBtn btn btn-link btn-lg" href="#" data-toggle="modal"
										data-target="#positionModal" pos-id="<%=pos.getId() %>" role="button">Edit Image</a>
						
						<a type="button" class="btn btn-link btn-lg" href="/photoManager.do?posid=<%=pos.getId()%>">To Photos</a>
  					</td>
  				</tr>
  			
  			<%}}%>
  		</thead>
  		
	</table>
	
	</ul>
										
	<div class="modal fade" id="positionModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="loginLabel">Resource List</h4>
				</div>
				<div class="modal-body">
					<input type="hidden" id="position_id" />
					<table class="table">
						<thead>
							<tr>
								<td>#</td>
								<td>Img</td>
							</tr>
						</thead>
						<tbody>
							<%
								List<RobotResource> resources = (List<RobotResource>)request.getAttribute("resources");
								if (resources != null && resources.size() > 0){
									for (RobotResource res : resources) {%>
									
									<tr>
										<td><input type="radio" name="resourceRadio" value="<%=res.getId()%>"/></td>
										<td><%if(!StringUtil.isEmpty(res.getMiniPath())){%><img style="width:25px;" src="<%=res.getMiniPath()%> " /><%}%></td>
									</tr>
									
								<%}}%>
						</tbody>
					</table>
				</div>

				<div class="modal-footer">
					<button type="submit" class="btn btn-primary" id="edit_position_img">Add</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	
</body>
<script type="text/javascript" src="/js/jquery-2.1.3.js"></script>
<script type="text/javascript" src="/js/bootstrap.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript">
	$(function() {
		
		$(".editPositionBtn").click(function(){
	    	var id = $(this).attr("pos-id");
	    	$("#position_id").val(id);
	    });
		
		$("#edit_position_img").click(function(){
			
			var resource_id = $('input[name="resourceRadio"]:checked').val();
			var position_id = $("#position_id").val();
			
			var url = "/editPositions.do";
	    	var param={resource_id:resource_id,position_id:position_id};
	    	jQuery.post(url,param,function(data){

	    		if(data == -1){
	    			alert("未登录！！");
	    		}else if(data == -2){
	    			alert("参数异常！");
	    		}else if(data == -3){
	    			alert("资源异常！");
	    		}else if(data == -4){
	    			alert("坐标异常！");
	    		}else if(data == -5){
	    			alert("系统异常！");
	    		}else if(data == 1){
	    			window.location.href=window.location.href;
	    		}
	    	});
			
		});
		
		
		$(".deletePosition").click(function(){
			var id = $(this).attr("pos-id");
			var url = "/deletePosition.do";
			var param = {id:id};
			jQuery.post(url,param,function(data){
				if(data == -1){
	    			alert("未登录！！");
	    		}else if(data == -2){
	    			alert("参数异常！");
	    		}else if(data == -3){
	    			alert("坐标异常！");
	    		}else if(data == -4){
	    			alert("系统异常！");
	    		}else if(data == 1){
	    			alert("成功！");
	    			refush();
	    		}
			});
			
		});
		
		
		
	});
	
	
</script>
</html>