<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스터디 수정 화면 : studyUpdate.jsp</title>
<script type="text/javascript" src="<c:url value="/ckeditor5/build/ckeditor.js" />"></script>
<style>
	#write {
		width: 500px;
	}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<div class="container-fluid m-5">
		<c:import url="/components/defaultHeader.jsp" />
		<div class="container-fluid m-5">
		<div class="row justify-content-center align-items-center">
	    	<div class="col-md-auto">
		    		<img alt="logo.png" src="<c:url value="/resources/imgs/logo.png" />" class="mb-2">
		   		</div>
			   <div class="col-md-1">
		       </div>
		       
		        <div class="col-md-auto">
	            <div class="custom-form text-center" style="width: 30rem;">
	            		<!-- 스터디 번호 -->
	            		<input type="hidden" id="no" name="no" value="${studyDTO.no}">
	                	<!-- 이름 -->
	                    <div class="mb-2 row  justify-content-start align-items-center">
	                    	<div class="col-md-3">
	                         <label for="exampleInputTodoName" class="form-label" style="color:#292929;"><b>Name</b></label>
	                        </div>
	                        <div class="col-md-auto">
	                        	<input type="text" id="name" name="name" value="${studyDTO.name}" size="20" maxlength="20"  class="form-control" required>
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
	                        	<input type="password" id="password" name="studyPwd" value="${studyDTO.studyPwd}" size="20" maxlength="6" class="form-control">
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
	                        	<input type="number" id="maxNum" name="maxNum" value="${studyDTO.maxNum}" class="form-control" min="1" max="100" required>
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
	                        		${studyDTO.detail}
								</textarea>
	                        </div>
	                    </div>
     					<!-- 카데고리 선택 -->
	                    <div class="mb-2 row  justify-content-start align-items-center">
	                    	<div class="col-md-3">
	                         <label for="exampleInputCategory" class="form-label" style="color:#292929;"><b>Category</b></label>
	                        </div>
	                        <div class="col-md-auto">
								<select id="category" name="category" class="form-select" required>
									<option value="" selected disabled>선택하세요</option>
								    <option value="r" ${studyDTO.category eq "r" ? 'selected' : ''}>독서</option>
								    <option value="t" ${studyDTO.category eq "t" ? 'selected' : ''}>여행</option>
								    <option value="g" ${studyDTO.category eq "g" ? 'selected' : ''}>게임</option>
								    <option value="m" ${studyDTO.category eq "m" ? 'selected' : ''}>영화</option>
								    <option value="e" ${studyDTO.category eq "e" ? 'selected' : ''}>운동</option>
								    <option value="c" ${studyDTO.category eq "c" ? 'selected' : ''}>요리</option>
								    <option value="p" ${studyDTO.category eq "p" ? 'selected' : ''}>프로그래밍</option>
								    <option value="s" ${studyDTO.category eq "s" ? 'selected' : ''}>노래</option>
								    <option value="l" ${studyDTO.category eq "l" ? 'selected' : ''}>어학</option>
								    <option value="o" ${studyDTO.category eq "o" ? 'selected' : ''}>기타</option>
								</select>
	                        </div>
	                        <div class="col-md-3">
						    </div>
	                    </div>
	                    <!-- 제출 버튼 or 리셋 버튼 -->
	                    <div class="mb-2 d-flex justify-content-end">
	                    	<input type="button" id="updateBtn" value="수정" class="btn" style="background-color:#B9A4BF; color:white;">
	                    	&nbsp;
							<input type="button" id="deleteBtn" value="삭제" class="btn btn-danger" >
							&nbsp;
							<input type="button" value="취소" class="btn btn-secondary" onclick="window.location.href='"studyDetailServlet?studyNo="+no'">	
	                    </div>
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
		
		$(document).ready(function() {
			$('#updateBtn').click(function(e){
				e.preventDefault();
				
				var no = $("#no").val();
				var name = $("#name").val();
				var detail = editor.getData();
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
</html>