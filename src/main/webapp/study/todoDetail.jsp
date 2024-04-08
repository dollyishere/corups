<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>할일 정보 확인</title>
</head>
<body>
<form action="TodoDetailServlet" method="post">
	<table border="1">
		<caption><b>스터디 이름</b></caption>
		<caption>할 일 이름</caption>
		<tbody>
			<tr>
				<th>챕터</th>
				<td>챕터1</td>
			</tr>
			<tr>
				<th>상태</th>
				<td><input type="radio" id="complete" name="status"
					value="complete"> <label for="complete">완료</label> <input
					type="radio" id="inProgress" name="status" value="inProgress">
					<label for="inProgress">진행중</label> <input type="radio"
					id="pending" name="status" value="보류"> <label for="pending">보류</label>

					<input type="radio" id="cancled" name="status" value="취소">
					<label for="cancled">취소</label></td>
			</tr>
			<tr>
				<th>기간</th>
				<td>2024.04.08 - 2024.04.18</td>
			<tr>
				<th>내용</th>
				<td>단어 100개 외우기</td>
				</td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="2">첨부자료가 있다면 첨부자료 다운로드 띄우기</td>
			</tr>
		</tfoot>
	</table>
</form>
</body>
</html>