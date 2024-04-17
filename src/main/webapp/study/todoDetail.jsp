<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
            	document.location ="${contextPath}/chapter/chapterDetailServlet?chapterNo=${chapter.no}";
            },
            error: function(error){
                alert("Error: " + error);  // 에러 처리
            }
        });
    });
});

function gotoUpdate(todoNo){
	document.location = "todoUpdateServlet?todoNo="+todoNo;
}
</script>
</head>
<body>
    <table border="1">
        <caption><b>
	        <a href="<c:url value="/study/studyDetailServlet?studyNo=${study.no}" />" style="text-decoration: none;">
		    	${study.name}
	    	</a>    
		</b></caption>
        <caption>
        <a href="<c:url value="/chapter/chapterDetailServlet?chapterNo=${chapter.no}" />" style="text-decoration: none;">
        ${chapter.name}
        </a>
        </caption>
        <tbody>
            <tr>
                <th>할 일 </th>
                <td>${todo.name}</td>
            </tr>
            <tr>
                <th>상태
                ${status}</th>
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
            <c:forEach var="file" items="${files}">
            <tr>
                <td colspan="2"><a href="${contextPath }/fileDownloadServlet?fileName=${file.name}&todoNo=${todo.no}">${file.name} 내려받기</a></td>
            </tr>
            </c:forEach>
        </tfoot>
    </table>
    <input type="hidden" id="todoNo" value="${todo.no}">
    
    <c:choose>
    	<c:when test="${mgr}">
    		<input type="button" id="okBtn" value="확인">
    		<input type="button" value="수정" onclick="gotoUpdate(${todo.no})">
    	</c:when>
    </c:choose>
</body>
</html>
