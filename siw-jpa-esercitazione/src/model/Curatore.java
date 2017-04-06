package model;

import javax.persistence.*;
import java.util.*;

@Entity
public class Curatore {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	@Temporal(TemporalType.DATE)
	private Date dataDiNascita;
	@OneToMany(mappedBy = "curatore")
	private List<Stanza> stanze;
	
	public Curatore() {
		this.stanze = new ArrayList<>();
	}
	
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public Date getDataDiNascita() {
		return dataDiNascita;
	}
	public List<Stanza> getStanze() {
		return stanze;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setDataDiNascita(Date dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}
	public void setStanze(List<Stanza> stanze) {
		this.stanze = stanze;
	} 
}
