<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"></c:set>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#margin {
		margin-bottom: 100px; /* 행 사이의 간격 설정 */
	}
</style>
<script>
	function gotoUpdate(no){
		document.location = "${contextPath}/study/studyDetailServlet?studyNo=" + no;
	}
</script>
</head>
<body>
<div class="container-fluid m-5">
	<c:import url="/components/adminHeader.jsp" />
		<div class="container mt-5">
			<div class="row justify-content-center align-items-flex-center mt-5">
				<div class="col-md-auto mb-3">
					<h2 class="mb-3" style="color:#F0EAEA;">Study Management</h2>
					<div class="custom-form text-center" style="width: 42rem; min-height: 30rem;">
						<table summary="스터디목록" class="table-light">
							<colgroup>
								<col width="40" />
								<col width="180" />
								<col width="100" />
								<col width="150" />
								<col width="80" />
								<col width="80" />
							</colgroup>
							<thead class="my-5">
								<!-- 회원목록제목 -->
								<tr>
									<th>#</th>
									<th>스터디이름</th>
									<th>생성한회원</th>
									<th>생성날짜</th>
									<th>참여자수</th>
									<th>수정</th>
								</tr>
							</thead>
								<!-- 회원목록의 데이터부분 시작 -->
							<tbody class="table-group-divider">
								<c:choose>
									<c:when test="${ empty studyList }">
										<%-- if() 부분 --%>
										<tr>
											<td align="center" colspan="6">등록된 스터디가 없습니다.</td>
										</tr>
									</c:when>
									<c:otherwise>
										<%-- else 부분 --%>
										<c:forEach var="study" items="${ studyList }" varStatus="status">
											<tr class="my-5">
												<!-- 인덱스 번호 -->
												<td align="center"><b><c:out value="${ status.index }" /></b></td>
												<!-- 스터디 이름 -->
												<td align="center"><c:out value="${ study.name }" /></td>
												<!-- 생성자 아이디 -->
												<td align="center"><c:out value="${ study.createUserId }" /></td>
												<!-- 생성일 -->
												<td align="center"><c:out value="${ study.createdDate }" /></td>
												<!-- 참여자 수 -->
												<td align="center"><b style="font-size: 14px;">(${ studyMemberNumList.get(status.index) }/${ study.maxNum })</b></td>
												<!-- 수정 -->
												<td>
													<a href="<c:url value="/study/studyUpdateServlet?studyNo=${study.no}" /> "  class="btn" style="background-color:#D996B5; color:white;">
														수정
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
</body>
</html>