<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Corups 관리자 메인 화면 : admin/main.jsp</title>
</head>
<style>
	body {
	    background-image: url('../resources/imgs/milky-way.jpg');
	}
</style>
<body>
	<div class="container-fluid m-5">
		<c:import url="/components/adminHeader.jsp" />
		<h1>${ session.adminID } 관리자님, 접속을 환영합니다.</h1>
		<div class="container-fluid m-5">
			<div class="row justify-content-evenly align-items-flex-start">
				<div class="col-md-auto mb-3">
					<h2 class="mb-3">My Study</h2>
					<div class="custom-form text-center" style="width: 30rem;  min-height: 30rem;">
						<form action="<c:url value="/study/studyListServlet" />" method="get">
						    <button type="submit" style="background-color: #B9A4BF; color:white;" class="btn">
						        <b style="color:white;">스터디 참여</b>
						    </button>
						</form>
					</div>
				</div>
				<div class="col-md-auto  mb-3">
					<h2 class="mb-3">My Todo</h2>
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
								<c:when test="${ empty memberList }">
									<%-- if() 부분 --%>
									<tr>
										<td class="m-5" valign="middle" align="center" colspan="7">진행 중인 todo가 없습니다.</td>
									</tr>
								</c:when>
								<c:otherwise>
									<%-- else 부분 --%>
									<c:forEach var="todo" items="${ todoList }" varStatus="status">
										<tr>
											<!-- 인덱스 번호 -->
											<td align="center"><c:out value="${ todo.index + 1 }" /></td>
											<!-- todo 이름 -->
											<td align="center"><c:out value="${ todo.name }" /></td>
											<!-- study 이름 -->
											<td align="center"><c:out value="${ todoStudyList.get(todo.index).getName() }" /></td>
											<!-- 시작일 -->
											<td align="center"><c:out value="${ todo.startDate }" /></td>
											<!-- 종료일 -->
											<td align="center"><c:out value="${ todo.endDate }" /></td>
											<!-- 상태 -->
											<td align="center"><c:out value="${ statusList.get(todo.index).getStatus() }" /></td>
											<td>
												<a href="<c:url value="/member/updateServlet?id=${ member.id }&amp;nowPath=a" /> ">
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
	

	<input type="button" value="회원관리" onclick="location.href='<c:url value="/member/memberListServlet"/>'" />
	<input type="button" value="스터디관리" onclick="location.href='<c:url value="/member/memberListServlet"/>'" />
</body>
</html>