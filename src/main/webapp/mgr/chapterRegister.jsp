<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="contextPath" value="${pageContext.request.contextPath }"></c:set>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="<c:url value="/ckeditor/ckeditor.js" />"></script>
<script>
    function confirmRegister() {
        alert("등록되었습니다.");
    }
</script>
<title>챕터 추가 화면 - 스터디 방장</title>
</head>
<body>
    <h2 align="center">${study.name} 스터디</h2>
    <div align="center" border="1">
        <form action="${contextPath}/chapter/chapterRegisterServlet" method="post">
            <table align="center" border="1" summary="챕터 추가">
                <tr>
                    <th colspan="2" align="center">새 chapter 등록</th>
                </tr>
                <tr>
                    <td>챕터 이름 :</td>
                    <td><textarea id="chapterName" name="chapterName" rows="4" cols="50" placeholder="챕터 이름을 입력하세요"></textarea>
                    </td>
                </tr>
                <tr>
                    <td>시작 날짜 :</td>
                    <td><input type="date" name="startDate" /></td>
                </tr>
                <tr>
                    <td>마감 날짜 :</td>
                    <td><input type="date" name="endDate" /></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="hidden" name="studyNo" value="${studyNo}" />
                        <input type="submit" name="insertChpater" value="등록" onclick="confirmRegister()" />
                        <input type="button" name="cancelUpdate" value="취소" onclick="location.href='${contextPath}/study/studyDetailServlet?no=${studyNo}'" />
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <script>
        CKEDITOR.replace('chapterName', {
            enterMode: CKEDITOR.ENTER_BR, // 줄 바꿈시 <br> 태그 사용
            shiftEnterMode: CKEDITOR.ENTER_P  // Shift+Enter로 <p> 태그 사용
        });
    </script>
</body>
</html>
