package it.uniroma3.triathlon.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import it.uniroma3.triathlon.model.Atleta;
import it.uniroma3.triathlon.model.RuoloUtente;
import it.uniroma3.triathlon.model.Utente;
import it.uniroma3.triathlon.service.RisultatoService;
import it.uniroma3.triathlon.service.RuoloUtenteService;
import it.uniroma3.triathlon.service.SocietaService;
import it.uniroma3.triathlon.service.UtenteService;

@Controller
@SessionAttributes("current_username")
public class UtenteController {

	@Autowired
	private UtenteService utenteService;
	@Autowired
	private RuoloUtenteService ruoloUtenteService;
	@Autowired
	private SocietaService societaService;
	@Autowired
	private RisultatoService risultatoService;
	
	@GetMapping("/utente/{username}")
	public String mostraUtente(@PathVariable("username") String user, 
			@SessionAttribute(name="current_username") String username, Model model){
		Utente utente = utenteService.findByUsername(username);
		
		if (utente.hasAtletaGestito()) {
			Atleta atleta = utente.getAtletaGestito();
			
			if (atleta.getSocieta()==null) {
				model.addAttribute("elencoSocieta", societaService.groupedByRegione(societaService.findAll()));
			} else if(!atleta.getRisultati().isEmpty()) {
				model.addAttribute("risultatiGareNonSvolte", risultatoService.getRisultatiGareNonSvolte(atleta.getRisultati()));
				model.addAttribute("risultatiGareSvolte", risultatoService.getRisultatiGareSvolte(atleta.getRisultati()));
			}
		}
		
		model.addAttribute("navAccesso", "active");
		model.addAttribute(utente);
		return "area_riservata";
	}
	
	@RequestMapping("/accedi")
	public String accedi(@Valid @ModelAttribute Utente utente, Model model) {
		model.addAttribute("navAccesso", "active");
		return "accesso";
	}
	
	@PostMapping("/registrazione")
	public String registraUtente(@Valid @ModelAttribute Utente utente, BindingResult bindingResult, Model model){
		String nextPage = "accesso";
		model.addAttribute("navAccesso", "active");
		
		if (!bindingResult.hasErrors()) {
			if (!utenteService.alreadyExists(utente.getUsername())) {
				utenteService.save(utente);

				RuoloUtente ruolo = new RuoloUtente(utente, "ROLE_UTENTE");
				ruoloUtenteService.add(ruolo);
				
				model.addAttribute("utente", new Utente());
				model.addAttribute("successo", "Utente registrato correttamente");
			} else {
				model.addAttribute("errore", "Un utente con questo username è già presente nel sistema.");
			}
		}
		return nextPage;
	}
}
