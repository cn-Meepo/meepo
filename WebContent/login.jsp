<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<link rel="stylesheet" href="${APP_PATH}/static/css/htmleaf-demo.css">
<link rel="stylesheet" href="${APP_PATH}/static/css/style.css">
<link rel="stylesheet" href="${APP_PATH}/static/layer/skin/layer.css">
</head>
<style>
.code_img{
    height: 45px;
    width: 104px;
}
.code_input{
    width: 141px;
    float: left;
    margin-left: 8%;
 }
</style>
<body>
	<div class="htmleaf-container">
		<div class="cont_principal">
			<div class="cont_join  ">
				<div class="cont_letras">
					<p>LET 'S</p>
					<p>GET</p>
					<p>LOST</p>
				</div>
				<div class="cont_form_join">
					<h2>JOIN</h2>
					<p>用户名:</p>
					<input type="text" class="input_text" id="loginNo" />
					<p>密 码:</p>
					<input type="password" class="input_text" id="password" />
					<p>验证码:</p>
					<div > 
                    <input type="text" class="input_text code_input" id="code" placeholder="请输入验证码"/>
                    </div>	
                    <div >
                    <img alt="验证码" class="code_img" id="code_img" src="${APP_PATH}/code" > 
                    </div>	
				</div>

				<div class="cont_join_form_finish">
				</div>

				<div class="cont_btn_join">
					<a href="#" id="login">JOIN</a>
				</div>
			</div>
		</div>
	</div>
</body>
<script src="${APP_PATH}/static/js/jquery-2.1.1.min.js"></script>
<script src="${APP_PATH}/static/js/util.js"></script>
<script src="${APP_PATH}/static/js/jQuery.md5.js"></script>
<script src="${APP_PATH}/static/bootstrap-3.3.7/js/bootstrap.min.js"></script>
<script src="${APP_PATH}/static/layer/layer.js"></script>
<script>
	var t = 0;

	$("#login").click(function() {
		if (t == 0) {
			document.querySelectorAll('.cont_letras > p')[0].style.left = '200px';
			document.querySelectorAll('.cont_letras > p')[1].style.left = '-320px';
			document.querySelectorAll('.cont_letras > p')[2].style.left = '200px';
			setTimeout(function() {
				document.querySelector('.cont_join').className = 'cont_join cont_join_form_act';}, 1000);
				t++;
		} else {
			var obj = {};
			var objdata = {};			
			var loginRegx = /(^[a-zA-Z0-9_-]{6,16}$)/;
			var loginNo = $("#loginNo").val();
			if(!loginRegx.test(loginNo)){
				layer.msg("请输入正确格式的用户名")
				return false;
			}
			obj.loginNo = loginNo;
			var passRegx = /^[a-zA-Z0-9_-]{6,18}$/;
			var password = $("#password").val();
			if(!passRegx.test(password)){
				layer.msg("请输入正确格式的密码")
				return false;
			}
			obj.password = $.md5(password);;
			var code = $("#code").val();
			if(!code){
				layer.msg("请输入验证码")
				return false;
			}
			obj.code = code;
			var content = JSON.stringify(obj);
			console.log(obj)
			objdata.content = base64_encode(encodeURI(content));
 			$.ajax({
				url : "${APP_PATH}/login",
				type : "POST",
				data : objdata,
				success : function(result) {
					console.log(result);
			 		if (result.code == 100) {
						layer.msg(result.extend.msg);
						document.querySelector('.cont_form_join').style.bottom = '-420px';
						setTimeout(function() {document.querySelector('.cont_join').className = 'cont_join cont_join_form_act cont_join_finish';}, 500);
						setTimeout(function() {self.location = "getemps";}, 2500);
					}else{
						layer.msg(result.extend.msg);	
					} 

				}
			})  

		}
	})
</script>
<script type="text/javascript">  
   $("#code_img").click(function(){
	   $("#code_img").val("");
	   var imgSrc = $("#code_img");  
       var src = imgSrc.attr("src"); 
	   var rad = Math.floor(Math.random() * Math.pow(10, 8));
       //uuuy是随便写的一个参数名称，后端不会做处理，作用是避免浏览器读取缓存的链接
       $("#code_img").attr("src", src+"?uuuy="+rad);
   })  
  
</script> 

</html>