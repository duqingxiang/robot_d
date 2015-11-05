<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="com.demo.util.*,com.demo.bean.Memorys,java.util.List" %>
<html lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport">
<meta name="description" content="">
<meta name="author" content="">
<title>Timeline</title>

<link href="/css/bootstrap.css" media="all" rel="stylesheet">
<link href="/css/timeline/font-awesome.min.css" media="all" rel="stylesheet">
<link href="/css/styles.css" media="all" rel="stylesheet">
<style>
body {
  background-color: #549EFE;
  padding: 5em 0; }
</style>

<%
	List<Memorys> memorysList = (List<Memorys>)request.getAttribute("memorysList");

%>

</head>
<body>
<nav class="navbar navbar-inverse  navbar-fixed-top">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Memorys</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
        <li><a href="#" data-toggle="modal" data-target="#myModal" >Link</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Dropdown <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li class="divider"></li>
            <li><a href="#">Separated link</a></li>
            <li class="divider"></li>
            <li><a href="#">One more separated link</a></li>
          </ul>
        </li>
      </ul>
<!--       <ul class="nav navbar-nav navbar-right">
        <li><a href="#">Link</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Dropdown <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li class="divider"></li>
            <li><a href="#">Separated link</a></li>
          </ul>
        </li>
      </ul> -->
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
<div class="container">
	<div class="jumbotron">
	  <h2>Just a Memorys for two People。。。</h2>
	</div>
</div>


<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Modal title</h4>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>




<div class="timeline animated">
	<div class="timeline-row active">
		<div class="timeline-time"><small>Oct 16</small>9:00 AM</div>
		<div class="timeline-icon">
			<div class="bg-info"><i class="fa fa-camera"></i></div>
		</div>
		<div class="panel timeline-content">
			<div class="panel-body">
				<h2>This is a Photo</h2>
				<img class="img-responsive" src="http://lonelyrobots.cn/domain/upload/robots.jpg">
				<p>Photo Test</p>
				<img class="img-responsive" src="http://lonelyrobots.cn/domain/upload/robots.jpg">
				<p>Photo Test</p>
			</div>
		</div>
	</div>
	
	<%
		if(memorysList != null && memorysList.size() > 0){
			int index = 0 ;
			for(Memorys mem : memorysList){
				String tagCss = "";
				String iCss = "";
				String activeCss = "";
				switch(mem.getType()){
					case 1 : tagCss = "bg-primary";iCss="fa-pencil";break;//文字
					case 2 : tagCss = "bg-info";iCss="fa-camera";break;//图片
					case 3 : tagCss = "bg-warning";iCss="fa-quote-right";break;//不知道是啥
					default :tagCss = "primary";break;//文字
				}
				if(index <=2){
					activeCss ="active";
				}
				String dateStr = DateUtil.dateUtil2String(mem.getCreatedDate(), "yyyy-MM-dd");
				%>
			<div class="timeline-row <%=activeCss %>">
				<div class="timeline-time"><%=dateStr %></div>
				<div class="timeline-icon">
					<div class="<%=tagCss %>"><i class="fa <%=iCss %>"></i></div>
				</div>
				<div class="panel timeline-content">
					<div class="panel-body">
						<h2><%=mem.getTag() %></h2>
						<%if(mem.getType()==2){%>
							<img class="img-responsive" src="<%=mem.getPhotoUrl()%>">
						<%}%>
						<p><%=mem.getContent() %></p>
					</div>
				</div>
			</div>
			
			<%}
		}
	%>
	
	
	<!-- 
	<div class="timeline-row active">
		<div class="timeline-time"><small>Oct 25</small>6:14 PM</div>
		<div class="timeline-icon">
			<div class="bg-warning"><i class="fa fa-quote-right"></i></div>
		</div>
		<div class="panel timeline-content">
			<div class="panel-body">
				<blockquote>
					<p>Lorem ipsum velit ullamco anim pariatur proident eu deserunt laborum. Lorem ipsum ad in nostrud adipisicing cupidatat anim officia ad id cupidatat veniam quis elit ullamco</p>
					<small>John Smith</small></blockquote>
			</div>
		</div>
	</div>
	-->
	
	
	
	
</div>

<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/timeline/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/timeline/main.js"></script>


</body></html>