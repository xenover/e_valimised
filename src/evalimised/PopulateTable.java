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

public class PopulateTable extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PopulateTable() {
        
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Candidates> candidate=new ArrayList<Candidates>();
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String id    =  request.getParameter("id");
		String party = request.getParameter("party");
		String region= request.getParameter("region");
		candidate=SearchCandidates.getCandidates(fname, lname, id, party, region);
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(candidate, new TypeToken<List<Candidates>>() {}.getType());
		JsonArray jsonArray = element.getAsJsonArray();
		response.setContentType("application/json");
		response.getWriter().print(jsonArray);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
