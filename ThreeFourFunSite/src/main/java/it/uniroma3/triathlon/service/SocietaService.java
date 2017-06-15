package it.uniroma3.triathlon.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.triathlon.model.Societa;
import it.uniroma3.triathlon.repository.SocietaRepository;

@Service
public class SocietaService {

	@Autowired
	private SocietaRepository societaRepository;

    public Iterable<Societa> findAll() {
        return this.societaRepository.findAll();
    }
    
    public Societa findOne(Long id) {
    	return this.societaRepository.findOne(id);
    }
	
    public boolean isDuplicate(final Societa societa) {
    	Iterable<Societa> elencoSocieta = this.societaRepository.findAll();
		for (Societa s : elencoSocieta) {
			if (s.getNome().equals(societa.getNome()))
				return true;
		}
		return false;
    }
    
	@Transactional
	public void add(final Societa societa) {
		this.societaRepository.save(societa);
	}

	public Map<String, List<Societa>> groupedByRegione(Iterable<Societa> elencoSocieta) {
		NavigableMap<String, List<Societa>> regione2societa = new TreeMap<>();
		List<Societa> societaPerRegione;
		
		for (Societa societa : elencoSocieta) {
			String r = societa.getRegione();
			
			if (!regione2societa.containsKey(r)) {
				societaPerRegione = new ArrayList<>();
				societaPerRegione.add(societa);
			} else {
				societaPerRegione = regione2societa.get(r);
				societaPerRegione.add(societa);
			}
			regione2societa.put(r, societaPerRegione);
		}
		
		return regione2societa;
	}


}
