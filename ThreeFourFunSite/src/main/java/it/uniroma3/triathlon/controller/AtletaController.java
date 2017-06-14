package it.uniroma3.triathlon.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.triathlon.model.Atleta;
import it.uniroma3.triathlon.service.AtletaService;
import it.uniroma3.triathlon.util.Calcolatore;

@Controller
public class AtletaController {

	@Autowired
	private AtletaService atletaService;

	@GetMapping("/atleta")
	public String showForm(Atleta atleta, Model model) {
		model.addAttribute("formAtleta",true);
		return "form";
	}

	@PostMapping("/atleta")
	public String checkAtletaInfo(@Valid @ModelAttribute Atleta atleta, BindingResult bindingResult, Model model) {
		String nextPage = "form";
		model.addAttribute("formAtleta",true);

		if (!bindingResult.hasErrors()) {
			if (Calcolatore.convalidaEtaAtleta(atleta.getDataNascita())) {
				if (atletaService.add(atleta)) {
					model.addAttribute(atleta);
					model.addAttribute("successo", "Atleta registrato correttamente");
				} else
					model.addAttribute("errore", "L'atleta è già presente nel sistema");
			} else
				model.addAttribute("errore", "L'atleta deve avere almeno 14 anni");
		}

		return nextPage;
	}
}
