<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="contextPath" value="${pageContext.request.contextPath }"></c:set>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script>
	function confirmRegister() {
		// 등록 여부를 확인하는 알림 창 표시
		var result = confirm("등록하시겠습니까?");

		// 사용자가 확인 버튼을 클릭했을 때
		if (result) {
			// 추가 또는 목록으로 이동 여부를 묻는 알림 창 표시
			var result2 = confirm("챕터를 계속 생성하시겠습니까?");
			// 사용자가 확인 버튼을 클릭했을 때
			if (result2) {
				// 추가 화면으로 이동
				window.location.href = "${pageContext.request.contextPath}/chapter/chapterRegisterServlet";

			} else {
				alert("목록으로 이동합니.");
				// 목록 화면으로 이동
				window.location.href = "${pageContext.request.contextPath}/study/studyDetailServlet?studyNo=${studyNo}";
			}
		} else {
			// 사용자가 취소 버튼을 클릭했을 때
			// 목록 화면으로 이동
			alert("등록이 취소되었습니다.");
			window.location.href = "${pageContext.request.contextPath}/study/studyDetailServlet?studyNo=${studyNo}";

		}
	}
</script>
<title>챕터 추가 화면 - 스터디 방장</title>
</head>
<body>
	<h2 align="center">study.name${study.name}</h2>
	<div align="center" border="1">

		<form
			action="${pageContext.request.contextPath}/chapter/chapterRegisterServlet"
			method="post">

			<table align="center" border="1" summary="챕터 추가">
				<tr>
					<th colspan="2" align="center">chapter 등록</th>
				</tr>
				<tr>
					<td>챕터 이름 :</td>
					<td><input type="text" name="chapterName"
						placeholder="챕터 이름을 입력하세요" /></td>
				</tr>
				<tr>
					<td>시작 날짜 :</td>
					<td><input type="date" name="startDate" /></td>
				</tr>
				<tr>
					<td>마감 날짜 :</td>
					<td><input type="date" name="endDate" /></td>


				</tr>
				<tr>
					<td colspan="2" align="center">
					<input type="submit"
						name="insertChpater" value="등록" onclick="confirmRegister()" />
					<input type="button" name="cancelUpdate" value="취소"
						onclick="location.href='${contextPath}/study/studyDetailServlet?no=${studyNo}'" />

					</td>
				</tr>
			</table>
		</form>
	</div>
</body>

</html>