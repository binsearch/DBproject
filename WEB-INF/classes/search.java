import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.sql.*;

//action for login.jsp file.

public class search extends HttpServlet{

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
	  	String query = request.getParameter("query");
	  	Integer type = Integer.parseInt(request.getParameter("type"));


		PreparedStatement pst;
	  
	 	try{

	 		if(type == 0){
				pst = conn.prepareStatement("select * from users where name = ?");
				pst.setString(1,query);



		 		ResultSet rs = pst.executeQuery();
		  		//if there is a match.
		  		if(rs.next()){
		  			Integer id = 0;
					id = rs.getInt("id");
					target = "/visit?visitid=" + Integer.toString(id);
		  		}
				else{
					target = "/search.jsp";
					String feed = "no user found";
					request.setAttribute("feedback",feed);
				}	  	
			}

			else{

				String ageop = (String)request.getParameter("ageop");
				String locop = (String)request.getParameter("locop");

				Calendar now = Calendar.getInstance();   // This gets the current date and time.
				Integer year = now.get(Calendar.YEAR);  //to get the year. 
				Integer lage = 0;
				if(request.getParameter("lage") != ""){
					lage = 	Integer.parseInt(request.getParameter("lage"));
				} 

				Integer rage = 0;
				if(request.getParameter("rage") != ""){
					rage = 	Integer.parseInt(request.getParameter("rage"));
				} 

				String loc = (String)request.getParameter("loc");
				String results = ""; //string to store results.
				pst = conn.prepareStatement("select interested_in from users where id = ?");
				pst.setInt(1,login_id);
				ResultSet rs = pst.executeQuery();
				rs.next();
				Integer int_in = rs.getInt("interested_in");



				if(ageop != null && locop != null){

					pst = conn.prepareStatement("select * from users where mod(birthday,10000) >= ? and mod(birthday,10000) <= ? and sex = ? and location = ?");
					Integer upper = year-lage;
					Integer lower = year-rage;

					pst.setInt(1,lower);
					pst.setInt(2,upper);
					pst.setInt(3, int_in);
					pst.setString(4,loc);
					rs = pst.executeQuery();

					//summing up the results into html code. 
					while(rs.next()){
						String ulink = "<a href=\"visit?visitid=";
						ulink = ulink + Integer.toString(rs.getInt("id"));
						ulink = ulink + "\">";
						ulink = ulink + rs.getString("name");
						ulink = ulink + "</a><br>";
						results = results + ulink;
					}

				}
				else if(ageop != null){
					// results = results + Integer.toString(year) + "  ";

					pst = conn.prepareStatement("select * from users where mod(birthday,10000) >= ? and mod(birthday,10000) <= ? and sex = ?");
					Integer upper = year-lage;
					Integer lower = year-rage;

					//for debuggind
					// results = results + Integer.toString(upper) + "  ";
					// results = results + Integer.toString(lower) + "  ";
					// results = results + Integer.toString(int_in) + "   ";

					pst.setInt(1,lower);
					pst.setInt(2,upper);
					pst.setInt(3, int_in);
					rs = pst.executeQuery();

					//summing up the results into html code. 
					while(rs.next()){
						String ulink = "<a href=\"visit?visitid=";
						ulink = ulink + Integer.toString(rs.getInt("id"));
						ulink = ulink + "\">";
						ulink = ulink + rs.getString("name");
						ulink = ulink + "</a><br>";
						results = results + ulink;
					}

				}
				else if(locop != null){
					pst = conn.prepareStatement("select * from users where location = ? and sex = ?");

					pst.setString(1,loc);
					pst.setInt(2, int_in);
					rs = pst.executeQuery();

					//summing up the results into html code. 
					while(rs.next()){
						String ulink = "<a href=\"visit?visitid=";
						ulink = ulink + Integer.toString(rs.getInt("id"));
						ulink = ulink + "\">";
						ulink = ulink + rs.getString("name");
						ulink = ulink + "</a><br>";
						results = results + ulink;
					}

				}

				target="/search.jsp";
				request.setAttribute("results", results);
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