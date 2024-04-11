<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>할일 정보 확인</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    $("#okBtn").click(function(){
        var status = $("input[name='status']:checked").val();
        var todoNo = $("#todoNo").val();
        var myTodoPage = ${sessionScope.myTodoPage};
        var datas = {
            status: status,
            todoNo: todoNo
        };
        
        $.ajax({
            url: "statusUpdateServlet",
            type: "POST",
            data: datas,
            dataType: "text",  // 응답 데이터 타입 지정
            success: function(response){
            	if(myTodoPage == true)
                	document.location = "todoListServlet";
                else
                	document.location = "todoListServlet";
            },
            error: function(error){
                alert("Error: " + error);  // 에러 처리
            }
        });
    });
});
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
                <input type="radio" id="status" name="status" value="D" ${status == 'D' ? 'checked' : ''}>완료
                <input type="radio" id="status" name="status" value="P" ${status == 'P' ? 'checked' : ''}>진행중
                <input type="radio" id="status" name="status" value="H" ${status == 'H' ? 'checked' : ''}>보류
                <input type="radio" id="status" name="status" value="C" ${status == 'C' ? 'checked' : ''}>취소
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
    <input type="hidden" id="todoNo" value="${todo.no}">
    <input type="button" id="okBtn" value="확인">
</body>
</html>
