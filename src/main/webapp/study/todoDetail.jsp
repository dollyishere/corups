<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>할일 정보 확인</title>

<script>
	function gotoBefore(){
		 window.history.back();
	}

</script>
</head>
<body>
    <table border="1">
        <caption><b>스터디 이름</b></caption>
        <caption>챕터 이름</caption>
        <tbody>
            <tr>
                <th>할 일 </th>
                <td>${todo.name}</td>
            </tr>
            <tr>
                <th>상태</th>
                <td>
                <input type="radio" id="Done" name="status" value="complete" ${status == 'D' ? 'checked' : ''}>완료
                <input type="radio" id="Progress" name="status" value="inProgress" ${status == 'P' ? 'checked' : ''}>진행중
                <input type="radio" id="Hold" name="status" value="보류" ${status == 'H' ? 'checked' : ''}>보류
                <input type="radio" id="Canceled" name="status" value="취소" ${status == 'C' ? 'checked' : ''}>취소
                </td>
            </tr>
            <tr>
                <th>기간</th>
                <td>${todo.startDate} ~ ${todo.endDate}</td>
            </tr>
            <tr>
                <th>내용</th>
                <td>${todo.detail}</td>
            </tr>
        </tbody>
        <tfoot>
            <tr>
                <td colspan="2">첨부자료가 있다면 첨부자료 다운로드 띄우기</td>
            </tr>
        </tfoot>
    </table>
    
    <input type="button" id="okBtn" value="확인" onclick="gotoBefore()">
</body>
</html>
