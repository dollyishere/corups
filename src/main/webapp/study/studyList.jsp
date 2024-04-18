<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"></c:set>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>studyList.jsp : 모든 스터디 목록 화면</title>
<style>
	.rect {
		width: 200px;
		height: 100px;
		border: 1px solid black;
	}
</style>
<script>
	function gotoDetail(no){
		document.location = "${contextPath}/study/studyDetailServlet?studyNo=" + no;
	}
</script>
</head>
<body>
	<div class="container-fluid m-5">
		<c:import url="/components/defaultHeader.jsp" />
			<form action="studySearchServlet" method="post" class="row justify-content-center align-items-center">
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
				<!-- 내 study 리스트 -->
				<div class="col-md-auto mb-3">
					<a href="<c:url value="/study/studyListServlet" />" style="text-decoration: none;">
						<h3 class="mb-3"><b>Study</b></h3>
					</a>
					<div class="custom-form text-center" style="width: 60rem;  min-height: 36rem;">
						<a href="studyRegister.jsp" class="btn m-3" style="background-color: #D996B5; color:white;">
							<b style="color:white;">스터디 생성</b>
						</a>
						<div class="containter">
							<c:choose>
								<c:when test="${ empty studyList }">
									<%-- if() 부분 --%>
									<tr>
										<td class="my-5" valign="middle" align="center" colspan="7">생성된 study가 없습니다.</td>
									</tr>
								</c:when>
								<c:otherwise>
									<%-- else 부분 --%>
									<div class="row justify-content-evenly">
									<c:forEach var="study" items="${ studyList }" varStatus="status">
										    <div class="col-auto">
												<div class="card border-light text-center mb-3" style="width: 10rem;">
												<div class="card-header">
													<c:choose>
								              			<c:when test="${userId == study.createUserId}">
								              				⭐
								              			</c:when>
								              			<c:otherwise>
								              				🔰
								              			</c:otherwise>
								              		</c:choose>
												</div>
												  <div class="card-body">
												  <div class="card-text"><b style="font-size: 14px;">${ study.name }</b></div>
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
													        <p class="card-text"><span class="badge text-bg-danger">요리</span></p>
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
													</form>
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