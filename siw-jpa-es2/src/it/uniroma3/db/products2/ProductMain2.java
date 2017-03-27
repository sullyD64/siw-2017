package it.uniroma3.db.products2;

import javax.persistence.*;

public class ProductMain2 {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("products-unit");
		EntityManager em = emf.createEntityManager();
		
		Address a1 = new Address("Viale Marconi", "Rome", null, "00111", "Italy");
		Address a2 = new Address("Via Vito Volterra", "Rome", null, "00111", "Italy");
		
		Provider prov1 = new Provider("Dollaroni SPA", "info@dollaroni.com", a1);
		
		Product p1 = new Product("ASUS ZENFONE 2", 242F, "Best phone ever", "ZE551ML-IT");
		Product p2 = new Product("SAMSUNG GALAXY S7", 800F, "A really expensive Android", "SM-G930F-IT");
		
		prov1.getProducts().add(p1);
		prov1.getProducts().add(p2);

		Customer c1 = new Customer("Lorenzo", "Sullivan", "lg@siw.net", null, a2);
	
		OrderLine ol1 = new OrderLine(p1, 2, 10F);
		OrderLine ol2 = new OrderLine(p2, 1, 50F);
		OrderLine ol3 = new OrderLine(p2, 5, 300F);
		
		Order o1 = new Order();
		Order o2 = new Order();
		
		o1.setCustomer(c1);
		o1.getOrderLines().add(ol1);
		o1.getOrderLines().add(ol2);
		o1.getOrderLines().add(ol3);
		
		o2.setCustomer(c1);
		o2.getOrderLines().add(ol2);
		o2.getOrderLines().add(ol3);
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(a1);
		em.persist(a2);
		em.persist(prov1);
		em.persist(p1);
		em.persist(p2);
		em.persist(c1);
		em.persist(ol1);
		em.persist(ol2);
		em.persist(ol3);
		em.persist(o1);
		em.persist(o2);
		
		tx.commit();
		
		em.close();
		emf.close();
	}
}
