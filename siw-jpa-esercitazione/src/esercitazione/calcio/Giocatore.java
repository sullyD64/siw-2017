package esercitazione.calcio;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Giocatore {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private String cognome;
	@Temporal(TemporalType.DATE)
	private Date dataNascita;
	private String ruolo;
	private Integer altezza;
	private Integer peso; 
	
	public Giocatore(String nome, String cognome, Date dataNascita, String ruolo, Integer altezza, Integer peso) {
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.ruolo = ruolo;
		this.altezza = altezza;
		this.peso = peso;
	}
	
	// Getters & Setters
	
	public Long getId() 							{ return this.id; }
	public String getNome() 						{ return this.nome; }
	public String getCognome() 						{ return this.cognome; }
	public Date getDataNascita() 					{ return this.dataNascita; }
	public String getRuolo() 						{ return this.ruolo; }
	public Integer getAltezza()						{ return this.altezza; }
	public Integer getPeso() 						{ return this.peso; }
	
	public void setId(Long id) 						{ this.id = id; }
	public void setNome(String nome) 				{ this.nome = nome; }
	public void setCognome(String cognome) 			{ this.cognome = cognome; }
	public void setDataNascita(Date dataNascita)	{ this.dataNascita = dataNascita; }
	public void setRuolo(String ruolo) 				{ this.ruolo = ruolo; }
	public void setAltezza(Integer altezza) 		{ this.altezza = altezza; }
	public void setPeso(Integer peso) 				{ this.peso = peso; }

	
	// Hashcode, Equals, Tostring
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((altezza == null) ? 0 : altezza.hashCode());
		result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
		result = prime * result + ((dataNascita == null) ? 0 : dataNascita.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((peso == null) ? 0 : peso.hashCode());
		result = prime * result + ((ruolo == null) ? 0 : ruolo.hashCode());
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
		Giocatore other = (Giocatore) obj;
		if (altezza == null) {
			if (other.altezza != null)
				return false;
		} else if (!altezza.equals(other.altezza))
			return false;
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		if (dataNascita == null) {
			if (other.dataNascita != null)
				return false;
		} else if (!dataNascita.equals(other.dataNascita))
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
		if (peso == null) {
			if (other.peso != null)
				return false;
		} else if (!peso.equals(other.peso))
			return false;
		if (ruolo == null) {
			if (other.ruolo != null)
				return false;
		} else if (!ruolo.equals(other.ruolo))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Giocatore");
		sb.append("{id=").append(id);
		sb.append(", nome='").append(nome);
		sb.append(", cognome='").append(cognome);
		sb.append(", dataNascita='").append(dataNascita.toString());
		sb.append(", ruolo='").append(ruolo.toString());
		sb.append(", altezza='").append(altezza.toString());
		sb.append(", peso='").append(peso.toString());
		sb.append("'}\n");
		return sb.toString();
	}
}
