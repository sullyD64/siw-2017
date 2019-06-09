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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.uniroma3.triathlon.model.Societa;
import it.uniroma3.triathlon.service.AtletaService;
import it.uniroma3.triathlon.service.SocietaService;
import it.uniroma3.triathlon.util.Calcolatore;

@Controller
public class SocietaController {
	
	@Autowired
	private AtletaService atletaService;
	@Autowired
	private SocietaService societaService;

	@GetMapping("/listSocieta")
	public String mostraListaSocieta(Model model) {
		model.addAttribute("navSocieta", "active");
		model.addAttribute("elencoSocieta", societaService.groupedByRegione(societaService.findAll()));
		return "view_societa";
	}

	@GetMapping("/listSocieta/{id}")
	public String mostraSocieta(@PathVariable("id") Long id, Model model){
		Societa societa = societaService.findOne(id);
		model.addAttribute("navSocieta", "active");
		model.addAttribute("elencoSocieta", societaService.groupedByRegione(societaService.findAll()));
		model.addAttribute("societaPanel", true);
		model.addAttribute("societa", societa);
		return "view_societa";
	}
	
	@GetMapping("/utente/newSocieta")
	public String mostraForm(Societa societa, 
			@SessionAttribute(name="current_username") String username,
			RedirectAttributes redir,
			Model model) {
		String nextPage = "form";

		// Redirect se l'utente non ha un profilo atleta registrato
		if (!societaService.utenteIsGestoreAtleta(username)) {
			redir.addFlashAttribute("erroreNewSocieta", "Puoi registrare una società solo dopo aver registrato il tuo profilo atleta!");
			nextPage = "redirect:/utente/" + username;
		}

		// Redirect se l'utente ha già registrato una società
		if (societaService.utenteIsGestoreSocieta(username)) {
			redir.addFlashAttribute("erroreNewSocieta", "Hai già registrato una società, non puoi registrarne altre!");
			nextPage = "redirect:/utente/" + username;
		}

		model.addAttribute("navSocieta", "active");
		model.addAttribute("formSocieta",true);
		return nextPage;
	}

	@PostMapping("/utente/newSocieta")
	public String checkSocietaInfo(@Valid @ModelAttribute Societa societa, 
			@SessionAttribute(name="current_username") String username,
			BindingResult bindingResult, Model model) {
		String nextPage = "form";
		model.addAttribute("navSocieta", "active");
		model.addAttribute("formSocieta",true);

		if (!bindingResult.hasErrors()) {
			// controllo data di fondazione non successiva ad oggi
			if (Calcolatore.convalidaDataSocieta(societa.getDataFondazione())) {
				if (!societaService.isDuplicate(societa)) {
					/*Attributi manipolati*/
					societa.setNome(societa.getNome().toUpperCase());

					// Service
					societaService.setAtletaPresidente(username, societa);
					societaService.setUtenteGestore(username, societa);
					societaService.save(societa);
					atletaService.setIscrittoASocieta(username, societa);

					model.addAttribute(societa);
					model.addAttribute("successo", "Società registrata correttamente. Da ora sei il presidente della società e ne risulti iscritto in quanto atleta.");
				} else {
					model.addAttribute("errore", "La società è già presente nel sistema");
				}
			} else {
				model.addAttribute("errore", "Inserire una data di fondazione valida");
			}
		}
		return nextPage;
	}
	
	@PostMapping("/utente/addAtletaASocieta/{id}")
	public String aggiungiAtletaASocieta(@PathVariable("id") Long id,
			@SessionAttribute("current_username") String username,
			@RequestParam(defaultValue="") Long societaID, 
			RedirectAttributes redir, Model model) {
		atletaService.setIscrittoASocieta(username, societaService.findOne(societaID));
		
		redir.addFlashAttribute("successo", "L'atleta ora è iscritto ad una società e può partecipare alle gare");
		return "redirect:/utente/" + username;
	}

	@PostMapping("/admin/deleteSocieta/{id}")
	public String eliminaSocieta(@PathVariable("id") Long id, 
			RedirectAttributes redir, Model model){
		societaService.deleteById(id);
		redir.addFlashAttribute("successo", "La società è stata rimossa dal sistema");
		return "redirect:/listSocieta";
	}
}
