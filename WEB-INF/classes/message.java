import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.sql.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class message extends HttpServlet{

	Connection conn;
	private String target="/message.jsp";;
	public void init(ServletConfig config) throws ServletException {
		
		super.init(config);
		//Load the PostgreSQL JDBC driver class
		
		try{
			Class.forName("org.postgresql.Driver");
		} 
		catch (ClassNotFoundException cnfe){
			// out.println("driver not loaded");
		}
		      
  		//Enter the connection details
	  	String hostname = "10.105.1.51";	// If PostgreSQL is running on some other machine enter the IP address of the machine here
	  	String username = "cs110050069"; // Enter your PostgreSQL username
	  	String password = "960050011"; // Enter your PostgreSQL password
	  	String dbName = "cs110050069"; // Enter the name of the database that has the university tables.
	  	String connectionUrl = "jdbc:postgresql://" + hostname +  "/" + dbName;

	  	//Connect to the database

	    try {
	   		
	   		conn = DriverManager.getConnection(connectionUrl,username, password);
	        // out.println("Connected successfully");
	    }
	    catch (SQLException sqle) {
	    	// out.println("connection not established");
	    }	    
	}

	public void doGet(HttpServletRequest request,
	  HttpServletResponse response)
	  throws ServletException, IOException {
	
	  // If it is a get request forward to doPost()
	  doPost(request, response);
	
	}

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
	
		 PrintWriter out = response.getWriter();

		//create a session if one doesn't exist already.
		 HttpSession session = request.getSession();

	   	Integer uname = (Integer)session.getAttribute("id");
	   	String  queryId = request.getParameter("queryID");
	  	String query =request.getParameter("query");
	  	String message =request.getParameter("message");
	  	


         String result=null,result1=null,result2 = null,temp =null,temp1 = null;
		 PreparedStatement pstmt,pstmt1,pstmt2,pstmt3,pstmt4,pstmt5,pstmt6,pstmt7;
		 ResultSet res,res1,res2,res3,res4 ,res5,res6,res7= null;
		 
	  
	     
         if (queryId != null){
         	
           
             if(queryId.equals("3")){
             	
             	try{
             		
		         	pstmt4 = conn.prepareStatement("select id  from users where name = ? ");
		         	pstmt4.setString(1,query);
		         	res4 = pstmt4.executeQuery();
		         	res4.next();
		         	
		         	int receiver = res4.getInt("id");
		         	res4 = pstmt4.executeQuery();
		         	
		         	pstmt7 = conn.prepareStatement("select userid from blocked where blocked_userid = ?");
		         	
		         	pstmt7.setInt(1,uname);
		         	res7 = pstmt7.executeQuery();
		         	
		         	if(res7.next()){
		         		
		         		if (receiver == res7.getInt("userid")){
		         			result2 = "cannot send message as the user has blocked you";
		         		}
		         		else{
		         			if(!res4.next()){
		         			}

		         	
				         	else {

				         		
				         		pstmt5 = conn.prepareStatement("select max(id)  from messages ");
				         		
				         		res5 = pstmt5.executeQuery();
				         		
				         		res5.next();
				         		
				         		int maxid = res5.getInt("max");
				         		pstmt6 = conn.prepareStatement("insert into messages values (?,?,?,?,?)");
				         		pstmt6.setInt(1,maxid+1);
				         		
								int date3 = maxid*20;
				         		pstmt6.setInt(2,date3);
				         		
				         		pstmt6.setString(3,message);
				         		pstmt6.setInt(4,uname);
				         		pstmt6.setInt(5,receiver);
				         		pstmt6.executeUpdate();
				         		result2 = "message sent";

				         	}

		         		}
		         	}
		         	else {
		         		if(!res4.next()){
		         			}

		         	
				         	else {

				         		pstmt5 = conn.prepareStatement("select max(id) as max from messages ");
				         		res5 = pstmt5.executeQuery();
				         		res5.next();
				         		int maxid = res5.getInt("max");
				         		pstmt6 = conn.prepareStatement("insert into messages values (?,?,?,?,?)");
				         		pstmt6.setInt(1,maxid+1);
								int date3 = maxid*20;
				         		pstmt6.setInt(2,date3);
				         		pstmt6.setString(3,message);
				         		pstmt6.setInt(4,uname);
				         		pstmt6.setInt(5,receiver);
				         		pstmt6.executeUpdate();
				         		result2 = "message sent";
				         	}

		         	}


		         	
		         	
		         	
		         }
		         catch(SQLException pstatement){
	  		     out.println("prepare statement error");
	  	        }
	  	    }
	  	    
		
		  }
		  try{
		          pstmt1 = conn.prepareStatement("select sender,time,body  from messages where receiver=? order by id desc");
		          pstmt1.setInt(1,uname);
		          
		          res1= pstmt1.executeQuery();
		          result1 = "";

		          while(res1.next()){
		          	String body = res1.getString("body");
		          	Integer id = res1.getInt("sender");
		          	Integer time = res1.getInt("time");
		          	pstmt = conn.prepareStatement("select name from users where id = ?");
		          	pstmt.setInt(1,id);
		          	res2 = pstmt.executeQuery();
		          	res2.next();
		          	String name = res2.getString("name"); 
		          	temp="<a href='visit?visitid="+id+"''>"+ name + "</a>";
		          	result1 += temp +"<p>" +body +"</p>";
		          }

				
				
		          pstmt3 = conn.prepareStatement("select receiver,time,body from messages where sender=? order by id desc");
		          
		          pstmt3.setInt(1,uname);
		          
		          res3 = pstmt3.executeQuery();
		          
		          result = "";

		          while(res3.next()){
		          	String body1 = res3.getString("body");
		          	Integer id1 = res3.getInt("receiver");
		          	Integer time1 = res3.getInt("time");
		          	pstmt2 = conn.prepareStatement("select name from users where id = ?");
		          	pstmt2.setInt(1,id1);
		          	res = pstmt2.executeQuery();
		          	res.next();
		          	String name1 = res.getString("name"); 
		          	temp1="<a href='visit?visitid="+id1+"''>"+ name1 + "</a>";
		          	result += temp1 +"<p>" +body1 +"</p>";
		          }
	            
				
			}
		  	catch(SQLException pstatement){
		  		out.println("prepare statement error");
		  	}

	  
	


	    HttpSession session1 = request.getSession();

	  	ServletContext context = getServletContext();
	  	
        request.setAttribute("result",result);
        request.setAttribute("result1",result1);
        request.setAttribute("result2",result2);

	    
	  	RequestDispatcher dispatcher = context.getRequestDispatcher(target);
	  	dispatcher.forward(request, response);
	}

	//to close the connection when the server is shutdown.
	public void destroy() {
	   try{
	     conn.close();
	   }
	   catch (SQLException sqle) {
	   }
	}
}