<html>
<head>
 <title>messages</title>
</head>


<body>
     
<tr><h2 style="color: #FFFFFF;"><b> <%
        if(!(request.getAttribute("str") == null)){
          out.println(request.getAttribute("str"));
        }
      %></b></h2></tr>
<a href="logout.jsp"><em> logout </em></a>

<tr><h2 style="color: #FFFFFF;"><b> <%
        if(!(request.getAttribute("result2") == null)){
          out.println(request.getAttribute("result2"));
        }
      %></b></h2></tr>
<tr><h2 style="color: #FFFFFF;"><b> <%
        if(!(request.getAttribute("result1") == null)){
          out.println(request.getAttribute("result1"));
        }
      %></b></h2></tr>
<tr><h2 style="color: #FFFFFF;"><b> <%
        if(!(request.getAttribute("result") == null)){
          out.println(request.getAttribute("result"));
        }
      %></b></h2></tr>


<!-- displaying all details inside -->

<tr>
  <form method="post" action="message" name="form1">
  <tr><td>
  <h> NEW MESSAGE </h><br>
  <input name="query" type="text" size="25" placeholder="SEND TO" /><br>
  <textarea name = "message" rows="5" cols="60" placeholder= "your message"></textarea>
  <input name="queryID" type="hidden" value="3" />  
  </td>
  <td><input name="submit" type="submit" value="SEND MESSAGE" /></td><br>
  </tr>
  </form>
  </tr>



</body>
</html>