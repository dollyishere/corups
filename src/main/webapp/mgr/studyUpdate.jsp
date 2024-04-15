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
			$('#updateBtn').click(function(e)){
				e.preventDefault();
				
				var name = $("#")
			}
		})
	</script>
</head>
<body>
		<form action="<c:url value="/study/studyUpdateServlet" />" method="post" >		
			<p> 스터디 수정
			<p>이름 :
				<input type="text" id="name" name="name" value="${studyDTO.name}" required><br>
			</p>
			<p id="write"><span>소개 :</span> 
				<textarea name="detail" class="ckeditor" rows="5" cols="22">${studyDTO.detail}</textarea><br>
			</p>
			<p>패스워드 :
				<input type="password" name="studyPwd" value="${studyDTO.studyPwd}" required><br>
			</p>
			<p>최대 인원 :
				<input type="number" name="maxNum" value="${studyDTO.maxNum}"><br>
			</p>
			<p>카테고리 :
				<select name="category">
					<option value="A" ${studyDTO.category eq "A" ? 'selected' : ''}>A</option>			
					<option value="B" ${studyDTO.category eq "B" ? 'selected' : ''}>B</option>
					<option value="C" ${studyDTO.category eq "C" ? 'selected' : ''}>C</option>		
				</select>
			</p><br>
			<a href="studyList.jsp"><input type="submit" id="updateBtn" value="수정하기"></a>
			<a href="studyList.jsp"><input type="button" value="취소"></a>
		</form>
</body>
</html>