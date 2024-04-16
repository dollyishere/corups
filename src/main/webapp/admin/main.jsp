<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Corups 관리자 메인 화면 : admin/main.jsp</title>
</head>
<body>
	<c:import url="/components/adminHeader.jsp" />
	<h1>${ session.adminID } 관리자님, 접속을 환영합니다.</h1>
	<input type="button" value="회원관리" onclick="location.href='<c:url value="/member/memberListServlet"/>'" />
	<input type="button" value="스터디관리" onclick="location.href='<c:url value="/member/memberListServlet"/>'" />
</body>
</html>