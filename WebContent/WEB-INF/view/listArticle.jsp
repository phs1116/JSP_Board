<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript"
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<script type="text/javascript"
	src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<link
	href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link
	href="http://pingendo.github.io/pingendo-bootstrap/themes/default/bootstrap.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div class="cover">
		<div class="navbar">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#navbar-collapse">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#"><span>Brand</span></a>
				</div>
				<div class="collapse navbar-collapse" id="navbar-ex-collapse">
					<ul class="nav navbar-nav navbar-right">
						<li class="active"><a href="#">Home</a></li>
						<li><a href="#">Contacts</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="background-image-fixed cover-image"
			style="background-image: url(https://unsplash.imgix.net/photo-1418065460487-3e41a6c84dc5?q=25&amp;fm=jpg&amp;s=127f3a3ccf4356b7f79594e05f6c840e);"></div>
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<table class="table table-hover">
						<thead>
							<tr class="success">
								<th>#</th>
								<th>Title</th>
								<th>Username</th>
								<th>Count<br></th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${articlePage.hasNoArticles() }">
								<tr>
									<td colspan="4">No article</td>
								</tr>
							</c:if>
							<c:forEach var="article" items="${articlePage.content }">
								<tr class="active">
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
					</table>
				</div>
			</div>
			<div class="row">
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
				<div class="col-md-12 text-center">
					<ul class="pagination">
						<c:if test="${articlePage.startPage>5 }">
							<li><a href="list.do?pageNo=${articlePage.startPage-5 }">Prev</a></li>
						</c:if>
						<c:forEach var="pNo" begin="${articlePage.startPage }"
							end="${articlePage.endPage}">
							<li><a href="list.do?pageNo=${pNo}">1</a></li>
						</c:forEach>
						<c:if test="${articlePage.endPage<articlePage.totalPage }">
							<li><a href="list.do?pageNo=${articlePage.startPage+5 }">Next</a></li>
						</c:if>
					</ul>
				</div>
			</div>
		</div>


	</div>
</body>
</html>