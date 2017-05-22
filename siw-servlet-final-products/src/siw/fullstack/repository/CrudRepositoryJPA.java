package siw.fullstack.repository;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class CrudRepositoryJPA<T> implements CrudRepository<T> {
	private EntityManager em;
	private Class<T> entityClass;
	
	public CrudRepositoryJPA(EntityManager em, Class<T> entityClass ) {
		this.em = em;
		this.entityClass = entityClass;
	}
	
	protected EntityManager getEm() {
		return this.em;
	}
	
	private String getClassName() {
		String fullClassName = this.entityClass.getCanonicalName();
		String className = fullClassName.substring(fullClassName.lastIndexOf('.') + 1);
		return className;
	}
	
	@Override
	public T save(T entity) {
		Method getId = null; 
		T persistentEntity = null;
		
		try {
			getId = this.entityClass.getMethod("getId");
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	
		try {
			if (getId.invoke(entity)==null) {
				this.em.persist(entity);
				persistentEntity = entity;
			} else
				persistentEntity = this.em.merge(entity);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return persistentEntity;
	}

	@Override
	public T findOne(Long id) {
		return em.find(this.entityClass, id);
	}

	@Override
	public List<T> findAll() {
		TypedQuery<T>query = em.createQuery("SELECT e FROM " + this.getClassName() + " e", this.entityClass); 
		return query.getResultList();
	}

	@Override
	public void delete(T entity) {
		em.remove(em.contains(entity) ? entity : em.merge(entity));
	}

	@Override
	public void deleteAll() {
		Query query = em.createQuery("DELETE FROM " + this.getClassName());
		query.executeUpdate();
	}
}
