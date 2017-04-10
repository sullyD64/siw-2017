package persistence;

import model.Stanza;
import java.util.*;

public interface StanzaCrudRepository {
	public Stanza save(Stanza stanza);
	public Stanza findOne(Long id);
	public List<Stanza> findAll();
	public void delete(Stanza stanza);
	public void deleteAll();
}
