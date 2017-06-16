package it.uniroma3.triathlon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.triathlon.model.Gara;
import it.uniroma3.triathlon.repository.GaraRepository;

@Service
public class GaraService {

	@Autowired
	private GaraRepository garaRepository;
	
	@Transactional
	public void add(final Gara gara) {
		this.garaRepository.save(gara);
	}

	public boolean isDuplicateSameDate(final Gara gara) {
		Iterable<Gara> elencoGare = this.garaRepository.findAll();
		for (Gara g : elencoGare) {
			if (g.getDataSvolgimento().equals(gara.getDataSvolgimento()))
				return true;
		}
		return false;
	}
	
}
