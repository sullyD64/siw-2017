package it.uniroma3.triathlon.service;

import java.util.ArrayList;
import java.util.Collections;
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

	public Iterable<Gara> findAll(){
		return this.garaRepository.findAll();
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

		if (gare!=null && gare.size() > 0) {
			Gara[] lastTemp = new Gara[3];
			for(int i = 0; i<lastTemp.length; i++) {
				lastTemp[i]=gare.get(i);
			}

			for(Gara g : lastTemp){
				lastThree.add(g);
			}
		}
		return lastThree;

	}


}
