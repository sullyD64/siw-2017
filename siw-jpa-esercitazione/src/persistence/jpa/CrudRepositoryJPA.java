package persistence.jpa;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import persistence.CrudRepository;

public class CrudRepositoryJPA<T> implements CrudRepository<T> {
	private EntityManager em;
	private Class<T> entityClass;
	
	public CrudRepositoryJPA(EntityManager em, Class<T> entityClass) {
		this.em = em;
		this.entityClass = entityClass;
		
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
			if (getId.invoke(entity) == null) {
				em.persist(entity);
				persistentEntity = entity;
			} else {
				persistentEntity = em.merge(entity);
			}
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
		TypedQuery<T> query = em.createQuery("SELECT e FROM " + this.getClassName() + " e", this.entityClass);
		return query.getResultList();
	}

	@Override
	public void delete(T entity) {
		em.remove(entity);		
	}

	@Override
	public void deleteAll() {
		Query query = em.createQuery("DELETE FROM " + this.getClassName());
		query.executeUpdate();
	}

	protected EntityManager getEm() {
		return this.em;
	}
}
