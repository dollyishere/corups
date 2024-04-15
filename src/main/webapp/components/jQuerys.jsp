<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	/* 아이디 중복 체크 */
	function fn_process() {
		var _id = $("#id").val();
		const regExp = /[a-zA-Z0-9]/g;
		
		if (!regExp.test(_id)) {
			alert("영숫자만 가능합니다.");
			$('#id').val('');
			return;
		}
		
		$.ajax({
			type: "post",
			async: false,
			url: "http://localhost:9000/corups/member/signupServlet/confirmDuplicate.do",
			dataType: "text",
			data: {id: _id},
			
			success:function(data, textStatus) {
				if (data == 'usable') {
					alert("사용할 수 있는 ID 입니다.");
					$("#id").prop("readonly", true);
					$("#double").prop("disabled", true);
					$("#pwd").prop("disabled", false);
					$("#pwd_check").prop("disabled", false);
				} else {
					alert("사용할 수 없는 ID입니다.")
					$('#id').val('');
				}
			},
			error:function(data, textStatus) {
				alert("다시 한 번 시도해주세요.");
			},
			complete: function(data, textStatus) {
				
			}
		})
	}

	/* 삭제 확인 및 삭제 */
	function confirmDelete() {
	    var _id = prompt("아이디를 입력해주세요.");
	    if (_id === null || _id === "") {
	        return;
	    }
	    var _pwd = prompt("비밀번호를 입력해주세요.");
	    if (_pwd === null || _pwd === "") {
	        return;
	    }
	
	    var confirmed = confirm("정말 탈퇴하시겠습니까?");
	    if (confirmed) {
	    	$.ajax({
	    		type: "post",
	    		async : false,
	    		url: "http://localhost:9000/corups/member/memberDeleteServlet",
	    		dataType: "text",
	    		data: {id: _id, pwd: _pwd},
	    		success: function(data, textStatus) {
	    			alert("탈퇴 완료되었습니다. \n이용해주셔서 감사합니다.");
	    			window.location.replace("<%= request.getContextPath() %>/index.jsp")
	    		},
	    		error: function(data, textStatus) {
	    			alert("다시 한 번 시도해주세요.");
	    		},
	    		complete: function(data, textStatus) {
	    			
	    		}
	    	});
	    }
	}
	
	/* 관리자 회원 탈퇴 */
	function aConfirmDelete() {
	    var _id = $("#id").val();
	    var _pwd = $("#pwd").val();
	
	    var confirmed = confirm("정말 해당 유저를 탈퇴시키시겠습니까?");
	    if (confirmed) {
	    	$.ajax({
	    		type: "post",
	    		async : false,
	    		url: "http://localhost:9000/corups/member/memberDeleteServlet",
	    		dataType: "text",
	    		data: {id: _id, pwd: _pwd},
	    		success: function(data, textStatus) {
	    			alert("탈퇴 완료되었습니다.");
	    			window.location.replace("<%= request.getContextPath() %>/getRedirect.jsp")
	    		},
	    		error: function(data, textStatus) {
	    			alert("다시 한 번 시도해주세요.");
	    		},
	    		complete: function(data, textStatus) {
	    			
	    		}
	    	});
	    }
	}
	
	
</script>
