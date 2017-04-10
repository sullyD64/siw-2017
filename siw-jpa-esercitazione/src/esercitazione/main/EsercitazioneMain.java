package esercitazione.main;

import javax.persistence.*;

import model.Artista;
import persistence.CrudRepository;
import persistence.jpa.CrudRepositoryJPA;

public class EsercitazioneMain {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("esercitazione-unit");
		EntityManager em = emf.createEntityManager();

		Artista a = new Artista();
		a.setNome("Pablo");
		
		CrudRepository<Artista> crudRepository = new CrudRepositoryJPA<Artista>(em, Artista.class);
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
//		artistaRepository.save(a);
		
		
		tx.commit();
		
		em.close();
		emf.close();
	}
}
