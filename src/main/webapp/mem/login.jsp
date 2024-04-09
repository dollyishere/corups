<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 로그인 페이지 : login.jsp</title>
<style>
	table {
		text-align: center;
	}
</style>
</head>
<body>
	<form action="<c:url value="/member/LoginServlet" />" method="post">
		<table border="1">
			<tr>
				<td colspan="2">로그인</td>
			</tr>
			<tr>
				<td>ID</td>
				<td><input type="text" name="id" required /></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="pwd" required /></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="login"/>
					<input type="reset" value="reset"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>