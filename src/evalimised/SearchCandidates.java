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

    public static Connection getConnection() {

		Connection c = null;
		try {
			DriverManager.registerDriver(new AppEngineDriver());
		    c = DriverManager.getConnection("jdbc:google:rdbms://riigipoore:riigipoore/evalimised", "root", "");
		 } catch (SQLException e) {
		        e.printStackTrace();
		 }
		return c;

    }
    
    public static ArrayList<Candidates> getCandidates(String fname, String lname, String id, String party, String region) {
    	connection = SearchCandidates.getConnection();
        ArrayList<Candidates> candidateList = new ArrayList<Candidates>();
        try {
            Statement statement = connection.createStatement();
            boolean has_where = false;
            String select = "select * from candidateInfo ";
            if (fname != null && !fname.trim().isEmpty()) {
            	select += has_where? "WHERE " : "";
            	has_where = true
            	select +=  " first_name LIKE \"%" + fname + "%\" AND "; 
            }
            if (lname != null && !lname.trim().isEmpty()) {
            	select += has_where? "WHERE " : "";
            	has_where = true
            	select +=  " last_name LIKE \"%" + lname + "%\" AND "; 
            }
            if (id != null && !id.trim().isEmpty()) {
            	select += has_where? "WHERE " : "";
            	has_where = true
            	select +=  " id = " + id + " AND "; 
            }
            if (party != null && !party.trim().isEmpty()) {
            	select += has_where? "WHERE " : "";
            	has_where = true
            	select +=  " party LIKE \"%" + party + "%\" AND "; 
            }
            if (region != null && !region.trim().isEmpty()) {
            	select += has_where? "WHERE " : "";
            	has_where = true
            	select +=  " region LIKE \"%" + region + "%\" AND "; 
            }
            select = select.substring(0, select.length()-5); // " AND " eemaldamiseks
            
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
