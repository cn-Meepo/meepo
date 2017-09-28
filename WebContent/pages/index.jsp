<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>首页</title>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<link rel="icon" type="image/png"
	href="${APP_PATH}/static/admin/assets/i/favicon.png">
<link rel="apple-touch-icon-precomposed"
	href="${APP_PATH}/static/admin/assets/i/app-icon72x72@2x.png">
<meta name="apple-mobile-web-app-title" content="Amaze UI" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="${APP_PATH}/static/css/htmleaf-demo.css">
<link rel="stylesheet" href="${APP_PATH}/static/css/style.css">
<link rel="stylesheet" href="${APP_PATH}/static/layer/skin/layer.css">
<link rel="stylesheet" href="${APP_PATH}/static/layer/skin/layer.css">
<link rel="stylesheet"
	href="${APP_PATH}/static/admin/assets/css/amazeui.min.css" />
<link rel="stylesheet"
	href="${APP_PATH}/static/admin/assets/css/admin.css">
<script src="${APP_PATH}/static/admin/assets/js/jquery.min.js"></script>
<script src="${APP_PATH}/static/admin/assets/js/app.js"></script>
<script src="${APP_PATH}/static/layer/layer.js"></script>
</head>

<body>
	<header class="am-topbar admin-header">
	<div class="am-topbar-brand">
		<img src="${APP_PATH}/static/admin/assets/i/logo.png">
	</div>


	<div
		style="float: right; font-size: 15px; margin-top: 10px; margin-right: 15px">
		<a href="#" id="logout">注销</a>
	</div>

	</header>

	<div class="am-cf admin-main">

		<div class="nav-navicon admin-main admin-sidebar">

			<div class="sideMenu am-icon-dashboard"
				style="color: #aeb2b7; margin: 10px 0 0 0;">欢迎系统管理员：${URL}</div>
			<div class="sideMenu">
				<h3 class="am-icon-flag">
					<em></em> <a href="#">个人中心</a>
				</h3>
				<ul>
					<li><a href="viewSelf" target="content">个人信息</a></li>
				</ul>
	<!-- 			<h3 class="am-icon-cart-plus">
					<em></em> <a href="#"> 订单管理</a>
				</h3>
				<ul>
					<li>订单列表</li>
					<li>合并订单</li>
					<li>订单打印</li>
					<li>添加订单</li>
					<li>发货单列表</li>
					<li>换货单列表</li>
				</ul> -->
				<h3 class="am-icon-users">
					<em></em> <a href="#">会员管理</a>
				</h3>
				<ul>
					<li><a href="toUsers" target="content">会员列表</a></li>
					<li>未激活会员</li>
					<li>团队系谱图</li>
					<li>会员推荐图</li>
					<li>推荐列表</li>
				</ul>
				<h3 class="am-icon-volume-up">
					<em></em> <a href="#">信息通知</a>
				</h3>
				<ul>
					<li>站内消息 /留言</li>
					<li>短信</li>
					<li>邮件</li>
					<li>微信</li>
					<li>客服</li>
				</ul>
				<h3 class="am-icon-gears">
					<em></em> <a href="#">系统设置</a>
				</h3>
				<ul>
					<li>数据备份</li>
					<li>邮件/短信管理</li>
					<li>上传/下载</li>
					<li>权限</li>
					<li>网站设置</li>
					<li>第三方支付</li>
					<li>提现 /转账 出入账汇率</li>
					<li>平台设置</li>
					<li>声音文件</li>
				</ul>
			</div>
			<!-- sideMenu End -->

			<script type="text/javascript">
				jQuery(".sideMenu").slide({
					titCell : "h3", //鼠标触发对象
					targetCell : "ul", //与titCell一一对应，第n个titCell控制第n个targetCell的显示隐藏
					effect : "slideDown", //targetCell下拉效果
					delayTime : 300, //效果时间
					triggerTime : 150, //鼠标延迟触发时间（默认150）
					defaultPlay : true, //默认是否执行效果（默认true）
					returnDefault : true
				//鼠标从.sideMen移走后返回默认状态（默认false）
				});
			</script>

		</div>

		<div class=" admin-content">

			<div class="daohang"></div>

			<div class="admin">
				<div id="content" style="padding: 0; height: 95.2%">
					<iframe name="content" id="index-content" width="100%"
						height="100%" src="${APP_PATH}/pages/main.jsp" scrolling="no"
						frameborder="0" style="border: 0px;"> </iframe>
				</div>

				<div class="foods">
					<ul>版权所有@2015
					</ul>
				</div>

			</div>

		</div>

	</div>
	<script src="${APP_PATH}/static/admin/assets/js/amazeui.min.js"></script>

</body>
<script>
$("#logout").click(function(){
	layer.confirm('您确定要注销登录吗？', {
		  btn: ['确定','取消'] //按钮
		}, function(){
			$.ajax({
				url:"${APP_PATH}/logout",
				type:"GET",
				success:function(data){
					console.log(data);
					layer.msg(data.extend.msg);
					setTimeout(function() {self.location="${APP_PATH}/login.jsp";}, 1500);
					;
				}
			})
		})
})

</script>
</html>