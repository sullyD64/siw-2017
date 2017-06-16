package it.uniroma3.triathlon.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.triathlon.model.Utente;

public interface UtenteRepository extends CrudRepository<Utente, String> {
	
	public Utente findByUsername(String username);
	

}
