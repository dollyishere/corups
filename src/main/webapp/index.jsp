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
<link rel="stylesheet"
	href="css/main.css">
<style>
	body {
	    background-image: url('resources/imgs/snow.jpg');
	}
</style>
</head>
<body>
 <div class="container-fluid h-100">
        <div class="row justify-content-center align-items-center h-100">
            <div class="col-md-auto">
                <img alt="logo.png" src="resources/imgs/logos/logo.png" class="mb-2">
            </div>
            <div class="col-md-2">
            </div>
            <div class="col-md-auto">
            <br>
                <div class="custom-form text-center" style="width: 20rem;"> <!-- text-center 클래스 추가 -->
                     <div class="mb-2">
							<input type="button" value="login" class="btn" style="background-color:#D996B5; color:white; width:120px;" onclick="location.href='<c:url value="/member/loginServlet"/>'" />
		              </div>
		              <div class="mb-2">
		                  <input type="button" value="sign up" class="btn" style="background-color:#D2C1D9; color:white; width:120px;" onclick="location.href='<c:url value="/member/signupServlet"/>'" />
		              </div>
		              <div class="mb-2">
		              	<input type="button" value="admin mode" class="btn" style="background-color:#B9A4BF ; color:white; width:120px;" onclick="location.href='<c:url value="/admin/adminLoginServlet"/>'" />
		              </div>
                </div>
                   <br>
            </div>
        </div>
    </div>
</body>
</html>
