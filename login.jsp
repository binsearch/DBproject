<!DOCTYPE html>
<html lang="en">
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
 		<title>miniFB</title>
 		<link href="dist/css/bootstrap.min.css" rel="stylesheet">
	</head>

	<body>
	 	
		<div class="container">	 
	  		<div class="row">
	  			<div class="col-md-3"></div>
	  			<div class="col-md-6">
	  				<h1 class="text-center text-primary"> miniFB </h1>
	  				<form name="loginForm" class="form-horizontal" method="post" role="form" action="home">
	  				  <div class="form-group">
	  				    <label class="col-sm-2 control-label">Username</label>
	  				    <div class="col-sm-10">
	  				      <input type="text" class="form-control" name="username">
	  				    </div>
	  				  </div>
	  				  <div class="form-group">
	  				    <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
	  				    <div class="col-sm-10">
	  				      <input type="password" class="form-control" id="inputPassword3" name="password">
	  				    </div>
	  				  </div>
	  				  <div class="form-group">
	  				    <div class="col-sm-10 col-sm-offset-2">
	  				      <button type="submit" class="btn btn-default">Sign in</button>
	  				    </div>
	  				  </div>
	  				</form>	
	  			</div>
<!-- 	  		<form name="loginForm" method="post" action="home">
	    
	    	<input type="text" name="username" placeholder="username" display = "block"> 
	    	<input type="text" name="password" placeholder="password" display = "block">
	    	<input type="Submit" name="Submit">

	  		</form>
 -->	  			
 				<div class="col-md-3"></div>
	  		</div> 
		</div>
	</body>
</html>