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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.uniroma3.triathlon.model.Atleta;
import it.uniroma3.triathlon.service.AtletaService;
import it.uniroma3.triathlon.service.SocietaService;
import it.uniroma3.triathlon.util.Calcolatore;

@Controller
@SessionAttributes("current_username")
public class AtletaController {

	@Autowired
	private AtletaService atletaService;
	@Autowired
	private SocietaService societaService;
	
	@GetMapping("/atleta/{id}")
	public String mostraAtleta(@PathVariable("id") Long id, Model model){
		Atleta atleta = atletaService.findOne(id);
		model.addAttribute("navAtleti", "active");
		model.addAttribute("atleta", atleta);
		return "view_atleta";
	}

	@GetMapping("/utente/newAtleta")
	public String mostraForm(Atleta atleta, 
			@SessionAttribute(name="current_username") String username,
			RedirectAttributes redir,
			Model model) {	
		String nextPage = "form";

		// Redirect se l'utente ha già registrato un atleta
		if (atletaService.hasUtenteGestore(username)) {
			redir.addFlashAttribute("erroreNewAtleta", "Hai già registrato il tuo profilo atleta, non puoi registrarne altri!");
			nextPage = "redirect:/utente/" + username;
		}

		model.addAttribute("navAtleti", "active");
		model.addAttribute("formAtleta",true);
		model.addAttribute("elencoSocieta", societaService.groupedByRegione(societaService.findAll()));
		return nextPage;
	}

	@PostMapping("/utente/newAtleta")
	public String checkAtletaInfo(@Valid @ModelAttribute Atleta atleta, 
			@RequestParam(defaultValue="") Long societaID, 
			@SessionAttribute(name="current_username") String username,
			BindingResult bindingResult, Model model) {
		String nextPage = "form";
		model.addAttribute("navAtleti", "active");
		model.addAttribute("formAtleta",true);

		if (!bindingResult.hasErrors()) {
			// controllo età minima
			if (Calcolatore.convalidaEtaAtleta(atleta.getDataNascita())) {
				if (!atletaService.isDuplicate(atleta)) {
					/* Attributi manipolati */
					atleta.setNome(atleta.getNome().toUpperCase());
					atleta.setCognome(atleta.getCognome().toUpperCase());

					/* Attributi derivati */
					atleta.setEta(Calcolatore.calcolaEta(atleta.getDataNascita()));
					atleta.setCategoria(Calcolatore.calcolaCategoria(atleta.getEta(), atleta.getSesso()));

					/* Relazioni */
					if (societaID!=null)
						atleta.setSocieta(societaService.findOne(societaID));
					else {
						model.addAttribute("societaNonSelezionata", true);
					}

					// Service
					atletaService.save(atleta);
					atletaService.setUtenteGestore(username, atleta);

					model.addAttribute(atleta);
					model.addAttribute("successo", "Atleta registrato correttamente");
				} else {
					model.addAttribute("errore", "L'atleta è già presente nel sistema");
				}
			} else {
				model.addAttribute("errore", "L'atleta deve avere almeno 14 anni");
			}	
			model.addAttribute("elencoSocieta", societaService.groupedByRegione(societaService.findAll()));
		}
		return nextPage;
	}

	@PostMapping("/admin/deleteAtleta/{id}")
	public String eliminaAtleta(@PathVariable("id") Long id, 
			RedirectAttributes redir, Model model){
		atletaService.deleteById(id);
		redir.addFlashAttribute("successo", "L'atleta è stato rimosso dal sistema");
		return "redirect:/";
	}
}
