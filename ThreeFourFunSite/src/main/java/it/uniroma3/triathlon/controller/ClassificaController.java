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

import it.uniroma3.triathlon.classifiche.Classifica;
import it.uniroma3.triathlon.model.Gara;
import it.uniroma3.triathlon.model.Risultato;
import it.uniroma3.triathlon.service.GaraService;
import it.uniroma3.triathlon.service.RisultatoService;
import it.uniroma3.triathlon.util.Calcolatore;

@Controller
@SessionAttributes({"current_username", "garaEditata"})
public class ClassificaController {

	@Autowired
	private GaraService garaService;
	@Autowired
	private RisultatoService risultatoService;
	
	@GetMapping("/classifiche")
	public String mostraclassificheGenerali(Model model) {
		model.addAttribute("navClassifiche", "active");
		model.addAttribute("gareCompletate", garaService.getGareCompletate());
		return "view_classifiche";
	}
	
	@GetMapping("/classifiche/{id}")
	public String mostraclassificaGara(@PathVariable("id") Long id, Model model) {
		model.addAttribute("navClassifiche", "active");
		model.addAttribute("classificaGaraSingola", true);
		model.addAttribute("gara", garaService.findOne(id));
		model.addAttribute("gareCompletate", garaService.getGareCompletate());
		
		Classifica classifica = new Classifica(garaService.findOne(id));
		classifica.ordinaClassifica();
		model.addAttribute(classifica);
		
		return "view_classifiche";
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
}
