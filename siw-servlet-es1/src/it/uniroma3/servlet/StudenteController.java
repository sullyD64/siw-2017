package it.uniroma3.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/studente")
public class StudenteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Studente studente;
		
		try {   
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfparser = new SimpleDateFormat("dd-MM-yyyy");
			String date = sdfparser.format(sdf.parse(request.getParameter("dataNascita")));
		
		// gestione della RICHIESTA
		
		// leggo i parametri  
		    studente = new Studente(
					request.getParameter("nome"), 
					request.getParameter("cognome"),
					Integer.parseInt(request.getParameter("matricola")),
					date); 
		
			// leggo (alcune) intestazioni http della richiesta
			String address = (String)request.getRemoteAddr();
			String host = (String)request.getRemoteHost();
			String userAgent = request.getHeader("User-Agent");
			
			
			// preparo il tipo (HTML)
			response.setContentType("text/html");
			// preparo un oggetto su cui scrivere la risposta
			PrintWriter out = response.getWriter();
			
			// scrivo il corpo
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta charset = \"ISO-8859-1\" />");
			out.println("<link rel=\"stylesheet\" href=\"prova.css\" />");
			out.println("<title>Mostra Parametri Studente</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<hr>");
			out.println("<h1>/operationSuccessful</h1>");
			out.println("<ul>");
			out.println("<li>Nome: <b>"+ studente.getNome().toUpperCase() +"</b></li>");
			out.println("<li>Cognome: <b>"+ studente.getCognome().toUpperCase() +"</b></li>");
			out.println("<li>Matricola: <b>"+ studente.getMatricola() +"</b></li>");
			out.println("<li>Data di nascita: <b>"+ studente.getDataNascita() +"</b></li>");
			out.println("</ul>");
			out.println("<hr>");
			out.println("<hr>");
			out.println("<h2>/netInfo</h1>");
			out.println("<br />IP: <b>"+address+"</b>");
			out.println("<br />Host: <b>"+host+"</b>");
			out.println("<br />User Agent: <b>"+userAgent+"</b>");
			out.println("</body>\n</html>");
			
		 } catch (ParseException e) {
		        e.printStackTrace();
		 }
	}
}
