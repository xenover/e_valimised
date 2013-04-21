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
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.rdbms.AppEngineDriver;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;


public class SearchPerson extends HttpServlet {

    private static Connection connection = null;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id    =  request.getParameter("id");

    	connection = CreateConnection.getConnection();
    	int number = 0;
        try {
            Statement statement = connection.createStatement();
            String select = "select count(*) from person where id=" + id;
            ResultSet rs = statement.executeQuery(select);
            while(rs.next()) {	
            	number = rs.getInt("count(*)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
//		Gson gson = new Gson();
//		JsonElement element = gson.toJsonTree(candidate, new TypeToken<List<Candidates>>() {}.getType());
//		JsonArray jsonArray = element.getAsJsonArray();
//		response.setContentType("application/json");
		response.getWriter().print(number);
    }
}
