<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" type="text/css" href="../static/css/style.css" />

</head>
<body>
	<div class="workingroom">
		<%@include file="include/menu.jsp"%>
		<table>
			<tr>
				<td>id</td>
				<td>权限名称</td>
				<td>权限描述</td>
				<td>权限对应路径</td>
				<td>修改</td>
				<td>删除</td>
			</tr>
			<c:forEach items="${permissions }" var="p">
				<tr>
					<td>${p.id }</td>
					<td>${p.name}</td>
					<td>${p.desc}</td>
					<td>${p.url}</td>
					<td><a href="editPermission?id=${p.id }" />修改</td>
					<td><a href="deletePermission?id=${p.id }" />删除</td>
				</tr>
			</c:forEach>
		</table>

		<div class="addOrEdit">
			<form action="addPermission" method="post">
				权限名称:<input type="text" name="name" value="${permission.name }">
				<br> 权限描述:<input type="text" name="desc"
					value="${permission.desc }"> <br> 
					权限对应的URL: <input
					type="text" name="url" value="${permission.url }"> <br>
				<br> <input type="submit" name="提交"> <br>
			</form>
		</div>
	</div>
</body>
</html>
