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

	/* 폼 데이터 제출 */
	function submit_form() {
	    // formData 객체 생성
	    var formData = new FormData($("form")[0]);
	    console.log(formData);
	    
	    // 요청 실행(회원가입)
	    $.ajax({
	        type: "POST",
	        async: false,
	        url: "http://localhost:9000/corups/member/signupServlet",
	        data: formData,
	        processData: false,
	        contentType: false,
	        success: function(data, textStatus) {
	            if (data === "sign up complete!") {
	                alert("회원 가입이 정상적으로 완료되었습니다.");
	                window.location.replace("<%= request.getContextPath() %>/mem/login.jsp")
	            } else {
	                alert("회원 가입 중 오류가 발생했습니다. 다시 시도해주세요.");
	            }
	        },
	        error: function(data, textStatus) {
	            alert("서버 오류가 발생했습니다. 다시 시도해주세요.");
	        },
	    });
	};
	
	/* 폼 데이터 제출(수정) */
	function submit_update_form() {
	    // formData 객체 생성
	    var formData = new FormData($("form")[0]);
	    console.log(formData);
	    
	    // 요청 실행(정보수정)
	    $.ajax({
	        type: "POST",
	        async: false,
	        url: "http://localhost:9000/corups/member/updateServlet",
	        data: formData,
	        processData: false,
	        contentType: false,
	        success: function(data, textStatus) {
	            if (data === "update complete!") {
	            	console.log(textStatus, data);
	                alert("수정이 정상적으로 완료되었습니다.");
	                window.location.replace("<%= request.getContextPath() %>/mem/main.jsp")
	            } else {
	                alert("정보 수정 중 오류가 발생했습니다. 다시 시도해주세요.");
	            }
	        },
	        error: function(data, textStatus) {
	            alert("서버 오류가 발생했습니다. 다시 시도해주세요.");
	        },
	    });
	};
	
	/* 로그인 */
	function submit_login_form() {
    	// 폼 데이터 직렬화
	    var formData = $("form").serialize();
	    
	    // 요청 실행(로그인)
	    $.ajax({
	        type: "POST",
	        url: "http://localhost:9000/corups/member/loginServlet",
	        data: formData,
	        success: function(data, textStatus) {
	            if (data === "login") {
	                console.log(textStatus, data);
	                alert("로그인 되었습니다.");
	                window.location.replace("<%= request.getContextPath() %>/mem/main.jsp")
	            } else {
	                alert("아이디나 비밀번호가 맞지 않습니다. 다시 시도해주세요.");
	            }
	        },
	        error: function(data, textStatus) {
	            alert("서버 오류가 발생했습니다. 다시 시도해주세요.");
	        },
	    });
	};
	
	/* 관리자 로그인 */
	function submit_a_login_form() {
	    // formData 객체 생성
	    var formData = $("form").serialize();
	    
	    // 요청 실행(로그인)
	    $.ajax({
	        type: "POST",
	        async: false,
	        url: "http://localhost:9000/corups/admin/adminLoginServlet",
	        data: formData,
	        success: function(data, textStatus) {
	            if (data === "login") {
	            	console.log(textStatus, data);
	                alert("로그인 되었습니다.");
	                window.location.replace("<%= request.getContextPath() %>/admin/main.jsp")
	            } else {
	                alert("아이디나 비밀번호가 맞지 않습니다. 다시 시도해주세요.");
	            }
	        },
	        error: function(data, textStatus) {
	            alert("서버 오류가 발생했습니다. 다시 시도해주세요.");
	        },
	    });
	};
	
	/* 로그아웃 */
	function submit_logout() {
	    // formData 객체 생성
	    
	    // 요청 실행(로그아웃)
	    $.ajax({
	        type: "POST",
	        async: false,
	        url: "http://localhost:9000/corups/member/logoutServlet",
	        success: function(data, textStatus) {
	            if (data === "logout_complete!") {
	            	console.log(textStatus, data);
	                alert("로그아웃 되었습니다.");
	                window.location.replace("<%= request.getContextPath() %>/index.jsp")
	            } else {
	                alert("로그아웃 중 오류가 발생했습니다. 다시 시도해주세요.");
	            }
	        },
	        error: function(data, textStatus) {
	            alert("서버 오류가 발생했습니다. 다시 시도해주세요.");
	        },
	    });
	};
	
	/* 관리자 로그아웃 */
	function submit_a_logout() {
	    // formData 객체 생성
	    
	    // 요청 실행(관리자 로그아웃)
	    $.ajax({
	        type: "POST",
	        async: false,
	        url: "http://localhost:9000/corups/admin/adminLogoutServlet",
	        success: function(data, textStatus) {
	            if (data === "logout_complete!") {
	            	console.log(textStatus, data);
	                alert("로그아웃 되었습니다.");
	                window.location.replace("<%= request.getContextPath() %>/index.jsp")
	            } else {
	                alert("로그아웃 중 오류가 발생했습니다. 다시 시도해주세요.");
	            }
	        },
	        error: function(data, textStatus) {
	            alert("서버 오류가 발생했습니다. 다시 시도해주세요.");
	        },
	    });
	};
	
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
	    			window.location.replace("http://localhost:9000/corups/member/memberListServlet");
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
