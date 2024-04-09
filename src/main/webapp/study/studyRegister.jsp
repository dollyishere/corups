<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스터디 생성 화면 : studyRegister.jsp</title>
<style>

</style>
</head>
<body>
	<section>
		<form action="">
			<p> 스터디 생성
			<p>이름 :
				<input type="text" required><br>
			</p>
			<p><span>소개 :</span> 
				<textarea rows="5" cols="22" required></textarea><br>
			</p>
			<p>패스워드 :
				<input type="password" required><br>
			</p>
			<p>최대 인원 :
				<input type="number" value="1"><br>
			</p>
			<p>카테고리 :
				<select name="category">
					<option value="common">일반</option>			
					<option value="hobby">취미</option>
					<option value="prepare">취준</option>
					<option value="change">이직</option>
					<option value="certification">자격증</option>
					<option value="student">학생</option>
					<option value="c-test">수험</option>
					<option value="book">독서</option>
					<option value="etc">기타</option>			
				</select>
			</p><br>
			<a href="studyList.jsp"><input type="button" value="생성하기"></a>
			<input type="button" value="취소">
		</form>
	</section>
</body>
</html>