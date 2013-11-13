<html>
<head>
 <title>messages</title>
</head>

<body>

<a href="message.jsp"><em> messages </em></a>

<a href="logout.jsp"><em> logout </em></a>

<!-- displaying all details inside -->

<tr><h2 style="color: #FFFFFF;"><b> <%
        if(!(request.getAttribute("result2") == null)){
          out.println(request.getAttribute("result2"));
        }
      %></b></h2></tr>

</body>
</html>