package it.uniroma3.triathlon.model;

import javax.persistence.*;

@Entity
@Table(name = "ruoli_utente", uniqueConstraints = {@UniqueConstraint(columnNames = {"utente_id", "ruolo"})})
public class RuoloUtente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ruolo_utente_id")
	private Integer ruoloUtenteId;
	
	@OneToOne
	private Utente utente;
	
	private String ruolo;
	
	public RuoloUtente(Utente utente, String ruolo){
		this.utente = utente;
		this.ruolo = ruolo;
	}
	
	public RuoloUtente() {
	}

	public Integer getRuoloUtenteId() {
		return ruoloUtenteId;
	}

	public void setRuoloUtenteId(Integer ruoloUtenteId) {
		this.ruoloUtenteId = ruoloUtenteId;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}	
}
