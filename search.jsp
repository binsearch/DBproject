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



<!-- search box -->
	<div class="row">
		<div class="col-sm-12">
			<form class="form-inline" role="form" method="post" action="search?type=0">
			  <div class="form-group">
			    <input type="text" class="form-control" placeholder="search for a user" name="query">
			  </div>
			  <button type="submit" class="btn btn-primary">
			  	<span class="glyphicon glyphicon-search"></span>
			  </button>
			</form>
		</div>
	</div>



	<div class="row">&nbsp;</div>

	<div class="row">
		<div class="col-sm-12">
			<h4><span class="text-primary"><strong> Advanced search </strong></span></h4>
		</div>
	</div>

	<div class="row">&nbsp;</div>

	<div class="row">
		<div class="col-sm-12">
			<form class="form-inline" role="form" method="post" action="search?type=1">
				<div class="form-group">
					<input type="checkbox" name="ageop" value="true"><strong>age:</strong>
				</div>
				<div class="form-group">
					<input type="text" class="form-control" name="lage" placeholder="from">
				</div>
				<div class="form-group">
					<input type="text" name="rage" class="form-control"placeholder="to">
				</div>
					<br>
				<div class="row">&nbsp;</div>
				<div class="form-group">
					<input type="checkbox" name="locop" value="true"><strong>location:</strong>
				</div>
				<div class="form-group">
					<input type="text" name="loc" placeholder="place" class="form-control">
				</div>
				<button type="submit" class="btn btn-primary">
					<span class="glyphicon glyphicon-search"></span>
				</button>
			</form>
		</div>
	</div>

	<!-- printing feedback for events like viewing blocked user -->
	<div class="row">&nbsp;</div>
	<div class="row">
		<div class="col-sm-12">
			<%
				String feed = (String)request.getAttribute("feedback");
				if(feed != null && feed != ""){
					out.print("<div class=\"alert alert-info\">");
					out.print(feed); 
					out.print("</div");
				}
			%>
		</div>	
	</div>


	<div class="row">&nbsp;</div>
	<div class="row">
		<div class="col-sm-12">
			<h4><strong>Results</strong></h4>
			<p><strong>
			<%
				String results = (String)request.getAttribute("results");
				if(results != null){
					out.print(results);
				}
			%>
			</strong></p>
			
		</div>
	</div>

</body>
</html>