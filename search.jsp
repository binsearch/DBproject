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

<h4> Advanced search </h4>
<form name="adsearch" method="post" action="search">
	<input type="checkbox" name="ageop"> age:
	<input type="text" name="lage" placeholder="from">
	<input type="text" name="rage" placeholder="to">
	<br>
	<input type="checkbox" name="locop"> location:
	<input type="text" name="loc" placeholder="place">
	<br>
	<input type="checkbox" name="male"> male
	<input type="checkbox" name="female"> female
</form>

</body>
</html>