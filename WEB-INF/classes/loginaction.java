import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.sql.*;

//action for login.jsp file.

public class loginaction extends HttpServlet{

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
	
		PrintWriter out = response.getWriter();

		//create a session if one doesn't exist already.
		HttpSession session = request.getSession();

	  	String uname = request.getParameter("username");
	  	String passwd = request.getParameter("password");



		PreparedStatement pst;
	  
	 	try{
	  		pst = conn.prepareStatement("select * from users where name = ?  and password = ?");
	  		pst.setString(1,uname);
	  		pst.setString(2,passwd);
	  		ResultSet rs = pst.executeQuery();

	  		if(!rs.next()){
	  			target = "/login.jsp";
	  		}
	  		else{
				int id = rs.getInt("id");
				//set id session variable.
				session.setAttribute("id",id);
	  			target = "/welcome.jsp";
	  		}
	  	
		}
	  	catch(SQLException pstatement){
	  		out.println("prepare statement error");
	  	}
	  	// pst.close();

	  	request.setAttribute("role",passwd);
	  	// session.setAttribute("role",passwd);
	  
	  	ServletContext context = getServletContext();
	  
	  	RequestDispatcher dispatcher = context.getRequestDispatcher(target);
	  	dispatcher.forward(request, response);

	}


	public void destroy() {
	   try{
	     conn.close();
	   }
	   catch (SQLException sqle) {
	   }
	}
}