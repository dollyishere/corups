<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="contextPath" value="${pageContext.request.contextPath }"></c:set>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>챕터 상세 페이지 - 스터디 방장</title>
 	<script type="text/javascript">
	 function gotoTodoRegister() {
	        document.location = "${contextPath}/todoRegisterServlet";
	}
	 </script>
</head>
<body>
	<h2 align="center">
		스터디 이름
		</h1>

		<div align="center">
			<table align="center" border="1" summary="챕터 상세">
				<tr>
					<div style="display: inline-block;">
						<h3 align="center">	chapter.name${chapter.name} </h3>
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
					<tr>
						<th>순번</th>
						<th>todo 이름</th>
						<th>기간</th>
						<th>상태</th>
					</tr>
				</thead>

				<tody> 
						<tr>
							<td align="center" colspan="4">
							<input type="button" name="insertTodo" value="할일추가" onclick="gotoTodoRegister()" />
							</td>
						</tr>
												
						<%-- else 부분 --%>
						<c:forEach var="todo" items="${todoList}" varStatus="status">
							<tr>
								<!-- 챕터 이름 -->
								<th>&{chapter.name}</th>
							</tr>
							<tr>
								<!-- 할 일  번호 -->
								<td align="center"><c:out
										value="${chapterCount - (status.index+1) + 1 - (chapter.pageNum - 1) * chapterlistCount}" />
								</td>

								<!-- 할 일 이름 -->
								<td><a
									href="<c:url value="/study/chapterListServlet?num=${chapter.num}" />">
										<c:out value="${board.subject}" />
								</a></td>


								<!-- 할 일 기간 -->
								<td align="center"><c:out
										value="${fn:substring(chapter.startDate, 0, 10)}" /></td>
								<td align="center"><c:out value="${todo.endDate}" /></td>

								<!-- 상태 -->
								<td align="center"><c:out value="${todo.status}" /></td>


							</tr>
						</c:forEach>
					

					</tody>

				<tfoot>
					<!-- 페이지 번호 -->
					<tr>
						<td align="center" colspan="4"><c:out
								value="${pageNavigator}" escapeXml="false" /></td>
					</tr>
				</tfoot>

				</div>
				
				<input type="button" name="updateTodo" value="수정하기"
								onclick="location.hrdf='<c:url value="/mgr/TodoupdateServlet"/>'" />
						</tr>
			</table>
</body>
</html>