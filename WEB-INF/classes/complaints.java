import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.sql.*;


public class complaints extends HttpServlet{

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


	 		if(mod_id == -1){
	 			target = "/home";
	 		}
	 		else{
	 			target = "/complaints.jsp";
	 		}


  			//find complaints assigned to this moderator.

  			pst = conn.prepareStatement("select * from assignedto where id = ?");
  			pst.setInt(1,mod_id);
  			ResultSet rs = pst.executeQuery();
  			String com_list = "";

  			while(rs.next()){
  				com_list = com_list + "<p>";
  				pst = conn.prepareStatement("select * from complaints where id = ?");
  				pst.setInt(1, rs.getInt("complaintid"));
  				ResultSet trs = pst.executeQuery();
  				if(trs.next()){
  					com_list = com_list + trs.getString("data");
  				}
  				com_list = com_list + "</p>";
  			}

  			request.setAttribute("com_list", com_list);

	  	
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