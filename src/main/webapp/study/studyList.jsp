<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>studyList.jsp : 모든 스터디 목록 화면</title>
<style>
	.rect {
		width: 200px;
		height: 100px;
		border: 1px solid black;
	}
</style>
</head>
<body>
	<form action="" method="get">
		<p>
			<select name="searchType">
				<option value="ALL" selected="selected">전체검색</option>
				<option value="SUBJECT">제목</option>
				<option value="WRITER">작성자</option>
				<option value="CONTENTS">내용</option>
			</select> 
			<input type="text" name="searchText">
			<input type="submit" value="검색" />
		</p>
	</form>
<c:choose>
	<c:when test="${empty studyList}" >
		<div class="rect">
			<p>등록된 스터디가 없습니다.</p>
	</div>
	</c:when>
	<c:when test="${!empty studyList }">
		<c:forEach var="study" items="${studyList }">
			<div align="center" class="rect">
				<p><a>${study.name}</a></p>
				<p>${study.category}</p>
			</div>
		</c:forEach>
	</c:when>
</c:choose>
 <a href="studyRegister.jsp"><button>생성하기</button></a>
</body>
</html>