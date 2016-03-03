<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 가입</title>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
</head>
<body>
	<script src="http://code.jquery.com/jquery.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<form action="join.do" method="post" class="form-horizontal">
		<div class="control-group">
			<label class="control-label" for="inputId">아이디</label>
			<div class="controls">
				<input type="text" name="id" id="inputId">
			</div>
			<c:if test="${errors.id }">ID를 입력해주세요.</c:if>
			<c:if test="${errors.duplicatedId }">이미 사용중인 ID입니다.</c:if>
		</div>
		<div class="control-group">
			<label class="control-label" for="inputName">이름</label>
			<div class="controls">
				<input type="text" name="name" id="inputName">
			</div>
			<c:if test="${errors.name }">이름을 입력해주세요.</c:if>
		</div>
		<div class="control-group">
			<label class="control-label" for="inputPass">비밀번호</label>
			<div class="controls">
				<input type="password" name="password" id="inputPass">
			</div>
			<c:if test="${errors.password }">비밀번호를 입력해주세요.</c:if>
		</div>
		<div class="control-group">
			<label class="control-label" for="confirmPass">비밀번호 확인</label>
			<div class="controls">
				<input type="password" name="confirmPassword" id="confirmPass">
			</div>
			<c:if test="${errors.confirmPassword}">비밀번호 학인을 입력해주세요.</c:if>
			<c:if test="${errors.noMatch }">암호가 일치하지 않습니다.</c:if>
		</div>
		<div class="control-group">
			<div class="controls">
				<button type="submit" class="btn">가입</button>
			</div>
		</div>
	</form>

</body>
</html>