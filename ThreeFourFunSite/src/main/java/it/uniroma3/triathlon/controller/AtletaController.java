package it.uniroma3.triathlon.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.triathlon.service.AtletaService;
import it.uniroma3.triathlon.model.Atleta;

@Controller
public class AtletaController {
	
	 @Autowired
	 private AtletaService atletaService; 

	@GetMapping("/atleta")
	public String showForm(Atleta atleta) {
        return "formAtleta";
    }
	
	@SuppressWarnings("deprecation")
	@PostMapping("/atleta")
	public String checkAtletaInfo(@Valid @ModelAttribute Atleta atleta, BindingResult bindingResult, Model model) {
				
	    if (bindingResult.hasErrors()) {
            return "formAtleta";
        } else {
	    	System.out.print(bindingResult.getFieldValue("nome") + ",");
	    	System.out.print(bindingResult.getFieldValue("cognome") + ",");
	    	System.out.print(bindingResult.getFieldValue("dataNascita") + ",");
	    	System.out.print(bindingResult.getFieldValue("sesso") + ",");
	    	System.out.println("");
        	
        	String dins = (String) bindingResult.getFieldValue("dataNascita");
	    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    	Date dataInserita;
			try {
				dataInserita = df.parse(dins);
		
        	LocalDate today = LocalDate.now();
        	Integer eta = today.getYear() - (dataInserita.getYear()+1900);
        	
        	atleta.setEta(eta);
        	
        	model.addAttribute("eta", eta);
        	
        	model.addAttribute(atleta);
            atletaService.add(atleta); 
            
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
        
        }
        return "formAtleta";
	}
}
