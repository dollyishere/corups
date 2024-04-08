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
<script>

	/* 아이디 중복 체크 */
	function fn_process() {
		var _id = $("#id").val();
		const regExp = /[a-zA-Z0-9]/g;
		
		if (!regExp.test(_id)) {
			alert("영숫자만 가능합니다.");
			$('#id').val('');
			return;
		}
		
		$.ajax({
			type: "post",
			async: false,
			url: "http://localhost:9000/corups/member/SignUpServlet/confirmDuplicate.do",
			dataType: "text",
			data: {id: _id},
			
			success:function(data, textStatus) {
				if (data == 'usable') {
					alert("사용할 수 있는 ID 입니다.");
					$("#id").prop("readonly", true);
					$("#double").prop("disabled", true)
				} else {
					alert("사용할 수 없는 ID입니다.")
					$('#id').val('');
				}
			},
			error:function(data, textStatus) {
				alert("다시 한 번 시도해주세요.");
			},
			complete: function(data, textStatus) {
				
			}
		})
	}
	
	/* 비번 확인 */
	$(document).ready(function() {
		// 암호 확인 기능 구현
		$("#pwd_check").keyup(function() {
			if($("#pwd").val() !== $("#pwd_check").val()) {
				$("#pwd_message").text("");
				$("#pwd_message").html("<b>비밀번호가 틀립니다.</b>");
			} else {
				$("#pwd_message").text("");
				$("#pwd_message").html("<b>비밀번호가 맞습니다.</b>");
			}
		})
		
		$("#pwd").keyup(function() {
			$("#pwd_message").text("");
		})
	})
	
</script>
</head>
<body>
	<form action="member/SignUpServlet" method="post" enctype="multipart/form-data" accept-charset="UTF-8">
		<table border="1" >
			<colgroup>
				<col width="150" />
				<col width="400" />
				<col width="200" />
			</colgroup>
			<!-- 회원가입 제목 -->
			<thead>
				<tr><th align="center" colspan="3">회원 가입</th></tr>
			</thead>
			<!-- 회원가입 입력 -->
			<tbody>
				<tr>
					<td>프로필 사진</td>
					<td>
						<input type="file">
					</td>
					<td>프로필 사진을 선택하세요.</td>
				</tr>
				<tr>
					<td>아이디</td>
					<td>
						<input type="text" name="id" id="id" size="20" maxlength="20" required />
						<input type="button" name="confirmDuplicate" id="double" onclick="fn_process()" value="ID중복확인" />
					</td>
					<td>아이디를 적어 주세요.</td>
				</tr>
				<tr>
					<td>패스워드</td>
					<td>
						<input type="password" name="pwd" id="pwd" size="20" maxlength="20" required />
					</td>
					<td>패스워드를 적어 주세요.</td>
				</tr>
				<tr>
					<td>패스워드 확인</td>
					<td>
						<input type="password" name="pwd_check" id="pwd_check" size="20" maxlength="20" required />
						&nbsp;
						<span id="pwd_message"></span>
					</td> 
					<td>패스워드를 확인합니다.</td>
				</tr>
				<tr>
					<td>이름</td>
					<td>
						<input type="text" name="name" size="20" maxlength="20" required />
					</td>
					<td>고객실명을 적어주세요.</td>
				</tr>
				<tr>
					<td>생년월일</td>
					<td>
						<input type="date" name="birthday" size="10" required />
					</td>
					<td>생년월일을 입력해 주세요.</td>
				</tr>
				<tr>
					<td>이메일</td>
					<td>
						<input type="email" name="email" size="30" maxlength="30" required />
					</td>
					<td>이메일을 적어주세요.</td>
				</tr>
				<tr>
					<td>직업</td>
					<td>
						<select name="job" required >
						  <option value="" selected disabled>선택하세요</option>
						  <option value="unemployed">무직</option>
						  <option value="student">교직학생</option>
						  <option value="homemaker">주부</option>
						  <option value="employee">직장인</option>
						  <option value="entrepreneur">사업가</option>
						</select>
					</td>
					<td>직업을 선택 하세요.</td>
				</tr>
				<tr>
					<td>관심사</td>
					<td>
						<label><input type="checkbox" name="internet" value="i">인터넷</label>
						<label><input type="checkbox" name="travel" value="t">여행</label>
						<label><input type="checkbox" name="gaming" value="g">게임</label>
						<label><input type="checkbox" name="movie" value="m">영화</label>
						<label><input type="checkbox" name="exercise" value="e">운동</label>
					</td>
					<td>관심사를 선택하세요.</td>
				</tr>

			</tbody>
			<!-- 회원가입 버튼 -->
			<tfoot>
				<tr><th align="center" colspan="3">
					<input type="submit" value="회원가입" />
					&nbsp;&nbsp;&nbsp;
					<input type="reset" value="다시쓰기" />
				</th></tr>
			</tfoot>
		</table>
	</form>
</body>
</html>