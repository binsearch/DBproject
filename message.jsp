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
    <div class="col-sm-7">

      <div class="row">
        <div class="col-sm-12">
          <p class="text-info">
            <%
              if(!(request.getAttribute("result2") == null)){
                out.println(request.getAttribute("result2"));
              }
            %>
          </p>
        </div>
      </div>      


      <div class="row">
        <div class="col-sm-12">
          <h4><strong> Inbox </strong></h4>
          <%
            if(!(request.getAttribute("result1") == null)){
              out.println(request.getAttribute("result1"));
            }
          %>
        </div>
      </div>

      <div class="row">
        <div class="col-sm-12">
          <h4><strong> Outbox </strong></h4>
          <%
            if(!(request.getAttribute("result") == null)){
              out.println(request.getAttribute("result"));
            }
          %>

        </div>
      </div>
      
    </div>

    <!-- send new message -->
    <div class="col-sm-5">
      <form method="post" action="message" class="form-horizontal" role="form">

      <Strong> New Message </Strong>
      <div class="row">&nbsp;</div>
      <input name="query" type="text" class="form-control" placeholder="Send To">
      <div class="row">&nbsp;</div>
      <textarea name = "message" placeholder= "your message" class="form-control" rows="5"></textarea>
      <input name="queryID" type="hidden" value="3" class="form-control">  
      <div class="row">&nbsp;</div>
      <button type="submit" class="btn btn-primary">
        <span class="glyphicon glyphicon-send"> send </span>
      </button>
      </form>
    </div>

  </div>
</div>
</body>
</html>