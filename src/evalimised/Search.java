package evalimised;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.google.appengine.api.rdbms.AppEngineDriver;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Search extends HttpServlet {
@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	
		PrintWriter out = response.getWriter();
		String eesnimi = request.getParameterValues("eesnimi")[0];
		String perenimi = request.getParameterValues("perenimi")[0];
		String ID = request.getParameterValues("kandidaadiNr")[0];
		String erakond = request.getParameterValues("Erakond")[0];
		String regioon = request.getParameterValues("regioon")[0];
	
		Connection c = null;
		try {
			DriverManager.registerDriver(new AppEngineDriver());
		    c = DriverManager.getConnection("jdbc:google:rdbms://riigipoore:riigipoore/guestbook");
		    String fname = request.getParameter("fname");
		    String content = request.getParameter("content");
			String queryString = "SELECT * FROM entries where guestName LIKE \"%" + fname + "\"";
		    if (fname == "" || content == "") {
		    	out.println("<html><head></head><body>You are missing either a message or a name! Try again! Redirecting in 3 seconds...</body></html>");
		    }
		    else {
			    Statement stmt = c.createStatement();
			    int success = 2;
			    ResultSet rs = stmt.executeQuery(queryString);
			      
			    while (rs.next()) {
			    	String coffeeName = rs.getString("guestName");
			        String supplierID = rs.getString("content");
			        int price = rs.getInt("entryID");
			        out.println(coffeeName + "\t" + supplierID +
			                   "\t" + price);
			    	}
		    	}
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        if (c != null) 
		          try {
		            c.close();
		            } catch (SQLException ignore) {
		         }
		      }
		
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	  throws IOException {
	  }
}

