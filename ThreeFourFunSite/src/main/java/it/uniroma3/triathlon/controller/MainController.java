package it.uniroma3.triathlon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.triathlon.model.Gara;
import it.uniroma3.triathlon.service.GaraService;

@Controller
public class MainController {
	
	@Autowired
	private GaraService garaService;

	@GetMapping("/")
	public String homepage(Model model) {
		return "home";
	}
	
	@GetMapping("/listaGare")
	public String mostraUltimeGare(Gara gara, Model model){
		model.addAttribute(garaService.getLastThree(garaService.getSortedByDate(garaService.findAll())));
		return "home";
	}
	
	
}
