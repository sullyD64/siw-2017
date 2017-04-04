package esercitazione.azienda;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Impiegato {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private String cognome;
	@Column(nullable = false, unique = true) 
	private String matricola;
	private Date dataDiNascita;
	@ManyToOne(fetch = FetchType.EAGER)
	private Divisione divisione;
	
	// Getters & Setters
	
	public Long getId() 								{ return this.id; }
	public String getNome() 							{ return this.nome; }
	public String getCognome() 							{ return this.cognome; }
	public String getMatricola() 						{ return this.matricola; }
	public Date getDataDiNascita() 						{ return this.dataDiNascita; }
	public Divisione getDivisione()						{ return this.divisione; }
	
	public void setId(Long id) 							{ this.id = id; }
	public void setNome(String nome) 					{ this.nome = nome; }
	public void setCognome(String cognome) 				{ this.cognome = cognome; }
	public void setMatricola(String matricola) 			{ this.matricola = matricola; }
	public void setDataDiNascita(Date dataDinascita)	{ this.dataDiNascita = dataDinascita; }
	public void setDivisione(Divisione divisione)		{ this.divisione = divisione; }

	
	// Hashcode, Equals, Tostring
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
		result = prime * result + ((dataDiNascita == null) ? 0 : dataDiNascita.hashCode());
		result = prime * result + ((divisione == null) ? 0 : divisione.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((matricola == null) ? 0 : matricola.hashCode());
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
		Impiegato other = (Impiegato) obj;
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		if (dataDiNascita == null) {
			if (other.dataDiNascita != null)
				return false;
		} else if (!dataDiNascita.equals(other.dataDiNascita))
			return false;
		if (divisione == null) {
			if (other.divisione != null)
				return false;
		} else if (!divisione.equals(other.divisione))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (matricola == null) {
			if (other.matricola != null)
				return false;
		} else if (!matricola.equals(other.matricola))
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
		sb.append("Giocatore");
		sb.append("{id=").append(id);
		sb.append(", nome='").append(nome);
		sb.append(", cognome='").append(cognome);
		sb.append(", matricola='").append(matricola);
		sb.append(", dataDiNascita='").append(dataDiNascita.toString());
		sb.append(", divisione='").append(divisione.getNome().toString());
		sb.append("'}\n");
		return sb.toString();
	}
}
