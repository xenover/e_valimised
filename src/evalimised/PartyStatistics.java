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

public class PartyStatistics extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PartyStatistics() {
        
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter test = response.getWriter();
		ArrayList<Party> party=new ArrayList<Party>();
//		String fname = request.getParameter("fname");
		party=SearchByParty.getParties("AA");
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(party, new TypeToken<List<Candidates>>() {}.getType());
		JsonArray jsonArray = element.getAsJsonArray();
		response.setContentType("application/json");
		response.getWriter().print(jsonArray);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
