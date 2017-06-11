package it.uniroma3.triathlon.controller;

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
	
	@PostMapping("/atleta")
	public String checkAtletaInfo(@Valid @ModelAttribute Atleta atleta, BindingResult bindingResult, Model model) {
		
	    if (bindingResult.hasErrors()) {
            return "formAtleta";
        } else {
        	model.addAttribute(atleta);
            atletaService.add(atleta); 
        }
        return "results";
	}
}
