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
	<script src="http://code.jquery.com/jquery.js"></script>

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
<title>게시글 목록</title>
</head>
<body>
	<table class="table table-hover">
		<thead>
			<tr>
				<td>번호</td>
				<td>제목</td>
				<td>작성자</td>
				<td>조회수</td>
			</tr>
		</thead>
		<tbody>
			<c:if test="${articlePage.hasNoArticles() }">
				<tr>
					<td colspan="4">게시글이 없습니다</td>
				</tr>
			</c:if>

			<c:forEach var="article" items="${articlePage.content }">
				<tr>
					<td>${article.number }</td>
					<td><a
						href="read.do?no=${article.number }&pageNo=${articlePage.currentPage }">
							<c:out value="${article.title }" />
					</a></td>
					<td>${article.writer.name }</td>
					<td>${article.readCount }</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<c:if test="${articlePage.hasArticles() }">
				<tr>
					<td colspan="4"><c:if test="${articlePage.startPage>5 }">
							<a href="list.do?pageNo=${articlePage.startPage-5 }">[이전]</a>
						</c:if> <c:forEach var="pNo" begin="${articlePage.startPage }"
							end="${articlePage.endPage}">
							<a href="list.do?pageNo=${pNo}"></a>
						</c:forEach> <c:if test="${articlePage.endPage<articlePage.totalPage }">
							<a href="list.do?pageNo=${articlePage.startPage+5 }">[다음]</a>
						</c:if></td>
				</tr>
			</c:if>
		</tfoot>
	</table>


</body>
</html>