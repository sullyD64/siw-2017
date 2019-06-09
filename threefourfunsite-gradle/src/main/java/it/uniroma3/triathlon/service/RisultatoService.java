package it.uniroma3.triathlon.service;

import java.util.ArrayList;
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
			risultato.setValido(false);
			risultato.resetTempi();
			save(risultato);
		}
	}
	
	public List<Risultato> getRisultatiGareNonSvolte(List<Risultato> allRisultatiAtleta) {
		List<Risultato> risultatiGareNonSvolte = new ArrayList<>();
		for (Risultato risultato : allRisultatiAtleta) {
			if (!risultato.getGara().isCompletata())
				risultatiGareNonSvolte.add(risultato);
		}
		return risultatiGareNonSvolte;
	}

	public List<Risultato> getRisultatiGareSvolte(List<Risultato> allRisultatiAtleta) {
		List<Risultato> risultatiGareSvolte = new ArrayList<>();
		for (Risultato risultato : allRisultatiAtleta) {
			if (risultato.isValido() && risultato.getGara().isCompletata())
				risultatiGareSvolte.add(risultato);
		}
		return risultatiGareSvolte;
	}
	
	@Transactional
	public void save(final Risultato risultato) {
		this.risultatoRepository.save(risultato);
		Collections.sort(risultato.getGara().getRisultati());
	}
}
