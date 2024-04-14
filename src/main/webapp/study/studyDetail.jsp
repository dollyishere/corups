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
        <h4>${studyName} 의 챕터</h4>
        <div align="center">
            <table border="1" summary="챕터 상세 - 목록">
                <colgroup>
                    <col width="50" />
                    <col width="300" />
                    <col width="200" />
                    <col width="200" />
                    <col width="100" />
                </colgroup>
                <thead>
                    <tr>
                        <th>순번</th>
                        <th>챕터 이름</th>
                        <th>StartDate</th>
                        <th>EndDate</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <input type="button" value="chapter 추가"
                        onclick="location.href='${contextPath}/chapter/chapterRegisterServlet?studyNo=${study.no}'" />
                    </td>
                    <c:choose>
                        <c:when test="${chapterCount == 0}">
                            <tr>
                                <td align="center" colspan="5">등록된 챕터가 없습니다.</td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="chapter" items="${chapterList}" varStatus="status">
                                <tr>
                                    <!-- 챕터 번호 -->
                                    <td align="center">${status.index + 1}</td>
                                    <!-- 챕터 제목 -->
                                    <td><a
                                        href="<c:url value="/chapter/chapterDetailServlet?no=${chapter.no}" />">
                                            <c:out value="${chapter.name}" />
                                    </a></td>
                                    <!-- 챕터 기간 -->
                                    <td align="center"><c:out value="${chapter.startDate}" /></td>
                                    <td align="center"><c:out value="${chapter.endDate}" /></td>
                                    <!-- 삭제 버튼 -->
                                    <td align="center">
                                        <!-- userid가 1인 경우에만 삭제 버튼 표시 -->
                                        <c:if test="${userid == 1}">
                                            <button onclick="deleteChapter(${chapter.no})">삭제</button>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </tbody>
                <tfoot>
                    <!-- 페이지 번호 -->
                    <tr>
                        <td align="center" colspan="5">페이지 네비<c:out
                                value="${pageNavigator}" escapeXml="false" /></td>
                    </tr>
                </tfoot>
            </table>
        </div>
    </div>
    <!-- 챕터 삭제 함수 -->
    <script>
        function deleteChapter(chapterNo) {
            // 삭제 여부를 확인하는 알림 창 표시
            var result = confirm("챕터를 삭제하시겠습니까?");
            // 사용자가 확인 버튼을 클릭했을 때
            if (result) {
                // 삭제를 수행하는 서블릿으로 이동
                window.location.href = "${contextPath}/chapter/deletechapterServlet?no=" + chapterNo;
            }
        }
    </script>
</body>
</html>
