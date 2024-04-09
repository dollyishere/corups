<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 모든 할 일 화면</title>
</head>
<body>

	<form action="TodoSearchServlet" method="post">
    <select name="status" id="status">
        <option value="완료">완료</option>
        <option value="진행중">진행중</option>
        <option value="보류">보류</option>
        <option value="취소">취소</option>
    </select>
    <input type="text" id="searchKeyword" name="keyword" placeholder="검색어를 입력하세요">
    <input type="submit" value="검색">
    <br>
    <table border="1px">
	    <tr>
		    <td>
		    내 할일1
		    </td>
		    <td>
		    설명 : 단어 100개 외우기<br>
		    상태 : 진행중<br>
		    기간 : 2024.04.05 - 2024.04.18<br>
<!-- 		    스터디 : 스터디1<br> -->
<!-- 		    챕터 : 챕터1<br> -->
		    </td>
	    </tr>
	    
	    <tfoot>
		
		<!-- 페이지 번호 -->
			<tr>
<%-- 				<td align="center" colspan="5"><c:out value="${pageNavigator}" escapeXml="false" /></td> --%>
			</tr>
		</tfoot>
    </table>
</form>

</body>
</html>