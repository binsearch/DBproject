import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.sql.*;


public class change extends HttpServlet{

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
		Integer rel = Integer.parseInt(request.getParameter("rel"));

		PreparedStatement pst;
	  
	 	try{

	  			

  			//1 means friends.
  			//2 means blocked.
  			//3 means accept request.
  			//4 means request sent.
  			
  			Integer login_id = (Integer)session.getAttribute("id");

  			//deleting the contacts entry from the table as unfriend is selected.
  			if(rel == 1){
				pst = conn.prepareStatement("delete from contacts where user1 = ? and user2 = ?");
				pst.setInt(1,login_id);
				pst.setInt(2,visit_id);
				pst.executeUpdate();

				pst.setInt(2,login_id);
				pst.setInt(1,visit_id);
				pst.executeUpdate();

  			}

  			//unblock
  			if(rel == 2){
				pst = conn.prepareStatement("delete from blocked where userid = ? and blocked_userid = ?");
				pst.setInt(1,login_id);
				pst.setInt(2,visit_id);
				pst.executeUpdate();
  			}

  			//accept request.
  			if(rel == 3){
  				pst = conn.prepareStatement("delete from requests where sender = ? and receiver = ?");
  				pst.setInt(1,visit_id);
  				pst.setInt(2,login_id);
  				pst.executeUpdate();
  				
  				//insert into notifications.
  				pst = conn.prepareStatement("select max(id) from notifications");
  				ResultSet trs = pst.executeQuery();
  				trs.next();
  				Integer max_id = trs.getInt("max") + 1;
  				
  				//get the name of request sender.
  				pst = conn.prepareStatement("select name from users where id = ?");
  				pst.setInt(1,login_id);
  				trs = pst.executeQuery();
  				trs.next();
  				String login_name = trs.getString("name");
  				
  				//making the body of notification.
  				String body = "<a href=\"visit?visitid=";
  				body = body + Integer.toString(login_id) + "\">";
  				body = body + login_name + "</a>";
  				body = body + " accepted your request";


  				pst = conn.prepareStatement("insert into notifications values (?,?,?)");
  				pst.setInt(1,max_id);
  				pst.setString(2,body);
  				pst.setInt(3,visit_id);
  				pst.executeUpdate();

  				pst = conn.prepareStatement("insert into contacts values (?,?)");
  				pst.setInt(1,login_id);
  				pst.setInt(2,visit_id);
  				pst.executeUpdate();
				
				pst.setInt(1,visit_id);
				pst.setInt(2,login_id);
				pst.executeUpdate();
  			}

  			//cancel request.
  			if(rel == 4){
				pst = conn.prepareStatement("delete from requests where sender = ? and receiver = ?");
				pst.setInt(1,login_id);
				pst.setInt(2,visit_id);
				pst.executeUpdate();
  			}

  			//send request.
  			if(rel == 0){
				pst = conn.prepareStatement("insert into requests values (?,?)");
				pst.setInt(1,login_id);
				pst.setInt(2,visit_id);
				pst.executeUpdate();

  			}

  			//block user.
  			if(rel == 5){
  				pst = conn.prepareStatement("insert into blocked values (?,?)");
  				pst.setInt(1,login_id);
  				pst.setInt(2,visit_id);
  				pst.executeUpdate();

  			}

  			target = "/visit?visitid=" + Integer.toString(visit_id);
  		
  	
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