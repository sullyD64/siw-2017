package esercitazione.azienda;

import javax.persistence.*;
import java.util.*;

@Entity
public class Divisione {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(unique = true)
	private String nome;
	@OneToMany(mappedBy = "divisione")
	private List<Impiegato> impiegati;
	
	public Divisione() {
		impiegati = new ArrayList<>();
	}
	
	
	// Getters & Setters
	
	public Long getId() 								{ return this.id; }
	public String getNome() 							{ return this.nome; }
	public List<Impiegato> getImpiegati() 				{ return this.impiegati; }
	
	public void setId(Long id) 							{ this.id = id; }
	public void setNome(String nome) 					{ this.nome = nome; }
	public void setImpiegati(List<Impiegato> impiegati) { this.impiegati = impiegati; }


	// Hashcode, Equals, Tostring	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((impiegati == null) ? 0 : impiegati.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Divisione other = (Divisione) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (impiegati == null) {
			if (other.impiegati != null)
				return false;
		} else if (!impiegati.equals(other.impiegati))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Progetto");
		sb.append("{id=").append(id);
		sb.append(", nome='").append(nome);
		sb.append(", impiegati='").append(impiegati.toString());
		sb.append("'}\n");
		return sb.toString();
	}
}
