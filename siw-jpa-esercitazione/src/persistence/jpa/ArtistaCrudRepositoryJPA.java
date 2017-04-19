package persistence.jpa;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Artista;
import model.Opera;

import java.util.*;

public class ArtistaCrudRepositoryJPA extends CrudRepositoryJPA<Artista> {
	
	public ArtistaCrudRepositoryJPA(EntityManager em) {
		super(em, Artista.class);
	}
	
	public List<Artista> findArtistaByOpera(Opera opera) {
		TypedQuery<Artista> query = getEm().createQuery("SELECT a FROM artista_opera a WHERE a.opere_id = " + opera.getId(), Artista.class);
		return query.getResultList();
	}
}
