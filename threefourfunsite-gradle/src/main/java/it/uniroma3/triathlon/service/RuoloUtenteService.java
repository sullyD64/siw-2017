package it.uniroma3.triathlon.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.triathlon.model.RuoloUtente;
import it.uniroma3.triathlon.repository.RuoloUtenteRepository;

@Service
public class RuoloUtenteService {
	
	@Autowired
	private RuoloUtenteRepository ruoloUtenteRepository;
	
	@Transactional
	public void add(final RuoloUtente ruoloUtente) {
		this.ruoloUtenteRepository.save(ruoloUtente);
	}
}