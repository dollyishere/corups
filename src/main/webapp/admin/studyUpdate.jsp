<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스터디 수정 화면 : studyUpdate.jsp</title>
	<script type="text/javascript" src="<c:url value="/ckeditor/ckeditor.js" />"></script>
<style>
	#write {
		width: 500px;
	}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#updateBtn').click(function(e){
				e.preventDefault();
				
				var no = $("#no").val();
				var name = $("#name").val();
				var detail = CKEDITOR.instances.detail.getData();
				var password = $("#password").val();
				var maxNum = $("#maxNum").val();
				var category = $("#category").val();
				
				$.ajax({
					url: "studyUpdateServlet",
					type: "post",
					async:true,
					data: {
						name : name,
						detail : detail,
						password : password,
						maxNum : maxNum,
						category : category,
						no : no
					},
					dataType: "text",
					success: function(response) {
						if(response == "성공"){
							alert("수정 성공");
							document.location = "studyDetailServlet?studyNo="+no;						
						}
						else
							alert("수정 실패");	
					},

					error: function(xhr,status,error){
						alert("에러 발생");
					}

					});
			});
			
			$("#deleteBtn").click(function(e){
				e.preventDefault();
				
				var no = $("#no").val();
				
				$.ajax({
					url: "studyDeleteServlet",
					type: "post",
					async:true,
					data: {
						no : no
					},
					dataType: "text",
					success: function(response) {
						if(response == "성공"){
							alert("삭제 성공");
							document.location = "studyListServlet";							
						}
						else
							alert("삭제 실패");	
					},

					error: function(xhr,status,error){
						alert("에러 발생");
					}

				});
				
			});
		});
	</script>
</head>
<body>
			<input type="hidden" id="no" name="no" value="${studyDTO.no}">
			<p> 스터디 수정
			<p>이름 :
				<input type="text" id="name" name="name" value="${studyDTO.name}" required><br>
			</p>
			<p id="write"><span>소개 :</span> 
				<textarea id="detail" name="detail" class="ckeditor" rows="5" cols="22">${studyDTO.detail}</textarea><br>
			</p>
			<p>패스워드 :
				<input type="password" id="password" name="studyPwd" value="${studyDTO.studyPwd}" required><br>
			</p>
			<p>최대 인원 :
				<input type="number" id="maxNum" name="maxNum" value="${studyDTO.maxNum}"><br>
			</p>
			<p>카테고리 :
				<select id="category" name="category">
					<option value="A" ${studyDTO.category eq "A" ? 'selected' : ''}>A</option>			
					<option value="B" ${studyDTO.category eq "B" ? 'selected' : ''}>B</option>
					<option value="C" ${studyDTO.category eq "C" ? 'selected' : ''}>C</option>		
				</select>
			</p><br>
			<input type="button" id="updateBtn" value="수정하기">
			<input type="button" id="deleteBtn" value="삭제하기">
			<input type="button" value="취소" onclick="window.location.href='"studyDetailServlet?studyNo="+no'">	
</body>
</html>