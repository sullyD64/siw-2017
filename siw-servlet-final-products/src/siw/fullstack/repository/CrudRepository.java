package siw.fullstack.repository;

public interface CrudRepository<T> {
	public T save(T entity);
	public T findOne(Long id);
	public java.util.List<T> findAll ();
	public void delete(T entity);
	public void deleteAll();
}
