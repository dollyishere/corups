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

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#addBtn").click(function(e) {
                e.preventDefault();

                var name = $("#name").val();
                var startDate = $("#startDate").val();
                var endDate = $("#endDate").val();
                var content = $("#content").val();
                var myTodoPage = ${sessionScope.myTodoPage};
                
                $.ajax({
                    url: "todoRegisterServlet",
                    type: "POST",
                    async: true,
                    data: { 
                    	name: name,
                    	startDate: startDate,
                    	endDate: endDate,
                    	content: content
                    },
                    dataType: "text",
                    success: function(response) {
                       	alert(response);
                       	if(response == "성공"){
                       	if(myTodoPage == true)
                           	document.location = "todoListServlet";
                           else
                           	document.location = "todoListServlet";
                       	}
                    },
                    error: function() {
                        alert("Error occurred while processing data");
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
				<th>이름</th>
				<td><input type="text" id="name" name="name"></td>
			</tr>
			<tr>
				<th>시작</th>
				<td><input type="date" id="startDate" name="startDate"></td>
			<tr>
				<th>마감</th>
				<td><input type="date" id="endDate" name="endDate"></td>
			<tr>
				<th>내용</th>
				<td><textarea id="content" id="content"  name="content" rows="4" cols="50"></textarea></td>
			</tr>
			<tr>
				<td colspan="2"><input type="file" id="file" name="file"></td>
			</tr>
		</tbody>
		<tfoot>
			<tr >
				<td colspan="2">
				<input type="button" id="addBtn" value="추가">
				<input type="button" value="취소">
				</td>
			</tr>
		</tfoot>
	</table>
</body>
</html>