// 百度地图API功能
	var map = new BMap.Map("allmap");    // 创建Map实例
	map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);  // 初始化地图,设置中心点坐标和地图级别
	
	//修改控件位置，(x,y) x距右侧，y距顶部
	var opts = {offset: new BMap.Size(5, 65)}  ;
	
	map.addControl(new BMap.MapTypeControl(opts));   //添加地图类型控件
	map.setCurrentCity("北京");          // 设置地图显示的城市 此项是必须设置的
	map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
	//测距
	var myDis = new BMapLib.DistanceTool(map);

	//设置点 
	//x:116.406875  y:40.026034
	//var point = new BMap.Point(116.406875, 40.026034);
	//var marker = new BMap.Marker(point);  // 创建标注
	//map.addOverlay(marker);               // 将标注添加到地图中
	//marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
	
	var overlays = [];
	var overlaycomplete = function(e){
        overlays.push(e.overlay);
    };
    var styleOptions = {
        strokeColor:"red",    //边线颜色。
        fillColor:"red",      //填充颜色。当参数为空时，圆形将没有填充效果。
        strokeWeight: 3,       //边线的宽度，以像素为单位。
        strokeOpacity: 0.8,	   //边线透明度，取值范围0 - 1。
        fillOpacity: 0.6,      //填充的透明度，取值范围0 - 1。
        strokeStyle: 'solid' //边线的样式，solid或dashed。
    };
	//实例化鼠标绘制工具
    var drawingManager = new BMapLib.DrawingManager(map, {
        isOpen: false, //是否开启绘制模式
        enableDrawingTool: true, //是否显示工具栏
        drawingToolOptions: {
            anchor: BMAP_ANCHOR_TOP_RIGHT, //位置
            offset: new BMap.Size(5, 5), //偏离值
        },
        circleOptions: styleOptions, //圆的样式
        polylineOptions: styleOptions, //线的样式
        polygonOptions: styleOptions, //多边形的样式
        rectangleOptions: styleOptions //矩形的样式
    });
	
	 //添加鼠标绘制工具监听事件，用于获取绘制结果
    drawingManager.addEventListener('overlaycomplete', overlaycomplete);
    
	 
    function clearAll() {
		for(var i = 0; i < overlays.length; i++){
            map.removeOverlay(overlays[i]);
        }
        overlays.length = 0   ;
        map.clearOverlays();
    }
	
	function searchs(){
		var key = document.getElementById("keyWord").value;
		
		if (key == null || key == "") {
			key = "北京";
		} 
		//清除其他的覆盖物
		map.clearOverlays();
		var local = new BMap.LocalSearch(map, {
			renderOptions:{map: map}
		});
		local.search(key);
	}
	
	function getSign(){
		
		for (var i=0; i<overlays.length ;i++) {
			var sign = overlays[i];
			var x = sign.point.lng;
			var y = sign.point.lat;
			alert("点位置   x:"+x+"  y:"+y);
			console.log("点位置   x:"+x+"  y:"+y);
		}
		
	}
	
	
	
	//http://123.57.59.129/domain/upload/robots.jpg
	
	function insertPosition(){
		if (overlays == null || overlays.length <= 0) {
			alert("请增加坐标点！！");
		}
			
		var position = overlays[0];
		var x = position.point.lng;
		var y = position.point.lat;
		
		var tags = document.getElementById("position_tags").value;
		var content = document.getElementById("position_content").value;
		
		var url = "/insertPosition.do";
		var param={positionX:x,positionY:y,tag:tags,content:content};
		jQuery.post(url,param,function(data){
			
			if(data == -1){
				alert("未登陆！！");
			}else if(data == -2){
				alert("坐标数据异常！");
			}else if(data == -3){
				alert("系统异常");
			}else if(data == 1){
				window.location.href="/map.do";
			}
		});
		
	}
	function makeSign(position){
		
		var sContent ="<h4 style='margin:0 0 5px 0;padding:0.2em 0'>"+position.tags+"</h4>" ;
		
		if (position.imgPath !=null && position.imgPath != "") {
			sContent+="<a href='/photo.do?posid="+position.id+"' target='_blank'><img style='float:right;margin:4px' id='img"+position.id+"' src='"+position.imgPath +"' width='139' height='104' title='机器人'/></a>";
		}
			
		sContent+="<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>"+position.content+"</p></div>" ;
		
		var point = new BMap.Point(position.positionX, position.positionY);
		var marker = new BMap.Marker(point);  // 创建标注
		var infoWindow = new BMap.InfoWindow(sContent);  // 创建信息窗口对象
		
		
		map.addOverlay(marker);               // 将标注添加到地图中
		marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
		
		marker.addEventListener("click", function(){          
		   this.openInfoWindow(infoWindow);
		   //图片加载完毕重绘infowindow
		   if (document.getElementById('img'+position.id) != null) {
			   document.getElementById('img'+position.id).onload = function (){
				   
				   infoWindow.redraw();   //防止在网速较慢，图片未加载时，生成的信息框高度比图片的总高度小，导致图片部分被隐藏
			   }
		   }
		   
		   map.centerAndZoom(point, 15);
		});
		
		
	}

	function makeCurve(positions){
		
		var points = new Array();
		for (var i=0 ;i< positions.length;i++) {
			var position = positions[i];
			var point=new BMap.Point(position.positionX,position.positionY);
			points[i]=point;
		}
		points.reverse();
		var curve = new BMapLib.CurveLine(points, {strokeColor:"blue", strokeWeight:3, strokeOpacity:0.5}); //创建弧线对象
		map.addOverlay(curve); //添加到地图中
		curve.enableEditing();
		
	}
	
	
	function getPositions(type){
		var years = document.getElementById("position_years").value;
		var url = "/getPositions.do";
		var param={years:years};
		jQuery.post(url,param,function(data){
			var result = eval('(' + data + ')');
			if (result != null) {
				
				if (result.success == 1) {
					var list = eval('(' + result.positionList + ')');
					if (type == 0) {//增加坐标点
						map.clearOverlays();
						for (var i=0 ;i < list.length;i++){
							var position = list[i];
							makeSign(position);
						}
					} else if(type == 1) {
						
						makeCurve(list);
						
						
					}
					
					
				}
				
			}
			
		});
		
	}
	