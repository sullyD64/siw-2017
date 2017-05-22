package siw.fullstack.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
//import javax.persistence.TypedQuery;

import siw.fullstack.model.Product;
import siw.fullstack.repository.ProductRepository;

public class ProductService {
	private EntityManager em;

	public Product insertProduct(Product p) {	
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("products-unit");
		this.em = emf.createEntityManager();
		ProductRepository pr = new ProductRepository(this.em);
		EntityTransaction tx = this.em.getTransaction();

		tx.begin();
		//		em.persist(prodotto);
		pr.save(p);
		tx.commit();
		em.close();
		emf.close();
		return p;
	}

	public List<Product> getAllProducts() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("products-unit");
		this.em = emf.createEntityManager();
		ProductRepository pr = new ProductRepository(this.em);
		EntityTransaction tx = this.em.getTransaction();

		tx.begin();
		//		TypedQuery<Product> query = em.createNamedQuery("findAll", Product.class);
		//		List<Product> products = query.getResultList(); 
		List<Product> products = pr.findAll();
		tx.commit();
		em.close();
		emf.close();
		return products;
	}

	public Product getOneProduct(Long id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("products-unit");
		this.em = emf.createEntityManager();
		ProductRepository pr = new ProductRepository(this.em);
		EntityTransaction tx = this.em.getTransaction();

		tx.begin();
		//		Product p = em.find(Product.class, id);
		Product p = pr.findOne(id);
		tx.commit();
		em.close();
		emf.close();
		return p;
	}
	
	public void deleteProduct(Product p) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("products-unit");
		this.em = emf.createEntityManager();
		ProductRepository pr = new ProductRepository(this.em);
		EntityTransaction tx = this.em.getTransaction();

		tx.begin();
		//		em.remove(em.contains(p) ? p : em.merge(p));
		pr.delete(p);
		tx.commit();
		em.close();
		emf.close();
	}
}
