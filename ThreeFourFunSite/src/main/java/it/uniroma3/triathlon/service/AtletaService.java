package it.uniroma3.triathlon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.triathlon.model.Atleta;
import it.uniroma3.triathlon.model.Societa;
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
    
    public Atleta findOne(Long id) {
    	return this.atletaRepository.findOne(id);
    }
    
    public void deleteById(Long id) {
    	this.atletaRepository.delete(id);
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
    
    public void setIscrittoASocieta(String username, Societa societa) {
		Atleta atletaIscritto = utenteService.findByUsername(username).getAtletaGestito();
		atletaIscritto.setSocieta(societa);
		save(atletaIscritto);
	}

	@Transactional
	public void save(final Atleta atleta) {		
		this.atletaRepository.save(atleta);
	}
}