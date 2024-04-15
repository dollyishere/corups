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
		alert("등록되었습니다.");
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
					<td colspan="2" align="center"><input type="submit"
						name="insertChpater" value="등록" onclick="confirmRegister()" /> <input
						type="button" name="cancelUpdate" value="취소"
						onclick="location.href='${contextPath}/study/studyDetailServlet?no=${studyNo}'" />

					</td>
				</tr>
			</table>
		</form>
	</div>
</body>

</html>