package it.uniroma3.triathlon.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.triathlon.model.Risultato;
import it.uniroma3.triathlon.repository.RisultatoRepository;

@Service
public class RisultatoService {

	@Autowired
	private RisultatoRepository risultatoRepository;

	public boolean isAlreadyRegistered(final Risultato risultato) {
		return risultatoRepository.findByAtletaPartecipanteAndGara(
				risultato.getAtletaPartecipante(), risultato.getGara())!=null;
	}
	
	public Risultato findOne(Long id) {
		return this.risultatoRepository.findOne(id);
	}
	
	public boolean areRisultatiValidi(List<Risultato> risultati) {
		boolean tuttoOk = true;
		for (Risultato risultato : risultati) {
			if(!risultato.isValido())
				tuttoOk = false;
		}
		return tuttoOk;
	}
	
	public void resetRisultati(List<Risultato> risultati) {
		for (Risultato risultato : risultati) {
			System.out.println("service: " + risultato.getId());
			risultato.setValido(false);
			risultato.resetTempi();
			save(risultato);
		}
	}

	@Transactional
	public void save(final Risultato risultato) {
		this.risultatoRepository.save(risultato);
		Collections.sort(risultato.getGara().getRisultati());
	}

	
}
