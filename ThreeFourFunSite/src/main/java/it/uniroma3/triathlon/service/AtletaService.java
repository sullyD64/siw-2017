package it.uniroma3.triathlon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.triathlon.model.Atleta;
import it.uniroma3.triathlon.repository.AtletaRepository;
import it.uniroma3.triathlon.util.Calcolatore;

@Service
public class AtletaService {

	@Autowired
	private AtletaRepository atletaRepository;
	
    public Iterable<Atleta> findAll() {
        return this.atletaRepository.findAll();
    }

	@Transactional
	public boolean add(final Atleta atleta) {
		List<Atleta> atleti = this.atletaRepository.findByCognome(atleta.getCognome());
		for (Atleta a : atleti) {
			if (a.getNome().equals(atleta.getNome()) && a.getDataNascita().equals(atleta.getDataNascita()))
				return false;
		}

		atleta.setNome(atleta.getNome().toUpperCase());
		atleta.setCognome(atleta.getCognome().toUpperCase());
		atleta.setEta(Calcolatore.calcolaEta(atleta.getDataNascita()));
		atleta.setCategoria(Calcolatore.calcolaCategoria(atleta.getEta(), atleta.getSesso()));

		this.atletaRepository.save(atleta);	
		return true;
	}


}
