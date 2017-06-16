package it.uniroma3.triathlon.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.triathlon.model.Gara;

public interface GaraRepository extends CrudRepository<Gara, Long> {
	
	List<Gara> findByDataSvolgimento(LocalDate dataSvolgimento);

}
