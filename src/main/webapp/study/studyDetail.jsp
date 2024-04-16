<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }"></c:set>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스터디 상세 - 챕터부분</title>
<style>
	.grid-container {
		display: grid;
		grid-template-columns: 200px 200px;
		grid-template-rows: 100px 100px;
	}
	#studyName {
		margin-left: 50px;
		border: 1px solid black;
		width: 40%;
	}
	#studyDetail {
		margin-right: 50px;
		border: 1px solid black;
		width: 40%
	}
	#studyMember {
		border: 1px solid black;
	}
	#chapter {
	}
	.rect {
		width: 200px;
		height: 100px;
		border: 1px solid black;
	}
	.item1 {
		grid-column: 1;
		grid-row: 1;
	}
	.item2 {
		grid-column: 2; /* 두 번째 열 */
		grid-row: 1; /* 두 번째 행 */
	}
	.item3 {
		grid-column: 1;
		grid-row: 2;
	}
	.item4 {
		grid-column: 2;
		grid-row: 2;
	}
</style>
</head>
<body>
<div class="grid-container">
	<div id="studyName" class="item1">
		<p>
			${studyName}
		</p>
	</div>
	<div id="studyDetail" class="item2">
		<p>
			${study.detail}
		</p>
	</div>
<c:choose>
	<c:when test="${empty memberStudyList}">
		<div class="rect" class="item3">
			<p>등록된 참여자가 없습니다.</p>
		</div>
	</c:when>
	<c:when test="${!empty memberStudyList }">
		<section id="studyMember">
			<c:forEach var="member" items="${memberStudyList}">	
				<div class="item3">	
					<p style="border: 1px solid blue">
						${member.id}	
					</p>
					<p style="border: 1px solid blue">
						${member.image} 
					</p>
				</div>
			</c:forEach>
		</section>
	</c:when>
</c:choose>	
	<div id="chapter" class="item4" align="center" style="border: 0.5px solid black;">

		<h4>${studyName} 의 챕터</h4>
		<div align="center">
			<table border="1" summary="챕터 상세 - 목록">

				<colgroup>
					<col width="50" />
					<col width="300" />
					<col width="200" />
					<col width="200" />
				</colgroup>
				<thead>
					<tr>
						<th>순번</th>
						<th>챕터 이름</th>
						<th>StartDate</th>
						<th>EndDate</th>
					</tr>
				</thead>

				<tbody>
				<tr><td>
					<input type="button" value="chapter 추가" 	onclick="location.href='${contextPath}/chapter/chapterRegisterServlet?studyNo=${study.no}'" />
				</td></tr>
					<c:choose>
						<c:when test="${chapterCount == 0}">
							<tr>
								<td align="center" colspan="4">등록된 챕터가 없습니다.</td>

							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="chapter" items="${chapterList}"
								varStatus="status">
								<tr>
									<!-- 챕터 번호 -->
									<td align="center">${chapter.no}</td>
									<!-- 챕터 제목 -->
									<td><a
										href="<c:url value="/study/chapterDetailServlet?no=${chapter.no}" />">
											<c:out value="${chapter.name}" />
									</a></td>
									<!-- 챕터 기간 -->
									<td align="center"><c:out value="${chapter.startDate}" /></td>
									<td align="center"><c:out value="${chapter.endDate}" /></td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>

					<tfoot>
					<!-- 페이지 번호 -->
					<tr>
						<td align="center" colspan="4">페이지 네비<c:out
								value="${pageNavigator}" escapeXml="false" /></td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
</div> <!-- grid container 끝 -->
					<input type="button" onclick="window.location.href='${contextPath}/study/studyUpdateServlet?no=${studyNo}'" value="스터디 수정하기">							
</body>
</html>