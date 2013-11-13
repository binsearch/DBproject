import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.sql.*;


public class addcom extends HttpServlet{

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
		Integer mod_id = (Integer)session.getAttribute("mod");


		PreparedStatement pst;
	  
	 	try{


	 		String com_body = (String)request.getParameter("complaint");

	 		//insert this complaint into complaints table and assign it to a 
	 		//moderator.

	 		//first generate id for the new complaint

  			pst = conn.prepareStatement("select max(id) from complaints");
  			ResultSet rs = pst.executeQuery();
  			Integer max_id = 0;
  			if(rs.next()){
  				max_id = rs.getInt("max") + 1;
  			}

  			//insert into the database.
  			pst = conn.prepareStatement("insert into complaints values(?,?)");
  			pst.setInt(1,max_id);
  			pst.setString(2,com_body);
  			pst.executeUpdate();

  			//assign this to a moderator.
  			Integer tot_mod = 0;
  			pst = conn.prepareStatement("select max(id) from moderators");
  			rs = pst.executeQuery();
  			if(rs.next()){
  				tot_mod = rs.getInt("max");
  			}
  			Integer ass_mod = (max_id%tot_mod)+1;

  			//insert this into the assignedto table.
  			pst = conn.prepareStatement("insert into assignedto values(?,?)");
  			pst.setInt(1,ass_mod);
  			pst.setInt(2,max_id);
  			pst.executeUpdate();

  			//adding feedback and redirecting to home
  			String feed = "we will get back to you soon";
  			request.setAttribute("feedback",feed);
  			target = "/home";

	  	
		}
	  	catch(SQLException pstatement){
	  		out.println("prepare statement error");
	  	}

	  
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