package evalimised;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Search extends HttpServlet {
@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	
		PrintWriter out = response.getWriter();
		out.println("Eesimi: " + request.getParameterValues("eesnimi")[0] + "\n");
		out.println("Perenimi: " + request.getParameterValues("perenimi")[0] + "\n");
		out.println("Kandidaadi number: " + request.getParameterValues("kandidaadiNr")[0] + "\n");
		out.println("Erakond: " + request.getParameterValues("erakond")[0] + "\n");
		out.println("Regioon: " + request.getParameterValues("regioon")[0] + "\n");
		
	}
}

