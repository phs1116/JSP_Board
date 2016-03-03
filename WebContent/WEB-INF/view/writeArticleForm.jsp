<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
<title>게시글 쓰기</title>
</head>
<body>

	<script src="http://code.jquery.com/jquery.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<form action="write.do" method="post" class="form-horizontal">
		<div class="control-group">
			<label class="control-label" for="inputTitle">제목</label>
			<div class="controls">
				<input type="text" name="title" id="inputTitle"
					value="${param.title}">
				<c:if test="${errors.title }">
					<span class="help-inline">제목을 입력해주세요.</span>
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="inputContent">내용</label>
			<div class="controls">
				<textarea rows="5" cols="30" name="content" id="inputContent">${param.content }</textarea>
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<button type="submit" class="btn">새 글 등록</button>
			</div>
		</div>
	</form>
</body>
</html>