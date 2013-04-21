package evalimised;
//
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.nio.CharBuffer;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.google.appengine.api.channel.ChannelMessage;
//import com.google.appengine.api.channel.ChannelService;
//import com.google.appengine.api.channel.ChannelServiceFactory;
//import com.google.gson.Gson;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonElement;
//import com.google.gson.reflect.TypeToken;
//import java.sql.PreparedStatement;
//
//public class Server extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//    public Server() {
//        
//    }
//	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		ChannelService channelService = ChannelServiceFactory.getChannelService();
//
//		// The channelKey can be generated in any way that you want, as long as it remains
//		// unique to the user.
//		String channelKey = "xyz";
//		String token = channelService.createChannel(channelKey);
//	    FileReader reader = new FileReader("isikute_statistika.html");
//	    CharBuffer buffer = CharBuffer.allocate(16384);
//	    reader.read(buffer);
//	    String index = new String(buffer.array());
//		index.replaceAll("\\{\\{ token \\}\\}", token);
//		// This is what actually sends the message.
//		channelService.sendMessage(new ChannelMessage(channelKey, "Hello World!"));
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	}
//}



import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.CharBuffer;

import javax.jdo.PersistenceManager;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Server extends HttpServlet {
  private String getGameUriWithGameParam(HttpServletRequest req,
      String gameKey) throws IOException {    
    try {
      String query;
      if (gameKey == null) {
        query = "";
      } else {
        query = "g=" + gameKey;
      }
      URI thisUri = new URI(req.getRequestURL().toString());
      URI uriWithOptionalGameParam = new URI(thisUri.getScheme(),
          thisUri.getUserInfo(),
          thisUri.getHost(),
          thisUri.getPort(),
          thisUri.getPath(),
          query,
          "");
      return uriWithOptionalGameParam.toString();
    } catch (URISyntaxException e) {
      throw new IOException(e.getMessage());
    }
    
  }
  
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {    
    final UserService userService = UserServiceFactory.getUserService();
    final URI uriWithOptionalGameParam; 
    String gameKey = req.getParameter("g");
    if (userService.getCurrentUser() == null) {
      String thisURL = req.getRequestURL().toString();      
      resp.getWriter().println("<p>Please <a href=\"" +
          userService.createLoginURL(getGameUriWithGameParam(req, gameKey)) + "\">sign in</a>.</p>");
      
      return;
    }    
    ChannelService channelService = ChannelServiceFactory.getChannelService();
    String token = channelService.createChannel("xyz");

    FileReader reader = new FileReader("index-template.html");
    CharBuffer buffer = CharBuffer.allocate(16384);
    reader.read(buffer);
    String index = new String(buffer.array());
    resp.getWriter().write(token);
    index = index.replaceAll("\\{\\{ token \\}\\}", token);
    
    resp.setContentType("text/html");
    resp.getWriter().write(index);
    reader.close();
  }
}