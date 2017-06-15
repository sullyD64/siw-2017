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

import it.uniroma3.triathlon.model.Societa;
import it.uniroma3.triathlon.service.SocietaService;
import it.uniroma3.triathlon.util.Calcolatore;

@Controller
public class SocietaController {

	@Autowired
	private SocietaService societaService;

	@GetMapping("/addSocieta")
	public String mostraForm(Societa societa, Model model) {
		model.addAttribute("formSocieta",true);
		return "form";
	}
	
	@GetMapping("/listSocieta")
	public String mostraListaSocieta(Model model) {
		model.addAttribute("elencoSocieta", societaService.groupedByRegione(societaService.findAll()));
		return "societa";
	}
	
	@GetMapping("/listSocieta/{id}")
	public String mostraSocieta(@PathVariable("id") Long id, Model model){
		Societa societa = societaService.findOne(id);
		model.addAttribute("societa", societa);
		model.addAttribute("elencoSocieta", societaService.groupedByRegione(societaService.findAll()));
		model.addAttribute("societaPanel",true);
		return "societa";
	}
	
	@PostMapping("/addSocieta")
	public String checkSocietaInfo(@Valid @ModelAttribute Societa societa, BindingResult bindingResult, Model model) {
		String nextPage = "form";
		model.addAttribute("formSocieta",true);

		if (!bindingResult.hasErrors()) {
			if (Calcolatore.convalidaEtaSocieta(societa.getDataFondazione())) {
				if (!societaService.isDuplicate(societa)) {
					/*Attributi manipolati*/
					societa.setNome(societa.getNome().toUpperCase());
					
					societaService.add(societa);
					
					model.addAttribute(societa);
					model.addAttribute("successo", "Società registrata correttamente");
				} else
					model.addAttribute("errore", "La società è già presente nel sistema");
			} else
				model.addAttribute("errore", "Inserire una data di fondazione valida");
		}

		return nextPage;
	}
}
