<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.rect {
		width: 200px;
		height: 100px;
		border: 1px solid black;
	}
</style>

<script type="text/javascript">
    function gotoDetail(no) {
    	// 세션 변수 설정
<%--    		<% session.setAttribute("myTodoPage", "true"); %> --%>
        document.location.href = "${contextPath}/study/studyDetailServlet?studyNo=" + no;
    }
    
</script>

</head>
<body>
	<div class="container-fluid m-5">
		<c:import url="/components/defaultHeader.jsp" />
		<div class="container-fluid m-5">
			<div class="row justify-content-center align-items-center">
		    	<div class="col-md-auto">
				<div class="row justify-content-evenly align-items-flex-start">
				<!-- 내 study 리스트 -->
				<div class="col-md-auto mb-3">
					<a href="<c:url value="/study/myStudyServlet" />" style="text-decoration: none;">
						<h2 class="mb-3">My Study</h2>
					</a>
					<div class="custom-form text-center" style="width: 60rem;  min-height: 36rem;">
						<div class="btn-group m-3" role="group">
							<a href="<c:url value="/study/studyListServlet" />" class="btn" style="background-color: #B9A4BF; color:white;">
								<b style="color:white;">스터디 참여</b>
							</a>
							<a href="studyRegister.jsp" class="btn" style="background-color: #D996B5; color:white;">
								<b style="color:white;">스터디 생성</b>
							</a>
						</div>
						<div class="containter">
							<c:choose>
								<c:when test="${ empty myStudyList }">
									<%-- if() 부분 --%>
									<tr>
										<td class="my-5" valign="middle" align="center" colspan="7">생성한 study가 없습니다.</td>
									</tr>
								</c:when>
								<c:otherwise>
									<%-- else 부분 --%>
									<div class="row justify-content-evenly">
									<c:forEach var="study" items="${ myStudyList }" varStatus="status">
										    <div class="col-auto">
												<div class="card border-light text-center mb-3" style="width: 10rem;">
												  <div class="card-header"><b>${ study.name }</b></div>
												  <div class="card-body">
												    <p class="card-text"><b style="font-size: 12px;">(${ myStudyMemberNumList.get(status.index) }/${ study.maxNum })</b></p>
												    <c:choose>
													    <c:when test="${study.category eq 'r'}">
													        <p class="card-text"><span class="badge text-bg-success">독서</span></p>
													    </c:when>
													    <c:when test="${study.category eq 't'}">
													        <p class="card-text"><span class="badge text-bg-info">여행</span></p>
													    </c:when>
													    <c:when test="${study.category eq 'g'}">
													        <p class="card-text"><span class="badge text-bg-danger">게임</span></p>
													    </c:when>
													    <c:when test="${study.category eq 'm'}">
													        <p class="card-text"><span class="badge text-bg-warning">영화</span></p>
													    </c:when>
													    <c:when test="${study.category eq 'e'}">
													        <p class="card-text"><span class="badge text-bg-info">운동</span></p>
													    </c:when>
													    <c:when test="${study.category eq 'c'}">
													        <p class="card-text"><span class="adge text-bg-danger">요리</span></p>
													    </c:when>
													    <c:when test="${study.category eq 'p'}">
													        <p class="card-text"><span class="badge text-bg-dark">프로그래밍</span></p>
													    </c:when>
													    <c:when test="${study.category eq 's'}">
													        <p class="card-text"><span class="badge text-bg-primary">노래</span></p>
													    </c:when>
													    <c:when test="${study.category eq 'l'}">
													        <p class="card-text"><span class="badge text-bg-success">어학</span></p>
													    </c:when>
													    <c:when test="${study.category eq 'o'}">
													        <p class="card-text"><span class="badge text-bg-light">기타</span></p>
													    </c:when>
													</c:choose>
													<form action="<c:url value="/study/studyDetailServlet" />" method="get">
														<input type="hidden" value="${ study.no }" name="studyNo">
													    <button type="submit" style="background-color: #D996B5; color:white;" class="btn btn-sm">
													        <b style="color:white;">상세보기</b>
													    </button>
													</form><br>
												  </div>
												</div>
										    </div>
									 
									</c:forEach>
									 </div>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
		    	</div>
		  	</div>
		  	</div>
	  </div>
	</div>
</body>
</html>