package it.uniroma3.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/processaDati")
public class LeggiParametri extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Studente studente;

		try {   
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			SimpleDateFormat sdfparser = new SimpleDateFormat("dd-MM-yyyy");
//			String date = sdfparser.format(sdf.parse(request.getParameter("dataNascita")));
			Date date = sdf.parse(request.getParameter("dataNascita"));

			// gestione della RICHIESTA

			// leggo i parametri  
			studente = new Studente(
					request.getParameter("nome").toUpperCase(), 
					request.getParameter("cognome").toUpperCase(),
					Integer.parseInt(request.getParameter("matricola")),
					date); 
			
			request.setAttribute("studente", studente);

			ServletContext application  = getServletContext();
			RequestDispatcher rd = application.getRequestDispatcher("/mostraDati");
			rd.forward(request, response);
			return; 

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
