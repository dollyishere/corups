<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<!-- TodoListServlet -> statusArray, todoArray -->
<!-- TodoSearchServlet -> statusArray, todoArray, searchText, searchStatus -->


		
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 모든 할 일 화면</title>
<script type="text/javascript">
    function gotoDetail(todoNo) {
        document.location = "${contextPath}/todoDetailServlet?todoNo="+todoNo + "&myTodoPage=true";
    }
    
</script>
</head>
<body>
	<div class="container-fluid m-5">
		<c:import url="/components/defaultHeader.jsp" />
			<form action="todoSearchServlet" method="post" class="row justify-content-center align-items-center">
			    <div class="col-md-auto">
			    	<select class="form-select" name="searchStatus" id="searchStatus">
				    	<option value="ALL" ${searchStatus == 'ALL' ? 'checked' : ''}>전체</option>
				        <option value="D" ${searchStatus == 'D' ? 'checked' : ''}>완료</option>
				        <option value="P" ${searchStatus == 'P' ? 'checked' : ''}>진행중</option>
				        <option value="H" ${searchStatus == 'H' ? 'checked' : ''}>보류</option>
				        <option value="C" ${searchStatus == 'C' ? 'checked' : ''}>취소</option>
				    </select>
			    </div>
			    <div class="col-md-auto">
			    	  <input type="text" id="searchText" name="searchText" max="100" size="20" placeholder="검색어를 입력하세요" class="form-control" value="${searchText}">
			    </div>
			    <div class="col-md-auto">
			    	<input type="submit" value="검색"  style="background-color: #D996B5; color:white;"  class="btn btn-sm">
			    </div>
			 </form>
		<div class="container-fluid m-5">
			<div class="row justify-content-center align-items-center">
		    	<div class="col-md-auto">
				<div class="row justify-content-evenly align-items-flex-start">
					<!-- 내 todo 리스트 -->
				<div class="col-md-auto  mb-3">
					<h3 class="mb-3"><b>My Todo</b></h3>
					<div class="custom-form text-center" style="width: 46rem; min-height: 30rem;">
					<table class="m-3" style="width: 40rem;">
					  <thead>
					    <tr>
					    <th scope="col">#</th>
					      <th scope="col">Todo명</th>
					      <th scope="col">Study명</th>
					      <th scope="col">시작일</th>
					      <th scope="col">종료일</th>
					      <th scope="col">상태</th>
					      <th scope="col">상세보기</th>
					    </tr>
					  </thead>
						  <tbody class="table-group-divider">
						  	<c:choose>
								<c:when test="${ empty todoArray }">
									<%-- if() 부분 --%>
									<tr>
										<td class="my-5" valign="middle" align="center" colspan="7">진행 중인 todo가 없습니다.</td>
									</tr>
								</c:when>
								<c:otherwise>
									<%-- else 부분 --%>
									<c:forEach var="todo" items="${ todoArray }" varStatus="status">
										<tr>
											<!-- 인덱스 번호 -->
											<td align="center"><b><c:out value="${ status.index + 1 }" /></b></td>
											<!-- todo 이름 -->
											<td align="center"><c:out value="${ todo.name }" /></td>
											<!-- study 이름 -->
											<td align="center"><c:out value="${ todoStudyList.get(status.index).getName() }" /></td>
											<!-- 시작일 -->
											<td align="center"><c:out value="${ todo.startDate }" /></td>
											<!-- 종료일 -->
											<td align="center"><c:out value="${ todo.endDate }" /></td>
											<!-- 상태 -->
											<td align="center"><c:out value="${ statusArray.get(status.index).getStatus() }" /></td>
											<td>
												<a href="<c:url value="/todoDetailServlet?todoNo=${ todo.no }&amp;" /> ">
													상세보기
												</a>
											</td>
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