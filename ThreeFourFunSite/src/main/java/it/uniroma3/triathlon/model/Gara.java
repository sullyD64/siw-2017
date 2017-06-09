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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Gara {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private String luogo;
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataSvolgimento;
	@OneToMany(mappedBy = "gara")
	private List<Risultato> risultati;
	
	public Gara(String luogo, Date dataSvolgimento) {
		this.luogo = luogo;
		this.dataSvolgimento = dataSvolgimento;
		this.risultati = new LinkedList<>();
	}
	
	public Gara() {
		this.risultati = new LinkedList<>();
	}
	
	public String getLuogo() {
		return luogo;
	}
	public Date getDataSvolgimento() {
		return dataSvolgimento;
	}
	public List<Risultato> getRisultati() {
		return risultati;
	}
	public void setLuogo(String luogo) {
		this.luogo = luogo;
	}
	public void setDataSvolgimento(Date dataSvolgimento) {
		this.dataSvolgimento = dataSvolgimento;
	}
	public void setRisultati(List<Risultato> risultati) {
		this.risultati = risultati;
	}
	
	
	
}
