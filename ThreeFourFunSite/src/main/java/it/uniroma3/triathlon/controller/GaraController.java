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
import it.uniroma3.triathlon.model.Gara;
import it.uniroma3.triathlon.model.Risultato;
import it.uniroma3.triathlon.model.Utente;
import it.uniroma3.triathlon.service.GaraService;
import it.uniroma3.triathlon.service.RisultatoService;
import it.uniroma3.triathlon.service.UtenteService;
import it.uniroma3.triathlon.util.Calcolatore;

@Controller
@SessionAttributes({"current_username", "garaEditata"})
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

	//	@GetMapping("/listClassifiche")
	//	public String mostraClassifiche(Model model) {
	//		model.addAttribute("navClassifiche", "active");
	//		model.addAttribute("");
	//		return "view_classifiche";
	//	}

	@GetMapping("/admin/newGara")
	public String mostraForm(Gara gara, Model model) {
		model.addAttribute("navGare", "active");
		model.addAttribute("formGara", true);
		return "form";
	}

	@GetMapping("/admin/newClassifica")
	public String mostraFormClassifica(Model model) {
		model.addAttribute("navClassifiche", "active");
		model.addAttribute("formClassifica", true);
		model.addAttribute("gareDaAggiornare", garaService.getGareDaAggiornare());
		return "form";
	}

	@GetMapping("/admin/newClassifica/{id}")
	public String mostraFormClassificaGara(@PathVariable("id") Long id, Model model) {
		model.addAttribute("navClassifiche", "active");
		model.addAttribute("formClassifica", true);
		model.addAttribute("editorClassifica", true);
		model.addAttribute("garaEditata", garaService.findOne(id));
		return "form";
	}

	@PostMapping("/admin/newClassifica/addRisultato/{id}")
	public String addTempiRisultato(@PathVariable("id") Long id,
			@RequestParam("comando") String comando,
			@SessionAttribute("garaEditata") Gara gara, 
			@Valid @ModelAttribute Risultato ris, 
			RedirectAttributes redir,
			BindingResult bindingResult, Model model) {
		
		String nextPage = "redirect:/admin/newClassifica/" + gara.getId();
		Risultato risultato = risultatoService.findOne(id);
		
		// Registra: congela il risultato, l'inserimento dei tempi viene bloccato
		if (comando.equalsIgnoreCase("registra")) {
			if (bindingResult.hasErrors()) {
				redir.addFlashAttribute("errore", "Inserire un tempo valido");
				return nextPage;
			}
			risultato.setTempoSwim(ris.getTempoSwim());
			risultato.setTempoBike(ris.getTempoBike());
			risultato.setTempoRun(ris.getTempoRun());
			risultato.setTempoTot(Calcolatore.calcolaTempoTot(risultato));
			risultato.setValido(true);
			risultatoService.save(risultato);
			
		// Modifica: scongela il risultato, i tempi vengono sbloccati e ritornano modificabili
		} else if (comando.equalsIgnoreCase("modifica")) {
			if (risultato.isValido())
				risultato.setValido(false);
			risultatoService.save(risultato);
		}
		return nextPage;
	}
	
	@PostMapping("/admin/newClassifica/terminaInserimento")
	public String confermaRisultati(@SessionAttribute("garaEditata") Gara gara, 
			@RequestParam("comando") String comando, 
			RedirectAttributes redir, Model model) {
		String nextPage = "redirect:/admin/newClassifica/" + gara.getId();
		
		// Conferma: se tutti i risultati sono confermati, imposta la gara come completata e chiude la form
		if (comando.equalsIgnoreCase("conferma")) {
			if (risultatoService.areRisultatiValidi(gara.getRisultati())) {
				gara.setCompletata(true);
				garaService.save(gara);
				redir.addFlashAttribute("successo", "La gara è stata aggiornata con i tempi inseriti.");
			} else {
				redir.addFlashAttribute("errore", "Devi registrare tutti i risultati prima di confermare la gara.");
			}
			
		// Reset: tutti i risultati vengono scongelati e resettati
		} else if (comando.equalsIgnoreCase("reset")) {
			risultatoService.resetRisultati(gara.getRisultati());
			garaService.save(gara);
		}
		
		return nextPage;
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
