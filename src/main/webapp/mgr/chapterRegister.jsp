<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="contextPath" value="${pageContext.request.contextPath }"></c:set>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>챕터 추가 화면 - 스터디 방장</title>
</head>
<body>
	<h2 align="center">
		study.name${study.name}
		</h1>

		<div align="center">
			<table align="center" border="1" summary="챕터 상세">
				<tr>
					<div style="display: inline-block;">
						<h3 align="center">chapter.name${chapter.name}</h3>
						<input type="button" value="chapter 추가">
					</div>
				</tr>

				<colgroup>
					<col width="50" />
					<col width="300" />
					<col width="200" />
				</colgroup>
				<thead>
					<!-- 챕터 이름   -->
					<tr>이름
					</tr>
					<tr>시작날짜
					</tr>
					<th>마감날짜</th>



				</thead>

				<tody> <c:choose>
					<%-- if() 부분 --%>
					<c:when test="${todoCount ==0}">
						<tr>
							<td align="center" colspan="4">todo가 없습니다.</td>
							<input type="button" name="insertTodo" value="할일추가"
								onclick="location.hrdf='<c:url value="/study/todoRegisterServlet"/>'" />
						</tr>
					</c:when>

					<c:otherwise>
						<%-- else 부분 --%>
						<c:forEach var="todo" items="${todoList}" varStatus="status">
							<tr>
								<!-- 챕터 이름 -->
								<th>&{chapter.name}</th>
							</tr>
							<tr>

								<!-- 챕터 이름 -->
								<td><a
									href="<c:url value="/study/chapterListServlet?num=${chapter.num}" />">
										<c:out value="${board.subject}" />
								</a></td>


								<!-- 시작 날짜 -->
								<td align="center"><c:out
										value="${fn:substring(chapter.startDate, 0, 10)}" /></td>
								<td align="center"><c:out value="${todo.endDate}" /></td>

								<!-- 마감 날짜 -->
								<td align="center"><c:out
										value="${fn:substring(chapter.startDate, 0, 10)}" /></td>
								<td align="center"><c:out value="${todo.endDate}" /></td>

								<!-- 상태 -->
								<td align="center"><c:out value="${todo.status}" /></td>


							</tr>
						</c:forEach>
					</c:otherwise>

				</c:choose> </tody>

				<tfoot>
					
				</tfoot>

				</div>

				<input type="button" name="updateTodo" value="수정하기"
					onclick="location.hrdf='<c:url value="/mgr/TodoupdateServlet"/>'" />
				</tr>
			</table>
</body>
</html>