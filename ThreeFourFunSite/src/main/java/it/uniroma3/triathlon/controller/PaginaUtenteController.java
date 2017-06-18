package it.uniroma3.triathlon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PaginaUtenteController {

//	@GetMapping("/utente/{username}")
	public String mostraPaginaUtente(@PathVariable("username") String username, Model model) {
		model.addAttribute("username", username);
		return "pannelloUtente";
	}
}
