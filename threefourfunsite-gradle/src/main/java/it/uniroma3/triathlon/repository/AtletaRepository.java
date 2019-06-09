package it.uniroma3.triathlon.repository;

import it.uniroma3.triathlon.model.Atleta;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AtletaRepository extends CrudRepository<Atleta, Long> {
	  List<Atleta> findByCognome(String cognome);
}