<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<!-- web路径
不以/开始的相对路径，找资源，以当前自营的路径为基准，经常容易出问题
以/开始的相对路径，找资源，以服务器的路径为标准(http://localhost:3306)需要加上项目名
 -->
<!-- 引用样式 -->
<link href="${APP_PATH}/static/bootstrap-3.3.7/css/bootstrap.min.css"
	rel="stylesheet">

<!-- 引入js -->
<script src="${APP_PATH}/static/js/jquery-2.1.1.min.js"></script>
<script src="${APP_PATH}/static/bootstrap-3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
 <label for="empName_input" class="col-sm-2 control-label">id</label>
 <div class="col-sm-10">
	<p class="form-control-static" id="empName_update_static">${user.id}</p>
</div>
 <label for="empName_input" class="col-sm-2 control-label">用户名</label>
 <div class="col-sm-10">
	<p class="form-control-static" id="empName_update_static">${user.loginNo}</p>
</div>
 <label for="empName_input" class="col-sm-2 control-label">用户状态</label>
 <div class="col-sm-10">
	<p class="form-control-static" id="empName_update_static">${user.statusInfo}</p>
</div>


</body>
</html>