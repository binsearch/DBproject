<html>
<head>
 <title>miniFB</title>
</head>
<body>

<%
	out.print(request.getAttribute("result1"));
	%>

<%
	out.print(request.getAttribute("result2"));
	%>

<a href="logout.jsp"><em> logout </em></a>

</body>
</html>	