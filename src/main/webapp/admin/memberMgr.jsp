<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리 페이지 : memberMgr.jsp</title>
</head>
<body>
<div class="container-fluid m-5">
	<c:import url="/components/adminHeader.jsp" />
		<div class="container mt-5">
			<div class="row justify-content-center align-items-flex-center mt-5">
				<div class="col-md-auto mb-3">
					<div class="custom-form text-center" style="width: 50rem; min-height: 30rem;">
						<table summary="회원목록"  style="width: 46rem; class="table-light">
							<colgroup>
								<col width="40" />
								<col width="80" />
								<col width="100" />
								<col width="80" />
								<col width="150" />
								<col width="150" />
								<col width="80" />
							</colgroup>
							<thead>
								<!-- 회원목록제목 -->
								<tr>
									<th>#</th>
									<th>회원이름</th>
									<th>회원아이디</th>
									<th>패스워드</th>
									<th>생년월일</th>
									<th>이메일</th>
									<th>수정</th>
								</tr>
							</thead>
								<!-- 회원목록의 데이터부분 시작 -->
							<tbody class="table-group-divider">
								<c:choose>
									<c:when test="${ empty memberList }">
										<%-- if() 부분 --%>
										<tr>
											<td align="center" colspan="6">등록된 회원이 없습니다.</td>
										</tr>
									</c:when>
									<c:otherwise>
										<%-- else 부분 --%>
										<c:forEach var="member" items="${ memberList }" varStatus="status">
											<tr>
												<!-- 인덱스 번호 -->
												<td align="center" scope="row"><c:out value="${ status.index }" /></td>
												<!-- 회원 이름 -->
												<td align="center"><c:out value="${ member.name }" /></td>
												<!-- 회원아이디 -->
												<td align="center"><c:out value="${ member.id }" /></td>
												<!-- 패스워드 -->
												<td align="center"><c:out value="${ member.pwd }" /></td>
												<!-- 생년월일 -->
												<td align="center"><c:out value="${ member.birthday }" /></td>
												<!-- 이메일 -->
												<td align="center"><c:out value="${ member.email }" /></td>
												<!-- 수정 -->
												<td>
													<a href="<c:url value="/member/updateServlet?id=${ member.id }&amp;nowPath=a" /> ">
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