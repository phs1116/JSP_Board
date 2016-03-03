<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

	<script src="http://code.jquery.com/jquery.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
<title>게시글 보기</title>
</head>
<body>

	<table class="table table-hover">
		<tr>
			<td>번호</td>
			<td>${articleData.article.number }</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${articleData.article.writer.name }</td>
		</tr>
		<tr>
			<td>제목</td>
			<td><c:out value='${articleData.article.title}' /></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><u:pre value='${articleData.content }' /></td>
		</tr>
		<tr>
			<td colspan="2"><c:set var="pageNo"
					value="${empty param.pageNo? '1':param.pageNo }" /> <a
				href="list.do?pageNo=${pageNo }">[목록]</a> <c:if
					test="${authUser.id == articleData.article.writer.id }">
					<a href="modify.do?no=${articleData.article.number }">[게시글수정]</a>
					<a href="delete.do?no=${articleData.article.number }">[게시글삭제]</a></td>
			</c:if>
		</tr>
	</table>
</body>
</html>