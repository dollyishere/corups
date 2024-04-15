<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정 화면: profileUpdate.jsp</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
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
					$("#imagePreview").html('<img alt="imagePreview" src="'+ e.target.result + '" />');
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
	<c:import url="/components/jQuerys.jsp" />
</head>
<body>

	<form action="<c:url value="/member/updateServlet" />" method="post" enctype="multipart/form-data" accept-charset="UTF-8">
		<input type="hidden" name="nowPath" value="a"/>
		<input type="hidden" name="nowCase" value="r"/>
		<table border="1" >
			<colgroup>
				<col width="150" />
				<col width="400" />
				<col width="200" />
			</colgroup>
			<!-- 회원수정 제목 -->
			<thead>
				<tr><th align="center" colspan="2">${ member.name } 회원님의 정보를 수정합니다.</th></tr>
			</thead>
			<!-- 회원수정 입력 -->
			<tbody>
				<tr>
					<td>프로필 사진</td>
					<td>
						<div style="width='400px';"  id="imagePreview">
							<img alt="imagePreview" id="imagePreview" src="${pageContext.request.contextPath}/uploads/profile_img/${ member.image }" />
						</div>
						<input type="file" name="image" id="image" accept="image/*" value="${ member.image }">
						<div id="preImg">
							<input type="hidden" name="preImg" value="${ member.image }">
						</div>
					</td>
				</tr>
				<tr>
					<td>아이디</td>
					<td>
						<input type="text" size="20" maxlength="20" value="${ member.id }" readonly disabled />
						<input type="hidden" name="id" id="id" size="20" maxlength="20" value="${ member.id }" />
					</td>
				</tr>
				<tr>
					<td>패스워드</td>
					<td>
						<input type="password" name="pwd" id="pwd" size="20" maxlength="20" value="${ member.pwd }" required/>
					</td>
				</tr>
				<tr>
					<td>패스워드 확인</td>
					<td>
						<input type="password" name="pwd_check" id="pwd_check" size="20" maxlength="20" disabled />
						&nbsp;
						<span id="pwd_message"></span>
					</td> 
				</tr>
				<tr>
					<td>이름</td>
					<td>
						<input type="text" name="name" size="20" maxlength="20" value="${ member.name }" required />
					</td>
				</tr>
				<tr>
					<td>생년월일</td>
					<td>
						<input type="date" name="birthday" size="10" value="${ member.birthday }" required />
					</td>
				</tr>
				<tr>
					<td>이메일</td>
					<td>
						<input type="email" name="email" size="30" maxlength="30" value="${ member.email }" required />
					</td>
				</tr>
				<tr>
					<td>직업</td>
					<td>
						<select name="job" required >
						  <option value="" disabled>선택하세요</option>
						  <option value="unemployed" ${ member.job eq 'unemployed' ? selected : '' }>무직</option>
						  <option value="student" ${ member.job eq 'student' ? selected : '' }>교직학생</option>
						  <option value="homemaker" ${ member.job eq 'homemaker' ? selected : '' }>주부</option>
						  <option value="employee" ${ member.job eq 'employee' ? selected : '' }>직장인</option>
						  <option value="entrepreneur" ${ member.job eq 'entrepreneur' ? selected : '' }>사업가</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>관심사</td>
					<td>
						<label><input type="checkbox" name="interests" value="r" ${fn:contains(member.interest, 'r') ? 'checked' : '' }>독서</label>
						<label><input type="checkbox" name="interests" value="t"  ${fn:contains(member.interest, 't') ? 'checked' : '' }>여행</label>
						<label><input type="checkbox" name="interests" value="g"  ${fn:contains(member.interest, 'g') ? 'checked' : '' }>게임</label>
						<label><input type="checkbox" name="interests" value="m"  ${fn:contains(member.interest, 'm') ? 'checked' : '' }>영화</label>
						<label><input type="checkbox" name="interests" value="e"  ${fn:contains(member.interest, 'e') ? 'checked' : '' }>운동</label>
						<br>
						<label><input type="checkbox" name="interests" value="c"  ${fn:contains(member.interest, 'c') ? 'checked' : '' }>요리</label>
						<label><input type="checkbox" name="interests" value="p"  ${fn:contains(member.interest, 'p') ? 'checked' : '' }>프로그래밍</label>
						<label><input type="checkbox" name="interests" value="s"  ${fn:contains(member.interest, 's') ? 'checked' : '' }>노래</label>
						<label><input type="checkbox" name="interests" value="l"  ${fn:contains(member.interest, 'l') ? 'checked' : '' }>어학</label>
						<label><input type="checkbox" name="interests" value="o"  ${fn:contains(member.interest, 'o') ? 'checked' : '' }>기타</label>
					</td>
				</tr>

			</tbody>
			<!-- 회원 정보 수정 버튼 -->
			<tfoot>
				<tr><th align="center" colspan="2">
					<input type="submit" value="수정완료" id="form-submit" />
					&nbsp;&nbsp;&nbsp;
					<input type="button" value="삭제하기" onclick="aConfirmDelete()"/>
				</th></tr>
			</tfoot>
		</table>
	</form>
</body>
</html>