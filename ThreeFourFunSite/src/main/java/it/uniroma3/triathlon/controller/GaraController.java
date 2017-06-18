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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.uniroma3.triathlon.model.Gara;
import it.uniroma3.triathlon.service.GaraService;
import it.uniroma3.triathlon.util.Calcolatore;

@Controller
@SessionAttributes("current_username")
public class GaraController {

	@Autowired
	private GaraService garaService;
	
	@GetMapping("/listGare")
	public String mostraListaSocieta(Model model) {
		model.addAttribute("navGare", "active");
		model.addAttribute("elencoGare", garaService.getSortedByDate());
		return "view_gare";
	}
	
	@GetMapping("/admin/newGara")
	public String mostraForm(Gara gara, Model model) {
		model.addAttribute("navGare", "active");
		model.addAttribute("formGara", true);
		return "form";
	}

	@PostMapping("/admin/newGara")
	public String checkGaraInfo(@Valid @ModelAttribute Gara gara, BindingResult bindingResult, Model model) {
		String nextPage = "form";
		model.addAttribute("navGare", "active");
		model.addAttribute("formGara", true);
		
		System.out.println(bindingResult.toString());

		if (!bindingResult.hasErrors()) {
			// controllo data futura
			if (Calcolatore.convalidaDataGara(gara.getDataSvolgimento())) {
				// controllo gara esistente nello stesso giorno
				if (!garaService.isDuplicateSameDate(gara)) {
					/*Attributi manipolati*/
					gara.setNomeLuogo(gara.getNomeLuogo().toUpperCase());

					garaService.add(gara);

					model.addAttribute(gara);
					model.addAttribute("successo", "Gara registrata correttamente");
				} else {
					model.addAttribute("errore", "È già presente una gara in programma per quella data");
				}
			} else {
				model.addAttribute("errore", "Inserire una data di svolgimento valida (successiva ad oggi)");
			}
		}
		return nextPage;
	}
	
	@PostMapping("/admin/deleteGara/{id}")
	public String eliminaGara(@PathVariable("id") Long id, 
			RedirectAttributes redir, Model model){
		garaService.deleteById(id);
		redir.addFlashAttribute("successo", "La gara è stata rimossa dal sistema");
		return "redirect:/listGare";
	}
}
