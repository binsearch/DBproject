<html>
<head>
 <title>miniFB</title>
</head>

<body>

<p><a href="home">home</a></p>

<%
	String com_list = (String)request.getAttribute("com_list");
	if(com_list != null){
		out.println(com_list);
	}
%>

</body>
</html>