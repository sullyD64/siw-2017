package it.uniroma3.triathlon.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.triathlon.model.Societa;

public interface SocietaRepository extends CrudRepository<Societa, Long> {
	  List<Societa> findByRegione(String regione);
}