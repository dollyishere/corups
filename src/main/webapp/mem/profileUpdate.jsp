<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정 화면: profileUpdate.jsp</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet"
	href="../resources/css/bootstrap.min.css">
<link rel="stylesheet"
	href="../css/main.css">
<c:import url="/components/jQuerys.jsp" />
<script>
	window.onload = function() {
		// 이미지 파일 자동 배정
		var filePath = "${pageContext.request.contextPath}/uploads/profile_img/${ member.image }";
		
		var fileInput = document.getElementById("image");
		var file = new File([null], filePath, { type: "image/*" });
		
		var fileList = new DataTransfer();
		fileList.items.add(file);
		
		fileInput.files = fileList.files;
	};
	
	document.addEventListener("DOMContentLoaded", function() {
		document.getElementById("image").addEventListener("change", function() {
			console.log("추적중...");
			if (this.files && this.files[0]) {
				console.log("성공!");
				$("#preImg").html('');
				var reader = new FileReader();
				reader.onload = function(e) {
					$("#imagePreview").html('');
					$("#imagePreview").html('<img alt="imagePreview" src="'+ e.target.result + '" class="img-fluid" />');
					console.log($("#imgPreview"));
				};
				reader.readAsDataURL(this.files[0]);

			} else {
				console.log("실패!");
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
			$("#pwd_check").prop("disabled", false);
			$("#pwd_check").prop("required", true);
			$("#form-submit").prop("disabled", true);
		})
	})
	
</script>
</head>
<body>
		<div class="container-fluid m-5">
			<c:import url="/components/defaultHeader.jsp" />
			<div class="container-fluid m-5">
			<div class="row justify-content-center align-items-center">
		    	<div class="col-md-auto">
		    		<img alt="logo.png" src="../resources/imgs/logo.png" class="mb-2">
		   		</div>
			   <div class="col-md-1">
		       </div>
		        <div class="col-md-auto">
		            <div class="custom-form text-center" style="width: 30rem;"> <!-- text-center 클래스 추가 -->
		                <form enctype="multipart/form-data" accept-charset="UTF-8">
		                 	<div class="mb-2">
		                 			<div class="rounded-circle d-inline-block overflow-hidden p-0" style="width: 160px; height:160px; border: none; background-color: black;" id="imagePreview">
										<img alt="imagePreview" id="imagePreview" src="${pageContext.request.contextPath}/uploads/profile_img/${ member.image }" class="img-fluid" />
									</div>
									<input type="file" name="image" id="image" accept="image/*" class="form-control" >
									<div id="preImg">
										<input type="hidden" name="preImg" value="${ member.image }">
									</div>
		                    </div>
		                     <div class="mb-2 row  justify-content-start align-items-center">
							    <div class="col-md-3">
							        <label for="exampleInputId" class="form-label" style="color:#292929;"><b>Id</b></label>
							    </div>
							    <div class="col-md-auto">
							        	<input type="text" size="20" maxlength="20" value="${ member.id }" class="form-control" readonly disabled />
										<input type="hidden" name="id" id="id" size="20" maxlength="20" value="${ member.id }" />
							    </div>
							    <div class="col-md-3">
							   
							    </div>
							</div>
		                    <div class="mb-2 row  justify-content-start align-items-center">
		                    	<div class="col-md-3">
		                        <label for="exampleInputPassword1" class="form-label" style="color:#292929;"><b>Password</b></label>
		                        </div>
		                        <div class="col-md-auto">
		                        	<input type="password" name="pwd" id="pwd" size="20" maxlength="20" value="${ member.pwd }" class="form-control" required/>
		                        </div>
		                        <div class="col-md-3">
							    </div>
		                    </div>
		                    <div class="mb-2 row  justify-content-start align-items-center">
		                    	<div class="col-md-3">
		                         <label for="exampleInputPassword2" class="form-label" style="color:#292929;"><b>Password Check</b></label>
		                        </div>
		                        <div class="col-md-auto">
		                       		<input type="password" name="pwd_check" id="pwd_check" size="20" maxlength="20" class="form-control" disabled />
		                        </div>
		                        <div class="col-md-3">
		                        	<span id="pwd_message"></span>
							    </div>
		                    </div>
		                    <div class="mb-2 row  justify-content-start align-items-center">
		                    	<div class="col-md-3">
		                         <label for="exampleInputName" class="form-label" style="color:#292929;"><b>Name</b></label>
		                        </div>
		                        <div class="col-md-auto">
		                        	<input type="text" name="name" size="20" maxlength="20" value="${ member.name }" class="form-control" required />
		                        </div>
		                        <div class="col-md-3">
							    </div>
		                    </div>
		                    <div class="mb-2 row  justify-content-start align-items-center">
		                    	<div class="col-md-3">
		                         <label for="exampleInputName" class="form-label" style="color:#292929;"><b>Birthday</b></label>
		                        </div>
		                        <div class="col-md-auto">
		                        		<input type="date" name="birthday" size="10" value="${ member.birthday }" class="form-control" required />
		                        </div>
		                        <div class="col-md-3">
							    </div>
		                    </div>
		                   	<div class="mb-2 row  justify-content-start align-items-center">
		                    	<div class="col-md-3">
		                         <label for="exampleInputEmail" class="form-label" style="color:#292929;"><b>Email</b></label>
		                        </div>
		                        <div class="col-md-auto">
		                        	<input type="email" name="email" size="30" maxlength="30" value="${ member.email }" class="form-control" required />
		                        </div>
		                        <div class="col-md-3">
							    </div>
		                    </div>
		                    <div class="mb-2 row  justify-content-start align-items-center">
		                    	<div class="col-md-3">
		                         <label for="exampleInputJob" class="form-label" style="color:#292929;"><b>Job</b></label>
		                        </div>
		                        <div class="col-md-auto">
			                        <select name="job" class="form-select" required >
										<option value="" disabled>선택하세요</option>
							 	 		<option value="unemployed" ${ member.job eq 'unemployed' ? selected : '' }>무직</option>
							  			<option value="student" ${ member.job eq 'student' ? selected : '' }>교직학생</option>
							  			<option value="homemaker" ${ member.job eq 'homemaker' ? selected : '' }>주부</option>
							 			<option value="employee" ${ member.job eq 'employee' ? selected : '' }>직장인</option>
							  			<option value="entrepreneur" ${ member.job eq 'entrepreneur' ? selected : '' }>사업가</option>
									</select>
		                        </div>
		                        <div class="col-md-3">
							    </div>
		                    </div>
	
		                   <div class="mb-2 row  justify-content-start align-items-center">
		                    	<div class="col-md-3">
		                         <label for="exampleInputPassword2" class="form-label" style="color:#292929;"><b>Interests</b></label>
		                        </div>
		                        <div class="col-md-auto">
		                        		<label><input type="checkbox" class="form-check-input" name="interests" value="r" ${fn:contains(member.interest, 'r') ? 'checked' : '' }>독서</label>
										<label><input type="checkbox" class="form-check-input" name="interests" value="t"  ${fn:contains(member.interest, 't') ? 'checked' : '' }>여행</label>
										<label><input type="checkbox" class="form-check-input" name="interests" value="g"  ${fn:contains(member.interest, 'g') ? 'checked' : '' }>게임</label>
										<label><input type="checkbox" class="form-check-input" name="interests" value="m"  ${fn:contains(member.interest, 'm') ? 'checked' : '' }>영화</label>
										<label><input type="checkbox" class="form-check-input" name="interests" value="e"  ${fn:contains(member.interest, 'e') ? 'checked' : '' }>운동</label>
										<br>
										<label><input type="checkbox" class="form-check-input" name="interests" value="c"  ${fn:contains(member.interest, 'c') ? 'checked' : '' }>요리</label>
										<label><input type="checkbox" class="form-check-input" name="interests" value="p"  ${fn:contains(member.interest, 'p') ? 'checked' : '' }>프로그래밍</label>
										<label><input type="checkbox" class="form-check-input" name="interests" value="s"  ${fn:contains(member.interest, 's') ? 'checked' : '' }>노래</label>
										<label><input type="checkbox" class="form-check-input" name="interests" value="l"  ${fn:contains(member.interest, 'l') ? 'checked' : '' }>어학</label>
										<label><input type="checkbox" class="form-check-input" name="interests" value="o"  ${fn:contains(member.interest, 'o') ? 'checked' : '' }>기타</label>
		                        </div>
		                    </div>
		                    <div class="mb-2 d-flex justify-content-end">
		                        <input type="button" value="update" class="btn" style="background-color:#B9A4BF; color:white;" id="form-submit" onclick="submit_update_form()" />
		                        &nbsp;
		                        <input type="reset" value="reset" class="btn btn-secondary"/>
		                    </div>
		                </form>
		            </div>
		        </div>
		 </div>
			</div>
	</div>
</body>
</html>