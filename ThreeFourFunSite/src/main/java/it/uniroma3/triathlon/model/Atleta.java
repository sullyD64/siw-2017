package it.uniroma3.triathlon.model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="atleti", uniqueConstraints = @UniqueConstraint(columnNames={"nome","cognome","dataNascita"}))
public class Atleta {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Size(min = 2, max = 25)
	@Column(nullable = false)
	private String nome;
	
	@NotNull
	@Size(min = 2, max = 25)
	@Column(nullable = false)
	private String cognome;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(nullable = false)
	private LocalDate dataNascita;
	
	@NotNull
	@Column(nullable = false)
	private String sesso;
	
	@NotNull
	@Column(nullable = false)
	private String nazione;
	
	@Column(nullable = false)
	private Integer eta;

	@Column(nullable = false)
	private String categoria;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private Societa societa;
	
	@OneToMany(mappedBy = "atletaPartecipante")
	private List<Risultato> risultati;
	
	public Atleta(String nome, String cognome, LocalDate dataNascita, String sesso, String nazione) {
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
	
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public LocalDate getDataNascita() {
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

	public void setDataNascita(LocalDate dataNascita) {
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
	
	public String getNomeCompleto() {
		return (this.getNome() +" "+ this.getCognome());
	}
}	
