package it.uniroma3.triathlon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.triathlon.model.Societa;
import it.uniroma3.triathlon.repository.SocietaRepository;

@Service
public class SocietaService {

	@Autowired
	private SocietaRepository societaRepository;

    public Iterable<Societa> findAll() {
        return this.societaRepository.findAll();
    }
	
	@Transactional
	public boolean add(final Societa societa) {
		Iterable<Societa> elencoSocieta = this.societaRepository.findAll();
		for (Societa s : elencoSocieta) {
			if (s.getNome().equals(societa.getNome()))
				return false;
		}
		
		societa.setNome(societa.getNome().toUpperCase());
		
		this.societaRepository.save(societa);
		return true;
	}


}
