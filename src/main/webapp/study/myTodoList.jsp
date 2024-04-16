<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<!-- TodoListServlet -> statusArray, todoArray -->
<!-- TodoSearchServlet -> statusArray, todoArray, searchText, searchStatus -->


		
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 모든 할 일 화면</title>
<script type="text/javascript">
    function gotoDetail(todoNo) {
    	// 세션 변수 설정
        <% session.setAttribute("myTodoPage", "true"); %>
        document.location = "${contextPath}/todoDetailServlet?todoNo="+todoNo;
    }
    
</script>
</head>
<body>

	<form action="todoSearchServlet" method="post">
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
    <br>
    
    <table border="1">
    	<tbody>
    		<c:choose>
    		<c:when test="${empty todoArray}">
    			<tr><td>할 일이 없습니다.</td></tr>
    		</c:when>
    		<c:otherwise>
	    	<!-- todo -->
	    	<c:forEach var="todo" items="${todoArray}">
			    <tr>
				    <td>
				    <button type="button" onclick="gotoDetail(${todo.no})">
				    	${todo.name}
				    </button>
				    </td>
				    <td>
				    설명 : ${todo.detail}<br>
					상태 : 
				    <c:forEach var="statusItem" items="${statusArray}">
					    <c:choose>
					        <c:when test="${todo.no eq statusItem.todoNo}">
					            <c:if test="${statusItem.status eq 'D'}">완료</c:if>
					            <c:if test="${statusItem.status eq 'P'}">진행중</c:if>
					            <c:if test="${statusItem.status eq 'H'}">보류</c:if>
					            <c:if test="${statusItem.status eq 'C'}">취소</c:if>
					        </c:when>
					    </c:choose>
					</c:forEach>
					<br>
					
				    기간 : ${todo.startDate} ~ ${todo.endDate}<br>
					<!-- 스터디 : 스터디1<br> -->
					<!-- 챕터 : 챕터1<br> -->
				    </td>
			    </tr>
		    </c:forEach>
    		</c:otherwise>
		    </c:choose>
	    </tbody>
	    
	    <tfoot>
		
		<!-- 페이지 번호 -->
			<tr>
<%-- 				<td align="center" colspan="5"><c:out value="${pageNavigator}" escapeXml="false" /></td> --%>
			</tr>
		</tfoot>
    </table>


</body>
</html>