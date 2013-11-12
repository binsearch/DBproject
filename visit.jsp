<html>
<head>
 <title>miniFB</title>
</head>

<body>


<%
	out.println("<h2>" + (String)request.getAttribute("name") + "</h2>");
%>  

<%
	Integer rel = (Integer)request.getAttribute("relation");
	String visit_id = Integer.toString((Integer)request.getAttribute("visitid"));

	try{
		if(rel == 1){
			out.println("<b>friends</b>");
			out.println("<a href=\"change?rel=1&visitid="+visit_id+"\"><em>(unfriend)</em></a>");
		}
		if(rel == 2){
			out.println("<b>blocked</b>");
			out.println("<a href=\"change?rel=2&visitid="+visit_id+"\"><em>(unblock)</em></a>");
		}
		if(rel == 3){
			out.print("<a href=\"change?rel=3&visitid="+visit_id+"\"><b>accept request</b></a>");
		}
		if(rel == 4){
			out.print("<b>request sent</b>");
			out.println("<a href=\"change?rel=4&visitid="+visit_id+"\"><em>(cancel request)</em></a>");	
		}
		if(rel == 0){
			out.print("<a href=\"change?rel=0&visitid="+visit_id+"\"><b>send request</b></a><br>");
			out.print("<a href=\"change?rel=5&visitid="+visit_id+"\"><b>block user</b></a>");
		}
	}
	catch(NullPointerException e ){
	      out.print("null pointer recieved for rel");
	}

%>

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


</body>
</html>