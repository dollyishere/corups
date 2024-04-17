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
<script>
  <!-- 챕터 삭제 함수 -->
  function deleteTodo(TodoNo) {
      // 삭제 여부를 확인하는 알림 창 표시
      var result = confirm("챕터를 삭제하시겠습니까?");
      // 사용자가 확인 버튼을 클릭했을 때
      if (result) {
          // form 엘리먼트 동적으로 생성
          var form = document.createElement('form');
          form.setAttribute('method', 'post');
          form.setAttribute('action', '<c:url value="/todoDeleteServlet"/>');

          // 챕터 번호를 전송하는 hidden input 추가
          var inputTodoNo = document.createElement('input');
          inputTodoNo.setAttribute('type', 'hidden');
          inputTodoNo.setAttribute('name', 'TodoNo');
          inputTodoNo.setAttribute('value', TodoNo);
          form.appendChild(inputTodoNo);

          // form을 body에 추가하고 자동으로 전송
          document.body.appendChild(form);
          form.submit();
      }
  }
</script>
</head>
<body>
	<h2 align="center">${study.name}</h2>

	<div align="center">
		<table align="center" border="1" summary="챕터 상세">
			<tr>
				<div style="display: inline-block;">
					<h3 align="center">${chapter.name}</h3>
					<input type="button" value="todo 추가"
						onclick="location.href='${contextPath}/todoRegisterServlet?chapterNo=${chapter.no}'" />
				</div>
			</tr>

			<colgroup>
				<col width="50" />
				<col width="300" />
				<col width="200" />
				<col width="200" />
			</colgroup>
			<thead>
				<tr>
					<th>순번</th>
					<th>todo 이름</th>
					<th>시작일</th>
					<th>마감일</th>
					<c:if test="${userid == study.createUserId}">
						<th>관리</th>
					</c:if>

				</tr>
			</thead>

			<tbody>
				<!-- 개인 할 일 목록-->
				<input type="hidden" name="chapterNo" value="${chapter.no}">
				<c:choose>
					<c:when test="${empty todoArray}">
						<tr>
							<td align="center" colspan="5">할 일이 없습니다.</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="todo" items="${todoArray}" varStatus="status">
							<tr>
								<!-- 할 일 번호 -->
								<td align="center">${status.index + 1}</td>

								<!-- 할 일 이름 -->
								<td><a
									href="<c:url value='/todoDetailServlet?todoNo=${todo.no}' />">
										<c:out value="${todo.name}" />
								</a></td>

								<!-- 할 일 기간 -->
								<td align="center">${todo.startDate}</td>
								<td align="center">${todo.endDate}</td>

								<!-- 상태 -->
								<!-- 삭제 버튼 -->
								<td align="center">
									<c:if test="${userid == study.createUserId}">
									 <button onclick="location.href='${contextPath}/todoUpdateServlet?todoNo=${todo.no}'" />수정</button>
                                            
										<button onclick="deleteChapter(${Todo.no})">삭제</button>
										
									</c:if>
								</td>

							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				<!-- 두 번째 파일의 개인 할 일 목록 코드 끝 -->
			</tbody>

			<tfoot>
				<tr>
					<!-- userid가 study.createUserId인 경우에만 삭제 버튼 표시 - colspan5 -->
					<c:choose>
						<c:when test="${userid == study.createUserId}">
							<td colspan="5" align="center"><input type="button"
								value="목록(뒤로가기)"
								onclick="location.href='<c:url value='/study/studyDetailServlet?studyNo=${chapter.studyNo}' />'" />
							</td>
						</c:when>
						<c:otherwise>
							<td colspan="4" align="center"><input type="button"
								value="목록(뒤로가기)"
								onclick="location.href='<c:url value='/study/studyDetailServlet?studyNo=${chapter.studyNo}' />'" />
							</td>
						</c:otherwise>
					</c:choose>


				</tr>
			</tfoot>
		</table>
	</div>

</body>
</html>
