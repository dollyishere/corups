<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
    tfoot td {
        text-align: center;
    }
</style>


</head>
<body>
<form action="todoRegisterServlet" method="post">
	<table border="1">
		<caption><b>스터디 이름</b></caption>
		<caption>챕터 이름</caption>
		<tbody>
			<tr>
				<th>이름</th>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<th>시작</th>
				<td><input type="date" name="startDate"></td>
			<tr>
				<th>마감</th>
				<td><input type="date" name="endDate"></td>
			<tr>
				<th>내용</th>
				<td><textarea id="content" name="content" rows="4" cols="50"></textarea></td>
			</tr>
			<tr>
				<td colspan="2"><input type="file" id="file" name="file"></td>
			</tr>
		</tbody>
		<tfoot>
			<tr >
				<td colspan="2">
				<input type="submit" value="추가">
				<input type="button" value="취소">
				</td>
			</tr>
		</tfoot>
	</table>
</form>
</body>
</html>