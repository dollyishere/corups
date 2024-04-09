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

</head>
<body>
	<table border="1" summary="챕터 상세 - 목록">
		<caption>챕터 이름</caption>
		<colgroup>
			<col width="50" />
			<col width="300" />
			<col width="200" />
		</colgroup>
		<thead>
			<!-- 챕터 이름   -->
			<tr>
				<th>순번</th>
				<th>챕터 이름</th>
				<th>기간</th>
			</tr>
		</thead>

		<tody> <c:choose>
			<%-- if() 부분 --%>
			<c:when test="${chapterCount ==0}">
				<tr>
					<td align="center" colspan="3">등록된 챕터가 없습니다.</td>
					<input type="button" name="insertChapter" value="챕터추가"
						onclick="location.hrdf='<c:url value="/study/chapterRegisterServlet"/>'" />
				</tr>
			</c:when>

			<c:otherwise>
				<%-- else 부분 --%>
				<c:forEach var="chapter" items="${chapterList}" varStatus="status">
					<tr>
						<!-- 챕터 번호 -->
						<td align="center"><c:out
								value="${chapterCount - (status.index+1) + 1 - (chapter.pageNum - 1) * chapterlistCount}" />
						</td>

						<!-- 챕터 제목 -->
						<td><a
							href="<c:url value="/study/chapterListServlet?num=${chapter.num}" />">
								<c:out value="${board.subject}" />
						</a></td>


						<!-- 챕터 기간 -->
						<td align="center"><c:out
								value="${fn:substring(chapter.startDate, 0, 10)}" /></td>
						<td align="center"><c:out value="${todo.endDate}" /></td>


					</tr>
				</c:forEach>
			</c:otherwise>

		</c:choose> </tody>

		<tfoot>
			<!-- 페이지 번호 -->
			<tr>
				<td align="center" colspan="5"><c:out value="${pageNavigator}"
						escapeXml="false" /></td>
			</tr>
		</tfoot>
</body>
</html>