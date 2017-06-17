package it.uniroma3.triathlon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.triathlon.model.Gara;
import it.uniroma3.triathlon.repository.GaraRepository;

import java.time.LocalDate;
import java.util.*;
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
	
	
	public List<Gara> getSortedByDate(Iterable<Gara> elencoGare){
		List<Gara> gare = new LinkedList<>();
		for(Gara g : elencoGare){
			gare.add(g);
		}
		Collections.sort(gare);

		return gare;
	}
	
	public List<Gara> getLastThree(List<Gara> gare){
		int treGare = 3;
		Gara[] lastTemp = new Gara[treGare];
		for(int i = 0; i<treGare; i++){
			lastTemp[i]=gare.get(i);
		}
		
		List<Gara> last = new ArrayList<>();
		for(Gara g : lastTemp){
			last.add(g);
		}
		
		return last;
	
	}

	
}
