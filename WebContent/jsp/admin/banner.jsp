<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
		<div class="viewFramework-topbar ng-scope">
			<!-- topbar -->
			<div class="aliyun-console-topbar ng-isolate-scope">
				<div class="topbar-wrap topbar-clearfix">
					<div class="topbar-head topbar-left">
						<a href="http://www.aliyun.com/" target="_blank" class="topbar-logo topbar-left">
							<span class="icon-logo1"></span>
						</a>
						<a href="http://home.console.aliyun.com/" target="_self" class="topbar-home-link topbar-btn topbar-left">
							<span class="ng-binding">管理控制台</span>
						</a>
					</div>
					<div class="topbar-nav topbar-left dropdown">
						<a href="https://home.console.aliyun.com/#" class="dropdown-toggle topbar-btn topbar-nav-btn">
							<span class="ng-binding">产品与服务</span>
							<span class="icon-arrow-down"></span>
						</a>
					</div>
					
					<div class="topbar-info topbar-right topbar-clearfix">
						<!-- 搜索 -->
						<div class="topbar-btn topbar-btn-search topbar-left ng-isolate-scope">
							<div class="aliyun-console-topbar-search-v1_3_21 ng-scope">
								<a href="#" class="topbar-btn">
									<span class="glyphicon glyphicon-search"></span>
									<span class="ng-binding">搜索</span>
								</a>
								<div class="topbar-search-dropdown" style="display:none;">
									<input class="topbar-search-ask ng-pristine ng-valid" type="text" name="input" placeholder="搜索">
										<a target="_blank" class="topbar-search-mark glyphicon glyphicon-search" href=""></a>
								</div>
							</div>
							<!-- end ngIf: versionGreaterThan1_3_21 -->
						</div>
						<!-- 手机app -->
						<!-- ngIf: versionGreaterThan1_3_21 && navLinks.qrcode.show -->
						<div class="topbar-qrcode topbar-left ng-scope">
							<a class="topbar-btn" href="http://www.aliyun.com/app"
								target="_blank" aliyun-console-spm="7" >
								<span class="glyphicon glyphicon-phone"></span>
								<span class="ng-scope ng-binding">手机版</span>
							</a>
							<div class="topbar-qrcode-panel">
							</div>
						</div>
						<!-- 站内信 -->
						<div class="dropdown topbar-notice topbar-btn topbar-left ng-scope" >
							<a href="https://home.console.aliyun.com/#" class="dropdown-toggle topbar-btn-notice" data-toggle="dropdown">
								<span class="glyphicon glyphicon-hand-right"></span> 
								<span class="topbar-btn-notice-num ng-binding">19</span>
							</a>
							<div class="topbar-notice-panel">
								<div class="topbar-notice-arrow"></div>
								<div class="topbar-notice-head">
									<span class="ng-binding">站内消息通知</span>
								</div>
								<div class="topbar-notice-body">
									<ul class="ng-scope">
										<li class="ng-scope">
											<a target="_blank" class="clearfix" href="https://msc.console.aliyun.com/#/innerMsg/allDetail/3000220308">
												<span class="inline-block">
													<span class="topbar-notice-link ng-binding">2015云计算终极盛典——杭州•云栖大会抢先报名</span>
													<span class="topbar-notice-time ng-binding">2015-08-26</span>
												</span>
												<span class="inline-block topbar-notice-class ng-scope">
													<span class="topbar-notice-class-name ng-binding">产品动态</span>
												</span>
											</a>
									</ul>
								</div>
								<div class="topbar-notice-foot">
									<a class="topbar-notice-more ng-binding" target="_blank" href="https://msc.console.aliyun.com/#/innerMsg/unread/0">查看更多</a>
								</div>
							</div>
						</div>
						<div class="topbar-left topbar-accesskeys topbar-info-item ng-scope">
							<a href="https://ak-console.aliyun.com/" target="_blank" class="topbar-btn ng-binding">AccessKeys</a>
						</div>
						<!-- 工单 -->
						<div class="topbar-left topbar-workorder topbar-info-item ng-scope" >
							<div class="dropdown">
								<a href="https://home.console.aliyun.com/#" class="dropdown-toggle topbar-btn" data-toggle="dropdown">
									<span class="ng-binding">工单服务</span>
									<span class="glyphicon glyphicon-triangle-bottom small"></span></a>
								<ul class="dropdown-menu">
									<li class="topbar-info-btn ng-scope">
										<a href="https://workorder.console.aliyun.com/#/ticket/list/" target="_blank">
											<span class="ng-binding">我的工单</span>
										</a>
									</li>
								</ul>
							</div>
						</div>
						
						<!-- user -->
						<div class="topbar-left ng-scope" ng-if="navLinks.user.show">
							<div class="dropdown topbar-info-item">
								<a href="https://home.console.aliyun.com/#" class="dropdown-toggle topbar-btn" data-toggle="dropdown">
									<span class="ng-binding">3638*****@qq.com</span>
									<span class="glyphicon glyphicon-triangle-bottom small"></span>
								</a>
								<ul class="dropdown-menu">
									<li class="topbar-info-btn ng-scope">
										<a href="" target="_self">
											<span class="ng-binding">退出</span>
										</a>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>