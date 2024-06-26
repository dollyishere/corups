<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="username" value="${sessionScope.name}" />
<c:set var="profile_img" value="${sessionScope.img}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>default header</title>
<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap.min.css" />">
<link rel="stylesheet"
	href="<c:url value="/css/main.css" />">
<c:import url="/components/jQuerys.jsp" />
 <style>
    /* 이미지를 둥근 원으로 만들기 */
    .rounded-circle img {
      border-radius: 50%;
    }

    /* 클릭 시 반응 스타일 */
    .rounded-circle:hover {
      opacity: 0.8;
    }
</style>
</head>
<div class="container-fluid m-3">
	<div class="row justify-content-between align-items-center">
	    <div class="col-md-4">
	    	<!-- 로고(메인 화면으로 이동) -->
	    	<a href="<c:url value="/member/memberDetailServlet" /> ">
	    		<img alt="logo.png" src="<c:url value="/resources/imgs/logos/logo.png" />" style="width:10rem" class="mb-2">
	    	</a>
	    </div>
	    <div class="col-md-4 column justify-content-center align-items-center">
	    	<b>${ name }</b>
	       	<button type="button" class="rounded-circle d-inline-block overflow-hidden p-0 mb-2" style="width: 40px; height: 40px; border: none; background-color: ${profile_img == '0_p_img.jpg' ? '#292929' : 'transparent'};" data-bs-toggle="modal" data-bs-target="#exampleModal">
	  			<img src="${pageContext.request.contextPath}/uploads/profile_img/${profile_img}" alt="" class="img-fluid">
			</button>
			&nbsp;
			<!-- 우측 버튼 -->
			<c:if test="${ not empty sessionScope.id }">
			    <div class="btn-group  btn-group-sm">
			    	<!-- 로그아웃 -->
					<input type="button" class="btn btn-secondary" value="로그아웃" onclick="submit_logout()"/>
					<!-- 정보수정 -->
					<input type="button" class="btn" style="background-color:#D996B5; color:white;" value="회원수정" onclick="location.href='<c:url value="/member/updateServlet"/>'" />
					<!-- 회원탈퇴 -->
					<input type="button" class="btn btn-danger" value="회원탈퇴" onclick="confirmDelete()">
				</div>	
			</c:if>
	    </div>
	</div>
</div>
</html>

	  