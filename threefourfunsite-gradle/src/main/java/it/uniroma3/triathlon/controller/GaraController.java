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
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.uniroma3.triathlon.model.Atleta;
import it.uniroma3.triathlon.model.Gara;
import it.uniroma3.triathlon.model.Risultato;
import it.uniroma3.triathlon.model.Utente;
import it.uniroma3.triathlon.service.GaraService;
import it.uniroma3.triathlon.service.RisultatoService;
import it.uniroma3.triathlon.service.UtenteService;
import it.uniroma3.triathlon.util.Calcolatore;

@Controller
@SessionAttributes("current_username")
public class GaraController {

	@Autowired
	private GaraService garaService;
	@Autowired
	private RisultatoService risultatoService;
	@Autowired
	private UtenteService utenteService;

	@GetMapping("/listGare")
	public String mostraListaGare(Model model) {
		model.addAttribute("navGare", "active");
		model.addAttribute("gareAperte", garaService.getGareAperte());
		model.addAttribute("garePassate", garaService.getGarePassate());
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

		if (!bindingResult.hasErrors()) {
			// controllo data futura
			if (Calcolatore.convalidaDataGara(gara.getDataSvolgimento())) {
				// controllo gara esistente nello stesso giorno
				if (!garaService.isDuplicateSameDate(gara)) {
					/*Attributi manipolati*/
					gara.setNomeLuogo(gara.getNomeLuogo().toUpperCase());

					garaService.save(gara);

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

	@PostMapping("/utente/iscriviAGara/{id}")
	public String iscriviAtleta(@PathVariable("id") Long id,
			@SessionAttribute("current_username") String username,
			RedirectAttributes redir, Model model) {
		Utente utente = utenteService.findByUsername(username);

		// Errore: l'utente non ha un profilo atleta registrato
		if (!utente.hasAtletaGestito()) {
			redir.addFlashAttribute("errore", "Devi aver registrato un profilo atleta per partecipare alla gara.");
		} else {
			Atleta atleta = utente.getAtletaGestito();
			// Errore: l'atleta non è iscritto ad una società
			if (atleta.getSocieta()==null) {
				redir.addFlashAttribute("errore", "Devi essere iscritto ad una società per partecipare alla gara.");
			} else {
				Gara gara = garaService.findOne(id);
				Risultato risultato = new Risultato(atleta, gara);

				// Errore: l'atleta è già iscritto alla gara
				if (risultatoService.isAlreadyRegistered(risultato)) {
					redir.addFlashAttribute("errore", "Sei già iscritto alla gara");

				} else { 	// Scenario di successo
					risultatoService.save(risultato);
					redir.addFlashAttribute("successo", "Sei iscritto alla gara!");
				}
			}
		}
		return "redirect:/listGare";
	}

	@PostMapping("/admin/deleteGara/{id}")
	public String eliminaGara(@PathVariable("id") Long id, 
			RedirectAttributes redir, Model model){
		garaService.deleteById(id);
		redir.addFlashAttribute("successo", "La gara è stata rimossa dal sistema");
		return "redirect:/listGare";
	}
}
