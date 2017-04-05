package esercitazione.main;

import javax.persistence.*;

public class EsercitazioneMain {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("esercitazione-unit");
		EntityManager em = emf.createEntityManager();

		em.close();
		emf.close();
	}
}
