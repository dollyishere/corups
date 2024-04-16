<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }"></c:set>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>챕터 수정 - 스터디 방장</title>
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
  }
</script>
</head>
<body>
	<h2 align="center">${study.name} 수정</h2>	
	<div align="center" border="1">
		<form action="${contextPath}/chapter/chapterUpdateServlet" method="post">
            <input type="hidden" name="chapterNo" value="${chapter.no}">
			<table align="center" border="1" summary="챕터 수정">
				<tr>
					<th colspan="2" align="center">${chapter.name} 수정</th>
				</tr>
				<tr>
					<td>챕터 이름 :</td>
					<td><input type="text" name="chapterName" value="${chapter.name}" /></td>
				</tr>
				<tr>
					<td>시작 날짜 :</td>
					<td><input type="date" name="startDate" value="${chapter.startDate}" /></td>
				</tr>
				<tr>
					<td>마감 날짜 :</td>
					<td><input type="date" name="endDate" value="${chapter.endDate}" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" name="updateChapter" value="수정" />
						<button onclick="deleteChapter(${chapter.no})">삭제</button>
						<input type="button" name="cancleUpdate" value="취소"
							onclick="location.href='<c:url value='/mgr/chapterListServlet?studyNo=${studyNo}'/>'" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
