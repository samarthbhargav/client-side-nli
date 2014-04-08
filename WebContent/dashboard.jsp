<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.nanobi.client.constants.Params"%>
<%
    if ( session.getAttribute( Params.SESSION_CRED_USERNAME ) == null ) {
        response.sendRedirect( "login.jsp" );
        return;
    }
%>
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

<title>Dashboard | NLI Client</title>
</head>
<body>
<div class="container" >
	<div class="row">
		<div class="col-md-12 white-bg" >
			<h2 class="text-center">Natural Language - Student Query System Dashboard</h2>
			<form class="form-signin" role="form" action="result.jsp" method="post">
					<input id="query" name="query" type="text" class="form-control" placeholder="Speak or Type your query" required="" autofocus="" x-webkit-speech>
					<br> 
					<button class="btn btn-lg btn-primary btn-block" type="submit">Query</button>
			</form>
		</div>
	</div> 
</div>
</body>
</html>
