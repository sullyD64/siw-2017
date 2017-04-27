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
import javax.servlet.http.HttpSession;

@WebServlet("/processa")
public class ControllerDati extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// gestione della RICHIESTA
		Studente studente;

		try {  
			Integer matricola = Integer.parseInt(request.getParameter("matricola"));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(request.getParameter("dataNascita"));

			// leggo e manipolo i parametri  
			studente = new Studente(
					request.getParameter("nome").toUpperCase(), 
					request.getParameter("cognome").toUpperCase(),
					matricola, date); 

			// metto i valori nella sessione
			HttpSession session = request.getSession();
			session.setAttribute("studente",studente);
			
			// inoltro 
			ServletContext application  = getServletContext();
			RequestDispatcher rd = application.getRequestDispatcher("/conferma");
			rd.forward(request, response);
			return; 

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
