<html>
<head>
 <title>miniFB</title>
</head>

<body>


<%
	out.println("<h2>" + (String)request.getAttribute("name") + "</h2>");
%>  

<b>
<%
	Integer rel = (Integer)request.getAttribute("relation");
	
	try{
		if(rel == 1){
			out.print("friends");
		}
		if(rel == 2){
			out.print("you blocked this user");
		}
		if(rel == 3){
			out.print("accept request");
		}
		if(rel == 4){
			out.print("request sent");
		}
		if(rel == 0){
			out.print("send request");
		}
	}
	catch(NullPointerException e ){
	      out.print("null pointer recieved for rel");
	}

%>
</b>
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