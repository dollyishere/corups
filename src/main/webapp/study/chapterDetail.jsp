<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }"></c:set>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>챕터 상세 페이지 - 스터디 방장</title>
 <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
  <!-- 챕터 삭제 함수 -->
  function deleteTodo(todoNo) {
      // 삭제 여부를 확인하는 알림 창 표시
      var result = confirm("할일을 삭제하시겠습니까?");
      // 사용자가 확인 버튼을 클릭했을 때
      if (result) {
            $.ajax({
                url: "todoDeleteServlet",
                type: "POST",
                async: true,
                data: { 
                	todoNo: todoNo
                },
                dataType: "text",
                success: function(response) {
                	if(response == "성공"){
	                   	alert("삭제되었습니다.");
	                   	document.location = "${contextPath}/chapter/chapterDetailServlet?chapterNo=${chapter.no}";
                	}
                	else
                	{
                		alert("삭제 실패.");
                	}
                	
                },
                error: function() {
                    alert("Error occurred while processing data");
                }
            });
      }
  }
</script>
</head>
<body>
	<div class="container-fluid m-5">
			<c:import url="/components/defaultHeader.jsp" />
			<div class="container-fluid">
			<div class="row justify-content-center align-items-center">
		    	<div class="col-md-auto">
		    		    <div class="">
		    		     <h1><b>${chapter.name}</b></h1>
					     <h5 align="center">
							<a href="<c:url value="/study/studyDetailServlet?studyNo=${study.no}" />" style="text-decoration: none;">
								<b>study: ${study.name}</b>
								</a>
							</h5>
					     
					       <c:if test="${userid == study.createUserId}">
					       		<div class="btn-group">
					       			<input type="button"
								      	 onclick="location.href='${contextPath}/chapter/chapterUpdateServlet?chapterNo=${chapter.no}'" 
								      	value="챕터 수정" style="background-color: #B9A4BF; color:white;" class="btn btn-sm">
								        <input type="button" value="Todo 추가"
											onclick="location.href='${contextPath}/todoRegisterServlet?chapterNo=${chapter.no}'"   style="background-color: #D996B5; color:white;" class="btn btn-sm">
					       		</div>
					       </c:if>
					   </div>
				<!-- 스터디 챕터 리스트 -->
				<div class="col-md-auto text-center my-3">
					<h3 class="mb-3"><b>${chapter.name}의 Todo</b></h3>
					<div class="custom-form text-center" style="width: 46rem; min-height: 30rem;">
					<table class="m-3" style="width: 40rem;">
					  <thead>
					    <tr>
					    <th scope="col">#</th>
					      <th scope="col">Todo명</th>
					      <th scope="col">시작일</th>
					      <th scope="col">종료일</th>
					      <th scope="col">상세보기</th>
					      <c:if test="${userid == study.createUserId}">
                           	<th scope="col" >관리</th>
                           </c:if>
					    </tr>
					  </thead>
						  <tbody class="table-group-divider">
						  <input type="hidden" name="chapterNo" value="${chapter.no}">
						  	<c:choose>
						  		
								<c:when test="${empty todoArray}">
									<%-- if() 부분 --%>
									<tr>
										<td class="my-5" valign="middle" align="center" colspan="7">등록된 Todo가 없습니다.</td>
									</tr>
								</c:when>
								<c:otherwise>
									<%-- else 부분 --%>
									<c:forEach var="todo" items="${todoArray}" varStatus="status">
										<tr>
											<!-- 인덱스 번호 -->
											<td align="center"><b><c:out value="${ status.index + 1 }" /></b></td>
											<!-- todo 이름 -->
											<td align="center"><c:out value="${ todo.name }" /></td>
											<!-- 시작일 -->
											<td align="center"><c:out value="${ todo.startDate }" /></td>
											<!-- 종료일 -->
											<td align="center"><c:out value="${ todo.endDate }" /></td>
											<td>
												<a href="<c:url value='/todoDetailServlet?todoNo=${todo.no}' />">
													상세보기
												</a>
											</td>
											<!-- 수정 & 삭제 버튼 -->
				                           <c:if test="${userid == study.createUserId}">
				                           		<td align="center">
				                           		<div class="btn-group" role="group">
					                           		<button class="btn btn-warning  btn-sm" onclick="location.href='${contextPath}/todoUpdateServlet?todoNo=${todo.no}'" />수정</button>  
					                              	<button class="btn btn-danger  btn-sm" onclick="deleteTodo(${todo.no})">삭제</button>
				                           		</div>
				                            	
				                              </td>
				                           </c:if>
										</tr>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						  </tbody>
						</table>
					</div>
				</div>
			</div>
		    	</div>
		  </div>
		  </div>
	</div>
</body>
</html>
</body>
</html>
