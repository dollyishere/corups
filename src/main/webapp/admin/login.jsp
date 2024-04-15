<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${ not empty sessionScope.adminId  }">
	<% response.sendRedirect(request.getContextPath() + "/admin/main.jsp"); %>
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 로그인: admin/login.jsp </title>
<c:import url="/components/jQuerys.jsp" />
</head>
<body>
	<form method="post">
		<table border="1">
			<tr>
				<td colspan="2">관리자 로그인</td>
			</tr>
			<tr>
				<td>Admin ID</td>
				<td><input type="text" name="id" required /></td>
			</tr>
			<tr>
				<td>Admin Password</td>
				<td><input type="password" name="pwd" required /></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" value="login" onclick="submit_a_login_form()"/>
					<input type="reset" value="reset"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>