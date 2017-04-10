package persistence.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import persistence.CrudRepository;

public class CrudRepositoryJPA<T> implements CrudRepository<T> {
	private EntityManager em;
	private Class<T> entityClass; 
	
	public CrudRepositoryJPA(EntityManager em, Class<T> entityClass) {
		this.em = em;
		this.entityClass = entityClass;
	}
	
	@Override
	public T save(T entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T findOne(Long id) {
		return em.find(this.entityClass, id);
	}

	@Override
	public List<T> findAll() {
		String className = this.entityClass.getCanonicalName();
		Query query = em.createQuery("SELECT e FROM" + className + " e");
		return query.getResultList();
	}

	@Override
	public void delete(T entity) {
		em.remove(entity);		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

}
