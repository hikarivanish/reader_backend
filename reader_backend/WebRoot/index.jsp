<%@page import="me.hikari.model.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	User user = (User) session.getAttribute("user");
	if (user != null) {
		response.sendRedirect(path + "/reader.jsp");
		return;
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>VanishReader</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="A Simple Rss Reader">
<!-- Bootstrap -->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<link href="css/vanishreader.css" rel="stylesheet">
</head>
<body>

	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">VanishReader</a>
			</div>

			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Home</a></li>
					<li><a href="#about">About</a></li>
					<li><a href="#contact">Contact</a></li>
				</ul>
				<form class="navbar-form navbar-right" id="loginform"
					action="login" method="post">
					<div class="form-group">
						<input type="text" placeholder="User Name" class="form-control"
							id="username" name="username" />
					</div>
					<div class="form-group">
						<input type="password" placeholder="Password" class="form-control"
							id="password" name="password" />
					</div>
					<button type="submit" class="btn btn-success">Sign in</button>
					<button type="button" class="btn btn-primary" id="register">Sign
						up</button>
				</form>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>

	<div class="container">
		<div class="alert alert-danger  hide" id="loginalert">
			<strong>Ops! </strong>&nbsp;&nbsp;Login incorrect
		</div>
		<div class="jumbotron">
			<h1>Make It Simple.</h1>
			<p>
				Vanish Reader is a simple rss reader for those who enjoy rss and
				want a simple page to read.<br /> Vanish Reader also provide a
				simple api for developer which can be used on mobile
			</p>
			<p>
				<a href="#" class="btn btn-primary btn-lg" role="button">Learn
					more &raquo;</a>
			</p>
		</div>
	</div>
	<!-- /.container -->


	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="js/jquery-2.1.1.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<!-- <script src="bootstrap/js/bootstrap.min.js"></script> -->
	<script>
		jQuery(document).ready(
				function($) {
					$("#loginform").submit(
							function(event) {
								event.preventDefault();
								$.post($(this).attr("action"), $(this)
										.serialize(), function(data) {
									if (data.login) {
										window.location = "reader.html";
									} else {
										$("#loginalert").removeClass("hide");
									}
								});
							});
					$("#register").click(function() {
						$("#loginalert").removeClass("hide");
					});
				});
	</script>
</body>
</html>
