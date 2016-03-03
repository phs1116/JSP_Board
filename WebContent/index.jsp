<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
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
						data-target="#navbar-ex-collapse">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#"><span>TEST</span></a>
				</div>
				<div class="collapse navbar-collapse" id="navbar-ex-collapse">
					<ul class="nav navbar-nav navbar-right">
						<li class="active"><a href="#">Home</a></li>
						<li><a href="#">Contacts</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="cover-image"
			style="background-image: url(https://unsplash.imgix.net/photo-1418065460487-3e41a6c84dc5?q=25&amp;fm=jpg&amp;s=127f3a3ccf4356b7f79594e05f6c840e);"></div>
		<div class="container">
			<div class="row">
				<div class="col-md-8 text-center">
					<h1 class="text-inverse">TestPage</h1>
					<p class="text-inverse">
						테스트용 페이지 <br>
					</p>
					<br> <br> <a class="btn btn-lg btn-primary">Click me</a>
				</div>
				<div class="col-md-4">
					<u:notLogin>
						<form action="login.do" method="post" role="form" class="logi">
							<div class="form-group">
								<label class="control-label" for="exampleInputEmail1">Username
									<br>
								</label> <input class="form-control" name="id" id="exampleInputEmail1"
									placeholder="Enter username" type="text">
							</div>
							<div class="span9 form-group">
								<label class="control-label" for="exampleInputPassword1">Password</label>
								<input class="form-control" name="password"
									id="exampleInputPassword1" placeholder="Password"
									type="password">
							</div>
							<div class="row">
								<div class="col-xs-6">
									<button type="submit" class="btn btn-default" style="">sing
										in</button>
								</div>
								<div class="col-xs-6 text-right">
									<a href="join.do"> <input type="button"
										class="btn btn-success" value="sign up"></a>
								</div>
					</u:notLogin>
					<u:isLogin>
					<br/>
					<div class="text-center">
						${authUser.name }님 안녕하세요.<br />
						<a href="logout.do">[로그아웃 하기]</a>
						<a href="changePwd.do">[비밀번호 변경]</a>
						<a href="article/list.do">[게시판]</a>
						</div>
					</u:isLogin>
					
				</div>
			</div>
		</div>
	</div>


</body>
</html>