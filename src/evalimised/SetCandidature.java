package evalimised;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

public class SetCandidature extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SetCandidature() {
        
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id    =  Integer.parseInt(request.getParameter("id"));
		String family = request.getParameter("family");
		String education = request.getParameter("education");
		String job = request.getParameter("job");
		String institution = request.getParameter("institution");
		String party = request.getParameter("party");
		int user_id = Integer.parseInt((String)request.getParameter("user_id"));
        int lab_id =  Integer.parseInt((String)request.getParameter("lab_id"));
		SetCandidature.setCandidates(id, family, education, job, institution, party);
	}
	
	private static void setCandidates(int id, String family, String education, String job, String institution, String party){
	    Connection connection = null;
	    connection = CreateConnection.getConnection();
	    String idquery = "";
	    ArrayList<Candidates> candidateList = new ArrayList<Candidates>();
	    try {
	    	Statement statement = connection.createStatement();
	    	String select = "INSERT INTO";
	    	ResultSet rs = statement.executeQuery(select);
	    	while(rs.next()) {	
	        	Candidates candidate=new Candidates();
	        	candidate.setId(rs.getInt("candidate_id"));
	        	candidate.setFirst_name(rs.getString("first_name"));
	        	candidate.setLast_name(rs.getString("last_name"));
	        	candidate.setParty(rs.getString("party_name"));
	        	candidate.setRegion(rs.getString("region"));
	        	candidateList.add(candidate);
	        }
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
	}
}
