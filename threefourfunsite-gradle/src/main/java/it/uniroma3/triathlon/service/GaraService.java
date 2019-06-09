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
import it.uniroma3.triathlon.util.Calcolatore;

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

	public List<Gara> getPrimeTreGareImminenti(){
		List<Gara> gareAperte = getGareAperte();
		List<Gara> lastThree = new ArrayList<>();
		Iterator<Gara> iter = gareAperte.iterator();

		if (gareAperte!=null) {
			for (int i=0; i < 3; i++) {
				if (iter.hasNext())
					lastThree.add(iter.next());
				else break;
			}
		}
		return lastThree;
	}
	
	public List<Gara> getGareAperte() {
		Iterable<Gara> allGare = getSortedByDate();
		List<Gara> gareAperte = new ArrayList<>();
		for (Gara gara: allGare) {
			if (!Calcolatore.isDataPassata(gara.getDataSvolgimento()) && (!gara.isCompletata()))
				gareAperte.add(gara);
		}
		return gareAperte;
	}
	
	public List<Gara> getGarePassate() {
		Iterable<Gara> allGare = getSortedByDate();
		List<Gara> garePassate = new ArrayList<>();
		for (Gara gara: allGare) {
			if (Calcolatore.isDataPassata(gara.getDataSvolgimento()))
				garePassate.add(gara);
		}
		return garePassate;
	}
	
	public List<Gara> getGareCompletate() {
		Iterable<Gara> allGare = getSortedByDate();
		List<Gara> gareCompletate = new ArrayList<>();
		for (Gara gara: allGare) {
			if (Calcolatore.isDataPassata(gara.getDataSvolgimento()) && (gara.isCompletata()))
				gareCompletate.add(gara);
		}
		return gareCompletate;
	}
	
	public List<Gara> getGareDaAggiornare() {
		Iterable<Gara> allGare = getSortedByDate();
		List<Gara> gareDaAggiornare = new ArrayList<>();
		for (Gara gara : allGare) {
			if (Calcolatore.isDataPassata(gara.getDataSvolgimento()) && !gara.isCompletata())
				gareDaAggiornare.add(gara);
		}
		return gareDaAggiornare;
	}

	@Transactional
	public void save(final Gara gara) {
		this.garaRepository.save(gara);
	}
}
