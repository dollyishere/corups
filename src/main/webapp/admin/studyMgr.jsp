<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"></c:set>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#margin {
		margin-bottom: 100px; /* 행 사이의 간격 설정 */
	}
</style>
<script>
	function gotoUpdate(no){
		document.location = "${contextPath}/study/studyDetailServlet?studyNo=" + no;
	}
</script>
</head>
<body>
	<c:choose>
		<c:when test="${empty studyList}">
			<p>등록된 스터디가 없습니다.</p>
		</c:when>
		
		<c:when test="${!empty studyList }">
			<div>
				스터디 목록
				<table border="1">
					<thead>
						<tr>
							<th>no</th>
							<th>name</th>
							<th>id</th>
							<th>생성 날짜</th>
							<th>관리</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="study" items="${studyList}">
							<tr id="margin">
								<td>${study.no}</td>
								<td>${study.name}</td>
								<td>${study.createUserId}</td>
								<td>${study.createdDate}</td>
								<td><input type="button" onclick="gotoUpdate(${study.no})" value="상세보기"></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</c:when>
	</c:choose>
</body>
</html>