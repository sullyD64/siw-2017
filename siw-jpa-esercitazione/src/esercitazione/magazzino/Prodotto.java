package esercitazione.magazzino;

import javax.persistence.*;

@Entity
public class Prodotto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String nome;
	private Integer quantita;
	private String descrizione;
	@ManyToOne(fetch = FetchType.EAGER)
	private Reparto reparto;
	
	// Getters & Setters
	
	public long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public Integer getQuantita() {
		return quantita;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public Reparto getReparto() {
		return reparto;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public void setReparto(Reparto reparto) {
		this.reparto = reparto;
	}
	
	// Hashcode, Equals, Tostring
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descrizione == null) ? 0 : descrizione.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((quantita == null) ? 0 : quantita.hashCode());
		result = prime * result + ((reparto == null) ? 0 : reparto.hashCode());
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
		Prodotto other = (Prodotto) obj;
		if (descrizione == null) {
			if (other.descrizione != null)
				return false;
		} else if (!descrizione.equals(other.descrizione))
			return false;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (quantita == null) {
			if (other.quantita != null)
				return false;
		} else if (!quantita.equals(other.quantita))
			return false;
		if (reparto == null) {
			if (other.reparto != null)
				return false;
		} else if (!reparto.equals(other.reparto))
			return false;
		return true;
	}
}
