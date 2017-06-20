package it.uniroma3.triathlon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import it.uniroma3.triathlon.model.Utente;
import it.uniroma3.triathlon.service.GaraService;
import it.uniroma3.triathlon.service.UtenteService;

@Controller
@SessionAttributes("current_username")
public class HomeController {
	
	@Autowired
	private UtenteService utenteService;
	@Autowired
	private GaraService garaService;

	@GetMapping(value={"/", "/utente", "/admin"})
	public String homepage(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			Utente utente = utenteService.findByUsername(authentication.getName());
		    model.addAttribute(utente);
		    model.addAttribute("current_username", utente.getUsername());
		}
		model.addAttribute("navHome", "active");
		model.addAttribute("treGareImminenti", garaService.getPrimeTreGareImminenti());
		return "home";
	}
	
	@GetMapping("/info")
	public String info() {
		return "info";
	}
}
