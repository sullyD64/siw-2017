package it.uniroma3.triathlon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.triathlon.model.Atleta;
import it.uniroma3.triathlon.model.Utente;
import it.uniroma3.triathlon.repository.AtletaRepository;

@Service
public class AtletaService {

	@Autowired
	private AtletaRepository atletaRepository;
	@Autowired
	private UtenteService utenteService;
	
	
    public Iterable<Atleta> findAll() {
        return this.atletaRepository.findAll();
    }
    
    public boolean isDuplicate(final Atleta atleta) {
    	List<Atleta> atleti = this.atletaRepository.findByCognome(atleta.getCognome());
		for (Atleta a : atleti) {
			if (a.getNome().equals(atleta.getNome()) && a.getDataNascita().equals(atleta.getDataNascita()))
				return true;
		}
		return false;
    }
    
    public boolean hasUtenteGestore(String username) {
		return utenteService.findByUsername(username).hasAtletaGestito();
	}
    
    public void setUtenteGestore(String username, Atleta atleta) {
		Utente utenteGestore = utenteService.findByUsername(username);
		utenteGestore.setAtletaGestito(atleta);
		utenteService.save(utenteGestore);
	}

	@Transactional
	public void add(final Atleta atleta) {		
		this.atletaRepository.save(atleta);
	}
}