<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath }"></c:set>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입 화면: signup.jsp</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet"
	href="../resources/css/bootstrap.min.css">
<link rel="stylesheet"
	href="../css/main.css">
<c:import url="/components/jQuerys.jsp" />
<script>
	document.addEventListener("DOMContentLoaded", function() {
		document.getElementById("image").addEventListener("change", function() {
			console.log("추적중...");
			if (this.files && this.files[0]) {
				console.log("성공!");
				var reader = new FileReader();
				reader.onload = function(e) {
					$("#imagePreview").html('<img alt="imagePreview" src="'+ e.target.result + '" class="img-fluid" />');
					console.log($("#imgPreview"))
				};
				reader.readAsDataURL(this.files[0]);
			};
		});
	});
	
	/* 비번 확인 */
	$(document).ready(function() {
		// 암호 확인 기능 구현
		$("#pwd_check").keyup(function() {
			if($("#pwd").val() !== $("#pwd_check").val()) {
				$("#pwd_message").text("");
				$("#pwd_message").html("<b>비밀번호가 틀립니다.</b>");
				$("#form-submit").prop("disabled", true);
			} else {
				$("#pwd_message").text("");
				$("#pwd_message").html("<b>비밀번호가 맞습니다.</b>");
				$("#form-submit").prop("disabled", false);
			}
		})
		
		$("#pwd").keyup(function() {
			$("#pwd_message").text("");
			$("#form-submit").prop("disabled", true);
		})
	})
	
	
	
</script>
</head>
<body>
	<div class="container-fluid h-100">
			<div class="row justify-content-center align-items-center h-100">
		    	<div class="col-md-auto">
		    		<img alt="logo.png" src="../resources/imgs/logo.png" class="mb-2">
		   		</div>
			   <div class="col-md-1">
		       </div>
		        <div class="col-md-auto">
		        	<!-- 회원가입 폼 -->
		            <div class="custom-form text-center" style="width: 30rem;">
		                <form enctype="multipart/form-data" accept-charset="UTF-8">
		                	<!-- 프로필 이미지 -->
		                 	<div class="mb-2">
		                       	<div  class="rounded-circle d-inline-block overflow-hidden p-0" style="width: 160px; height:160px; border: none; background-color: black;" id="imagePreview"></div>
		                        <input type="file" name="image" id="image" accept="image/*" class="form-control" >
		                    </div>
		                    <!-- id -->
		                     <div class="mb-2 row  justify-content-start align-items-center">
							    <div class="col-md-3">
							        <label for="exampleInputId" class="form-label" style="color:#292929;"><b>Id</b></label>
							    </div>
							    <div class="col-md-auto">
							        <input type="text" name="id" id="id" size="20" maxlength="20"  class="form-control" required />
							    </div>
							    <!-- id 중복 체크 -->
							    <div class="col-md-3">
							        <input type="button" name="confirmDuplicate" id="double" onclick="fn_process()" value="ID check" class="btn" style="background-color:#D996B5; color:white;" id="form-submit" />
							    </div>
							</div>
							<!-- 비밀번호 -->
		                    <div class="mb-2 row  justify-content-start align-items-center">
		                    	<div class="col-md-3">
		                        <label for="exampleInputPassword1" class="form-label" style="color:#292929;"><b>Password</b></label>
		                        </div>
		                        <div class="col-md-auto">
		                        	<input type="password" name="pwd" id="pwd" size="20" maxlength="20" class="form-control"  required disabled />
		                        </div>
		                        <div class="col-md-3">
							    </div>
		                    </div>
		                    <!-- 비밀번호 체크 -->
		                    <div class="mb-2 row  justify-content-start align-items-center">
		                    	<div class="col-md-3">
		                         <label for="exampleInputPassword2" class="form-label" style="color:#292929;"><b>Password Check</b></label>
		                        </div>
		                        <div class="col-md-auto">
		                       		<input type="password" name="pwd_check" id="pwd_check" size="20" maxlength="20" class="form-control" required disabled />
		                        </div>
		                        <div class="col-md-3">
		                        	<span id="pwd_message"></span>
							    </div>
		                    </div>
		                    <!-- 이름 -->
		                    <div class="mb-2 row  justify-content-start align-items-center">
		                    	<div class="col-md-3">
		                         <label for="exampleInputName" class="form-label" style="color:#292929;"><b>Name</b></label>
		                        </div>
		                        <div class="col-md-auto">
		                        	<input type="text" name="name" size="20" maxlength="20"  class="form-control" required />
		                        </div>
		                        <div class="col-md-3">
							    </div>
		                    </div>
		                    <!-- 생일 -->
		                    <div class="mb-2 row  justify-content-start align-items-center">
		                    	<div class="col-md-3">
		                         <label for="exampleInputName" class="form-label" style="color:#292929;"><b>Birthday</b></label>
		                        </div>
		                        <div class="col-md-auto">
		                        	<input type="date" name="birthday" size="10"  class="form-control" required />
		                        </div>
		                        <div class="col-md-3">
							    </div>
		                    </div>
		                    <!-- 이메일 -->
		                   	<div class="mb-2 row  justify-content-start align-items-center">
		                    	<div class="col-md-3">
		                         <label for="exampleInputEmail" class="form-label" style="color:#292929;"><b>Email</b></label>
		                        </div>
		                        <div class="col-md-auto">
		                        	<input type="email" name="email" size="30" maxlength="30" class="form-control" required />
		                        </div>
		                        <div class="col-md-3">
							    </div>
		                    </div>
		                    <!-- 직업 선택 -->
		                    <div class="mb-2 row  justify-content-start align-items-center">
		                    	<div class="col-md-3">
		                         <label for="exampleInputJob" class="form-label" style="color:#292929;"><b>Job</b></label>
		                        </div>
		                        <div class="col-md-auto">
			                        <select name="job" class="form-select" required >
									  <option value="" selected disabled>선택하세요</option>
									  <option value="unemployed">무직</option>
									  <option value="student">교직학생</option>
									  <option value="homemaker">주부</option>
									  <option value="employee">직장인</option>
									  <option value="entrepreneur">사업가</option>
									</select>
		                        </div>
		                        <div class="col-md-3">
							    </div>
		                    </div>
							<!-- 흥미 -->
		                   <div class="mb-2 row  justify-content-start align-items-center">
		                    	<div class="col-md-3">
		                         <label for="exampleInputPassword2" class="form-label" style="color:#292929;"><b>Interests</b></label>
		                        </div>
		                        <div class="col-md-auto">
		                        	<label><input type="checkbox" class="form-check-input"  name="interests" value="r">독서</label>
									<label><input type="checkbox" class="form-check-input" name="interests" value="t">여행</label>
									<label><input type="checkbox" class="form-check-input" name="interests" value="g">게임</label>
									<label><input type="checkbox" class="form-check-input" name="interests" value="m">영화</label>
									<label><input type="checkbox" class="form-check-input" name="interests" value="e">운동</label>
									<br>
									<label><input type="checkbox" class="form-check-input" name="interests" value="c">요리</label>
									<label><input type="checkbox" class="form-check-input" name="interests" value="p">프로그래밍</label>
									<label><input type="checkbox" class="form-check-input" name="interests" value="s">노래</label>
									<label><input type="checkbox" class="form-check-input" name="interests" value="l">어학</label>
									<label><input type="checkbox" class="form-check-input" name="interests" value="o">기타</label>
		                        </div>
		                    </div>
		                    <!-- 제출 버튼 or 리셋 버튼 -->
		                    <div class="mb-2 d-flex justify-content-end">
		                        <input type="button" value="signup" class="btn" style="background-color:#B9A4BF; color:white;" id="form-submit" disabled onclick="submit_form()"/>
		                        &nbsp;
		                        <input type="reset" value="reset" class="btn btn-secondary"/>
		                    </div>
		                </form>
		            </div>
		        </div>
		 </div>
	</div>
</body>
</html>