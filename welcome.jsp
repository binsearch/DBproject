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
	<!-- header ends  -->

	<!-- printing feedback for events like viewing blocked user -->

	<div class="row">
		<%
			String feed = (String)request.getAttribute("feedback");
			if(feed != null && feed != ""){
				out.print("<div class=\"alert alert-info\">");
				out.print(feed); 
				out.print("</div");
			}
		%>
			
	</div>
	<!-- displaying name and other details -->
	<div class="row">
		<div class="col-sm-2">
			
			<div class="row">			
				<a href="search.jsp" class="btn btn-primary" role="button">
					<span class="glyphicon glyphicon-search"></span> 
					search
				</a>
			</div>
			<div class="row">&nbsp;</div>

			<div class="row">
				<a href="message" class="btn btn-success" role="button">
					<span class="glyphicon glyphicon-comment"></span> 
					messages
				</a>			
			</div>
			<div class="row">&nbsp;</div>
			<div class="row">
				<a href="requests" class="btn btn-info" role="button">
					<span class="glyphicon glyphicon-user"></span> 
					requests
				</a>			
			</div>

			<div class="row">&nbsp;</div>
			<div class="row">
				<a href="notifications" class="btn btn-warning" role="button">
					<span class="glyphicon glyphicon-bell"></span> 
					notifications
				</a>			
			</div>

			<div class="row">&nbsp;</div>
			<div class="row">
				<a href="contacts" class="btn btn-primary" role="button">
					<span class="glyphicon glyphicon-book"></span> 
					contacts
				</a>			
			</div>

		</div>
		<div class="col-sm-10">
			<div class="row">
				<h3 class="text-success"><strong>
					<%
						out.print(request.getAttribute("name"));	
					%>  
					</strong>
				</h3>
			</div>

			<div class="row">
				<div class="col-sm-6">

					<p>
					<!-- displaying all details inside -->
						<%
							Integer bday = (Integer)request.getAttribute("bday");
							String year = Integer.toString(bday%10000);
							bday = bday/10000;

							String month = Integer.toString(bday%100);
							bday = bday/100;

							String day = Integer.toString(bday%100);
							String tot_bday = day + " " + month + " " + year;

							out.println("<strong>birthday: </strong>" + tot_bday);
						%>
					</p>

					<p>
						<strong>sex:
							<span class="text-danger">
							<%
								Integer sex = (Integer)request.getAttribute("sex");
								if(sex == 1){
									out.println("male");
								}

								else{
									out.println("female");
								}
							%>
							</span>
						</strong>
					</p>

					<p>
						<strong> education: </strong>
						<br>
						<%
							out.println((String) request.getAttribute("edu"));
						%>
					</p>

					<p>
						<strong> location: </strong>
						<br>
						<%
							out.println((String) request.getAttribute("loc"));
						%>
					</p>

					<p>
						<strong> email id: 
							<span class="text-primary">
								<%
									out.println((String) request.getAttribute("email"));
								%>
							</span>
						</strong>
					</p>

					<p>
						<strong> interested in: 
							<span class="text-danger">
								<%
									if((Integer)request.getAttribute("in_in") == 1){
										out.println("boys");
									}

									else{
										out.println("girls");
									}
								%>
							</span>
						</strong>
					</p>

				</div>
				<div class="col-sm-6">
					<h4><strong> Interests </strong></h4>
					<p>
					<%
						out.print((String)request.getAttribute("interests"));
					%>
					</p>

					<h4><strong>Liked Pages</strong></h4>
					<p>
					<%
						out.print((String)request.getAttribute("pagelist"));
					%>
					</p>

				</div>

			</div>

		</div>
	</div>

<a href="pictures"><h4> Pictures </h4></a>
<a href="addcom.jsp"><h4> Add a complaint </h4></a>

<%
	Integer mod = (Integer)session.getAttribute("mod");
	if(mod != -1){
		out.print("<a href=\"complaints\"> complaints </a>");
	}
%>

</div>
<script src="dist/js/bootstrap.js"></script>
</body>
</html>