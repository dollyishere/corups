<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 로그인 페이지 : login.jsp</title>
<link rel="stylesheet"
	href="../resources/css/bootstrap.min.css">
<link rel="stylesheet"
	href="../css/main.css">
<c:import url="/components/jQuerys.jsp" />
</head>
<body>
	<div class="container-fluid h-100">
		<div class="row justify-content-center align-items-center h-100">
	    	<div class="col-md-auto">
	    		<img alt="logo.png" src="../resources/imgs/logo.png" class="mb-2">
	   		</div>
		   <div class="col-md-2">

	       </div>
	        <div class="col-md-auto">
	            <div class="custom-form text-center" style="width: 20rem;"> <!-- text-center 클래스 추가 -->
	                <form>
	                    <div class="mb-2">
	                        <label for="exampleInputEmail1" class="form-label" style="color:#292929;"><b>Id</b></label>
	                        <input type="text" class="form-control" name="id" required />
	                    </div>
	                    <div class="mb-2">
	                        <label for="exampleInputPassword1" class="form-label" style="color:#292929;"><b>Password</b></label>
	                        <input type="password" name="pwd" class="form-control" required />
	                    </div>
	                    <div class="mb-2 d-flex justify-content-end">
	                        <input type="button" value="login" class="btn" style="background-color:#D996B5; color:white;" onclick="submit_login_form()"/>
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