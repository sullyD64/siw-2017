package it.uniroma3.triathlon.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Atleta {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotNull
	@Size(min = 1)
	@Column(nullable = false)
	private String nome;
	@NotNull
	@Size(min = 1)
	@Column(nullable = false)
	private String cognome;
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataNascita;
	@Column(nullable = false)
	private String sesso;
	@Column(nullable = false)
	private String nazione;
	@Min(18)
	private Integer eta;
	private String categoria;
	@ManyToOne
	private Societa societa;
	@OneToMany(mappedBy = "atletaPartecipante")
	private List<Risultato> risultati;
	
	public Atleta(String nome, String cognome, Date dataNascita, String sesso, String nazione) {
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.sesso = sesso;
		this.nazione = nazione;
		this.risultati = new LinkedList<>();
	}
	
	public Atleta() {
		this.risultati = new LinkedList<>();
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public String getSesso() {
		return sesso;
	}

	public String getNazione() {
		return nazione;
	}

	public Integer getEta() {
		return eta;
	}

	public String getCategoria() {
		return categoria;
	}

	public Societa getSocieta() {
		return societa;
	}

	public List<Risultato> getRisultati() {
		return risultati;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public void setNazione(String nazione) {
		this.nazione = nazione;
	}

	public void setEta(Integer eta) {
		this.eta = eta;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public void setSocieta(Societa societa) {
		this.societa = societa;
	}

	public void setRisultati(List<Risultato> risultati) {
		this.risultati = risultati;
	}	
}	
