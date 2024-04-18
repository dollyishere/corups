<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>할일 정보 확인</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    $("#okBtn").click(function(){
        var status = $("input[name='status']:checked").val();
        var todoNo = $("#todoNo").val();
        var datas = {
            status: status,
            todoNo: todoNo
        };
        
        $.ajax({
            url: "statusUpdateServlet",
            type: "POST",
            data: datas,
            dataType: "text",  // 응답 데이터 타입 지정
            success: function(response){
            	document.location ="${contextPath}/chapter/chapterDetailServlet?chapterNo=${chapter.no}";
            },
            error: function(error){
                alert("Error: " + error);  // 에러 처리
            }
        });
    });
});

function gotoUpdate(todoNo){
	document.location = "todoUpdateServlet?todoNo="+todoNo;
}
</script>
</head>
<body>
	<div class="container-fluid m-5">
		<c:import url="/components/defaultHeader.jsp" />
		<img alt="logo.png" src='<c:url value="/resources/imgs/logos/logo_detail_t.png"/>' class="mb-2" style="height:60px;">
		<div class="container-fluid mt-3">
			<div class="row justify-content-center align-items-center">
				<div class="col-md-auto">
					<div class="">
						<h1><b>${todo.name}</b></h1>
						<div>
							<a href="<c:url value="/study/studyDetailServlet?studyNo=${study.no}" />" style="text-decoration: none;">
								<b>study: ${study.name}</b>
							</a>
							<b> / </b>
							<a href="<c:url value="/chapter/chapterDetailServlet?chapterNo=${chapter.no}" />" style="text-decoration: none;">
								<b>chapter: ${chapter.name}</b>
							</a>
						</div>
					</div>
					<!-- todo 상태 -->
					<div class="col-md-auto text-center my-3">
						<div class="custom-form text-center" style="width: 30rem; min-height: 20rem;">
							<div class="row justify-content-center align-items-center">
								<div class="row justify-content-center align-items-cente">
									<%-- <input type="radio" class="btn-check" name="options-outlined" id="success-outlined" autocomplete="off"  id="status" name="status" value="D" ${status == 'D' ? 'checked' : ''}>
									<label class="btn btn-outline-success" for="success-outlined"  style="font-weight: bold;">완료</label>
					                <input type="radio" class="btn-check" name="options-outlined" id="primary-outlined" autocomplete="off"  id="status" name="status" value="P" ${status == 'P' ? 'checked' : ''}>
					                <label class="btn btn-outline-primary" for="primary-outlined"  style="font-weight: bold;">진행중</label>
					                <input type="radio" class="btn-check" name="options-outlined" id="warning-outlined" autocomplete="off"  id="status" name="status" value="H" ${status == 'H' ? 'checked' : ''}>
					                <label class="btn btn-outline-warning" for="warning-outlined"  style="font-weight: bold;">보류</label>
					                <input  type="radio" class="btn-check" name="options-outlined" id="dark-outlined" autocomplete="off"  id="status" name="status" value="C" ${status == 'C' ? 'checked' : ''}>
					                <label class="btn btn-outline-dark" for="dark-outlined"  style="font-weight: bold;">취소</label> --%>
					                <div class="col-md-auto form-check">
					                	 <input class="form-check-input" type="radio" id="status1" name="status" value="D" ${status == 'D' ? 'checked' : ''}>
						                 <label class="form-check-label" for="status1">
										   	완료
										  </label>
					                </div>
										
									  <div class="col-md-auto form-check">
					                <input class="form-check-input" type="radio" id="status2" name="status" value="P" ${status == 'P' ? 'checked' : ''}>
					                 	<label class="form-check-label" for="status2">
										    진행중
										  </label>
					                </div>
					                <div class="col-md-auto form-check">
						                <input class="form-check-input" type="radio" id="status3" name="status" value="H" ${status == 'H' ? 'checked' : ''}>
						                <label class="form-check-label" for="status3">
										    보류
										  </label>
					                </div>
					                <div class="col-md-auto form-check">
						                <input class="form-check-input" type="radio" id="status4" name="status" value="C" ${status == 'C' ? 'checked' : ''}>
						                <label class="form-check-label" for="status14">
										    취소
										</label>
					                </div>
								</div>
								<div>
									
								</div>
							</div>
							<div class="my-5">
								${todo.detail}
							</div>
							<div>
								<c:forEach var="file" items="${files}">
									<a href="${contextPath }/fileDownloadServlet?fileName=${file.name}&todoNo=${todo.no}">${file.name} 내려받기</a>
					            </c:forEach>
							</div>
						</div>
					</div>
					
				    		<div class="btn-group mt-3">
								<input type="hidden" id="todoNo" value="${todo.no}">
								<c:choose>
				    				<c:when test="${mgr}">
										<input type="button" value="수정" onclick="gotoUpdate(${todo.no})"  style="background-color: #B9A4BF; color:white;" class="btn btn-sm">
								   	</c:when>
				    			</c:choose>
								<input type="button" id="okBtn"
									value="확인" class="btn btn-sm btn-secondary">
							</div>
				 
				</div>
			</div>
		</div>
	</div>
</body>
</html>

