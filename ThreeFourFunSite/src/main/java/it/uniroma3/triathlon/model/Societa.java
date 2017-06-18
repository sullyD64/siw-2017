package it.uniroma3.triathlon.model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
public class Societa {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Size(min = 2, max = 40)
	@Column(nullable = false)
	private String nome;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(nullable = false)
	private LocalDate dataFondazione;

	@NotNull
	@Column(nullable = false)
	private String regione;

	@OneToOne(optional = false)
	private Atleta presidente;

	@OneToMany(mappedBy = "societa", fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
	private List<Atleta> atleti;

	public Societa(String nome, LocalDate dataFondazione, String regione) {
		this.nome = nome;
		this.dataFondazione = dataFondazione;
		this.regione = regione;
		this.atleti = new LinkedList<>();
	}

	public Societa() {
		this.atleti = new LinkedList<>();
	}

	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	public LocalDate getDataFondazione() {
		return dataFondazione;
	}
	public String getRegione() {
		return regione;
	}
	public Atleta getPresidente() {
		return presidente;
	}
	public List<Atleta> getAtleti() {
		return atleti;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setDataFondazione(LocalDate dataFondazione) {
		this.dataFondazione = dataFondazione;
	}
	public void setRegione(String regione) {
		this.regione = regione;
	}
	public void setPresidente(Atleta presidente) {
		this.presidente = presidente;
	}
	public void setAtleti(List<Atleta> atleti) {
		this.atleti = atleti;
	}


}
