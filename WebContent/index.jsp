<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" type="text/css" href="static/css/style.css" />


</head>
<body>


<div class="workingroom">
	<div class="loginDiv">
	
	

	<c:if test="${empty subject.principal}">
		<a href="login.jsp">登录</a><br>
	</c:if>
	<c:if test="${!empty subject.principal}">
		<span class="desc">你好，${subject.principal}，</span>
		<a href="doLogout">退出</a><br>	
	</c:if>
		
	<a href="listCode.jsp">查看产品</a><span class="desc">(登录后才可以查看) </span><br>
	<a href="deleteCode.jsp">删除产品</a><span  class="desc">(要有产品管理员角色, Gaki没有，Tinker 有) </span><br>
	<a href="deleteFeature.jsp">删除订单</a><span class="desc">(要有删除功能的权限, Gaki有，Tinker没有) </span><br>
</div>









</body>
</html>