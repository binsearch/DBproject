<html>
<head>
 <title>miniFB</title>
</head>

<body>

<!-- search box -->
<form name="searchForm" method="post" action="search?type=0">
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

<h4> Advanced search </h4>
<form name="adsearch" method="post" action="search?type=1">
	<input type="checkbox" name="ageop" value="true"> age:
	<input type="text" name="lage" placeholder="from">
	<input type="text" name="rage" placeholder="to">
	<br>
	<input type="checkbox" name="locop" value="true"> location:
	<input type="text" name="loc" placeholder="place">
	<input type="Submit" name="Submit">	
</form>

<p>
<%
	String results = (String)request.getAttribute("results");
	if(results != null){
		out.print(results);
	}
%>
</p>

</body>
</html>