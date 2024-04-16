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
	<div>
		<h1>${study.name}</h1>
		<h3>${study.detail}</h3>
		<input type="button"
		onclick="window.location.href='${contextPath}/study/studyUpdateServlet?studyno=${studyNo}'"
		value="스터디 수정하기">
	</div>

	<div align="center" style="border: 0.5px solid black;">
		<c:choose>
			<c:when test="${empty memberStudyList}">
				<div>
					<p>등록된 참여자가 없습니다.</p>
				</div>
			</c:when>
			<c:when test="${!empty memberStudyList }">
				<section id="studyMember">
					<c:forEach var="member" items="${memberStudyList}">
						<div>
							<p>${member.id}</p>
							<p>${member.image}</p>
						</div>
					</c:forEach>
				</section>
			</c:when>
		</c:choose>
	</div>
	<br>

	<div id="chapter" align="center" style="border: 0.5px solid black;">

		<h4>${study.name}의 챕터</h4>
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
						<c:if test="${userid == 1}">
						<th>관리</th>
					</c:if>
					</tr>
				</thead>

				<tbody>
					<input type="button" value="chapter 추가"
						onclick="window.location.href='${contextPath}/chapter/chapterRegisterServlet?studyNo=${study.no}'" />

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
									<td align="center">${status.index +1}</td>
									<input type="hidden" name="chapterNo" value="${chapter.no}" />
									<!-- 챕터 제목 -->
									<td><a
										href="<c:url value="/chapter/chapterDetailServlet?chapterNo=${chapter.no}" />">
											<c:out value="${chapter.name}" />
									</a></td>
									<!-- 챕터 기간 -->
									<td align="center"><c:out value="${chapter.startDate}" /></td>
									<td align="center"><c:out value="${chapter.endDate}" /></td>
								
								<!-- 삭제 버튼 -->
								<td align="center">
									<!-- userid가 1인 경우에만 삭제 버튼 표시 --> <c:if test="${userid == 1}">
									 <button onclick="location.href='${contextPath}/chapter/chapterUpdateServlet?chapterNo=${chapter.no}'" />수정</button>
                                            
										<button onclick="deleteChapter(${chapter.no})">삭제</button>
										</tr>
									</c:if>
								</td>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
			<br>
		</div>
	</div>


</body>
<script>
<!-- 챕터 삭제 함수 -->
function deleteChapter(chapterNo) {
    // 삭제 여부를 확인하는 알림 창 표시
    var result = confirm("챕터를 삭제하시겠습니까?");
    // 사용자가 확인 버튼을 클릭했을 때
    if (result) {
        // form 엘리먼트 동적으로 생성
        var form = document.createElement('form');
        form.setAttribute('method', 'post');
        form.setAttribute('action', '<c:url value="/chapter/chapterDeleteServlet"/>');

        // 챕터 번호를 전송하는 hidden input 추가
        var inputChapterNo = document.createElement('input');
        inputChapterNo.setAttribute('type', 'hidden');
        inputChapterNo.setAttribute('name', 'chapterNo');
        inputChapterNo.setAttribute('value', chapterNo);
        form.appendChild(inputChapterNo);

        // form을 body에 추가하고 자동으로 전송
        document.body.appendChild(form);
        form.submit();
    }
}</script>
</html>
