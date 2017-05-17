package it.uniroma3.validator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

public class ProductValidator {
	
	public boolean validate(HttpServletRequest request) {
		boolean tuttoOk = true;
		
		String nomeIns = request.getParameter("nome");
		String descIns = request.getParameter("descrizione");
		String prezIns = request.getParameter("prezzo");
		String dataIns = request.getParameter("dataScadenza");
		
		if (nomeIns == null || nomeIns.equals("")){
			request.setAttribute("errNome", "Campo obbligatorio");
			tuttoOk = false;
		}
		if (descIns == null || descIns.equals("")){
			request.setAttribute("errDesc", "Campo obbligatorio");
			tuttoOk = false;
		}
		if (prezIns == null || prezIns.equals("")){
			request.setAttribute("errPrez", "Campo obbligatorio");
			tuttoOk = false;
		}
		if (dataIns == null || dataIns.equals("")){
			request.setAttribute("errData", "Campo obbligatorio");
			tuttoOk = false;
		}
		
		try {
			Float.parseFloat((prezIns));
		} catch (NumberFormatException e) {
			request.setAttribute("errPrez", "Deve essere un numero");
			tuttoOk = false;
		}
		
		try {
			DateFormat df = new  SimpleDateFormat("dd/mm/yyyy");
			df.parse(dataIns);
		} catch (ParseException e) {
			request.setAttribute("errData", "Rispettare il formato!");
			tuttoOk = false;
		}
		
		return tuttoOk;
	}
}
