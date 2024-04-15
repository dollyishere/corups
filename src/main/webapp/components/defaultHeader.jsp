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
	href="../resources/css/bootstrap.min.css">
<link rel="stylesheet"
	href="../css/main.css">
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
<body>
	<div class="row justify-content-between align-items-center">
	    <div class="col-md-3">
	    	<img alt="logo.png" src="../resources/imgs/logo.png" style="width:10rem"class="mb-2">
	    </div>
	    <div class="col-md-4">
        <button type="button" class="rounded-circle d-inline-block overflow-hidden p-0" style="width: 40px; height: 40px; border: none; background-color: transparent;" data-bs-toggle="modal" data-bs-target="#exampleModal">
   			<img src="${pageContext.request.contextPath}/uploads/profile_img/${profile_img}" alt="Image" class="img-fluid">
		</button>
	   	<table>
			<tr>
			    <c:if test="${ not empty sessionScope.id }">
			      	<th>
			    		<input type="button" value="로그아웃" onclick="submit_logout()"/>
			    	</th>
					<th><input type="button" value="회원수정" onclick="location.href='<c:url value="/member/updateServlet"/>'" /></th>
					<th><input type="button" value="회원탈퇴" onclick="confirmDelete()"></th>
			    </c:if>
			</tr>
		</table>  
	    </div>
<!-- Button trigger modal -->

	</div>
</body>


</html>