<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.demo.util.*,com.demo.bean.RobotUser,com.demo.bean.RobotResource,com.demo.bean.RobotPosition"%>
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
<link rel="stylesheet" type="text/css" href="/css/uploadify/uploadify.css">
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
	<h3>资源、坐标管理</h2>
	<input type="file" name="file_upload" id="file_upload"/>

	<%
	
		List<RobotResource> list =(List<RobotResource>) request.getAttribute("resources") ;
	
	%>
	<table class="table table-hover">
  		<thead>
  			<tr>
  				<th></th>
  				<th>类型</th>
  				<th>实际地址</th>
  				<th>网络地址</th>
  				<th>操作</th>
  			</tr>
  			<% if (list != null && list.size() > 0) {
  				for (RobotResource res : list){
  			%>
  				<tr>
  					<td><img style="width: 100px;" src="<%=res.getMiniPath()%>"/></td>
  					<td><%=res.getType() %></td>
  					<td><%=res.getRealPath() %></td>
  					<td><%=res.getNetPath() %></td>
  					<td><a type="button" res-id ="<%=res.getId() %>" class="deleteResourceBtn btn btn-link btn-lg" href="#">Delete</a></td>
  				</tr>
  			
  			<%}}%>
  		</thead>
  		<% 
  		int nowPage = request.getAttribute("page") != null ? StringUtil.toInteger(request.getAttribute("page").toString(), 0) : 0;
		String beforeUrl="/uploadManager.do?page="+(nowPage-1);
		String nextUrl="/uploadManager.do?page="+(nowPage+1);
  		%>
	</table>
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
</body>
<script type="text/javascript" src="/js/jquery-2.1.3.js"></script>
<script type="text/javascript" src="/js/bootstrap.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<script type="text/javascript" src="/js/uploadify/jquery.uploadify.js"></script>
<script type="text/javascript">
               
$(function() {
	
	 $("#file_upload").uploadify({
	        //开启调试
	        'debug' : false,
	        //是否自动上传
	        'auto':true,
	        //超时时间
	        'successTimeout':99999,
	        //附带值
	        'formData':{
	            'userid':'用户id',
	            'username':'用户名',
	            'rnd':'加密密文'
	        },
	        //flash
	        'swf': "/js/uploadify/uploadify.swf",
	        //不执行默认的onSelect事件
	        'overrideEvents' : ['onDialogClose'],
	        //文件选择后的容器ID
	        'queueID':'uploadfileQueue',
	        //服务器端脚本使用的文件对象的名称 $_FILES个['upload']
	        'fileObjName':'upload',
	        //上传处理程序
	        'uploader':'/uploadFile.do',
	        //浏览按钮的背景图片路径
	        //'buttonImage':'upbutton.gif',
	        //浏览按钮的宽度
	        'width':'100',
	        //浏览按钮的高度
	        'height':'32',
	        
	        //在浏览窗口底部的文件类型下拉菜单中显示的文本
	        'fileTypeDesc':'支持的格式：',
	        //允许上传的文件后缀
	        'fileTypeExts':'*.*',
	        //上传文件的大小限制
	        'fileSizeLimit':'3MB',
	        //上传数量
	        'queueSizeLimit' : 25,
	        //每次更新上载的文件的进展
	        'onUploadProgress' : function(file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) {
	             //有时候上传进度什么想自己个性化控制，可以利用这个方法
	             //使用方法见官方说明
	        },
	        //选择上传文件后调用
	        'onSelect' : function(file) {
	                  
	        },
	        //返回一个错误，选择文件的时候触发
	        'onSelectError':function(file, errorCode, errorMsg){
	            switch(errorCode) {
	                case -100:
	                    alert("上传的文件数量已经超出系统限制的"+$('#file_upload').uploadify('settings','queueSizeLimit')+"个文件！");
	                    break;
	                case -110:
	                    alert("文件 ["+file.name+"] 大小超出系统限制的"+$('#file_upload').uploadify('settings','fileSizeLimit')+"大小！");
	                    break;
	                case -120:
	                    alert("文件 ["+file.name+"] 大小异常！");
	                    break;
	                case -130:
	                    alert("文件 ["+file.name+"] 类型不正确！");
	                    break;
	            }
	        },
	        //检测FLASH失败调用
	        'onFallback':function(){
	            alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");
	        },
	        //上传到服务器，服务器返回相应信息到data里
	        'onUploadSuccess':function(file, data, response){
	            if (data == null)
	            	alert("System error...");
	            var result = eval('(' + data + ')');
	            
	            alert(result.name+"上传完成！");
	            
	        }
	    });
	 
	 
	    
	    $(".deleteResourceBtn").click(function(){
	    	var id = $(this).attr("res-id");
	    	
	    	var url = "/deleteFile.do";
	    	var param={id:id};
	    	jQuery.post(url,param,function(data){

	    		if(data == -1){
	    			alert("未登录！！");
	    		}else if(data == -2){
	    			alert("参数异常！");
	    		}else if(data == -3){
	    			alert("资源异常！");
	    		}else if(data == -4 || data == -5){
	    			alert("资源文件异常！");
	    		}else if(data == -6){
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