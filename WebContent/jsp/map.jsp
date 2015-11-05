<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<style type="text/css">
body,html,#allmap {
	width: 100%;
	height: 100%;
	overflow: hidden;
	margin: 0;
	font-family: "微软雅黑";
}
</style>
	
	<link rel="stylesheet" href="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.css" />
<title>Insert title here</title>
</head>

<body>

	<div style="width:25%;height:100%;background-color: rgb(0, 161, 255);float:left;">
		<div style="padding-top:50px;display:none;" align="center">
			<input type="button" onclick="myDis.open();" value="测距（开）">
			<input type="button" onclick="myDis.close();" value="测距（关）">
			<input type="button" onclick="clearAll()" value="清除" />
			<input type="button" onclick="getSign()" value="测试点坐标" />
			<input type="button" onclick="makeSign()" value="创建一个点" />
			
		</div>
		<div style="padding-top:50px;" align="center">
			
			<input type="text" id="keyWord"><a href="javascript:void(0);" onclick="searchs();">搜索</a>
		</div>
		<hr>
		<div style="padding-top:50px;padding-left: 10px;" align="left">
			<label>Tags:</label><input type="text" style="margin-left:25px;" id="position_tags" />
			<br/>
			<label>Content:</label><textarea style=" width: 167px; height: 100px;" rows="3" cols="2" id="position_content"></textarea>
			<br/>
			<input type="button" onclick="insertPosition();" style="margin-left:65px;margin-top:10px;width: 136px;height: 30px;" value="新增坐标">
		</div>
		<hr>
		<div style="padding-top:50px;padding-left: 10px;" align="left">
			<label>Years:</label><input type="text" style="margin-left:25px;" id="position_years" />
			<br/>
			<input type="button" onclick="getPositions(0);" style="margin-left:70px;margin-top:10px;width: 136px;height: 30px;" value="获取坐标">
			<br/>
			<input type="button" onclick="getPositions(1);" style="margin-left:70px;margin-top:10px;width: 136px;height: 30px;" value="获取路径">
		</div>
		<hr>
		<div style="padding-top:50px;padding-left: 75px;" align="left">
			<a href="/uploadManager.do" >资源管理</a>
			<a href="/positionManager.do" >坐标管理</a>
		</div>
	</div><div style="width:75%;height:100%;float:left;">
	
	
		<div id="allmap" style="">
			<div id="map" style="height:100%;-webkit-transition: all 0.5s ease-in-out;transition: all 0.5s ease-in-out;"></div>
		</div>
	
	</div>

	
	
	<!--  -->
</body>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=97E17D33f85eaf3e22feace6fe5a7c7f"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/library/DistanceTool/1.2/src/DistanceTool_min.js"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.js"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/library/CurveLine/1.5/src/CurveLine.min.js"></script>
	
	<script type="text/javascript" src="/js/timeline/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="/js/myMap.js"></script>
</html>