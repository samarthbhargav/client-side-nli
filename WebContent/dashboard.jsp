<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.oxf.client.constants.Params"%>
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
<script src="js/speakClient.js"></script>
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<title>Dashboard | NLI Client</title>
</head>
<body style="background-image:url('bg-dash.jpg')">
<div class="container" >
	<div class="row">
		<div class="col-md-2">
		</div>
		<div class="col-md-8 white-bg" >
			<a href="logout.jsp" >Logout</a> <br/>
			<h2 class="text-center">Voice Driven Report Generation System</h2>
			<h3 class="text-center">A Generic Named Entity Recognition Engine with multiple namespaces</h3>
			<form class="form-signin" role="form" action="result.jsp" method="post">
					<input id="query" name="query" type="text" class="form-control" placeholder="Speak or Type your query" required="" autofocus="" x-webkit-speech>
					<br> 
					<button class="btn btn-lg btn-primary btn-block" type="submit">Query</button>
			</form>
		</div>
	</div> 
</div>
<div id="audio"></div>
<script>
speak("I am a Voice Driven Report Generation System. Please Speak or Type the Query into the box and Click the Query Button to proceed");
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/annyang/1.1.0/annyang.min.js"></script>
<script>
if (annyang) {
  // Let's define our first command. First the text we expect, and then the function it should call
  var commands = {
    '*term': function(term) {
      document.getElementById('query').value = term; 
    }
  };

  // Add our commands to annyang
  annyang.addCommands(commands);

  // Start listening. You can call this here, or attach this call to an event, button, etc.
  annyang.start();
}
</script>
</body>
</html>
