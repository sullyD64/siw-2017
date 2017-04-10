package persistence;

import java.util.List;

public interface CrudRepository<T> {
	public T save(T entity);
	public T findOne(Long id);
	public List<T> findAll();
	public void delete(T entity);
	public void deleteAll();
}
