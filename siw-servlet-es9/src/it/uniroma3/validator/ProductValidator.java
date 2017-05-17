package it.uniroma3.validator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import it.uniroma3.model.Prodotto;

public class ProductValidator {
	
	public boolean validate(HttpServletRequest request) {
		
		boolean tuttoOk = true;
		String nomeIns = request.getParameter("nome");
		String descIns = request.getParameter("descrizione");
		String prezIns = request.getParameter("prezzo");
		String dataIns = request.getParameter("dataScadenza");
		
		Prodotto prodotto = (Prodotto)request.getAttribute("prodotto");
		
		if (nomeIns == null || nomeIns.equals("")){
			request.setAttribute("errNome", "Campo obbligatorio");
			tuttoOk = false;
		} else
			prodotto.setNome(nomeIns);
		
		if (descIns == null || descIns.equals("")){
			request.setAttribute("errDesc", "Campo obbligatorio");
			tuttoOk = false;
		} else
			prodotto.setDesc(descIns);
		
		
		if (prezIns == null || prezIns.equals("")){
			request.setAttribute("errPrez", "Campo obbligatorio");
			tuttoOk = false;
		} else
			try {
				prodotto.setPrezzo(Float.parseFloat((prezIns)));
			} catch (NumberFormatException e) {
				request.setAttribute("errPrez", "Deve essere un numero");
				tuttoOk = false;
			}
		
		if (dataIns == null || dataIns.equals("")){
			request.setAttribute("errData", "Campo obbligatorio");
			tuttoOk = false;
		} else {	
			try {
				DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
				prodotto.setDataScadenza(df.parse(dataIns));
				if (df.parse(dataIns).compareTo(new Date()) < 0)
					request.setAttribute("errData", "Deve essere successiva alla data di oggi!");
			} catch (ParseException e) {
				request.setAttribute("errData", "Rispettare il formato!");
				tuttoOk = false;
			}
		}
		
	
		return tuttoOk;
	}
}
