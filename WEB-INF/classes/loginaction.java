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
		Integer login_id = (Integer)session.getAttribute("id");
	  	String uname = request.getParameter("username");
	  	String passwd = request.getParameter("password");



		PreparedStatement pst;
	  
	 	try{

	 		if(login_id != null){
	 			pst = conn.prepareStatement("select * from users where id = ?");
	 			pst.setInt(1, login_id);
	 		}
	 		else{
				pst = conn.prepareStatement("select * from users where name = ?  and password = ?");
				pst.setString(1,uname);
				pst.setString(2,passwd);	 			
	 		}
	  		target = "/login.jsp";


	 		ResultSet rs = pst.executeQuery();
	  		//if there is a match.
	  		if(rs.next()){
	  			Integer id = 0;
				id = rs.getInt("id");

				//set id session variable.
				session.setAttribute("id",id);
	  			target = "/welcome.jsp";

	  			//filling in request attributes to display in JSP
	  			request.setAttribute("name",rs.getString("name"));
	  			request.setAttribute("bday",rs.getInt("birthday"));
	  			request.setAttribute("edu",rs.getString("education"));
	  			request.setAttribute("sex",rs.getInt("sex"));
	  			request.setAttribute("loc",rs.getString("location"));
	  			request.setAttribute("email",rs.getString("emailid"));
	  			request.setAttribute("passwd",rs.getString("password"));
	  			request.setAttribute("in_in",rs.getInt("interested_in"));

	  			//find all interests of the user.
	  			pst = conn.prepareStatement("select * from interests where userid = ?");
	  			pst.setInt(1,id);
	  			rs = pst.executeQuery();
	  			String interests = "";
	  			while(rs.next()){
	  				interests = interests+rs.getString("name");
	  				interests = interests+"<br>";
	  			}
	  			request.setAttribute("interests", interests);
	  			// request.setAttribute("pagelist", interests);
	  			//get liked pages
	  			pst = conn.prepareStatement("select * from likes where userid = ?");
				pst.setInt(1,id);
				rs = pst.executeQuery();
				String pagelist = "";
				while(rs.next()){
					pst = conn.prepareStatement("select * from pages where id = ?");
					pst.setInt(1,rs.getInt("id"));
					ResultSet trs = pst.executeQuery();
					trs.next();
					pagelist = pagelist+trs.getString("name");
					pagelist = pagelist+"<br>";
				}
				request.setAttribute("pagelist", pagelist);

				//find out whether the user is a moderator.
				pst = conn.prepareStatement("select * from moderators where userid = ?");
				pst.setInt(1,id);
				rs = pst.executeQuery();
				Integer mod = -1;
				
				if(rs.next()){
					mod = rs.getInt("id");
				}
				session.setAttribute("mod",mod);

	  		}
	  	
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