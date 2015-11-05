$("#goodAddShow").click(function(){
	$("#good_div").show();
	$("#goodEditSave").hide();
	$("#goodSave").show();
	clearInput();
});
$("#goodClose").click(function(){
	$("#good_div").hide();
});
$("#goodSave").click(function(){
	
	var editor = UE.getEditor('editor');
	
	var good_name = $("#good_name").val();
	var good_price = $("#good_price").val();
	var is_discount = 0;
	var good_dis_price = $("#good_dis_price").val();
	var good_pic_url = $("#good_pic_url").val();
	var good_tag = $("#good_tag").val();
	var good_type = $("#good_type").val();
	var good_content = editor.getContent();
	
	if ($("#is_discount").is(':checked')) {
		is_discount = 1;
	}
	
	if (good_name == null || good_name == "") {
		alert("名称不能为空");
		return ;
	}
	
	if (good_price == null || good_price == "") {
		alert("价格不能为空");
		return ;
	}
	
	if (good_pic_url == null || good_pic_url == "") {
		alert("头像地址不能为空");
		return ;
	}
	
	if (good_tag == null || good_tag == "") {
		alert("简介不能为空");
		return ;
	}
	
	if ( !editor.hasContents() ) {
		alert("详细内容不能为空");
		return ;
	}
	
	var param = {good_name:good_name,good_price:good_price,is_discount:is_discount,
			good_dis_price:good_dis_price,good_pic_url:good_pic_url,good_tag:good_tag,
			good_content:good_content,good_type:good_type}
	var url = "/shop/goodsAdd.do";
	
	$.post(url,param,function(data){
		
		if (data == 1) {
			alert("成功");
			refush();
		} else if (data == -1) {
			alert("未登录");
		} else if (data == -2) {
			alert("无权限");
		} else if (data == -3) {
			alert("参数错误");
		} else {
			alert("系统异常");
		}
	});
	
});

function getGoodDetail(id){
	
	var url = "/shop/goodsForUpdate.do";
	var param = {id:id};
	$.post(url,param,function(data){
		
		if (data == null) {
			alert("异常！");
			return ;
		}
		
		var result = eval('(' + data + ')');
		if (result == null) {
			alert("异常！！");
			return ;
		}
		var resultCode = result.result;
		if (resultCode == 1) {
			var good = result.good;
			if (good == null) {
				alert("异常！！！");
				return ;
			}
			var goodId = good.id;
			var goodName = good.goodName;
			var goodTag = good.goodTag;
			var goodPrice = good.goodPrice;
			var isDiscount = good.isDiscount;
			var goodDisPrice = good.goodDisPrice;
			var goodPicUrl = good.goodPicUrl;
			var content =  good.goodContent;
			var goodType = good.goodType;
			//清除文本框内容
			clearInput();
			//赋值
			$("#good_id").val(goodId);
			$("#good_name").val(goodName);
			$("#good_tag").val(goodTag);
			$("#good_price").val(goodPrice);
			$("#good_dis_price").val(goodDisPrice);
			$("#good_pic_url").val(goodPicUrl);
			$("#good_type").val(goodType);
			if (isDiscount ==1) {
				$("#is_discount").attr("checked",true);
			} else {
				$("#is_discount").attr("checked",false);
			}
			UE.getEditor('editor').execCommand('insertHtml',content );
			
			//显示新增、编辑区域
			$("#good_div").show();
			$("#goodEditSave").show();
			$("#goodSave").hide();
		} else if (resultCode == -1) {
			alert("未登录");
		} else if (resultCode == -2) {
			alert("无权限");
		} else if (resultCode == -3) {
			alert("该商品不存在");
		} else {
			alert("系统异常");
		}
		
	});
	
}

$("#goodEditSave").click(function(){
	
	var editor = UE.getEditor('editor');
	
	var good_name = $("#good_name").val();
	var good_price = $("#good_price").val();
	var is_discount = 0;
	var good_dis_price = $("#good_dis_price").val();
	var good_pic_url = $("#good_pic_url").val();
	var good_tag = $("#good_tag").val();
	var good_content = editor.getContent();
	var good_id = $("#good_id").val();
	
	if ($("#is_discount").is(':checked')) {
		is_discount = 1;
	}
	
	if (good_name == null || good_name == "") {
		alert("名称不能为空");
		return ;
	}
	
	if (good_price == null || good_price == "") {
		alert("价格不能为空");
		return ;
	}
	
	if (good_pic_url == null || good_pic_url == "") {
		alert("头像地址不能为空");
		return ;
	}
	
	if (good_tag == null || good_tag == "") {
		alert("简介不能为空");
		return ;
	}
	
	if ( !editor.hasContents() ) {
		alert("详细内容不能为空");
		return ;
	}
	
	var param = {good_id:good_id,good_name:good_name,good_price:good_price,is_discount:is_discount,
			good_dis_price:good_dis_price,good_pic_url:good_pic_url,good_tag:good_tag,
			good_content:good_content}
	
	var url = "/shop/goodsUpdate.do";
	
	$.post(url,param,function(data){
		
		if (data == 1) {
			alert("成功");
			refush();
		} else if (data == -1) {
			alert("未登录");
		} else if (data == -2) {
			alert("无权限");
		} else if (data == -3) {
			alert("参数错误");
		} else {
			alert("系统异常");
		}
	});
	
	
});

function goodOnOff(id,status){
	var url = "/shop/goodsOnOff.do";
	var param = {id:id,status:status};
	$.post(url,param,function(data){
		if (data == 1) {
			alert("成功");
			refush();
		} else if (data == -1) {
			alert("未登录");
		} else if (data == -2) {
			alert("无权限");
		} else if (data == -3) {
			alert("参数错误");
		} else {
			alert("系统异常");
		}
	});
	
}

function inCart(id){
	var value = id ;
	var cookieStr = getCooike("robot_shop_cart");
	if (cookieStr == null || cookieStr == "") {
		cookieStr = value;
	} else {
		cookieStr = cookieStr +"-"+value;
	}
	
	addCookie("robot_shop_cart",cookieStr);
	alert("加入成功！");
	
}

$(".goodAmount").keyup(function(){
	var amount ;
	var amountStr = $(this).val();
	if (amountStr == null || amountStr == "")
		amount = 1;
	else 
		amount = Number(amountStr);
	var price = $(this).parent().parent().find(".goodPrice").html();
	$(this).parent().parent().find(".goodSum").html(amount*price);
	sumGoods();
});

function sumGoods(){
	var sum = 0 ;
	$(".goodCheck").each(function(){
		var checked = $(this).is(':checked');
		if (checked) {
			var s = $(this).parent().parent().parent().find(".goodSum").html();
			sum+=Number(s);
		}
	});
	$("#showSum").html(sum);
}

$("#confirmBtn").click(function(){
	var arr = new Array();
	$(".goodCheck").each(function(){
		var checked = $(this).is(':checked');
		if (checked) {
			var id = $(this).val();
			var amount = 1 ;
			var value = $(this).parent().parent().parent().find(".goodAmount").val();
			if (value != null && value != "")
				amount = Number(value);
			var c = id+"="+amount;
			arr.push(c);
		}
	});
	
	arr.join(";");
	var goodContent = arr.toString() ;
	if (goodContent == null || goodContent == "") {
		alert("未选择商品");
		return ;
	}
	
	var name = $("#received_name").val();
	if (name == null || name == "") {
		alert("请填写收货人姓名！");
		return ;
	}
	var phone = $("#received_phone").val();
	if (phone == null || phone == "") {
		alert("请填写收货人手机号！");
		return ;
	}
	var address = $("#received_address").val();
	if (address == null || address == "") {
		alert("请填写收货人地址！");
		return ;
	}
	
	$("#good_content").val(goodContent);
	
	$("#orderForm").submit();
	
});

function toPay(){
	$("#payForm").submit();
}

function outCart(obj,id){
	var value = id;
	var cookieStr = getCooike("robot_shop_cart");
	if (cookieStr == null || cookieStr == "") {
		alert("删除成功！");
		return
	}
	if (cookieStr.indexOf(value+"-") >-1) {
		cookieStr = cookieStr.replace(value+"-","");
		addCookie("robot_shop_cart",cookieStr);
		removeGoodTr(obj);
		alert("删除成功！");
		return ;
	}
	
	if (cookieStr.indexOf("-"+value) >-1) {
		cookieStr = cookieStr.replace("-"+value,"");
		addCookie("robot_shop_cart",cookieStr);
		removeGoodTr(obj);
		alert("删除成功！");
		return ;
	}
	
	if (cookieStr.indexOf(value) >-1) {
		cookieStr = cookieStr.replace(value,"");
		addCookie("robot_shop_cart",cookieStr);
		removeGoodTr(obj);
		alert("删除成功！");
		return ;
	}
		
}

function removeGoodTr(obj){
	$(obj).parent().parent().remove();
}


function getCooike(name) {
	var strCookie = document.cookie;
	var arrCookie = strCookie.split("; ");
	for ( var i = 0; i < arrCookie.length; i++) {
		var arr = arrCookie[i].split("=");
		if (arr[0] == name)
			return arr[1];
	}
	return "";
}
function addCookie(name, value, expiresHours) {
	var cookieString = name + "=" + escape(value);
	// 判断是否设置过期时间
	if (expiresHours > 0) {
		var date = new Date();
		date.setTime(date.getTime + expiresHours * 3600 * 1000);
		cookieString = cookieString + "; path=/;expires=" + date.toGMTString();
	}
	document.cookie = cookieString;
} 


function clearInput(){
	
	$("#good_name").val("");
	$("#good_price").val("");
	$("#good_dis_price").val("");
	$("#good_pic_url").val("");
	$("#good_tag").val("");
	$("#good_type").val(0);
	$("#is_discount").attr("checked",false);
	UE.getEditor('editor').setContent("");
	
}

//---------  ordersManager -------------

function toDetail(id){
	var url = "/shop/orderDetail.do";
	var param= {id:id};
	$.post(url,param,function(data){
		if (data == null || data == "") {
			alert("系统异常！");
			return ;
		}
		var result = eval('(' + data + ')');
		if (result == null) {
			alert("系统异常！！");
			return ;
		}
		var resultCode = result.resultCode;
		if (resultCode == 1) {
			var array = result.array;
			var html = "";
			if (array != null) {
				for (var i=0 ;i<array.length; i++) {
					var good = array[i];
					var h = "";
					h+="<tr>";
					h+="<td><a target=\"_blank\" href=\"/detail/good/"+good.id+".do\">"+good.name+"</a></td>";
					h+="<td>"+good.amount+"</td>";
					h+="<td>"+good.price+"</td>";
					h+="<td>"+(Number(good.amount)*Number(good.price))+"</td>";
					h+="</tr>";
					html +=h;
				}
			}else {
				html="<tr><td colspan=\"4\" class=\"text-center\">无详情数据</td></tr>"
			}
			
			$("#detailBody").empty().append(html);
			$("#detailDiv").modal('show');
		} else if (resultCode == -1) {
			alert("未登录！");
			return ;
		} else if(resultCode == -2) {
			alert("订单不存在！");
		} else {
			alert("系统异常！！！");
		}
		
	});
	
}

function closeOrder(id){
	var url = "/shop/closeOrder.do";
	var param= {id:id};
	$.post(url,param,function(data){
		if (data ==1 ){
			alert("成功");
			refush();
		} else if (data == -1) {
			alert("未登录");
		} else if(data == -2) {
			alert("订单不存在");
		} else if(data == -3) {
			alert("该状态下的订单不能关闭");
		} else {
			alert("系统异常！");
		}
		
	});
	
}

function sendOrder(id){
	var url = "/shop/sendOrder.do";
	var param= {id:id};
	$.post(url,param,function(data){
		if (data ==1 ){
			alert("成功");
			refush();
		} else if (data == -1) {
			alert("未登录");
		} else if(data == -2) {
			alert("权限错误！");
		} else if(data == -3) {
			alert("该状态下的订单不能发货");
		} else {
			alert("系统异常！");
		}
	});
	
}

///------------首页异步加载----------

function moreGoods(){
	
	var nowPages = $("#indexPages").val();
	var type = $("#moreType").val();
	var key = $("#moreKey").val();
	
	var morePages = Number(nowPages)+1;
	
	var url = "/shop/getGoodMore.do";
	var params = {p:morePages,s:key,t:type};
	$.post(url,params,function(data){
		if (data == null || data == "") {
			alert("系统异常！");
			return ;
		}
		var result = eval('(' + data + ')');
		if (result == null) {
			alert("系统异常！！");
			return ;
		}
		
		var size = result.size;
		if (size <= 0 ) {
			alert("无更多商品!");
			return null;
		}
		var html = "";
		var array = result.array;
		for (var i = 0 ;i < array.length ; i++) {
			var good = array[i];
			if (good == null)
				continue;
			
			html+="<div class=\"col-xs-6 col-md-3\">";
			html+="<div class=\"thumbnail\">";
			html+="<img style=\"width:100%;height: 200px;\" src='"+good.url+"' alt=\"...\">";
			html+="<div class=\"caption\">";
			html+="<h3><a href=\"/detail/good/"+good.id+".do\" target=\"_blank\">"+good.name+"</a></h3>";
			html+="<p>￥"+good.price+"</p>";
			html+="<p>";
			html+="<a href=\"/detail/good/"+good.id+".do\" target=\"_blank\" class=\"btn btn-success\" role=\"button\"><span class=\"glyphicon glyphicon-heart\"></span>&nbsp;查看</a>";
			html+="&nbsp;<a href=\"javascript:void(0)\" onclick=\"inCart("+good.id+")\" class=\"btn btn-primary\" role=\"button\">加入购物车</a>";
			html+="</p>";
			html+="</div>";
			html+="</div>";
			html+="</div>";
		}
		
		if (html == "") {
			alert("无更多商品!");
			return null;
		}
		$("#goodListContent").append(html);
		$("#indexPages").val(morePages);
		
	});
	
}
