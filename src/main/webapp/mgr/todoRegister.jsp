<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }"></c:set>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<c:url value="/ckeditor/ckeditor.js" />"></script>

<style>
    tfoot td {
        text-align: center;
    }
</style>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $("#addBtn").click(function() {
        	   var name = $("#name").val();
               var startDate = $("#startDate").val();
               var endDate = $("#endDate").val();
               var content = CKEDITOR.instances.content.getData();
               var chapterNo = $("#chapterNo").val();
               
               var data = {
                   name: name,
                   startDate: startDate,
                   endDate: endDate,
                   content: content,
                   chapterNo : chapterNo
               };
            $.ajax({
                url: "${contextPath}/todoRegisterServlet",
                type: "POST",
                data: data,
                dataType: "text",
				async : true,
				
                success: function(todoNo) {
                    if(todoNo !== "0") {
                        fileUpload(todoNo);
                    }
                    else{
                    	alert("등록에 실패하였습니다.");
                    }
                    
                },
                error: function() {
                    alert("Error occurred while processing data");
                }
            });
            
        });
    });
    
    
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
// 				if(${sessionScope.myTodoPage} == true)
// 	            	document.location = "${contextPath}/todoListServlet";
// 	            else
	            	document.location = "${contextPath}/todoListServlet";
            },
            error: function() {
                alert("Error occurred while processing data");
            }
        });
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
				<td><textarea id="content" name="content" class="ckeditor"  rows="4" cols="50"></textarea></td>
			</tr>
			<tr>
				<td colspan="2"><input type="file" id="file" name="file" multiple></td>
			</tr>
		</tbody>
		<tfoot>
			<tr >
				<td colspan="2">
				<input type="hidden" id="chapterNo" value="${chapterNo}">
				<input type="button" id="addBtn" value="추가">
				<input type="button" value="취소">
				</td>
			</tr>
		</tfoot>
	</table>
	</form>
	
		
</body>
</html>