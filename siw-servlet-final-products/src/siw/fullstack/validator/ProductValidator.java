package siw.fullstack.validator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import siw.fullstack.model.Product;

public class ProductValidator {
	
	public boolean validate(HttpServletRequest request) {
		boolean tuttoOk = true;
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String price = request.getParameter("price");
		String expirationDate = request.getParameter("expirationDate");
		Product product = (Product)request.getAttribute("product");
		
		if(name == null || name.equals("")) {
			request.setAttribute("errName", "Campo obbligatorio");
			tuttoOk = false;
		} else
			product.setName(name);
		
		if(description == null || description.equals("")) {
			request.setAttribute("errDesc", "Campo obbligatorio");
			tuttoOk = false;
		} else
			product.setDescription(description);
		
		if(price == null || price.equals("")) {
			request.setAttribute("errPrice", "Campo obbligatorio");
			tuttoOk = false;
		} else {
			try {
				product.setPrice(Float.parseFloat(price));
			} catch (NumberFormatException e) {
				request.setAttribute("errPrice", "Deve essere un numero!");
				tuttoOk = false;
			}
		}
		
		if(expirationDate == null || expirationDate.equals("")) {
			request.setAttribute("errDate", "Campo obbligatorio");
			tuttoOk = false;
		} else {
			try {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				product.setExpirationDate(df.parse(expirationDate));
				if(df.parse(expirationDate).compareTo(new Date()) <0) {
					request.setAttribute("errDate", "Deve essere successiva alla data di oggi!");
					tuttoOk = false;
				}
			} catch (ParseException e) {
				request.setAttribute("errDate", "Deve essere una data valida!");
				tuttoOk = false;
			}
		}
	
		return tuttoOk;
	}
}
