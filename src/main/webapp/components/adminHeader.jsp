<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin header</title>

<style>
 input {
 		border: none;
 		background-color: transparent;
	 	border-radius: 0; /* 라운딩 없애기 */
	 }
th{
	background-color: orange;
}
</style>

</head>
<body>
	<table>
		<tr>
		    <c:if test="${ not empty sessionScope.adminId }">
		      	<th>
		    		<form action="<c:url value="/admin/adminLogoutServlet"/>" method="post">
		    			<input type="submit" value="로그아웃" />
		    		</form>
		    	</th>
				<th><input type="button" value="회원관리" onclick="location.href='<c:url value="/member/memberListServlet"/>'" /></th>
		    </c:if>
		</tr>
	</table>
</body>
</html>