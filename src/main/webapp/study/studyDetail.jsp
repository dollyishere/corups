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
                    joinButton.innerHTML = '<button onclick="gotoStudyOut(${study.no})" class="btn btn-danger my-3">나가기</button>';
                    window.location.replace("http://localhost:9000/corups//study/studyDetailServlet?studyNo=" + studyNo);
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
	                    joinButton.innerHTML = '<button onclick="gotoStudyRegister(${study.no})" style="background-color: #B9A4BF; color:white;" class="btn my-3">참여하기</button>';
	                    window.location.replace("http://localhost:9000/corups//study/studyDetailServlet?studyNo=" + studyNo);
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
	<div class="container-fluid m-5">
			<c:import url="/components/defaultHeader.jsp" />
			<div class="container-fluid">
			<div class="row justify-content-center align-items-center">
		    	<div class="col-md-auto">
		    		    <div class="">
					     <h1><b>${study.name}</b></h1>
					      <h3>${study.detail}</h3>
					       <c:if test="${userid == study.createUserId}">
					       		<div class="btn-group">
					       			<input type="button"
								      	onclick="window.location.href='${contextPath}/study/studyUpdateServlet?studyNo=${study.no}'"
								      	value="스터디 수정" style="background-color: #B9A4BF; color:white;" class="btn btn-sm">
								        <button onclick="window.location.href='${contextPath}/chapter/chapterRegisterServlet?studyNo=${study.no}'"  style="background-color: #D996B5; color:white;" class="btn btn-sm">
								            챕터 추가
								        </button>
					       		</div>
					       </c:if>
					   </div>
				<div class="row justify-content-evenly align-items-flex-start mt-3">
				<!-- study 참가자 리스트 -->
				<div class="col-md-auto mb-3">
					<h3 class="mb-3"><b>참여자</b></h3>
					<div class="custom-form text-center" style="width: 10rem;  min-height: 30rem;">
						<b style="font-size: 12px;">참여자(${studyMemberCount}/${study.maxNum})</b>
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
						                    <button onclick="gotoStudyOut(${study.no})" class="btn btn-danger my-3">나가기</button>
						                </c:when>
						                <c:otherwise>
						                    <button onclick="gotoStudyRegister(${study.no})" style="background-color: #B9A4BF; color:white;" class="btn my-3">참여하기</button>
						                </c:otherwise>
						            </c:choose>
						        </span>
						    </c:when>
						    
						</c:choose>
						
						<div class="containter">
							<c:choose>
								 <c:when test="${empty memberStudyList}">
									<%-- if() 부분 --%>
									<tr>
										<td class="my-5" valign="middle" align="center" colspan="7">진행 중인 study가 없습니다.</td>
									</tr>
								</c:when>
								<c:otherwise>
									<%-- else 부분 --%>
									<div class="row justify-content-evenly">
									<c:forEach var="member" items="${memberStudyList}">
						                  <div class="m-2">
									       	<button type="button" class="rounded-circle d-inline-block overflow-hidden p-0 mb-2" style="width: 40px; height: 40px; border: none; background-color: ${profile_img == '0_p_img.jpg' ? '#292929' : 'transparent'};" data-bs-toggle="modal" data-bs-target="#exampleModal">
									  			<img src="${pageContext.request.contextPath}/uploads/profile_img/${ member.image }" alt="" class="img-fluid">
											</button>
											<b>${ member.id }</b>
						                  </div>
						               </c:forEach>
									 </div>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
				<!-- 스터디 챕터 리스트 -->
				<div class="col-md-auto text-center  mb-3">
					<h3 class="mb-3"><b>${study.name}의 챕터</b></h3>
					<div class="custom-form text-center" style="width: 46rem; min-height: 30rem;">
					<table class="m-3" style="width: 40rem;">
					  <thead>
					    <tr>
					    <th scope="col">#</th>
					      <th scope="col">Chapter명</th>
					      <th scope="col">시작일</th>
					      <th scope="col">종료일</th>
					      <th scope="col">상세보기</th>
					      <c:if test="${userid == study.createUserId}">
                           	<th scope="col" >관리</th>
                           </c:if>
					    </tr>
					  </thead>
						  <tbody class="table-group-divider">
						  	<c:choose>
								<c:when test="${ empty chapterList }">
									<%-- if() 부분 --%>
									<tr>
										<td class="my-5" valign="middle" align="center" colspan="7">등록된 Chapter가 없습니다.</td>
									</tr>
								</c:when>
								<c:otherwise>
									<%-- else 부분 --%>
									<c:forEach var="chapter" items="${chapterList}" varStatus="status">
										<tr>
											<!-- 인덱스 번호 -->
											<td align="center"><b><c:out value="${ status.index + 1 }" /></b></td>
											<!-- chapter 이름 -->
											<td align="center"><c:out value="${ chapter.name }" /></td>
											<!-- 시작일 -->
											<td align="center"><c:out value="${ chapter.startDate }" /></td>
											<!-- 종료일 -->
											<td align="center"><c:out value="${ chapter.endDate }" /></td>
											<td>
												<a href="<c:url value="/chapter/chapterDetailServlet?chapterNo=${chapter.no}" />">
													상세보기
												</a>
											</td>
											<!-- 수정 & 삭제 버튼 -->
				                           <c:if test="${userid == study.createUserId}">
				                           		<td align="center">
				                           		<div class="btn-group" role="group">
					                           		<button class="btn btn-warning  btn-sm" onclick="location.href='${contextPath}/chapter/chapterUpdateServlet?chapterNo=${chapter.no}'" />수정</button>  
					                              	<button class="btn btn-danger  btn-sm" onclick="deleteChapter(${chapter.no})">삭제</button>
				                           		</div>
				                            	
				                              </td>
				                           </c:if>
										</tr>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						  </tbody>
						</table>
					</div>
				</div>
			</div>
		    	</div>
		  </div>
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