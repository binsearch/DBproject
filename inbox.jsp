<html>
<head>
 <title>messages</title>
</head>

<body>



<!-- displaying all details inside -->

<tr><h2 style="color: #FFFFFF;"><b> <%
        if(!(request.getAttribute("result") == null)){
          out.println(request.getAttribute("result"));
        }
      %></b></h2></tr>

</body>
</html>