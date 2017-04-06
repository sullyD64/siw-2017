package persistence.jpa;

import java.util.List;
import javax.persistence.*;
import model.Artista;
import persistence.ArtistaCrudRepository;

public class ArtistaCrudRepositoryJPA implements ArtistaCrudRepository {
	private EntityManager em;
	private EntityTransaction tx;
	
	public ArtistaCrudRepositoryJPA(EntityManager em, EntityTransaction tx) {
		this.em = em;
		this.tx = tx;
	}
	
	@Override
	public Artista save(Artista artista) {
		if (artista.getId() == null) {
			em.persist(artista);
		} else {
			em.merge(artista);
		}
		return artista;
	}

	@Override
	public Artista findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Artista> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Artista artista) {
		// TODO Auto-generated method stub
		
	}
}
