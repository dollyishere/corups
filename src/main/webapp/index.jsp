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
    background-size: cover; /* 이미지를 화면에 꽉 채우도록 설정 */
    background-repeat: no-repeat; /* 이미지 반복 없음 */
    background-position: center; /* 이미지를 가운데 정렬 */
    text-align: center;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: transparent;
}
</style>
</head>
<body>
<div class="row justify-content-center align-items-center">
	    <div class="col-md-3">
	    	<img alt="logo.png" src="resources/imgs/logo.png" class="mb-2">
	    </div>
	    <div class="col-md-4">
	    </div>
        <div class="col-md-5">
            <!-- 입력 폼을 감싸는 테두리가 둥근 직사각형 -->
            <div class="custom-form text-center" style="width: 20rem;"> <!-- text-center 클래스 추가 -->
                <div class="mb-2">
 						<input type="button" value="로그인" class="btn" style="background-color:#D996B5; color:white;" onclick="location.href='<c:url value="/member/loginServlet"/>'" />
                    </div>
                    <div class="mb-2">
                        <input type="button" value="회원가입" class="btn" style="background-color:#D2C1D9; color:white;" onclick="location.href='<c:url value="/member/signupServlet"/>'" />
                    </div>
                    <div class="mb-2">
                    	<input type="button" value="관리자 모드" class="btn" style="background-color:#BAC2D9; color:white;" onclick="location.href='<c:url value="/admin/adminLoginServlet"/>'" />
                    </div>
            </div>
        </div>
	 </div>
</body>
</html>
