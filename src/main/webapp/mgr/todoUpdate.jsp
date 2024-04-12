<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
<c:set var="contextPath" value="${pageContext.request.contextPath }"></c:set>
	<!-- todo, returnPage -->
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>할 일 수정 페이지</title>

<style>
    tfoot td {
        text-align: center;
    }
</style>
 <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#updateBtn").click(function(e) {
                e.preventDefault();

                var name = $("#name").val();
                var startDate = $("#startDate").val();
                var endDate = $("#endDate").val();
                var content = $("#content").val();
                var todoNo = $("#todoNo").val();
                
                $.ajax({
                    url: "todoUpdateServlet",
                    type: "POST",
                    async: true,
                    data: { 
                    	name: name,
                    	startDate: startDate,
                    	endDate: endDate,
                    	content: content,
                    	todoNo: todoNo
                    },
                    dataType: "text",
                    success: function(response) {
                    	document.location = "todoDetailServlet?todo_no=" + todoNo;
                    },
                    error: function() {
                        alert("Error occurred while processing data");
                    }
                });
            }); 
       
	        $("#deleteBtn").click(function(e) {
	            e.preventDefault();
	            
	            var todoNo = $("#todoNo").val();
	            var rootPage = $("#rootPage").val();
	            var myTodoPage = ${sessionScope.myTodoPage};
	            $.ajax({
	                url: "todoDeleteServlet",
	                type: "POST",
	                async: true,
	                data: { 
	                	todoNo: todoNo
	                },
	                dataType: "text",
	                success: function(response) {
	                   	alert("할 일 삭제");
	                   	if(myTodoPage == true)
	                       	document.location = "todoListServlet";
	                       else
	                       	document.location = "todoListServlet";
	                },
	                error: function() {
	                    alert("Error occurred while processing data");
	                }
	            });
	        });   
        });
    </script>
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
				<th>할 일</th>
				<td><input type="text" id="name" name="name" value="${todo.name}"></td>
			</tr>
			<tr>
				<th>시작</th>
				<td><input type="date" id="startDate" name="startDate" value="${todo.startDate}"></td>
			<tr>
				<th>마감</th>
				<td><input type="date" id="endDate" name="endDate" value="${todo.endDate}"></td>
			<tr>
				<th>내용</th>
				<td><textarea id="content" id="content" name="content" value="${todo.detail}" rows="4" cols="50">${todo.detail}</textarea></td>
			</tr>
			<tr>
				<td colspan="2"><input type="file" id="file" name="file"></td>
			</tr>
		</tbody>
		<tfoot>
			<tr >
				<td colspan="2">
				<input type="hidden" id="todoNo" name="todoNo" value="${todo.no}">
				<input type="button" id="updateBtn" value="수정">
				<input type="button" id="deleteBtn" value="삭제">
				<input type="button" value="취소" onclick="gotoBefore()">
				</td>
			</tr>
		</tfoot>
	</table>
</form>
</body>
</html>