package it.uniroma3.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.uniroma3.model.Prodotto;
import it.uniroma3.service.ProductService;
import it.uniroma3.validator.ProductValidator;

@WebServlet("/prodotto")
public class ControllerProdotto extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String nextPage;
			
			String nomeIns = request.getParameter("nome");
			String descIns = request.getParameter("descrizione");
			String prezIns = request.getParameter("prezzo");
			String dataIns = request.getParameter("dataScadenza");
			
			ProductValidator validator = new ProductValidator();
			
			if (validator.validate(request)) {
				Prodotto prodotto = new Prodotto();
				prodotto.setNome(nomeIns);
				prodotto.setDesc(descIns);
				prodotto.setPrezzo(Float.parseFloat(prezIns));
				
				DateFormat df = new  SimpleDateFormat("dd/mm/yyyy");
				try {
					prodotto.setDataScadenza(df.parse(dataIns));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				ProductService service = new ProductService();
				service.inserisciProdotto(prodotto);
		
				nextPage = "/prodotti.jsp";
			} else 
				nextPage = "/index.jsp";	
			
			ServletContext application  = getServletContext();
			RequestDispatcher rd = application.getRequestDispatcher(nextPage);
			rd.forward(request, response);
			return; 

	}
}
