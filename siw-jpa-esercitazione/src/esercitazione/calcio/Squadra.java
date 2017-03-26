package esercitazione.calcio;

import javax.persistence.*;

import java.util.List;
import java.util.ArrayList;

@Entity
public class Squadra {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private String allenatore;
	private Integer annoFondazione;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "squadra_id", foreignKey = @ForeignKey(name = "fk_squadra_id"))
	private List<Giocatore> giocatori;
	
	public Squadra() {
		this.giocatori = new ArrayList<>();
	}
	
	// Getters & Setters
	
	public Long getId() 									{ return this.id; }
	public String getNome() 								{ return this.nome; }
	public String getAllenatore() 							{ return this.allenatore; }
	public Integer getAnnoFondazione() 						{ return this.annoFondazione; }
	public List<Giocatore> getGiocatori() 					{ return this.giocatori; } 
	
	public void setId(Long id) 								{ this.id = id; }
	public void setNome(String nome) 						{ this.nome = nome; }
	public void setAllenatore(String allenatore) 			{ this.allenatore = allenatore; }
	public void setAnnoFondazione(Integer annoFondazione) 	{ this.annoFondazione = annoFondazione; }
	public void setGiocatori(List<Giocatore>giocatori)		{ this.giocatori = giocatori; }

	
	// Hashcode, Equals, Tostring

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((allenatore == null) ? 0 : allenatore.hashCode());
		result = prime * result + ((annoFondazione == null) ? 0 : annoFondazione.hashCode());
		result = prime * result + ((giocatori == null) ? 0 : giocatori.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Squadra other = (Squadra) obj;
		if (allenatore == null) {
			if (other.allenatore != null)
				return false;
		} else if (!allenatore.equals(other.allenatore))
			return false;
		if (annoFondazione == null) {
			if (other.annoFondazione != null)
				return false;
		} else if (!annoFondazione.equals(other.annoFondazione))
			return false;
		if (giocatori == null) {
			if (other.giocatori != null)
				return false;
		} else if (!giocatori.equals(other.giocatori))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		sb.append("Squadra");
		sb.append("{id=").append(id);
		sb.append(", nome='").append(nome);
		sb.append(", allenatore='").append(allenatore);
		sb.append(", annoFondazione='").append(annoFondazione.toString());
		sb.append(", giocatori='").append(giocatori.toString());
		sb.append("'}\n");
		return sb.toString();
	}
}
