package it.uniroma3.triathlon.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.triathlon.model.RuoloUtente;
import it.uniroma3.triathlon.model.Utente;
import it.uniroma3.triathlon.service.RuoloUtenteService;
import it.uniroma3.triathlon.service.UtenteService;

@Controller
public class UtenteController {
	private static final String ADMIN = "RUOLO_ADMIN";
	private static final String UTENTE = "RUOLO_UTENTE";
	
	@Autowired
	private UtenteService utenteService;
	@Autowired
	private RuoloUtenteService ruoloUtenteService;
	
	@GetMapping("/registrazione")
	public String registrazione(Utente utente, Model model){
		model.addAttribute("atleti", true);
		model.addAttribute("formUtente",  true);
		return "form";
	}
	
	@PostMapping("/registrazione")
	public String checkUtenteInfo(@Valid @ModelAttribute Utente utente, BindingResult bindingResult, Model model){
		String nextPage = "form";
		model.addAttribute("atleti", true);
		
		if (!bindingResult.hasErrors()) {
			if (!utenteService.isDuplicate(utente.getUsername())) {
				utenteService.add(utente);
				
				RuoloUtente ruolo = new RuoloUtente(utente, UTENTE);
				ruoloUtenteService.add(ruolo);
				model.addAttribute(utente);
				model.addAttribute("successo", "Utente registrato correttamente");
			} else {
				model.addAttribute("errore", "Un utente con questo username è già presente nel sistema.");
			}
		}
		return nextPage;
	}
}
