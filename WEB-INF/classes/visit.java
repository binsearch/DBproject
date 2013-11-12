import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.sql.*;


public class visit extends HttpServlet{

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

		Integer visit_id = Integer.parseInt(request.getParameter("visitid"));

		PreparedStatement pst;
	  
	 	try{
	  		pst = conn.prepareStatement("select * from users where id = ?");
	  		pst.setInt(1, visit_id);
	  		ResultSet rs = pst.executeQuery();

	  		//if there is no match
	  		if(!rs.next()){
	  			request.setAttribute("found",0);
	  		}
	  		//if there is a match.
	  		else{
	  			request.setAttribute("found", 1);
	  			target = "/visit.jsp";

	  			//filling in request attributes to display in JSP
	  			request.setAttribute("name",rs.getString("name"));
	  			request.setAttribute("bday",rs.getString("birthday"));
	  			request.setAttribute("edu",rs.getString("education"));
	  			request.setAttribute("sex",rs.getInt("sex"));
	  			request.setAttribute("loc",rs.getString("location"));
	  			request.setAttribute("email",rs.getString("emailid"));
	  			request.setAttribute("in_in",rs.getInt("interested_in"));
	  			
	  			Integer rel = 0; //no relation yet.
	  			//1 means friends.
	  			//2 means blocked.
	  			//3 means accept request.
	  			//4 means request sent.
	  			
	  			Integer user1 = (Integer)session.getAttribute("id");
	  			Integer user2 = visit_id;

	  			pst = conn.prepareStatement("select * from requests where sender = ? and receiver = ?");
	  			pst.setInt(1, user2);
	  			pst.setInt(2, user1);
	  			rs = pst.executeQuery();
	  			//3 accept request
	  			if(rs.next()){
	  				rel = 3;
	  			}

	  			pst.setInt(1, user1);
	  			pst.setInt(2, user2);
	  			rs = pst.executeQuery();
	  			//4 request sent
	  			if(rs.next()){
	  				rel = 4;
	  			}

				pst = conn.prepareStatement("select * from contacts where user1 = ? and user2 = ?");
				pst.setInt(1, user2);
				pst.setInt(2, user1);
				rs = pst.executeQuery();
				//1 friends

				if(rs.next()){
					rel = 1;
				}

				pst = conn.prepareStatement("select * from blocked where userid = ? and blocked_userid = ?");
				pst.setInt(1, user1);
				pst.setInt(2, user2);
				rs = pst.executeQuery();
				//2 unblock person

				if(rs.next()){
					rel = 2;
				}

				pst.setInt(1, user2);
				pst.setInt(2, user1);
				rs = pst.executeQuery();
				//loggedin user is blocked.
				if(rs.next()){
					target = "/home";
				}

				request.setAttribute("relation", rel);
				request.setAttribute("visitid",visit_id);
				
				//if it's his own profile.
				if(visit_id == user1){
					target = "/home";
				}

	  		}

		}
	  	catch(SQLException pstatement){
	  		out.println("prepare statement error");
	  	}

	  	// request.setAttribute("role",passwd);
	  	// session.setAttribute("role",passwd);
	  
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