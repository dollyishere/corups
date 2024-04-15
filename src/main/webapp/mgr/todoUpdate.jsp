<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
                    url: "${contextPath}/todoUpdateServlet",
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
                    	if(response === "성공") {
                            fileUpload(todoNo);
                        }
                        else{
                        	alert("실패");
                        }
                    },
                    error: function() {
                        alert("Error occurred while processing data");
                    }
                });
            }); // updateBtn
            
            
         function fileUpload(todoNo){
         	var formData = new FormData();
             formData.append("todoNo", todoNo);
             var files = $('#file')[0].files;
             for(var i = 0; i < files.length; i++) {
                 formData.append('files[]', files[i]);
             }
             
         	$.ajax({
                 url: "${contextPath}/fileUploadServlet",
                 type: "POST",
                 data: formData,
                 dataType: "text",
     			async : true,
     			processData: false,
                 contentType: false,
                 success: function(fileCount) {
                	 alert("수정되었습니다.");
                 	document.location = "todoDetailServlet?todoNo=" + todoNo;
                 },
                 error: function() {
                     alert("Error occurred while processing data");
                 }
             });
         	
         } // fileUpload
       
	        $("#deleteBtn").click(function(e) {
	            e.preventDefault();
	            
	            var todoNo = $("#todoNo").val();
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
	                   	alert("삭제되었습니다.");
	                   	if(myTodoPage == true)
	                       	document.location = "todoListServlet";
	                       else
	                       	document.location = "todoListServlet";
	                },
	                error: function() {
	                    alert("Error occurred while processing data");
	                }
	            });
	        }); // deleteBtn  
        }); // $(document).ready
        
        function deleteFile(btn, fileNo){
        	 var row = $(btn).closest("tr"); // 가장 가까운 tr 요소 찾기

            $.ajax({
                url: "${contextPath}/fileDeleteServlet",
                type: "POST",
                data: {
                    fileNo: fileNo
                },
                dataType: "text",
                success: function(response) {
                    if (response === "success") {
                        row.remove(); // 해당 행 삭제
                    } else {
                        alert("파일 삭제 실패");
                    }
                },
                error: function() {
                    alert("Error occurred while processing data");
                }
            });
        } // deleteFile
        
    </script>
<script>
	function gotoBefore(){
		 window.history.back();
	}
</script>

</head>
<body>    
<form method="post" id="myForm">
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
			
			<c:forEach var="file" items="${files}">
            <tr>
                <td colspan="2">
                	${file.name} <input type="button" value="삭제" onclick="deleteFile(this, ${file.no})">
				</td>
            </tr>
            </c:forEach>
            
			<tr>
				<td colspan="2"><input type="file" id="file" name="file" multiple></td>
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