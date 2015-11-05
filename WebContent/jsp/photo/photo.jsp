<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.*,com.demo.util.*,com.demo.bean.RobotPhotos"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我的相册</title>

<link href="/css/photo/picture.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/jquery-2.1.3.js"></script>

</head>
<%
	List<RobotPhotos> list = (List<RobotPhotos>)request.getAttribute("photos");
	RobotPhotos firstPhotos = list.get(0);
	String picList = request.getAttribute("picListJson") != null ?request.getAttribute("picListJson").toString() : "";
	
%>
<body style="background:#101010;">
<div class="indexBody">
  <div class="btn-float"><a class="maxBtn-l" href="javascript:void(0);"></a><a class="maxBtn-r" href="javascript:void(0);"></a></div>
  <div class="indexbody-main">
    <div class="indexbody-left"></div>
    <div class="indexbody-right"></div>
  </div>
  <div class="demo w990">
    <div class="maxPic-box">
      <div class="maxPic">
        <div class="maxPicBox"><span></span><img id="mainPic" src="<%=firstPhotos.getNetPath()%>"/></div>
      </div>
    </div>
    <div class="Pic-pageln">
        <span class="l">可用“<font>←</font>”或“<font>→</font>”方向键快速翻页</span>
        <span class="pic-r-span r">
            <a id="showOriginal" target="_blank" href="<%=firstPhotos.getNetPath()%>" class="ico02"><i></i>查看原图</a>
            <a href="http://www.17sucai.com" class="ico05"><i></i>返回图集</a>
        </span>
    </div>    
    <div id="tplist" class="w-width clearfix">
      <div class="Up-tuzu">
          <!--<a class="outpic" href="/" id="prevUrl"><span></span><img src="images/prev.jpg" /></a>
          <a class="inpic" href="/">上一组</a><span class="prevspan"></span>-->
      </div>
      
      <div class="bottom-lists l">
        <div class="PicBtn-a PicBtn-a-l"><a class="PicBtn-left" href="javascript:void(0);"></a></div>
        <div class="minPic l">
          <ul class="gallery_demo_unstyled">
          <%
          	int count = 1;
          	for(RobotPhotos pho : list) {%>
          		<li id="tu_<%=count%>"><span></span><a href="javascript:void(0);"><img width="80" height="60" src="<%=pho.getMiniPath() %>" /></a></li>
          <%
          	count ++;
          	}%>
          </ul>
        </div>
        <div class="PicBtn-a PicBtn-a-r"><a class="PicBtn-right" href="javascript:void(0);"></a></div>
      </div>
      
      <div class="Next-tuzu"></div>
    </div>
  </div>
</div>


<script type="text/javascript">
$(function(){
	var prevDiv = $(".Up-tuzu");
	var nextDiv = $(".Next-tuzu");
	if(prevDiv.find("a").length<1){
		 prevDiv.html("<p style='line-height:120px;color:#666;'><a href='' id='prevUrl'>没有了</a></p>");
		}
	if(nextDiv.find("a").length<1){
	 nextDiv.html("<p style='line-height:120px;color:#666;'><a href='' id='nextUrl'>没有了</a></p>");
	}
});

var selectKey = "1";
var picList = <%=picList%>
</script> 
<script type="text/javascript" src="/js/photo/picture.js"></script> 

</body>
</html>
