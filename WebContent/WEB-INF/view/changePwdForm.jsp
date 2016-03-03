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
<title>비밀번호 변경</title>
</head>
<body>
	<form action="changePwd.do" method="post" class="form-horizontal">

		<c:if test="${empty errors.curPwd}">
			<div class="control-group">
				<label class="control-label" for="inputCurPw">현재 비밀번호</label>
				<div class="controls">
					<input type="password" name="curPwd" id="inputCurPw">
				</div>
			</div>
		</c:if>

		<c:if test="${errors.curPwd}">
			<div class="control-group error">
				<label class="control-label" for="inputCurPw">현재 비밀번호</label>
				<div class="controls">
					<input type="password" name="curPwd" id="inputCurPw"> <span
						class="help-inline">현재 비밀번호를 입력해주세요.</span>
				</div>
			</div>
		</c:if>
		
		<c:if test="${empty errors.newPwd}">
			<div class="control-group">
				<label class="control-label" for="inputNewPw">새 비밀번호</label>
				<div class="controls">
					<input type="password" name="newPwd" id="inputNewPw">
				</div>
			</div>
		</c:if>
		<c:if test="${errors.newPwd}">
			<div class="control-group error">
				<label class="control-label" for="inputNewPw">새 비밀번호</label>
				<div class="controls">
					<input type="password" name="newPwd" id="inputNewPw"> <span
						class="help-inline">새 비밀번호를 입력해주세요.</span>
				</div>
			</div>
		</c:if>

		<div class="control-group">
			<div class="controls">
				<button type="submit" class="btn">비밀번호 변경</button>
			</div>
		</div>
	</form>

</body>
</html>