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
import java.sql.PreparedStatement;

public class SetCandidature extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SetCandidature() {
        
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			Connection connection = CreateConnection.getConnection();
			try{
				String sql = "insert into candidate(id, family, education, job, institution, party) values(?, ?, ?, ?, ?, ?)";

				PreparedStatement ps = connection.prepareStatement(sql);
				int id = Integer.parseInt(request.getParameter("id"));
				String family = request.getParameter("family");
				String education = request.getParameter("education");
				String job = request.getParameter("job");
				String institution = request.getParameter("institution");
				String party = request.getParameter("party");
				// Partei ID leidmiseks
				Statement statement = connection.createStatement();
	            String select = "select * from party where nimi like \"" + party + "%\"";
	            ResultSet rs = statement.executeQuery(select);
	            int party_id = 0;
	            while(rs.next()) {	
	            	party_id = rs.getInt("id");
	            }
				ps.setInt(1, id);
				ps.setString(2, family);
				ps.setString(3, education);
				ps.setString(4, job);
				ps.setString(5, institution);
				ps.setInt(6, party_id);
				ps.executeUpdate();
			}
			catch (Exception e){
				pw.println(e);
			}
	}
}