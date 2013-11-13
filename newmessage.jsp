<html>
<head>
 <title>messages</title>
</head>

<body>
<a href="message.jsp"><em> messages </em></a>

<a href="logout.jsp"><em> logout </em></a>
<!--//<jsp:forward page="/inbox.jsp"/>
<jsp:forward page="/outbox.jsp"/>-->
<!-- displaying all details inside -->
<tr>
  <form method="post" action="login" name="form1">
  <tr><td>
  
  <input name="queryID" type="hidden" value="1" />  
  </td>
  <td><input name="submit" type="submit" value="INBOX" /></td><br>
  </tr>
  </form>
  </tr>

</body>
</html>