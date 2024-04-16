<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스터디 생성 화면 : studyRegister.jsp</title>
<script type="text/javascript" src="<c:url value="/ckeditor/ckeditor.js" />"></script>
<style>
	#write {
		width: 500px;
	}
</style>
</head>
<body>
		<form action="<c:url value="/study/studyRegisterServlet" />" method="post" >		
			<p> 스터디 생성
			<p>이름 :
				<input type="text" name="name" required><br>
			</p>
			<p id="write"><span>소개 :</span> 
				<textarea name="detail" class="ckeditor" rows="5" cols="22"></textarea><br>
			</p>
			<p>패스워드 :
				<input type="password" name="studyPwd"><br>
			</p>
			<p>최대 인원 :
				<input type="number" name="maxNum" value="1"><br>
			</p>
			<p>카테고리 :
				<select name="category">
					<option value="A">A</option>			
					<option value="B">B</option>
					<option value="C">C</option>		
				</select>
			</p><br>
			<input type="submit" value="생성하기">
			<a href="studyList.jsp"><input type="button" value="취소"></a>
		</form>
</body>
</html>