package it.uniroma3.triathlon.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.triathlon.model.Atleta;
import it.uniroma3.triathlon.service.AtletaService;
import it.uniroma3.triathlon.service.SocietaService;
import it.uniroma3.triathlon.util.Calcolatore;

@Controller
public class AtletaController {

	@Autowired
	private AtletaService atletaService;
	@Autowired
	private SocietaService societaService;

	@GetMapping("/atleta")
	public String showForm(Atleta atleta, Model model) {
		model.addAttribute("formAtleta",true);
		model.addAttribute("elencoSocieta", societaService.groupedByRegione(societaService.findAll()));
		return "form";
	}

	@PostMapping("/addAtleta")
	public String checkAtletaInfo(@Valid @ModelAttribute Atleta atleta, @RequestParam(defaultValue="") Long societaID, BindingResult bindingResult, Model model) {
		String nextPage = "form";
		model.addAttribute("formAtleta",true);

		if (!bindingResult.hasErrors()) {
			// controllo per binding società
			if (societaID!=null) {
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
						atleta.setSocieta(societaService.findOne(societaID));

						atletaService.add(atleta);

						model.addAttribute(atleta);
						model.addAttribute("successo", "Atleta registrato correttamente");
					} else {
						model.addAttribute("errore", "L'atleta è già presente nel sistema");
					}
				} else {
					model.addAttribute("errore", "L'atleta deve avere almeno 14 anni");
				}
			} else {
				model.addAttribute("errore", "Selezionare una società dall'elenco");
			}
			
			model.addAttribute("elencoSocieta", societaService.groupedByRegione(societaService.findAll()));
		}

		return nextPage;
	}
}
