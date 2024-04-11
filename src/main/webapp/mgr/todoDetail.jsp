<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스터디 생성자 할일 정보 확인</title>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

	function gotoUpdate(todoNo, rootPage){
		document.location = "todoUpdateServlet?todoNo="+todoNo+"&rootPage="+rootPage;
	}
	
	$(document).ready(function(){
	    $("#okBtn").click(function(){
	        var status = $("#status").val();
	        var todoNo = $("#todoNo").val();
	        var rootPage = $("rootPage").val();
	        var myTodoPage = ${sessionScope.myTodoPage};
	        
	        var datas = {
	            status: status,
	            todoNo: todoNo,
	            rootPage: rootPage
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
	<%@ include file="/study/todoDetail.jsp" %>
    
    <input type="button" value="수정" onclick="gotoUpdate(${todo.no}, ${rootPage})">
</body>
</html>
