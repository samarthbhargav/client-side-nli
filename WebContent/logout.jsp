<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<%
    session.invalidate();
%>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link type="text/css" href="css/bootstrap.css" rel="stylesheet">
<script src="js/speakClient.js"></script>
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<title>Logged Out | NLI Client</title>
</head>
<body>
	<div id="audio"></div>
	<form class="form-signin" role="form" >
		<h2 class="form-signin-heading">Successfully logged out</h2>

		<a href="login.jsp" class="text-center">Log Back in</a>
	</form>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script>
	speak("You have Logged out. Thank you for using our system");
	</script>
</body>
</html>
