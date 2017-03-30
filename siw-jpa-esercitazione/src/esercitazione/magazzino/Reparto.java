package esercitazione.magazzino;

import javax.persistence.*;
import java.util.*;

@Entity
public class Reparto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String nome;
	private String piano;
	@OneToMany(mappedBy = "reparto", cascade = CascadeType.PERSIST)
	private List<Prodotto> prodotti;
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Magazziniere> magazzinieri;
	
	// Getters & Setters
	
	public long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getPiano() {
		return piano;
	}
	public List<Prodotto> getProdotti() {
		return prodotti;
	}
	public List<Magazziniere> getMagazzinieri() {
		return magazzinieri;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setPiano(String piano) {
		this.piano = piano;
	}
	public void setProdotti(List<Prodotto> prodotti) {
		this.prodotti = prodotti;
	}
	public void setMagazzinieri(List<Magazziniere> magazzinieri) {
		this.magazzinieri = magazzinieri;
	}
	
	// Hashcode, Equals, Tostring
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((magazzinieri == null) ? 0 : magazzinieri.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((piano == null) ? 0 : piano.hashCode());
		result = prime * result + ((prodotti == null) ? 0 : prodotti.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reparto other = (Reparto) obj;
		if (id != other.id)
			return false;
		if (magazzinieri == null) {
			if (other.magazzinieri != null)
				return false;
		} else if (!magazzinieri.equals(other.magazzinieri))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (piano == null) {
			if (other.piano != null)
				return false;
		} else if (!piano.equals(other.piano))
			return false;
		if (prodotti == null) {
			if (other.prodotti != null)
				return false;
		} else if (!prodotti.equals(other.prodotti))
			return false;
		return true;
	}
}
