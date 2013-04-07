package evalimised;

import java.io.IOException;
import java.io.PrintWriter;
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

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.google.appengine.api.rdbms.AppEngineDriver;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class FirstLastName extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Connection con = null;

    public FirstLastName() {
        
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<List<String>> info = FirstLastName.getFirstLastNames();
		JSONObject returnJson = new JSONObject();
		try {
			returnJson.put("first_names", info.get(0).toArray());
			returnJson.put("last_names", info.get(1).toArray());
		} catch (JSONException je) {
			je.printStackTrace();
		}

		response.getWriter().print(returnJson.toString());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected static ArrayList<List<String>> getFirstLastNames() {
		con = CreateConnection.getConnection();
		String qry = "SELECT first_name, last_name FROM candidateInfo";
		ArrayList<List<String>> firstLastNames = new ArrayList<List<String>>();
		List<String> firstNames = new ArrayList<String>();
		List<String> lastNames = new ArrayList<String>();
		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(qry);
			while(rs.next()) {
				firstNames.add(rs.getString("first_name"));
				lastNames.add(rs.getString("last_name"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		firstLastNames.add(firstNames);
		firstLastNames.add(lastNames);
		
		return firstLastNames;
	}

}
