package persistence.jpa;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import model.Artista;
import persistence.ArtistaCrudRepository;

public class ArtistaCrudRepositoryJPA implements ArtistaCrudRepository {
	private EntityManager em;
	
	public ArtistaCrudRepositoryJPA(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public Artista save(Artista artista) {
		if (artista.getId() == null) {
			em.persist(artista);
		} else {
			return em.merge(artista);
		}
		return artista;
	}

	@Override
	public Artista findOne(Long id) {
		return em.find(Artista.class, id);
	}

	@Override
	public List<Artista> findAll() {
		Query query = em.createQuery("SELECT a FROM Artista a");
		return query.getResultList();
	}

	@Override
	public void delete(Artista artista) {
		em.remove(artista);
	}

	@Override
	public void deleteAll() {
		Query query = em.createQuery("DELETE FROM Artista");
		query.executeUpdate();
	}
}
