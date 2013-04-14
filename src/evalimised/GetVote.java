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

import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

public class GetVote extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GetVote() {
        
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id    =  Integer.parseInt(request.getParameter("id"));
		Connection connection = CreateConnection.getConnection();
        ArrayList<Candidates> vote = new ArrayList<Candidates>();
        String idquery = "";
        try {
            Statement statement = connection.createStatement();
            String select = "select * from voteInfo WHERE voter_id='" + id + "'";
            ResultSet rs = statement.executeQuery(select);
            while(rs.next()) {	
            	Candidates candidate=new Candidates();
            	candidate.setFirst_name(rs.getString("first_name"));
            	candidate.setLast_name(rs.getString("last_name"));
            	candidate.setParty(rs.getString("party_name"));
            	vote.add(candidate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(vote, new TypeToken<List<Candidates>>() {}.getType());
		JsonArray jsonArray = element.getAsJsonArray();
		response.setContentType("application/json");
		response.getWriter().print(jsonArray);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
}

//public class GetVote extends HttpServlet {
//	  @Override
//	  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//
//		    ChannelService channelService = ChannelServiceFactory.getChannelService();
//
//		    // The 'Game' object exposes a method which creates a unique string based on the game's key
//		    // and the user's id.
//		    String token = channelService.createChannel("xyz");
//
//		    // Index is the contents of our index.html resource, details omitted for brevity.
//		    index = index.replaceAll("\\{\\{ token \\}\\}", token);
//
//		    resp.setContentType("text/html");
//		    resp.getWriter().write(index);
//	  }
//}
