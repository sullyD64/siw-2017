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

@WebServlet("/studenteController")
public class StudenteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/* Verifica la correttezza dei valori inseriti.
	 * Se i valori non sono corretti, aggiunge un attributo alla richiesta
	 * di nome parametroError, e come valore una stringa con un messaggio di errore.
	 * */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Studente studente = new Studente();

		try {  
			String nomeInserito = request.getParameter("nome");
			String cognomeInserito = request.getParameter("cognome");
			String matricolaInserita = request.getParameter("matricola");
			String dataInserita = request.getParameter("dataNascita");
			
			String nextPage;
			
			if (matricolaInserita != null && !matricolaInserita.equals("")) {
				Integer matricola = Integer.parseInt(matricolaInserita);
				
				if (dataInserita != null && !dataInserita.equals("")) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date date = sdf.parse(dataInserita);
					studente.setDataNascita(date);
				}
				
				studente.setNome(nomeInserito.toUpperCase());
				studente.setCognome(cognomeInserito.toUpperCase());
				studente.setMatricola(matricola);
				
				request.setAttribute("studente",studente);
				nextPage = "/studente.jsp";
			} else {
				if (matricolaInserita == null || matricolaInserita.equals("")) {
					request.setAttribute("errMatricola", "Campo obbligatorio!");
				}
				request.setAttribute("nome", nomeInserito);
				request.setAttribute("cognome", cognomeInserito);
				request.setAttribute("matricola", matricolaInserita);
				request.setAttribute("dataNascita", dataInserita);
				nextPage = "/index.jsp";
			}
			
			ServletContext application  = getServletContext();
			RequestDispatcher rd = application.getRequestDispatcher(nextPage);
			rd.forward(request, response);
			return; 

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
