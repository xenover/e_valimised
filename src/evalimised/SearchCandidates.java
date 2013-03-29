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
    
    public static ArrayList<Candidates> getCandidates(String fname) {
    	connection = SearchCandidates.getConnection();
        ArrayList<Candidates> candidateList = new ArrayList<Candidates>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from candidateInfo WHERE first_name LIKE \"" + fname + "%\"");
        
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
