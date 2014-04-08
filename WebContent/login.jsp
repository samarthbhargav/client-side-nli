<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">



<link href="css/bootstrap.min.css" rel="stylesheet">
<link type="text/css" href="css/bootstrap.css" rel="stylesheet">

<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<%@ page import="com.nanobi.client.dao.UserDao" %>
<%@ page import="com.nanobi.client.constants.Params" %>
<title>Login | NLI Client</title>
</head>
<body>

	<%
		String message = null;
	    if ( "POST".equalsIgnoreCase( request.getMethod() ) ) {
	        
			UserDao dao = new UserDao();
			String uname = request.getParameter( "uname" );
			String pass = request.getParameter("pass");
			if(uname == null || pass ==null) {
			    message ="Wrong POST";
			} else {
			    if(dao.authenticate( uname, pass )) {
			        session.setAttribute( Params.SESSION_CRED_USERNAME, uname );
			        response.sendRedirect( "dashboard.jsp" );
			        return;
			    } else {
			        message = "Username or password incorrect";
			    }
			}
	    } 
	%>
	<form class="form-signin" role="form" action="login.jsp" method="post">
		<h2 class="form-signin-heading">Please sign in</h2>
		<input id="uname" name="uname"type="text" class="form-control" placeholder="UserName" required="" autofocus=""> 
			<input id="pass" name="pass" type="password" class="form-control" placeholder="Password" required="">
			<%
			if(message != null) { %>
			<p><strong> <%= message %></strong></p>
			<%}
			%>
		<button class="btn btn-lg btn-primary btn-block" type="submit">Sign	in</button>
	</form>


	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>
