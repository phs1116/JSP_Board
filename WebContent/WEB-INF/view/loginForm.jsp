<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="http://code.jquery.com/jquery.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
<title>로그인</title>
</head>
<body>

	<c:if test="${errors.idOrPwNotMatch}">아이디와 비밀번호가 일치하지 않습니다. </c:if>
	<form action="login.do" method="post" class="form-inline">
		<c:if test="${empty errors.id }">
			<input type="text" name="id" class="input-small" placeholder="ID">
		</c:if>

		<c:if test="${errors.id}">
			<input type="text" name="id" class="input-small"
				placeholder="ID가 비었습니다..">

		</c:if>

		<c:if test="${empty errors.password }">
			<input type="password" name="password" class="input-small"
				placeholder="Password">
		</c:if>

		<c:if test="${errors.password}">

			<input type="password" name="password" class="input-small"
				placeholder="비밀번호가 비었습니다.">

		</c:if>

		<button type="submit" class="btn">로그인</button>
	</form>


</body>
</html>