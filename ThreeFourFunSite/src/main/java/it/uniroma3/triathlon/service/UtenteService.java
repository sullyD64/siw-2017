package it.uniroma3.triathlon.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.triathlon.model.Utente;
import it.uniroma3.triathlon.repository.UtenteRepository;

@Service
public class UtenteService {
	
	@Autowired
	private UtenteRepository utenteRepository;
	
	public boolean isDuplicate(String username) {
		return (this.utenteRepository.findByUsername(username)!=null);	
	}

	@Transactional
	public void add(final Utente utente) {
		this.utenteRepository.save(utente);
	}
}
