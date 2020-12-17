<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link rel="stylesheet" type="text/css" href="../static/css/style.css" />
</head>
<body>
	<div class="workingroom">
		<%@include file="include/menu.jsp"%>
		<table>
			<tr>
				<td>ID</td>
				<td>用户名称</td>
				<td>用户密码</td>
				<td>加密盐</td>
				<td>角色</td>
				<td>编辑</td>
				<td>删除</td>
			</tr>
			<c:forEach items="${users}" var="user">
				<tr>
					<td>${user.id}</td>
					<td>${user.name}</td>
					<td>${fn:substring(user.password,0,5)}...</td>
					<td>${fn:substring(user.salt,0,5)}...</td>
					<td><c:forEach items="${userRoles[user]}" var="role">
				${role.name} <br>
						</c:forEach></td>
					<td><a href="editUser?id=${user.id}">编辑</a></td>
					<td><a href="deleteUser?id=${user.id}">删除></a></td>
				</tr>
			</c:forEach>

		</table>

		<div class="addOrEdit">
			<form action="addUser" method="post">
				用戶名: <input type="text" name="name"><br> 密碼: <input
					type="password" name="password"><br>
				<br> <input type="submit" value="增加">
			</form>
		</div>
	</div>

</body>
</html>