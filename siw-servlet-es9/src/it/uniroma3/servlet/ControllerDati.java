package it.uniroma3.servlet;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.uniroma3.model.Prodotto;

@WebServlet("/controllerDati")
public class ControllerDati extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Prodotto> prodotti = new ArrayList<>();
		Prodotto prodotto1 = new Prodotto();
		prodotto1.setNome("penna");
		prodotto1.setPrezzo(3F);
		prodotti.add(prodotto1);
		
		Prodotto prodotto2 = new Prodotto();
		prodotto2.setNome("gomma");
		prodotto2.setPrezzo(1F);
		prodotti.add(prodotto2);
		
		request.setAttribute("prodotti",prodotti);
		
		ServletContext application  = getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/prodotti.jsp");
		rd.forward(request, response);
		return; 
		
	}
}
