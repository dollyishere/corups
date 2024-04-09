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

	<div align="center" style="border: 0.5px solid black;">
	 <h4>
        name ${study.name} 스터디의 챕터
    </h4>
    <div align="center">
		<table border="1" summary="챕터 상세 - 목록">
			
			<colgroup>
				<col width="50" />
				<col width="300" />
				<col width="200" />
			</colgroup>
			<thead>
				<tr>
					<th>순번</th>
					<th>챕터 이름</th>
					<th>기간</th>
				</tr>
			</thead>

			<tbody>
				<c:choose>
					<%-- if() 부분 --%>
					<c:when test="${chapterCount == 0}">
						<tr>
							<td align="center" colspan="3">등록된 챕터가 없습니다.</td>
							<td><input type="button" name="insertChapter" value="챕터추가"
								onclick="location.href='<c:url value="/study/chapterRegisterServlet"/>'" /></td>
						</tr>
					</c:when>
					<c:otherwise>
						<%-- else 부분 --%>
						<c:forEach var="chapter" items="${chapterList}" varStatus="status">
							<tr>
								<!-- 챕터 번호 -->
								<td align="center"><c:out
										value="${chapterCount - (status.index + 1) + 1 - (chapter.pageNum - 1) * chapterlistCount}" />
								</td>
								<!-- 챕터 제목 -->
								<td><a
									href="<c:url value="/study/chapterListServlet?num=${chapter.num}" />">
										<c:out value="${chapter.name}" />
								</a></td>
								<!-- 챕터 기간 -->
								<td align="center"><c:out value="${ chapter.startDate}" /></td>
								<td align="center"><c:out value="${chater.endDate}" /></td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>

			<tfoot>
				<!-- 페이지 번호 -->
				<tr>
					<td align="center" colspan="5"><c:out value="${pageNavigator}"
							escapeXml="false" /></td>
				</tr>
			</tfoot>
		</table>
		</div>
	</div>
</body>
</html>
