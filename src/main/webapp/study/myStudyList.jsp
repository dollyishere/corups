<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.rect {
		width: 200px;
		height: 100px;
		border: 1px solid black;
	}
</style>

<script type="text/javascript">
    function gotoDetail(no) {
    	// 세션 변수 설정
<%--    		<% session.setAttribute("myTodoPage", "true"); %> --%>
        document.location.href = "${contextPath}/study/studyDetailServlet?studyNo=" + no;
    }
    
</script>

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
	<c:when test="${empty myStudyList}" >
		<div class="rect">
			<p>등록된 스터디가 없습니다.</p>
	</div>
	</c:when>
	<c:when test="${!empty myStudyList }">
		<c:forEach var="myStudy" items="${myStudyList }">
			<div align="center" class="rect">
				<p><button type="button" onclick="gotoDetail(${myStudy.no})">
				    	${myStudy.name}
				    </button></p>
				<p>${myStudy.category}</p>
			</div>
		</c:forEach>
	</c:when>
</c:choose>
 <a href="studyRegister.jsp"><button>생성하기</button></a>
</body>
</html>