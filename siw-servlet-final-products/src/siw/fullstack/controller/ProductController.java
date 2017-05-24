package siw.fullstack.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import siw.fullstack.model.Product;
import siw.fullstack.service.ProductService;
import siw.fullstack.validator.ProductValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@WebServlet("/product")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage;
		
		if (request.getParameter("command") != null) {
			Long id = Long.parseLong(request.getParameter("id"));
			ProductService service = new ProductService();
			Product product = service.getOneProduct(id);
			service.deleteProduct(product);
			request.setAttribute("products", service.getAllProducts());
			nextPage = "/prodotti.jsp";
			
		} else if (request.getParameter("deleteAll") != null) {
			ProductService service = new ProductService();
			service.deleteAllProducts();
			request.setAttribute("products", service.getAllProducts());
			nextPage = "/prodotti.jsp";
				
		} else {
		
			Product product = new Product();
			request.setAttribute("product", product);
			
			ProductValidator validator = new ProductValidator();
			
			if (validator.validate(request)) {
				ProductService service = new ProductService();
				service.insertProduct(product);
				nextPage = "/prodotto.jsp";
			} else
				nextPage = "/index.jsp";
		}
			
		ServletContext application = getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = "/prodotti.jsp";
		ProductService service = new ProductService();
		
		if (request.getParameter("id")!=null) {
			Long id = Long.parseLong(request.getParameter("id"));
			Product product = service.getOneProduct(id);
			request.setAttribute("product", product);
			nextPage = "/prodotto.jsp";
		} else {
			List<Product> products = service.getAllProducts();
			request.setAttribute("products", products);	
		}
		
		ServletContext application = getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}
}
