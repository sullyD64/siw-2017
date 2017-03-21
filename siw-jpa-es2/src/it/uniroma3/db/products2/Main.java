package it.uniroma3.db.products2;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("products-unit");
		EntityManager em = emf.createEntityManager();
		
		Product p1 = new Product();
		p1.setName("ASUS ZENFONE 2");
		p1.setDescription("Best phone ever");
		p1.setPrice(242F);
		p1.setCode("ZE551ML-IT");
		
		Product p2 = new Product();
		p2.setName("SAMSUNG GALAXY S7");
		p2.setDescription("A really expensive Android");
		p2.setPrice(800F);
		p2.setCode("SM-G930F-IT");
		
		OrderLine ol1 = new OrderLine(null,p1,1,0F);
		OrderLine ol2 = new OrderLine(null,p2,1,50F);
		List<OrderLine> orderLines = new ArrayList<OrderLine>();
		orderLines.add(ol1);
		orderLines.add(ol2);
		
		Customer c1 = new Customer();
		c1.setFirstName("Lorenzo");
		c1.setLastName("Sullivan");
		c1.setEmail("lg@siw.net");
			
		Order o1 = new Order(null,null,null,c1,orderLines);
		
		List<Order> orders = new ArrayList<Order>();
		orders.add(o1);
		
		c1.setOrders(orders);
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(p1);
		em.persist(p2);
		em.persist(ol1);
		em.persist(ol2);
		em.persist(o1);
		
		tx.commit();
		
		em.close();
		emf.close();
	}

}
