package it.uniroma3.triathlon;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ThreeFourFunSiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThreeFourFunSiteApplication.class, args);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("triathlon-unit");
	    EntityManager em = emf.createEntityManager();
	    EntityTransaction tx = em.getTransaction();
	    tx.begin();
	    tx.commit();
	    em.close();
	    emf.close();
	}
}
