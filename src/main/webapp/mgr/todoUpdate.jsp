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

<script type="text/javascript" src="<c:url value="/ckeditor5/build/ckeditor.js" />"></script>
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
	        	<!-- todo 등록 폼 -->
	            <div class="custom-form text-center" style="width: 30rem;">
	                <form enctype="multipart/form-data" accept-charset="UTF-8">
	                	<!-- 클래스 이름 -->
	                    <div class="mb-2 row  justify-content-start align-items-center">
	                    	<div class="col-md-3">
	                         <label for="exampleInputClassName" class="form-label" style="color:#292929;"><b>Class name</b></label>
	                        </div>
	                        <div class="col-md-auto">
	                        	${ study.name }
	                        </div>
	                        <div class="col-md-3">
						    </div>
	                    </div>
	                    <!-- 챕터 이름 -->
	                    <div class="mb-2 row  justify-content-start align-items-center">
	                    	<div class="col-md-3">
	                         <label for="exampleInputChapterName" class="form-label" style="color:#292929;"><b>Chapter name</b></label>
	                        </div>
	                        <div class="col-md-auto">
	                        	${ chapter.name }
	                        </div>
	                        <div class="col-md-3">
						    </div>
	                    </div>
	                	<!-- 이름 -->
	                    <div class="mb-2 row  justify-content-start align-items-center">
	                    	<div class="col-md-3">
	                         <label for="exampleInputTodoName" class="form-label" style="color:#292929;"><b>Name</b></label>
	                        </div>
	                        <div class="col-md-auto">
	                        	<input type="text" id="name" name="name" value="${todo.name}" size="20" maxlength="20"  class="form-control" required>
	                        </div>
	                        <div class="col-md-3">
						    </div>
	                    </div>
	                    <!-- 시작일 -->
	                    <div class="mb-2 row  justify-content-start align-items-center">
	                    	<div class="col-md-3">
	                         <label for="exampleInputStartDate" class="form-label" style="color:#292929;"><b>Start date</b></label>
	                        </div>
	                        <div class="col-md-auto">
	                        	<input type="date" id="startDate" name="startDate" value="${todo.startDate}" size="10"  class="form-control" >
	                        </div>
	                        <div class="col-md-3">
						    </div>
	                    </div>
	                    <!-- 종료일 -->
	                    <div class="mb-2 row  justify-content-start align-items-center">
	                    	<div class="col-md-3">
	                         <label for="exampleInputEndDate" class="form-label" style="color:#292929;"><b>End date</b></label>
	                        </div>
	                        <div class="col-md-auto">
	                        	<input type="date" id="endDate" name="endDate" value="${todo.endDate}" size="10"  class="form-control" required >
	                        </div>
	                        <div class="col-md-3">
						    </div>
	                    </div>
	                    <!-- 내용 -->
	                    <div class="mb-2 row  justify-content-start align-items-center">
	                    	<div class="col-md-3">
	                         <label for="exampleInputName" class="form-label" style="color:#292929;"><b>Content</b></label>
	                        </div>
	                        <div class="col-md-auto">
	                        	<textarea name="content" id="editor">
	                        		${todo.detail}
								</textarea>
	                        </div>
	                    </div>
	                    <!-- 파일 -->
	                    	<table>
			                    <c:forEach var="file" items="${files}">
			                    	<tr>
			                    	<td>
						             ${file.name} <input type="button" value="삭제" onclick="deleteFile(this, ${file.no})">
						             </td>
						             </tr>
						        </c:forEach>
				             </table>
	                    <div class="mb-2 row  justify-content-start align-items-center">
	                        <div class="col-md-auto">
	                        	<input  class="form-control" type="file" id="file" name="file" multiple>
	                        </div>
	                    </div>
	                    <!-- 제출 버튼 or 리셋 버튼 -->
	                    <div class="mb-2 d-flex justify-content-end">
	                    	<input type="hidden" id="todoNo" name="todoNo" value="${todo.no}">
	                        <input type="button" id="updateBtn" value="수정" class="btn" style="background-color:#B9A4BF; color:white;"/>
	                        &nbsp;
	                        <input type="button" id="deleteBtn" value="삭제" class="btn btn-danger" >
	                        &nbsp;
	                        <input type="button" value="취소" onclick="goBack()" class="btn btn-secondary" />
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
			document.location ="${contextPath}/chapter/chapterDetailServlet?chapterNo=${chapter.no}";
	    }
		
        $(document).ready(function() {
        	
            $("#updateBtn").click(function(e) {
                e.preventDefault();
                
                console.log("할 일 수정");

                var name = $("#name").val();
                var startDate = $("#startDate").val();
                var endDate = $("#endDate").val();
                var content = editor.getData();
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
                        	alert("값을 모두 입력해주세요.");
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
	            var result = confirm("할일을 삭제하시겠습니까?");
	            if(result){
		            var todoNo = $("#todoNo").val();
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
		                 	document.location = "${contextPath}/chapter/chapterDetailServlet?chapterNo=${chapter.no}";
		                },
		                error: function() {
		                    alert("Error occurred while processing data");
		                }
		            });	            	
	            }
	            
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
        
        // 시작일(input 태그)이 변경될 때 실행되는 함수
        document.getElementById('startDate').addEventListener('change', function() {
            var startDate = new Date(this.value); // 시작일 값
            var endDateInput = document.getElementById('endDate');

            // 종료일(input 태그)의 최소값 설정
            // 시작일보다 이전의 날짜를 선택하지 못하도록 함
            endDateInput.min = this.value;

            // 종료일(input 태그)의 값을 변경
            // 만약 종료일이 시작일보다 이전으로 설정되어 있다면, 시작일과 동일한 값으로 설정
            var endDate = new Date(endDateInput.value);
            if (endDate < startDate) {
                endDateInput.value = this.value;
            }
        });

        // 종료일(input 태그)이 변경될 때 실행되는 함수
        document.getElementById('endDate').addEventListener('change', function() {
            var endDate = new Date(this.value); // 종료일 값
            var startDateInput = document.getElementById('startDate');

            // 시작일(input 태그)의 최대값 설정
            // 종료일보다 이후의 날짜를 선택하지 못하도록 함
            startDateInput.max = this.value;

            // 시작일(input 태그)의 값을 변경
            // 만약 시작일이 종료일보다 이후로 설정되어 있다면, 종료일과 동일한 값으로 설정
            var startDate = new Date(startDateInput.value);
            if (startDate > endDate) {
                startDateInput.value = this.value;
            }
        });
	
    </script>
</html>