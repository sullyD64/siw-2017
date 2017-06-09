package it.uniroma3.triathlon.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class Societa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataFondazione;
	@Column(nullable = false)
	private String regione;
	@OneToOne
	private Atleta presidente;
	@OneToMany(mappedBy = "societa")
	private List<Atleta> atleti;
	
	public Societa(String nome, Date dataFondazione, String regione, Atleta presidente) {
		this.nome = nome;
		this.dataFondazione = dataFondazione;
		this.regione = regione;
		this.presidente = presidente;
		this.atleti = new LinkedList<>();
	}
	
	public Societa() {
		this.atleti = new LinkedList<>();
	}
	
	public String getNome() {
		return nome;
	}
	public Date getDataFondazione() {
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
	public void setDataFondazione(Date dataFondazione) {
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
