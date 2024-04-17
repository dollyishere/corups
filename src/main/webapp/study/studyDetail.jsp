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
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<script type="text/javascript">
	function gotoStudyRegister(studyNo){
		var result = confirm("참여하시겠습니까?");
		if(result){
			$.ajax({
	            url: "${contextPath}/studyMemberRegisterServlet",
	            type: "POST",
	            data: {
	            	studyNo : studyNo
	            },		
	            dataType: "text",  // 응답 데이터 타입 지정
	            success: function(response){
	            	if(response == "success"){
	                	alert("참여하였습니다.");
	            	var joinButton = document.getElementById("joinButton");
                    joinButton.innerHTML = '<button onclick="gotoStudyOut(${study.no})">나가기 버튼</button>';
                    
	            	}else
	                	alert("참여실패.");
	            },
	            error: function(error){
	                alert("Error: " + error);  // 에러 처리
	            }
	        });
		}
	}
	
	function gotoStudyOut(studyNo){
		var result = confirm("나가시겠습니까?");
		if(result){
			$.ajax({
	            url: "${contextPath}/studyMemberDeleteServlet",
	            type: "POST",
	            data: {
	            	studyNo : studyNo
	            },		
	            dataType: "text",  // 응답 데이터 타입 지정
	            success: function(response){
	            	if(response == "success"){
	                	alert("나갔습니다.");
	            	
	            	 var joinButton = document.getElementById("joinButton");
	                    joinButton.innerHTML = '<button onclick="gotoStudyRegister(${study.no})">참여하기 버튼</button>';
	                } else {
	                	alert("나가기실패.");
	                }
	            },
	            error: function(error){
	                alert("Error: " + error);  // 에러 처리
	            }
	        });
		}
	}
	
</script>


</head>
<body>
   <div>
      <h1>${study.name}</h1>
      <h3>${study.detail}</h3>
      <h4>참여자(${studyMemberCount}/${study.maxNum})</h4>
      <input type="button"
      onclick="window.location.href='${contextPath}/study/studyUpdateServlet?studyNo=${study.no}'"
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

      <h3>${study.name}의 챕터</h3>
      <div align="center">
         <table border="1" summary="챕터 상세 - 목록">

            <colgroup>
               <col width="50" />
               <col width="300" />
               <col width="250" />
               
            </colgroup>
            <thead>
               <tr>
                  <th>번호</th>
                  <th>챕터 이름</th>
                  <th>기간</th>
                  <c:if test="${userid == study.createUserId}">
                  <th>관리</th>
               </c:if>
               </tr>
            </thead>

            <tbody>
            	
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
                           <td align="center"><a
                              href="<c:url value="/chapter/chapterDetailServlet?chapterNo=${chapter.no}" />">
                                 <c:out value="${chapter.name}" />
                           </a></td>
                           <!-- 챕터 기간 -->
                           <td align="center"><c:out value="${chapter.startDate}" /> ~ <c:out value="${chapter.endDate}" /></td>
                           
                        
                        <!-- 삭제 버튼 -->
                        <td align="center">
                           <!-- userid가 1인 경우에만 삭제 버튼 표시 --> <c:if test="${userid == study.createUserId}">
                            <button onclick="location.href='${contextPath}/chapter/chapterUpdateServlet?chapterNo=${chapter.no}'" />수정</button>
                                            
                              <button onclick="deleteChapter(${chapter.no})">삭제</button>
                              </tr>
                           </c:if>
                           
                        </td>
                     </c:forEach>
                  </c:otherwise>
              </c:choose>	
              	<c:choose>
				    <c:when test="${userid != study.createUserId}">
				        <c:set var="join" value="false"/>
				        <c:forEach var="member" items="${memberStudyList}">
				            <c:if test="${member.id == userid}">
				                <c:set var="join" value="true"/>
				            </c:if>
				        </c:forEach>
				        <span id="joinButton">
				            <c:choose>
				                <c:when test="${join}">
				                    <button onclick="gotoStudyOut(${study.no})">나가기 버튼</button>
				                </c:when>
				                <c:otherwise>
				                    <button onclick="gotoStudyRegister(${study.no})">참여하기 버튼</button>
				                </c:otherwise>
				            </c:choose>
				        </span>
				    </c:when>
				    <c:otherwise>
				        <button onclick="window.location.href='${contextPath}/chapter/chapterRegisterServlet?studyNo=${study.no}'">
				            chapter 추가
				        </button>
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