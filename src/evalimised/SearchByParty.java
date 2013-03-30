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


public class SearchByParty {

    private static Connection connection = null;
    
    public static ArrayList<Party> getParties(String region) {
    	connection = CreateConnection.getConnection();
        ArrayList<Party> partyList = new ArrayList<Party>();
        try {
            Statement statement = connection.createStatement();
            String select = "select * from partyVotesRegion";
            ResultSet rs = statement.executeQuery(select);
            while(rs.next()) {	
            	Party party=new Party();
            	party.setVotes(rs.getInt("votes"));
            	party.setName(rs.getString("party_name"));
            	partyList.add(party);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return partyList;
    }
}

