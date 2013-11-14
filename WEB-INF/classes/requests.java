import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.sql.*;

//action for requests.jsp file.

public class requests extends HttpServlet{

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
		target = "/requests.jsp";
		PreparedStatement pst,pst1;	  
	 	try{
	  		pst = conn.prepareStatement("select sender from requests where receiver = ?");
	  		pst.setInt(1,id);
	  		ResultSet rs = pst.executeQuery();
	  		//if there is no match
	  		while(rs.next()){
	  			int user_id = rs.getInt("sender");
				pst1 = conn.prepareStatement("select name from users where id = ?");
	  			pst1.setInt(1,user_id);
	  			ResultSet rs1 = pst1.executeQuery();
	  			rs1.next();
	  			String user_name ="";
	  			user_name = rs1.getString("name");
	  			temp=temp+"<a href='visit?visitid="+user_id+"'>"+ user_name + "</a> <br>";
	  		}
//	  		temp=temp+"</p>";
	  	
		}
	  	catch(SQLException pstatement){
	  		out.println("prepare statement error");
	  	}

        	String temp1="";
	  	temp1="<a href='/DBproject/home'><h2>HOME</h2> </a> <br>";
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