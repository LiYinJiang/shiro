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
		<div class="addOrEdit">
			<form action="updateRole" method="post">
				角色名:<input type="text" name="name" value="${role.name }"><br>
				角色描述:<input type="text" name="desc" value="${role.desc }"><br>
				<br> 配置权限:<br>

				<div
					style="text-align: left; width: 300px; margin: 0px auto; padding-left: 50px">
					<c:forEach items="${permissions }" var="permission">
						<c:set var="hasPermission" value="false" />
						<c:forEach items="${currentPermissions }" var="currentPermission">
							<c:if test="${permission.id = currentPermisson.id}">
								<c:set var="hasPermission" value="true" />
							</c:if>
						</c:forEach>
						<input type="checkbox" ${hasPermission?"checked='checked'":"" }
							name="permissionId" value="${permission.id }">
					${permission.name } <br>

					</c:forEach>
				</div>
			</form>
			<input type="hidden" name="id" value="${role.id }"><br>
			<input type="submit" value="修改"><br>
		</div>
	</div>
</body>
</html>