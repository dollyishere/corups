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
</head>
<body>
    <h2 align="center">
        study.name ${study.name}
    </h2>

    <div align="center">
        <table align="center" border="1" summary="챕터 상세">
            <tr>
                <div style="display: inline-block;">
                    <h3 align="center">chapter.name ${chapter.name} </h3>
                    <input type="button" value="chapter 추가">
                </div>
            </tr>

            <colgroup>
                <col width="50" />
                <col width="300" />
                <col width="200" />
                <col width="100" />
            </colgroup>
            <thead>
                <tr>
                    <th>순번</th>
                    <th>todo 이름</th>
                    <th>기간</th>
                    <th>상태</th>
                </tr>
            </thead>

            <tbody>
                <c:choose>
                    <c:when test="${todoCount == 0}">
                        <tr>
                            <td align="center" colspan="4">todo가 없습니다.</td>
                            <td><input type="button" name="insertTodo" value="할일추가"
                                onclick="location.href='<c:url value='/study/todoRegisterServlet' />'" /></td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="todo" items="${todoList}" varStatus="status">
                            <tr>
                                <!-- 할 일 번호 -->
                                <td align="center">${status.index + 1}</td>

                                <!-- 할 일 이름 -->
                                <td><a href="<c:url value='/study/chapterListServlet?num=${chapter.num}' />">
                                        <c:out value="${todo.name}" />
                                </a></td>

                                <!-- 할 일 기간 -->
                                <td align="center">${todo.startDate}</td>
                                <td align="center">${todo.endDate}</td>

                                <!-- 상태 -->
                                <td align="center">${todo.status}</td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </tbody>

            <tfoot>
                <!-- 페이지 번호 -->
                <tr>
                    <td align="center" colspan="4"><c:out value="${pageNavigator}" escapeXml="false" /></td>
                </tr>
            </tfoot>
        </table>
    </div>

</body>
</html>
