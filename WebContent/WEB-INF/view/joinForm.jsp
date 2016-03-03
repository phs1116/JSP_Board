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
<script type="text/javascript">
	$(function(){
		
		
	});
</script>
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
		<div class="cover-image"
			style="background-image: url(https://unsplash.imgix.net/photo-1418065460487-3e41a6c84dc5?q=25&amp;fm=jpg&amp;s=127f3a3ccf4356b7f79594e05f6c840e);"></div>
		<div class="container">
			<div class="row text-center">
				<div class="col-md-12">
					<h1>
						Sign up <br> <br>
					</h1>
					<form action="join.do" method="post" class="form-horizontal" role="form">
						<div class="form-group has-feedback">
							<div class="col-sm-offset-2 col-sm-2">
								<label for="inputEmail3" class="control-label">Username</label>
							</div>
							<div class="col-sm-5">
								<input type="text" class="form-control" name="id" id="inputUsername"
									placeholder="Username">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-2">
								<label for="inputPassword3" class="control-label">Full
									name</label>
							</div>
							<div class="col-sm-5">
								<input type="text" class="form-control" name="name" id="inputName"
									placeholder="Full name">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-2">
								<label class="control-label">Password</label>
							</div>
							<div class="col-sm-5">
								<input type="password" class="form-control" name="password" id="inputPassword" 
									placeholder="Password">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-2">
								<label class="control-label">Password Confirm</label>
							</div>
							<div class="col-sm-5">
								<input type="password" class="form-control" name="confirmPassword" id="inputConfirm"
									placeholder="Password Confirm">
							</div>
						</div>

						<div class="form-group">
							<div class="col-sm-5 col-sm-offset-2 text-right">
								<button type="submit" class="btn btn-default">Sign in</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>