package it.uniroma3.db.products2;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("products-unit");
		EntityManager em = emf.createEntityManager();
		
		Product product = new Product();
		product.setName("ASUS ZENFONE 2");
		product.setDescription("Best phone ever");
		product.setPrice(242F);
		product.setCode("ZE551ML-IT");
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(product);
		tx.commit();
		
		em.close();
		emf.close();
	}

}
