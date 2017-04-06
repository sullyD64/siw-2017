package model;

import javax.persistence.*;
import java.util.*;

@Entity
public class Opera {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String titolo;
	private String anno;
	@ManyToMany(mappedBy = "opere")
	private List<Artista> artisti;
	
	public Opera() {
		this.artisti = new ArrayList<>();
	}
	
	public Long getId() {
		return id;
	}
	public String getTitolo() {
		return titolo;
	}
	public String getAnno() {
		return anno;
	}
	public List<Artista> getArtisti() {
		return artisti;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public void setAnno(String anno) {
		this.anno = anno;
	}
	public void setArtisti(List<Artista> artisti) {
		this.artisti = artisti;
	}
}
