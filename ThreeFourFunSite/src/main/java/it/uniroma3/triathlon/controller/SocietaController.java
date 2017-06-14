package it.uniroma3.triathlon.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.triathlon.model.Societa;
import it.uniroma3.triathlon.service.SocietaService;
import it.uniroma3.triathlon.util.Calcolatore;

@Controller
public class SocietaController {

	@Autowired
	private SocietaService societaService;

	@GetMapping("/societa")
	public String showForm(Societa societa, Model model) {
		model.addAttribute("formSocieta",true);
		return "form";
	}

	@PostMapping("/societa")
	public String checkSocietaInfo(@Valid @ModelAttribute Societa societa, BindingResult bindingResult, Model model) {
		String nextPage = "form";
		model.addAttribute("formSocieta",true);

		if (!bindingResult.hasErrors()) {
			if (Calcolatore.convalidaEtaSocieta(societa.getDataFondazione())) {
				if (societaService.add(societa)) {
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
