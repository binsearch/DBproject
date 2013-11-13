import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.sql.*;

//action for login.jsp file.

public class notifications extends HttpServlet{

	Connection conn;
	private String target;
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
		String temp = "";
		PrintWriter out = response.getWriter();

		//create a session if one doesn't exist already.
		HttpSession session = request.getSession();
		Integer id = (Integer) session.getAttribute("id");
		target = "/notifications.jsp";
		PreparedStatement pst,pst1;	  
	 	try{
	  		pst = conn.prepareStatement("select id,body from notifications where userid = ? order by id desc");
	  		pst.setInt(1,id);
	  		ResultSet rs = pst.executeQuery();
	  		while(!rs.next()){
	  			String body = rs.getString("body");
	  			temp=temp + "<div> * <p>" + body + "</p> </div> <br>";
	  		}
	  	
		}
	  	catch(SQLException pstatement){
	  		out.println("prepare statement error");
	  	}
	  	String temp1="";
	  	temp1="<a href='visit?visitid="+id+"'><h2>HOME</h2> </a> <br>";
	  	request.setAttribute("result1",temp1);		
        request.setAttribute("result2",temp);
	  	ServletContext context = getServletContext();
	  
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