<!DOCTYPE html>
<html lang="en">
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
 		<title>miniFB</title>
 		<link href="dist/css/bootstrap.min.css" rel="stylesheet">
	</head>

<body>

<div class="container">
	<!-- line added for space between rows -->
	<div class="row">&nbsp;</div>
	
	<div class="row">
		<!-- home button-->
		<div class="col-sm-1">
			<a href="home" class="btn btn-danger" role="button">
				<span class="glyphicon glyphicon-home"></span>
			</a>			
		</div>

		<!-- logout button -->
		<div class="col-sm-9">
			<!-- <a href="home"> -->
				<!-- <h1 class="text-primary"><strong> miniFB </strong></h1> -->
			<!-- </a> -->
		</div>
		<div class="col-sm-2">
			<a href="logout.jsp" class="btn btn-danger" role="button">
				<span class="glyphicon glyphicon-log-out"></span>
				logout
			</a>
		</div>
	</div>

	<div class="row">&nbsp;</div>

	<div class="row">
		<div class="col-sm-12">
			<h4><strong>Requests</strong></h4>
			<%
				out.print(request.getAttribute("result2"));
			%>
		</div>
	</div>
</div>


</body>
</html>	