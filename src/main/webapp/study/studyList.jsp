<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"></c:set>

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
<script>
	function gotoDetail(no){
		document.location = "${contextPath}/study/studyDetailServlet?studyNo=" + no;
	}
</script>
</head>
<body>
	<form action="studySearchServlet" method="post">
    <select name="searchStatus" id="searchStatus">
    	<option value="ALL" ${searchStatus == 'ALL' ? 'checked' : ''}>전체</option>
        <option value="D" ${searchStatus == 'D' ? 'checked' : ''}>완료</option>
        <option value="P" ${searchStatus == 'P' ? 'checked' : ''}>진행중</option>
        <option value="H" ${searchStatus == 'H' ? 'checked' : ''}>보류</option>
        <option value="C" ${searchStatus == 'C' ? 'checked' : ''}>취소</option>
    </select>
    <input type="text" id="searchText" name="searchText" placeholder="검색어를 입력하세요" value="${searchText}">
    <input type="submit" value="검색">
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
			<button type="button" onclick="gotoDetail(${study.no })">
				<p>${study.name}</p>
				<p>${study.category}</p>
			</button>
			</div>
		</c:forEach>
	</c:when>
</c:choose>
 <a href="studyRegister.jsp"><button>생성하기</button></a>
</body>
</html>