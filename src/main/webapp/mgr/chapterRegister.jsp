<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="contextPath" value="${pageContext.request.contextPath }"></c:set>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>챕터 추가 화면 - 스터디 방장</title>
</head>
<body>
	<h2 align="center">
		study.name${study.name}
	</h2>
	<div align="center" border="1">
		
		<form action="${contextPath}/chapter/chapterRegisterServlet" method="post">
		
			<table align="center" border="1" summary="챕터 추가">
				<tr>
					<th colspan="2" align="center">chapter${chapter.name} 추가</th>
				</tr>
				<tr>
					<td>챕터 이름 :</td>
					<td><input type="text" name="chapterName" value="챕터 이름을 입력하세요" /></td>
				</tr>
				<tr>
					<td>시작 날짜 :</td>
					<td><input type="date" name="startDate" value="시작 날짜를 입력하세요" /></td>
				</tr>
				<tr>
					<td>마감 날짜 :</td>
					<td><input type="date" name="endDate" value="마감 날짜를 입력하세요" /></td>
										
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" name="insertChpater" value="추가"
							onclick="location.href='<c:url value='/chapter/chapterRegisterServlet'/>'" />
						<input type="button" name="cancleUpdate" value="취소"
							onclick="location.href='<c:url value='/chapter/chapterListServlet?num=${chapter.num}'/>'" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>

</html>