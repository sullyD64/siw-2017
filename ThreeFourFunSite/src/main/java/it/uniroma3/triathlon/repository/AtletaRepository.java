package it.uniroma3.triathlon.repository;

import it.uniroma3.triathlon.model.Atleta;

import org.springframework.data.repository.CrudRepository;

public interface AtletaRepository extends CrudRepository<Atleta, Long> {
	
}