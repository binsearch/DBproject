<html>
<head>
 <title>miniFB</title>
</head>

<body>

<!-- search -->

<p><a href="search.jsp">search</a></p>

<!-- printing feedback for events like viewing blocked user -->
<p><em>
<%
	String feed = (String)request.getAttribute("feedback");
	if(feed != null){
		out.print(feed); 
	}
%>
</em></p>

<h2>

<%
	out.print(request.getAttribute("name"));	
%>  
</h2>
<a href="logout.jsp"><em> logout </em></a>

<h3> Details </h3>
<!-- displaying all details inside -->
<%
	
	out.println("<p>birthday  " + (String)request.getAttribute("bday") + "</p>");

	Integer sex = (Integer)request.getAttribute("sex");
	if(sex == 1){
		out.println("<p>boy here</p>");
	}

	else{
		out.println("<p>girl here</p>");
	}


	out.println("<p>education  " + (String) request.getAttribute("edu") + "</p>");

	out.println("<p>location  " + (String) request.getAttribute("loc") + "</p>");

	out.println("<p>email id  " + (String) request.getAttribute("email") + "</p>");

	out.print("</p>interested in  ");

	if((Integer)request.getAttribute("in_in") == 1){
		out.println("boys</p>");
	}

	else{
		out.println("girls</p>");
	}



%>

<h4> Interests </h4>
<p>
<%
	out.print((String)request.getAttribute("interests"));
%>
</p>

<h4> Liked Pages </h4>
<p>
<%
	out.print((String)request.getAttribute("pagelist"));
%>
</p>


<a href="message"> <h4> Messages </h4> </a>
<a href="requests"> <h4> Requests</h4> </a>
<a href="contacts"> <h4>Contacts </h4> </a>
<a href="notifications"> <h4> Notifications </h4> </a>
<a href="pictures"><h4> Pictures </h4></a>
<a href="addcom.jsp"><h4> Add a complaint </h4></a>

<%
	Integer mod = (Integer)session.getAttribute("mod");
	if(mod != -1){
		out.print("<a href=\"complaints\"> complaints </a>");
	}
%>

</body>
</html>