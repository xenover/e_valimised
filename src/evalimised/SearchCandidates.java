package evalimised;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.google.appengine.api.rdbms.AppEngineDriver;


public class SearchCandidates {

    private static Connection connection = null;
    
    public static ArrayList<Candidates> getCandidates(String fname, String lname, String id, String party, String region) {
    	connection = CreateConnection.getConnection();
    	String idquery = "";
    	if(id.length() > 0){
    		int candidate_id = Integer.parseInt(id);
    		idquery = "AND candidate_id LIKE '" + candidate_id + "'";
    	}
        ArrayList<Candidates> candidateList = new ArrayList<Candidates>();
        try {
            Statement statement = connection.createStatement();
            String select = "select * from candidateInfo WHERE first_name LIKE \"" + fname + "%\" AND last_name LIKE \"" + lname + "%\" AND region LIKE \"" + region + "%\" AND party_name LIKE \"" + party + "%\"" + idquery;
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

        return candidateList;
    }
}
