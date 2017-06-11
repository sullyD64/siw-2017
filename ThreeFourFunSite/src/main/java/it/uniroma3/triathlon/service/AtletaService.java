package it.uniroma3.triathlon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.triathlon.model.Atleta;
import it.uniroma3.triathlon.repository.AtletaRepository;

@Service
public class AtletaService {

	@Autowired
	private AtletaRepository atletaRepository;
	
	@Transactional
	public void add(final Atleta atleta) {
		this.atletaRepository.save(atleta);
	}

}
