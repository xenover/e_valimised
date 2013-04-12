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

public class DeleteVote extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteVote() {
        
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			Connection connection = CreateConnection.getConnection();
			try{
				String sql = "delete from vote where voter_id=?";
				PreparedStatement ps = connection.prepareStatement(sql);
				int voter_id = Integer.parseInt(request.getParameter("voter_id"));
				ps.setInt(1, voter_id);
				ps.executeUpdate();
			}
			catch (Exception e){
				pw.println(e);
			}
	}
}