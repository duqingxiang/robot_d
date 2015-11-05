<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="com.demo.util.*,com.demo.bean.Memorys,java.util.List" %>
<html lang="en" >
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport">
<meta name="description" content="">
<meta name="author" content="">
<title>Timeline</title>

<link href="/css/bootstrap.css" media="all" rel="stylesheet">
<link href="/css/timeline/font-awesome.min.css" media="all" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/css/water/default.css">

<!--必要样式-->
<link rel="stylesheet" type="text/css" href="/css/water/component.css">
<script src="/js/water/modernizr.custom.js"></script>

<style>
body{
	background-image: linear-gradient(to right, #8DD4FE 0, #5A5499 135%);
}
.slide{
		    
		    box-shadow: 0px 10px 30px 0px rgba(0,0,0,0.3);
		    border:12px white solid;
		    box-sizing:border-box;
		    border-radius:5px; 
		}
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
<br>
<div class="container">
	<ul class="grid effect-1" id="grid" style="position: relative; height: 9771.5px;">
		
		
		<%
		if(memorysList != null && memorysList.size() > 0){
			int index = 0 ;
			for(Memorys mem : memorysList){
				String srcStr = "real_src";
				if(index <=5){
					srcStr ="src";
				}
				%>
				<li class="shown" style="position: absolute;"><div class="slide"><img <%=srcStr%>="<%=mem.getPhotoUrl()%>"></div></li>
			
			<%}
		}
	%>
	</ul>
</div>


<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/timeline/bootstrap.min.js"></script>

<script src="/js/water/masonry.pkgd.min.js"></script>
<script src="/js/water/imagesloaded.js"></script>
<script src="/js/water/classie.js"></script>
<script src="/js/water/AnimOnScroll.js"></script>
<script type="text/javascript">
	new AnimOnScroll( document.getElementById( 'grid' ), {
		minDuration : 0.4,
		maxDuration : 0.7,
		viewportFactor : 0.2
	} );
	(function ($) {
	    $.fn.Y_lazyload = function (options) {
	    /**
	    *   option 的默认属性说明
	    *
	    *   event           //加载img的触发事件
	    *   img             //随后加载 img 的dom
	    *   real_src        //要加载真实 src 使用到的自定义属性(attr)
	    *   animate         //动画效果
	    *   animate_delay   //动画等待时间，等待图片下载完成
	    *   animate_time    //动画执行时间
	    *   time_out        //设置延迟加载，有时IE下如果太快会有个别图片无反应的情况，设置此延时就可以解决
	    **/
	    var defaults = {
	        event : 'scroll',
	        img : 'img[real_src]',
	        real_src : 'real_src',
	        animate : "",
	        animate_delay : 500,
	        animate_time : 1000,
	        time_out : 0
	    }; 
	    var options = $.extend(defaults, options); 
	    return this.each(function(){
	        //这里写自己的方法
	        var client_h = 0;   //浏览器可视区域高
	        if (window.innerHeight) 
	            client_h = window.innerHeight; 
	        else if ((document.body) && (document.body.clientHeight)) 
	            client_h = document.body.clientHeight;
	        //封装函数，便于多次调用
	        var foreach_img = function(){
	            $(options.img).each(function(){
	                var dom = $(this);
	                //元素 offset 高
	                var offset_top = $(this).offset().top;
	                //计算出已划过的高度
	                var scroll_height = client_h + $(document).scrollTop();
	                //如果在浏览器可视内
	                if(offset_top <= scroll_height){
	                    var find_img = false;
	                     
	                    $(this).prop("src",$(this).attr(options.real_src));
	                    $(this).removeAttr(options.real_src);
	                    switch(options.animate){
	                        case 'fadeIn' : 
	                        $(this).css({"opacity":"0"}).delay(options.animate_delay).animate({"opacity":"1"},options.animate_time);break;
	                        case 'slideDown' : 
	                        $(this).css({"height":"0px","background":"url('.')"}).delay(options.animate_delay).animate({"height":$(this).height()+"px"},options.animate_time);break;
	                        default : break;
	                    }
	                 
	                    return true;    //返回重新循环
	                }
	                return false;       //退出循环
	            });
	        };
	         
	        //页面加载完就执行一次
	        setTimeout(function(){
	            foreach_img();
	        },options.time_out);
	        //绑定触发事件
	        $(this).bind(options.event,function(){
	            setTimeout(function(){foreach_img();},options.time_out);
	        });
	         
	    }); 
	    }
	})(jQuery);
	
	$(function(){
        /**
        *   option 的默认属性说明，进入 Y_lazyload.js 中查看注释
        *
        *   event           //加载img的触发事件
        *   img             //随后加载 img 的dom
        *   real_src        //要加载真实 src 使用到的自定义属性(attr)
        *   animate         //动画效果
        *   animate_delay   //动画等待时间，等待图片下载完成
        *   animate_time    //动画执行时间
        *   time_out        //设置延迟加载，有时IE下如果太快会有个别图片无反应的情况，设置此延时就可以解决
        **/
        $(window).Y_lazyload({
            time_out : 1000
        });
    });
	
</script>

</body></html>