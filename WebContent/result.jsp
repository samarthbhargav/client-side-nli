<%@page import="com.oxf.client.service.IService"%>
<%@page import="com.oxf.client.mapping.ServiceMapping"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.oxf.client.utils.Utils"%>
<%@page import="com.oxf.client.communication.TranslationResult"%>
<%@page import="com.oxf.client.communication.CommunicationServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.oxf.client.constants.Params"%>
<%
    if ( session.getAttribute( Params.SESSION_CRED_USERNAME ) == null ) {
        response.sendRedirect( "login.jsp" );
        return;
    }

    CommunicationServlet comm = new CommunicationServlet();
    TranslationResult result = comm.getTranslation( request.getParameter( Params.NLI_PARAM_QUERY ) );
    ArrayList<String> mappings = Utils.constructMappingFromResult( result );
    ServiceMapping mapper = ServiceMapping.getInstance();
    String resultString = null;
    try {
        resultString = mapper.getServiceResponseAsString( mappings, result );
    } catch ( Exception e ) {
        e.printStackTrace(  );
		resultString = e.getMessage(  );
		resultString += "\n<br> <div id=\"result-div\"> Your Query Could not be answered</div>";
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

<title>Results | NLI Client</title>
</head>
<body >
	<div class="container">
		<div class="row">
			<div class="col-md-12 white-bg">
				<a href="dashboard.jsp">Go Back to Dashboard</a>
				<h2 class="text-center">Results of Query:</h2>
				<div id="result"><%=resultString%></div>
			</div>
		</div>
	</div>
	<div id="audio"></div>
	<script>
		var toSpeak = document.getElementById("result-div").innerHTML;
		//toSpeak = toSpeak.replace(/<h3>|</h3>|<br>/g, '');
		toSpeak = toSpeak.replace(/<(?:.|\n)*?>/gm, '');
		speak(toSpeak);
	</script>
</body>
</html>
