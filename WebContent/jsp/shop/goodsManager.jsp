<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.demo.util.*,com.demo.bean.RobotUser,com.demo.bean.RobotShopGood"%>
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
.red {
	color: red;
}

.td1_width {
	width: 20px;
}
.td2_width {
	width: 150px;
}
.td3_width {
	width:15%;
}
.td4_width {
	width:25%;
}
.td4_width span {
	color: #9c9c9c;
	font-size:13px;
}
.td5_width {
	width:15%;
}
.td5_width span {
	color: #f40;
	font-size:15px;
}
.td6_width {
	width:10%;
}

.img_css {
	height: 100px;
  	width: 100px;
}

</style>
</head>
<body class="body_css">
	<div class=".container-fluid">
		<div class="row">
			<div class="col-md-1"></div>
			<div class="col-md-10">
				<%@include file="shopBanner.jsp" %>
				
				<div class="header_title"	style="background-image: url(/img/bg3.jpg); background-repeat: repeat-y;margin-bottom:25px;">
					<div class="container">
						<div class="row">
							<div class="col-xs-12">
								<span class="title_tag"><a href="">商品管理</a></span>
								
							</div>
						</div>
					</div>
				
				</div>
				<div class="panel panel-primary" id="good_div" style="display:none;">
					<div class="panel-heading">
						商品新增
					</div>
					<div class="panel-body">
					
						<div class="form-inline">
							<div class="form-group">
								<label for="good_name">商品名称：</label>
	    						<input type="text" class="form-control" id="good_name" placeholder="Text">
							</div>
							<div class="form-group">
								<label for="good_price">商品价格：</label>
	    						<input type="text" class="form-control" id="good_price" placeholder="Text">
							</div>
							<div class="form-group">
								<label class="checkbox-inline">
								  <input type="checkbox" id="is_discount" value="1">折扣
								</label>
							</div>
							<div class="form-group">
								<label for="good_dis_price">折扣价格：</label>
	    						<input type="text" class="form-control" id="good_dis_price" placeholder="Text">
							</div>
							<div class="form-group">
								<label for="good_type">商品类型：</label>
	    						<select id="good_type" class="form-control">
								  <option value="0">默认</option>
								  <option value="1">书籍</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="good_pic_url">头像地址：</label>
	   						<input type="text" class="form-control" id="good_pic_url" placeholder="Text">
						</div>
						
						<div class="form-group">
							<label for="good_tag">商品简介：</label>
	   						<input type="text" class="form-control" id="good_tag" placeholder="Text">
						</div>
					
						<script id="editor" type="text/plain" style=" height: 250px;"></script>
				
						<div class="text-right">
							<input type="hidden" id="good_id"/>
							<button type="button" class="btn btn-success" id="goodEditSave">编辑保存</button>
							<button type="button" class="btn btn-success" id="goodSave">保存</button>
							<button type="button" class="btn btn-default" id="goodClose">关闭</button>
						</div>
					</div>
				</div>
				<div class="panel panel-primary">
					<div class="panel-body">
					<button type="button" class="btn btn-primary" id="goodAddShow">新增</button>
					<br/><br/>
						<table class="table table-hover">
							<tr>
								<th></th>
								<th>头像</th>
								<th>名称</th>
								<th>简介</th>
								<th>价格</th>
								<th>状态</th>
								<th>操作</th>
							</tr>
							<% List<RobotShopGood> goodList = (List<RobotShopGood>)request.getAttribute("goodList"); 
								if (!CollectionUtils.isEmpty(goodList)) {
									for (RobotShopGood good : goodList) {%>
										<tr>
											<td class="td1_width">
												<span><input type="checkbox" /></span>
											</td>
											<td class="td2_width">
												<img class="img-thumbnail img_css" src="<%=good.getGoodPicUrl() %>" />
											</td>
											<td class="td3_width" style="vertical-align: middle;">
												<span><a href="/detail/good/<%=good.getId() %>.do" target="_blank"><%=good.getGoodName() %></a></span>
											</td>
											<td class="td4_width" style="vertical-align: middle;">
												<span><%=good.getGoodTag() %></span>
											</td>
											<td class="td5_width" style="vertical-align: middle;">
												<span><%=good.getGoodPrice() %><%if(good.getIsDiscount() == 1) {%>/(折扣)<%=good.getGoodDisPrice()%><%} %></span>
											</td>
											<td class="td6_width" style="vertical-align: middle;">
												<span><%if(good.getGoodStatus()== 0) {%>正常<%}else if(good.getGoodStatus()== -1){%>已下架<%}else{%>已删除<%} %></span>
											</td>
											<td style="vertical-align: middle;">
												<%if(good.getGoodStatus()== 0) {%>
													<a href="javascript:void(0);" onclick="goodOnOff(<%=good.getId()%>,-1)">下架</a>
												<%}else if(good.getGoodStatus()== -1){%>
													<a href="javascript:void(0);" onclick="goodOnOff(<%=good.getId()%>,0)">上架</a>
												<%}else{%>已删除<%} %>
												/&nbsp;<a href="javascript:void(0);" onclick="getGoodDetail(<%=good.getId()%>)">修改</a>
												/&nbsp;<a href="javascript:void(0);" onclick="goodOnOff(<%=good.getId()%>,-2)">删除</a>
											</td>
										</tr>
										
										
										
							<%		}
								}
							
							%>
							
							
							
							
						</table>
							<% 
						  		int nowPage = request.getAttribute("page") != null ? StringUtil.toInteger(request.getAttribute("page").toString(), 0) : 0;
								String beforeUrl="/shop/goodsManager.do?p="+(nowPage-1);
								String nextUrl="/shop/goodsManager.do?p="+(nowPage+1);
						  	%>
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
					</div>
					
					
				</div>
				
				<br/>
				<%@ include file="footer.jsp" %>
			</div>
			<div class="col-md-1"></div>
		</div>
	</div>

</body>

	<script type="text/javascript" src="/js/jquery-2.1.3.js"></script>
	<script type="text/javascript" src="/js/bootstrap.js"></script>
	<script type="text/javascript" charset="utf-8" src="/js/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="/js/ueditor/ueditor.all.min.js"></script>
	<script type="text/javascript" charset="utf-8" src="/js/ueditor/zh-cn.js"></script>
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/js/shop/good.js"></script>
	<script>
		var ue = UE.getEditor('editor');
	</script>

</html>