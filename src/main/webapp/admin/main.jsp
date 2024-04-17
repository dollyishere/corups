<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<meta charset="UTF-8">
<title>Corups 관리자 메인 화면 : admin/main.jsp</title>
<style>
.material-symbols-outlined {
  font-size: 100px; /* 아이콘의 크기를 48px로 설정합니다 */
  color: white; /* 아이콘의 색상을 파란색으로 설정합니다 */
  font-variation-settings:
  'FILL' 0,
  'wght' 400,
  'GRAD' 0,
  'opsz' 24
}
</style>
</head>
<body>
	<div class="container-fluid m-5">
		<c:import url="/components/adminHeader.jsp" />
		<!-- 환영인사 -->
		<h3><b style="color:#F0EAEA;">관리자님, 접속을 환영합니다.</b></h3>
		<h5 style="color:#E7DFDD;" class="mb-5">원하시는 작업을 선택해주세요.</h5>
		<div class="container mt-5">
			<div class="row justify-content-center align-items-flex-center mt-5">
				<div class="col-md-auto mb-3">
				    <div class="custom-form d-flex justify-content-center align-items-center" style="width: 20rem; height: 20rem;">
				    <!-- 회원 관리 페이지 이동 버튼 -->
				    	<div class="col-md-auto m-3">
					    	<div class="material-symbols-outlined">
								manage_accounts
							</div>
				    	</div>
				    	<div class="col-md-auto m-3">
				    		<input type="button" value="회원관리" onclick="location.href='<c:url value="/member/memberListServlet"/>'" class="btn btn-outline-light" style="font-weight: bold;" />
				    	</div>
				    </div>
				</div>
				<div class="col-md-1">
				</div>
				<div class="col-md-auto mb-3">
					<!-- 스터디 관리 페이지 이동 버튼 -->
				    <div class="custom-form d-flex justify-content-center align-items-center" style="width: 20rem; height: 20rem;">
				    	<div class="col-md-auto m-3">
					    	<div class="material-symbols-outlined">
					            crowdsource
					        </div>
					        <br>
				    	</div>
				    	<div class="col-md-auto m-3">
				    		<input type="button" value="스터디관리" onclick="location.href='<c:url value="/member/memberListServlet"/>'" class="btn btn-outline-light" style="font-weight: bold;" />
				    	</div>
				    </div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>