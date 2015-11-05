<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.demo.util.*"%>
<!DOCTYPE html>
<!-- saved from url=(0034)https://home.console.aliyun.com/#/ -->
<html lang="zh">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="data-spm" content="5176">
<meta name="referrer" content="origin">
<title class="ng-binding">阿里云管理控制台首页</title>

<link rel="stylesheet" type="text/css" href="/css/bootstrap.css" media="all">
<link rel="stylesheet" type="text/css" href="/js/admin/angular-growl.min.css">
<link rel="stylesheet" type="text/css" href="/js/admin/console1412.css">
<link rel="stylesheet" type="text/css" href="/js/admin/home.console1412.css">
<script src="/js/admin/messages_zh-cn.js"></script>

<script type="text/javascript" src="/js/jquery-2.1.3.js"></script>
<script type="text/javascript" src="/js/bootstrap.js"></script>
</head>
<body style="">

	<div>
		<!-- banner -->
		<%@include file="banner.jsp" %>
		<!-- banner -->
		
		<div class="viewFramework-body viewFramework-sidebar-full" >
			<!--  left  -->
			<%@include file="left.jsp" %>
			<!--  left  -->
			
			<div class="viewFramework-product" >
				<div class="viewFramework-product-body">
					<div>
						<div class="ng-scope">
							<div class="home-container ng-scope">
							
								<!-- 提示 start -->
								<!-- <div>
									<div class="console-global-notice">
										<div class="console-global-notice-list">
											<div class="alert alert-success console-global-notice-item">
												<div class="clearfix console-global-notice-item-inner">
													<div class="console-global-notice-content ng-binding">
														<b>公告：</b>推荐码申请进行中，分享使用得返利，<a href="" target="_blank">点击查看详情</a>。
													</div>
												</div>
											</div>
										</div>
									</div>
								</div> -->
								<div style="height:20px;"></div>
								<!-- 提示 end -->
								
								
								<div class="user-container clearfix ng-scope">
									<!-- 个人信息 -->
									<div class="user-section-wrap">
										<div class="user-section user-info">
											<div class="section-head">
												<img width="30" height="30" class="user-header ng-scope" src="/js/admin/header.png">
											</div>
											<div class="section-content-wrap">
												<div class="section-content">
													<div class="user-name">
														<span class="user-name-hi">Hi,</span>
														<a href="" class="home-blue">杜庆祥</a>
													</div>
													<div class="user-email ng-binding">3638*****@qq.com</div>
													<div class="user-auth">
														<span class="auth-item glyphicon glyphicon-user ng-scope active"></span>
														<span class="auth-item glyphicon glyphicon-phone ng-scope active"></span>
														<span class="auth-item-gap"></span>
														<span class="auth-level ng-binding">安全等级: 
															<a href="" class="auth-level-num ng-isolate-scope">
																<span class="text-warning">中</span>
															</a>
														</span>
													</div>
												</div>
											</div>
										</div>
									</div>
									
									<!-- 资金信息 -->
									<div class="user-section-wrap">
										<div class="user-section user-charge">
											<div class="section-head">
												<div class="section-icon">
													<span class="glyphicon glyphicon-yen"></span>
												</div>
											</div>
											<div class="section-content-wrap">
												<div class="section-content">
													<div class="user-balance">
														<span class="balance-num ng-isolate-scope">
															<span class="ng-binding">0</span>
															<span class="balance-after-point ng-binding">.00</span>
															<span class="balance-unit ng-binding">元</span>
														</span>
														<a href="" class="btn btn-sm btn-primary user-recharge ng-binding">充值</a>
													</div>
													<div class="user-opts">
														<div class="opts-row ng-scope">
															<span class="ng-scope">
																<a href="" class="ng-binding">提现</a>
																<span class="opt-gap ng-scope"></span>
															</span>
															<span lass="ng-scope">
																<a href="" class="ng-binding">退款</a>
																<span class="opt-gap ng-scope"></span>
															</span>
															<span class="ng-scope">
																<a href="" class="ng-binding">订单管理</a>
															</span>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									
									<!-- 消息信息 -->
									<div class="user-section-wrap">
										<div class="user-section user-undo">
											<div class="section-head">
												<div class="section-icon">
													<span class="glyphicon glyphicon-list-alt"></span>
												</div>
											</div>
											<div class="section-content-wrap">
												<!-- 这部分代看起来需要重构 -->
												<div class="section-content">
													<div class="undo-group ng-scope inactive">
														<div class="group-total home-transition">
															<span class="undo-num ng-binding">0</span>
															<span class="undo-text ng-binding">续费待办</span>
														</div>
														<ul class="group-list">
															<li class="group-item">abc</li>
															<!-- ngIf: undo.resources.ecs && undo.resources.ecs.count > 0 -->
															<!-- ngIf: undo.resources.rds && undo.resources.rds.count > 0 -->
															<!-- ngIf: undo.resources.domain > 0 -->
															<!-- ngIf: undo.resources.host > 0 -->
															<!-- ngIf: undo.resources.mail > 0 -->
														</ul>
													</div>
													<div class="undo-group ng-scope inactive">
														<div class="group-total home-transition">
															<span class="undo-num ng-binding">0</span>
															<span class="undo-text ng-binding">工单待办</span>
														</div>
														<ul class="group-list">
															<!-- ngIf: undo.workorder.wait_feedback -->
															<!-- ngIf: undo.workorder.wait_confirm -->
														</ul>
													</div>
													<div class="undo-group undo-group-last ng-scope inactive">
														<div class="group-total home-transition">
															<span class="undo-num ng-binding">0</span>
															<span class="undo-text ng-binding">代金券</span>
															<!-- ngIf: undo.coupon && undo.coupon.couponCount > 0 -->
														</div>
														<ul class="group-list">
															<li class="group-item">
																<a href="">
																	<span class="undo-num ng-binding">0</span>
																	<span class="undo-text ng-scope">张代金券可用</span>
																</a>
															</li>
														</ul>
								 					</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<!-- end ngIf: !isSubAccount -->
								
								
								<!-- product section -->
								<div class="product-container">
									<div class="product-section">
										<div class="section-head">
											<span class="title-strong">产品与服务</span>
										</div>
										<div class="section-content clearfix">
											<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 product-col ng-scope">
												<div class="product-category ng-scope">
													<p class="category-title ng-binding">弹性计算</p>
													<ul>
														<li class="product-item clearfix ng-scope">
															<a href="" class="pull-left open-link">
																<span class="category-icon icon-ecs"></span>
																	<span class="ng-binding">云服务器</span>
																	<span class="ng-binding">ECS</span>
																	<span class="item-data ng-scope">
																	<span class="item-count ng-binding">2</span>
																	<span class="ng-binding">个</span>
																</span>
															</a>
													</ul>
												</div>
												<div class="product-category ng-scope">
													<p class="category-title ng-binding">安全与管理</p>
													<ul>
														<li class="product-item clearfix ng-scope">
															<a href="" class="pull-left open-link">
																<span class="category-icon icon-yundun"></span>
																<span class="ng-binding">云盾</span>
																<span class="ng-binding"></span>
															</a>
														</li>
														<li class="product-item clearfix ng-scope">
															<a href="" class="pull-left open-link">
																<span class="category-icon icon-yunjiankong"></span>
																<span class="ng-binding">云监控</span>
																<span class="ng-binding">CMS</span>
															</a>
														</li>
													</ul>
												</div>
											</div>
											
											
											<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 product-col ng-scope">
												<div class="product-category ng-scope">
													<p class="category-title ng-binding">域名和网站</p>
													<ul>
														<li class="product-item clearfix ng-scope">
															<a href="" class="pull-left open-link">
																<span class="category-icon icon-yuming"></span>
																<span class="ng-binding">域名</span>
																<span class="ng-binding"></span>
																<span class="item-data ng-scope">
																	<span class="item-count ng-binding">5</span>
																	<span class="ng-binding">个</span>
																</span>
															</a>
														</li>
														<li class="product-item clearfix ng-scope">
															<a href="" class="pull-left open-link">
																<span class="category-icon icon-yunjiexi"></span>
																<span class="ng-binding">云解析</span>
																<span class="ng-binding">DNS</span>
																<span class="item-data ng-scope">
																	<span class="item-count ng-binding">5</span>
																	<span class="ng-binding">个</span>
																</span>
															</a>
														</li>
													</ul>
												</div>
												<div class="product-category ng-scope">
													<p class="category-title ng-binding">其他</p>
													<ul>
														<li class="product-item clearfix ng-scope">
															<a href="" class="pull-left open-link">
																<span class="category-icon icon-toolsimage"></span>
																<span class="ng-binding">云市场</span>
																<span class="ng-binding"></span>
															</a>
														</li>
													</ul>
												</div>
											</div>
											
										</div>
									</div>
								</div>


								<div class="product-container">
									<div class="product-section">
										<div class="section-head">
											<span class="title-strong">产品与服务</span>
										</div>
										<div class="console-table-wapper" style="position: relative;">
											<div class="ng-isolate-scope">
												<div class="ng-scope">
													<div class="gridSection">
														<table class="table table-hover"
															style="margin-bottom: 57px;">
															<thead>
																<tr>
																	<th width="10"><input type="checkbox"
																		class="ng-pristine ng-valid"></th>
																	<th>实例ID/名称</th>
																	<th class="ng-isolate-scope"><span
																		class="ng-scope"> <span class="ng-scope">所在可用区</span>
																	</span></th>
																	<th>IP地址</th>
																	<th class="ng-isolate-scope">
																		<div class="dropdown ng-scope">
																			<a href="#" class="dropdown-toggle"> <span>
																					<span class="ng-scope">状态</span>
																			</span> <span class="ng-binding">(全部)</span> <span
																				class="caret"></span></a>
																		</div>
																	</th>
																	<th class="ng-isolate-scope">
																		<div class="dropdown ng-scope">
																			<a href="#" class="dropdown-toggle"
																				data-toggle="dropdown"> <span> <span
																					class="ng-scope">网络类型</span>
																			</span> <span class="ng-binding">(全部)</span> <span
																				class="caret"></span></a>
																			<ul
																				class="dropdown-menu aliyun-console-table-search-list">
																				<li class="ng-scope"><a href="javascript:">
																						<span class="icon-yes ng-scope"></span> <span
																						class="ng-binding">全部</span>
																				</a></li>
																				<li class="ng-scope"><a href="javascript:">
																						<span class="ng-binding">经典网络</span>
																				</a></li>
																				<li class="ng-scope"><a href="javascript:">
																						<span class="ng-binding">专有网络</span>
																				</a></li>
																			</ul>
																		</div>
																	</th>
																	<th>配置</th>
																	<th class="ng-isolate-scope">
																		<div class="dropdown ng-scope">
																			<a href="#" class="dropdown-toggle"
																				data-toggle="dropdown"> <span> <span
																					class="ng-scope">付费方式</span>
																			</span> <span class="ng-binding">(全部)</span> <span
																				class="caret"></span>
																			</a>
																			<ul
																				class="dropdown-menu aliyun-console-table-search-list">
																				<li class="ng-scope"><a href="javascript:">
																						<span class="icon-yes ng-scope"></span> <span
																						class="ng-binding">全部</span>
																				</a></li>
																				<li class="ng-scope"><a href="javascript:">
																						<span class="ng-binding">包年包月</span>
																				</a></li>
																				<li class="ng-scope"><a href="javascript:">
																						<span class="ng-binding">按量</span>
																				</a></li>
																			</ul>
																		</div>
																	</th>
																	<th class="text-right">操作</th>
																</tr>
															</thead>
															<tbody>
																<tr class="ng-scope">
																	<td width="10"><input type="checkbox"
																		class="ng-pristine ng-valid"></td>
																	<td>
																		<div class="ng-scope">
																			<p>
																				<a class="ng-binding" href="#">i-25ntcf8lz</a>
																			</p>
																			<p style="height: 20px" class="">
																				<span class="ng-scope"> <span>memorys</span>
																				</span>
																			</p>
																		</div>
																	</td>
																	<td><span bo-text="item.izNoName">北京可用区A</span></td>
																	<td>
																		<div class="ng-isolate-scope">
																			<p class="ng-scope ng-binding">
																				123.57.59.129 <span class="text-muted">(公)</span>
																			</p>
																			<p class="ng-scope ng-binding">
																				10.165.121.244 <span class="text-muted">(内)</span>
																			</p>
																		</div>
																	</td>
																	<td><span class="run-status run-status-running">运行中</span>
																	</td>
																	<td>
																		<div class="ng-isolate-scope">
																			<p class="ng-scope">
																				<span>经典网络</span>
																			</p>
																		</div>
																	</td>
																	<td>
																		<div>
																			<div>
																				<p>
																					<span class="nowrap">CPU： 1核</span> &nbsp;&nbsp; <span
																						class="nowrap">内存： 1024 MB</span> <span
																						class="ng-isolate-scope"></span>
																				</p>
																				<p>
																					<span class="ng-isolate-scope"> <span
																						class="nowrap ng-scope ng-binding"> 带宽： <span
																							class="">1Mbps</span>
																					</span>
																					</span>
																				</p>
																			</div>
																		</div>
																	</td>
																	<td>
																		<div>
																			<p class="ng-isolate-scope">包年包月</p>
																			<p class="ng-isolate-scope">
																				<span class=""></span> <span class="">15-11-02
																					00:00到期</span>
																			</p>
																		</div>
																	</td>
																	<td class="text-right">
																		<div class="ng-isolate-scope">
																			<div class="ng-scope">
																				<div class="nowrap">
																					<a href="#">管理</a> <span class="text-explode">|</span>
																					<a href="javascript:;" class="ng-isolate-scope">变配</a>
																					<span class="caret" style="border-top: 0px;"></span>
																				</div>
																				<div class="nowrap">
																					<a href="javascript:;">续费</a> <span
																						class="text-explode">|</span>
																					<div class="dropdown inline-block">
																						<a href="javascript:;" class="dropdown-toggle"
																							data-toggle="dropdown">更多<span class="caret"></span>
																						</a>
																						<ul class="dropdown-menu">
																							<li class="disabled"><a href="javascript:;"
																								class="ng-pristine ng-valid"> <span>启动</span>
																							</a></li>
																							<li class=""><a href="javascript:;"
																								class="ng-pristine ng-valid"> <span>停止</span>
																							</a></li>
																							<li class=""><a href="javascript:;"
																								class="ng-pristine ng-valid"> <span>重启</span>
																							</a></li>
																							<li class=""><a href="javascript:;"
																								class="ng-pristine ng-valid"> <span>重置密码</span>
																							</a></li>
																							<li class=""><a href="javascript:;"
																								class="ng-pristine ng-valid"> <span>修改信息</span>
																							</a></li>
																							<li class=""><a href="javascript:;"
																								class="ng-pristine ng-valid" target="_blank">
																									<span>连接管理终端...</span>
																							</a></li>
																							<li class=""><a href="javascript:;"
																								class="ng-pristine ng-valid"> <span>连接帮助</span>
																							</a></li>
																							<li class="disabled"><a href="javascript:;"
																								class="ng-pristine ng-valid" disabled="disabled">
																									<span>重新初始化磁盘</span>
																							</a></li>
																							<li class="disabled"><a href="javascript:;"
																								class="ng-pristine ng-valid" disabled="disabled">
																									<span>更换系统盘</span>
																							</a></li>
																							<li class=""><a href="javascript:;"
																								class="ng-pristine ng-valid"> <span>购买相同配置</span>
																							</a></li>
																							<li class=""><a href="javascript:;"
																								class="ng-isolate-scope ng-pristine ng-valid">
																									<span>编辑标签</span>
																							</a></li>
																							<li class="disabled"><a href="javascript:;"
																								class="ng-pristine ng-valid" disabled="disabled">
																									<span>修改私网IP</span>
																							</a></li>
																						</ul>
																					</div>
																				</div>
																			</div>
																		</div>
																	</td>
																</tr>
															</tbody>
														</table>
														<table class="table table-fixed" style="margin: 0px; z-index: 99;  bottom: 0px;">
																<tr>
																	<td colspan=" 10">
																		<div class="pull-left">
																			<div class="ng-isolate-scope"></div>
																		</div>
																		<div class="ng-scope">
																			<div class="pull-right ng-isolate-scope">
																				<div class="pagination-info">
																					<span class="ng-binding">共有2条</span>，
																					 <span class="ng-binding">每页显示：20条</span>
																				</div>
																				<ul class="pagination pagination ng-isolate-scope">
																					<li class="ng-scope disabled"><a
																						class="ng-binding">«</a></li>
																					<li class="ng-scope disabled"><a
																						class="ng-binding">‹</a></li>
																					<li class="ng-scope active"><a
																						class="ng-binding">1</a></li>
																					<li class="ng-scope disabled"><a
																						class="ng-binding">›</a></li>
																					<li class="ng-scope disabled"><a
																						class="ng-binding">»</a></li>
																				</ul>
																			</div>
																		</div>
																</tr>
														</table>
													</div>
												</div>
											</div>

										</div>
									</div>
								</div>



							</div>
						</div>
					</div>
					
					
					
					
				</div>
			</div>
		</div>
	</div>


</body>
</html>