package it.uniroma3.triathlon.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.triathlon.model.Atleta;
import it.uniroma3.triathlon.model.Gara;
import it.uniroma3.triathlon.model.Risultato;

public interface RisultatoRepository extends CrudRepository<Risultato, Long> {
	
	public Risultato findByAtletaPartecipanteAndGara(Atleta atleta, Gara gara);
	
}