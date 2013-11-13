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
	  	String username = "cs110050071"; // Enter your PostgreSQL username
	  	String password = "170050011"; // Enter your PostgreSQL password
	  	String dbName = "cs110050071"; // Enter the name of the database that has the university tables.
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
		         			result2 = "<h style = 'color : #000000'> cannot send message as the user has blocked you </h>";
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
				         		
				         		//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
								//Date date = new Date();
								//String date1= dateFormat.format(date);
								//int date2 = Integer.parseInt(date1);
								int date3 = maxid*20;
				         		pstmt6.setInt(2,date3);
				         		
				         		pstmt6.setString(3,message);
				         		pstmt6.setInt(4,uname);
				         		pstmt6.setInt(5,receiver);
				         		pstmt6.executeUpdate();
				         		result2 = "<h style = 'color : #000000;'> message sent </h>";

				         	}

		         		}
		         	}
		         	else {
		         		//result2 = "<h style = 'color : #000000;'> u got yucked</h>";
		         		if(!res4.next()){
		         			//result2 = "<h style = 'color : #000000;'> u got ked</h>";
		         			}

		         	
				         	else {

				         		//result2 = "<h style = color : #000000;'> u got loved</h>";
				         		pstmt5 = conn.prepareStatement("select max(id) as max from messages ");
				         		res5 = pstmt5.executeQuery();
				         		res5.next();
				         		int maxid = res5.getInt("max");
				         		pstmt6 = conn.prepareStatement("insert into messages values (?,?,?,?,?)");
				         		pstmt6.setInt(1,maxid+1);
				         		//result2 = "<h style = 'color : #000000;'> u got killed</h>";
				         		//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
								//Date date = new Date();
								//String date1= dateFormat.format(date);
								//int date2 = Integer.parseInt(date1);
								int date3 = maxid*20;
				         		pstmt6.setInt(2,date3);
				         		//result2 = "<h style = 'color : #000000;'> u rocked</h>";
				         		pstmt6.setString(3,message);
				         		pstmt6.setInt(4,uname);
				         		pstmt6.setInt(5,receiver);
				         		pstmt6.executeUpdate();
				         		result2 = "<h style = 'color : #000000;'> message sent </h>";
				         		//target = "/outbox.jsp";

				         	}

		         	}


		         	
		         	
		         	
		         }
		         catch(SQLException pstatement){
	  		     out.println("prepare statement error");
	  	        }
	  	    }
	  	    
		
		  }
		  try{
		          //pstmt = conn.prepareStatement("select sender  from messages where receiver=? order by time desc");
		          pstmt1 = conn.prepareStatement("select sender,time,body  from messages where receiver=? order by id desc");
		          //pstmt.setInt(1,uname);
		          pstmt1.setInt(1,uname);
		          
		          res1= pstmt1.executeQuery();
		          //res = pstmt.executeQuery();
		          
		          result1 = "";
		          result1 +="<h style = 'color:#000000;'>INBOX </h>";
		          while(res1.next()){
		          	String body = res1.getString("body");
		          	Integer id = res1.getInt("sender");
		          	Integer time = res1.getInt("time");
		          	pstmt = conn.prepareStatement("select name from users where id = ?");
		          	pstmt.setInt(1,id);
		          	res2 = pstmt.executeQuery();
		          	res2.next();
		          	String name = res2.getString("name"); 
		          	temp="<a href='visit?visitid="+id+"''>"+ name + "</a> <br>";
		          	result1 += "<div style='color: #FFFFFF;'><table>" ;
		          	result1 += "<tr><td>"+temp +"<br>" +body +"</td></tr>";
		          	result1 += "</table></div>";
		          }

				  // if(!res1.next()){
				  //   //target = "/message.jsp";
				  //   result1="<tr> Inbox empty </tr>";
				  			
				  // 	}
				  		
				  // else{
					 //  	//target = "/message.jsp";
					 //  	ResultSetMetaData rsmd=res1.getMetaData();
		    //             int columnCount=rsmd.getColumnCount();
		    //             pstmt2 = conn.prepareStatement("select name  from users where id=? ");

		    //         result1 += "<div style='color: #FFFFFF;'><table><p style ='color :#000000;'>INBOX</p>" ;
		    //             do{
		    //             	for (int i = 1; i<= columnCount; i++){ 
	     //                        int s= i;
			   //                  //int k = res.getInt(i);
			   //              	//pstmt2.setInt(1,k);
			   //              	//res2 = pstmt2.executeQuery();	
			   //              	result1 += "<tr><td>"+res1.getString(i)+"</td></tr>";
			   //              }
			   //              	}while(res1.next());
			   //               	result1 += "</table></div>";
		    //       }
		        
		            
				
				
		          pstmt3 = conn.prepareStatement("select receiver,time,body from messages where sender=? order by id desc");
		          
		          pstmt3.setInt(1,uname);
		          
		          res3 = pstmt3.executeQuery();
		          
		          result = "";

				  // if(!res3.next()){
				  //   //target = "/message.jsp";
				  //   result= "<tr> Outbox empty </tr>";
				  			
				  // 	}
				  		
				  // else{
				  // 	//target = "/message.jsp";
				  // 	ResultSetMetaData rsmd2=res3.getMetaData();
	     //            int columnCount1=rsmd2.getColumnCount();

	     //        result += "<div style='color: #FFFFFF;'><table><p style ='color : #000000;'>OUTBOX</p>" ;
	     //            do{
	     //             for (int i = 1; i<= columnCount1; i++) 
	     //             result += "<tr><td>"+res3.getString(i)+"</td></tr>";
	     //              }while(res3.next());
	     //             result += "</table></div>";
	     //          }
		          result += "<h style = 'color:#000000 ;''> OUTBOX </h>";
		          while(res3.next()){
		          	String body1 = res3.getString("body");
		          	Integer id1 = res3.getInt("receiver");
		          	Integer time1 = res3.getInt("time");
		          	pstmt2 = conn.prepareStatement("select name from users where id = ?");
		          	pstmt2.setInt(1,id1);
		          	res = pstmt2.executeQuery();
		          	res.next();
		          	String name1 = res.getString("name"); 
		          	temp1="<a href='visit?visitid="+id1+"''>"+ name1 + "</a> <br>";
		          	result += "<div style='color: #FFFFFF;'><table>" ;
		          	result += "<tr><td>"+temp1 +"<br>" +body1 +"</td></tr>";
		          	result += "</table></div>";
		          }
	            
	            

	            
	           


	             
			  
				
			}
		  	catch(SQLException pstatement){
		  		out.println("prepare statement error");
		  	}

	  
	


	  	// request.setAttribute("role",passwd);
	  	// session.setAttribute("role",passwd);
	    
	    // out.println("sachin");

	    HttpSession session1 = request.getSession();

	   	Integer uname1 = (Integer)session1.getAttribute("id");
	  	ServletContext context = getServletContext();
	  	String str ="<a href="+"'visit?visitid=" + uname1+"'>"+ "HOME" +"</a>";
	  	
        request.setAttribute("result",result);
        request.setAttribute("result1",result1);
        request.setAttribute("result2",result2);
        request.setAttribute("temp",temp);
		        request.setAttribute("str",str);

	    
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