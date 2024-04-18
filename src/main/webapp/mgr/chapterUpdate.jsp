<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }"></c:set>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>챕터 수정 - 스터디 방장</title>
</head>
<body>
<div class="container-fluid m-5">
		<c:import url="/components/defaultHeader.jsp" />
		<div class="container-fluid m-5">
		<div class="row justify-content-center align-items-center">
	    	<div class="col-md-auto">
		    		<img alt="logo.png" src="<c:url value="/resources/imgs/logos/logo_update_c.png" />" class="mb-2">
		   		</div>
			   <div class="col-md-1">
		       </div>
		        <div class="col-md-auto">
	        	<!-- todo 등록 폼 -->
	            <div class="custom-form text-center" style="width: 30rem;">
	                <form action="${contextPath}/chapter/chapterUpdateServlet" method="post" accept-charset="UTF-8">
	                	<input type="hidden" name="chapterNo" value="${chapter.no}">
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
	                	<!-- 이름 -->
	                    <div class="mb-2 row  justify-content-start align-items-center">
	                    	<div class="col-md-3">
	                         <label for="exampleInputTodoName" class="form-label" style="color:#292929;"><b>Name</b></label>
	                        </div>
	                        <div class="col-md-auto">
	                        	<input type="text" id="name" name="chapterName" value="${chapter.name}" size="20" maxlength="20"  class="form-control" required>
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
	                        	<input type="date" id="startDate" name="startDate" value="${chapter.startDate}" size="10"  class="form-control" >
	                        </div>
	                        <div class="col-md-3">
						    </div>
	                    </div>
	                    <!-- 마감일 -->
	                    <div class="mb-2 row  justify-content-start align-items-center">
	                    	<div class="col-md-3">
	                         <label for="exampleInputEndDate" class="form-label" style="color:#292929;"><b>End date</b></label>
	                        </div>
	                        <div class="col-md-auto">
	                        	<input type="date" id="endDate" name="endDate" value="${chapter.endDate}" size="10"  class="form-control" required >
	                        </div>
	                        <div class="col-md-3">
						    </div>
	                    </div>
	                    <!-- 제출 버튼 or 리셋 버튼 -->
	                    <div class="mb-2 d-flex justify-content-end">
	                    	<input type="hidden" name="studyNo" value="${study.no}" />
	                        <input type="submit" name="updateChapter" value="수정" onclick="confirmRegister()" class="btn" style="background-color:#B9A4BF; color:white;"/>
	                        &nbsp;
	                        <input type="button" onclick="deleteChapter(${chapter.no})" value="삭제" class="btn btn-danger"/>
	                        &nbsp;
	                        <input type="button" name="cancelUpdate" value="취소"
							onclick="location.href='${contextPath}/study/studyDetailServlet?studyNo=${study.no}'" class="btn btn-secondary" />
	                    </div>
	                </form>
                </div>
           </div>
	  	</div>
	  </div>
	</div>
</body>
<script>
  <!-- 챕터 삭제 함수 -->
  function deleteChapter(chapterNo) {
      // 삭제 여부를 확인하는 알림 창 표시
      var result = confirm("챕터를 삭제하시겠습니까?");
      // 사용자가 확인 버튼을 클릭했을 때
      if (result) {
          // form 엘리먼트 동적으로 생성
          var form = document.createElement('form');
          form.setAttribute('method', 'post');
          form.setAttribute('action', '<c:url value="/chapter/chapterDeleteServlet"/>');

          // 챕터 번호를 전송하는 hidden input 추가
          var inputChapterNo = document.createElement('input');
          inputChapterNo.setAttribute('type', 'hidden');
          inputChapterNo.setAttribute('name', 'chapterNo');
          inputChapterNo.setAttribute('value', chapterNo);
          form.appendChild(inputChapterNo);

          // form을 body에 추가하고 자동으로 전송
          document.body.appendChild(form);
          form.submit();
      }
  }
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
