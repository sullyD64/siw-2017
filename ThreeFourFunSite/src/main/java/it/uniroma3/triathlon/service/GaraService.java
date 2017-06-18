package it.uniroma3.triathlon.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.triathlon.model.Gara;
import it.uniroma3.triathlon.repository.GaraRepository;

@Service
public class GaraService {

	@Autowired
	private GaraRepository garaRepository;

	public boolean isDuplicateSameDate(final Gara gara) {
		Iterable<Gara> elencoGare = this.garaRepository.findAll();
		for (Gara g : elencoGare) {
			if (g.getDataSvolgimento().equals(gara.getDataSvolgimento()))
				return true;
		}
		return false;
	}

	public Iterable<Gara> findAll(){
		return this.garaRepository.findAll();
	}

	public Gara findOne(Long id){
		return this.garaRepository.findOne(id);
	}

	public void deleteById(Long id) {
		garaRepository.delete(id);
	}

	public List<Gara> getSortedByDate(){
		Iterable<Gara> elencoGare = findAll();
		List<Gara> gare = new LinkedList<>();
		for(Gara g : elencoGare){
			gare.add(g);
		}
		Collections.sort(gare);

		return gare;
	}

	public List<Gara> getLastThree(){
		List<Gara> gare = getSortedByDate();
		List<Gara> lastThree = new ArrayList<>();
		Iterator<Gara> iter = gare.iterator();

		if (gare!=null) {
			for (int i=0; i < 3; i++) {
				if (iter.hasNext())
					lastThree.add(iter.next());
				else break;
			}
		}
		return lastThree;
	}

	@Transactional
	public void add(final Gara gara) {
		this.garaRepository.save(gara);
	}
}
