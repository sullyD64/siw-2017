package siw.fullstack.repository;

import javax.persistence.EntityManager;

import siw.fullstack.model.Product;

public class ProductRepository extends CrudRepositoryJPA<Product> {

	public ProductRepository(EntityManager em) {
		super(em, Product.class);
	}
}
