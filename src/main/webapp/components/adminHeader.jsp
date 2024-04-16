<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin header</title>
<link rel="stylesheet"
	href="../resources/css/bootstrap.min.css">
<link rel="stylesheet"
	href="../css/main.css">
<c:import url="/components/jQuerys.jsp" />
<style>
 input {
 		border: none;
 		background-color: transparent;
	 	border-radius: 0; /* 라운딩 없애기 */
	 }
th{
	background-color: orange;
}
</style>
<c:import url="/components/jQuerys.jsp" />
</head>
<div class="container-fluid m-3">
	<div class="row justify-content-between align-items-center">
	    <div class="col-md-4">
	    	<a href="<c:url value="/index.jsp"/>">
	    		<img alt="logo_dark.png" src="../resources/imgs/logo.png" style="width:10rem" class="mb-2">
	    	</a>
	    </div>
	    <div class="col-md-4 column justify-content-center align-items-center">
	    	<b>${ adminId }</b>
	       	<button type="button" class="rounded-circle d-inline-block overflow-hidden p-0 mb-2" style="width: 40px; height: 40px; border: none; background-color: transparent;" data-bs-toggle="modal" data-bs-target="#exampleModal">
	  			<img src="${pageContext.request.contextPath}/uploads/profile_img/${profile_img}" alt="Image" class="img-fluid">
			</button>
			&nbsp;
			<c:if test="${ not empty sessionScope.adminId }">
			    <div class="btn-group  btn-group-sm">
					<input type="button" class="btn btn-secondary" value="로그아웃" onclick="submit_a_logout()"/>
				</div>	
			</c:if>
	    </div>
	</div>
</div>
</html>