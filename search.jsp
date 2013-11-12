<html>
<head>
 <title>miniFB</title>
</head>

<body>

<!-- search box -->
<form name="searchForm" method="post" action="search">
  <input type="text" name="query" placeholder="search for a user" display = "block"> 
  <input type="Submit" name="Submit">
</form> 

<!-- printing feedback for events like viewing blocked user -->
<p><em>
<%
	String feed = (String)request.getAttribute("feedback");
	if(feed != null){
		out.print(feed); 
	}
%>
</em></p>

</body>
</html>