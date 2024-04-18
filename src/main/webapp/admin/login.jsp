<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${ not empty sessionScope.adminId  }">
	<% response.sendRedirect(request.getContextPath() + "/admin/main.jsp"); %>
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 로그인: admin/login.jsp </title>
<link rel="stylesheet"
	href="../resources/css/bootstrap.min.css">
<link rel="stylesheet"
	href="../css/main.css">
<c:import url="/components/jQuerys.jsp" />
<style>
	body {
	    background-image: url('../resources/imgs/milky-way.jpg');
	}
</style>
</head>
<body>
 <div class="container-fluid h-100">
	<div class="row justify-content-center align-items-center h-100">
	    <div class="col-md-auto">
	    	<img alt="logo.png" src="../resources/imgs/logos/logo_login_a.png" class="mb-2">
	    </div>
	    <div class="col-md-2">
	    </div>
        <div class="col-md-auto">
            <div class="custom-form text-center" style="width: 20rem;"> <!-- text-center 클래스 추가 -->
                <form>
                    <div class="mb-2">
                        <label for="exampleInputEmail1" class="form-label" style="color:#292929;"><b>Admin Id</b></label>
                        <input type="text" class="form-control" name="id" required />
                    </div>
                    <div class="mb-2">
                        <label for="exampleInputPassword1" class="form-label" style="color:#292929;"><b>Admin Password</b></label>
                        <input type="password" name="pwd" class="form-control" required />
                    </div>
                    <div class="mb-2 d-flex justify-content-end">
                        <input type="button" value="login" class="btn" style="background-color:#B9A4BF; color:white;" onclick="submit_a_login_form()"/>
                        &nbsp;
                        <input type="reset" value="reset" class="btn btn-secondary"/>
                    </div>
                </form>
            </div>
        </div>
	 </div>
</div>
</body>
</html>