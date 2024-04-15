<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>landing page: index.jsp</title>
<link rel="stylesheet"
	href="resources/css/bootstrap.min.css">
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f8f9fa;
        margin: 0;
        padding: 0;
    }
    .container {
        position: fixed;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        text-align: center;
    }
    img {
        width: 300px;
    }
    h5 {
        color: #800020; /* 버건디 계열의 색상 */
    }
    h1 {
        font-style: italic;
        color: #800020; /* 버건디 계열의 색상 */
    }
    input[type="button"] {
        margin: 10px;
        padding: 10px 20px;
        border: none;
        background-color: #800020; /* 버건디 계열의 색상 */
        color: white;
        cursor: pointer;
    }
    input[type="button"]:hover {
        background-color: #4B0082; /* 버건디 계열의 어두운 보색 */
    }
</style>
</head>
<body>
    <div class="container">
        <img alt="main_img.jpg" src="resources/imgs/main_img.jpg"><br>
        <h5>함께 만들어요</h5>
        <h1>Corups!</h1>
        <div>
            <input type="button" value="로그인" onclick="location.href='<c:url value="/member/loginServlet"/>'" />
            <input type="button" value="회원가입" onclick="location.href='<c:url value="/member/signupServlet"/>'" />
        </div>
        <input type="button" value="관리자 모드" onclick="location.href='<c:url value="/admin/adminLoginServlet"/>'" />
    </div>
</body>
</html>
