<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스터디 생성 화면 : studyRegister.jsp</title>
<script type="text/javascript" src="<c:url value="/ckeditor5/build/ckeditor.js" />"></script>
<style>
	#write {
		width: 500px;
	}
</style>
</head>
<body>
	<div class="container-fluid m-5">
		<c:import url="/components/defaultHeader.jsp" />
		<div class="container-fluid m-5">
		<div class="row justify-content-center align-items-center">
	    	<div class="col-md-auto">
		    		<img alt="logo.png" src="../resources/imgs/logo.png" class="mb-2">
		   		</div>
			   <div class="col-md-1">
		       </div>
		        <div class="col-md-auto">
	        	<!-- todo 등록 폼 -->
	            <div class="custom-form text-center" style="width: 30rem;">
	               <form action="<c:url value="/study/studyRegisterServlet" />" accept-charset="UTF-8" method="post" >
	                	<!-- 이름 -->
	                    <div class="mb-2 row  justify-content-start align-items-center">
	                    	<div class="col-md-3">
	                         <label for="exampleInputTodoName" class="form-label" style="color:#292929;"><b>Name</b></label>
	                        </div>
	                        <div class="col-md-auto">
	                        	<input type="text" id="name" name="name" size="20" maxlength="20"  class="form-control" required>
	                        </div>
	                        <div class="col-md-3">
						    </div>
	                    </div>
	                    <!-- 비밀번호 -->
	                    <div class="mb-2 row  justify-content-start align-items-center">
	                    	<div class="col-md-3">
	                         <label for="exampleInputClassPwd" class="form-label" style="color:#292929;"><b>Password</b></label>
	                        </div>
	                        <div class="col-md-auto">
	                        	<input type="password" name="studyPwd" size="20" maxlength="6" class="form-control">
	                        </div>
	                        <div class="col-md-3">
						    </div>
	                    </div>
	                    <!-- 최대 참가 인원 -->
	                    <div class="mb-2 row  justify-content-start align-items-center">
	                    	<div class="col-md-3">
	                         <label for="exampleInputClassPwd" class="form-label" style="color:#292929;"><b>Max participants</b></label>
	                        </div>
	                        <div class="col-md-auto">
	                        	<input type="number" name="maxNum" class="form-control" value="1" min="1" max="100" required>
	                        </div>
	                        <div class="col-md-3">
						    </div>
	                    </div>
	                    <!-- 소개 -->
	                    <div class="mb-2 row  justify-content-start align-items-center">
	                    	<div class="col-md-3">
	                         <label for="exampleInputDetail" class="form-label" style="color:#292929;"><b>Intro</b></label>
	                        </div>
	                        <div class="col-md-auto">
	                        	<textarea name="detail" id="editor">
								</textarea>
	                        </div>
	                    </div>
     					<!-- 카데고리 선택 -->
	                    <div class="mb-2 row  justify-content-start align-items-center">
	                    	<div class="col-md-3">
	                         <label for="exampleInputCategory" class="form-label" style="color:#292929;"><b>Category</b></label>
	                        </div>
	                        <div class="col-md-auto">
								<select name="category" class="form-select" required>
									<option value="" selected disabled>선택하세요</option>
								    <option value="r">독서</option>
								    <option value="t">여행</option>
								    <option value="g">게임</option>
								    <option value="m">영화</option>
								    <option value="e">운동</option>
								    <option value="c">요리</option>
								    <option value="p">프로그래밍</option>
								    <option value="s">노래</option>
								    <option value="l">어학</option>
								    <option value="o">기타</option>
								</select>
	                        </div>
	                        <div class="col-md-3">
						    </div>
	                    </div>
	                    <!-- 제출 버튼 or 리셋 버튼 -->
	                    <div class="mb-2 d-flex justify-content-end">
	                        <input  type="submit" value="생성" class="btn" style="background-color:#B9A4BF; color:white;"/>
	                        &nbsp;
	                        <input type="button" value="취소" class="btn btn-secondary" onclick="goBack()" />
	                    </div>
	                </form>
                </div>
           </div>
	  	</div>
	  </div>
	</div>
</body>
<script type="text/javascript">
	let editor;
	
	ClassicEditor
    .create( document.querySelector( '#editor' ) )
    .then( newEditor => {
        editor = newEditor;
    } )
    .catch( error => {
        console.error( error );
    } );

	function goBack() {
        window.history.back();
	}
</script>
</html>