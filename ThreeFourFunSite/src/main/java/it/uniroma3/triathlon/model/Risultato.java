package it.uniroma3.triathlon.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="risultati")
public class Risultato {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private boolean garaEffettuata;
	@ManyToOne
	private Atleta atletaPartecipante;
	@ManyToOne
	private Gara gara;
	@Temporal(TemporalType.TIMESTAMP)
	private Date tempoNuoto;
	@Temporal(TemporalType.TIMESTAMP)
	private Date tempoBici;
	@Temporal(TemporalType.TIMESTAMP)
	private Date tempoCorsa;
	
	public Risultato(Atleta atletaPartecipante, Gara gara) {
		this.atletaPartecipante = atletaPartecipante;
		this.gara = gara;
		this.garaEffettuata = false;
	}
	
	public Risultato() {
		this.garaEffettuata = false;
	}
	
	public boolean isGaraEffettuata() {
		return garaEffettuata;
	}
	public Atleta getAtletaPartecipante() {
		return atletaPartecipante;
	}
	public Gara getGara() {
		return gara;
	}
	public Date getTempoNuoto() {
		return tempoNuoto;
	}
	public Date getTempoBici() {
		return tempoBici;
	}
	public Date getTempoCorsa() {
		return tempoCorsa;
	}
	public void setGaraEffettuata(boolean garaEffettuata) {
		this.garaEffettuata = garaEffettuata;
	}
	public void setAtletaPartecipante(Atleta atletaPartecipante) {
		this.atletaPartecipante = atletaPartecipante;
	}
	public void setGara(Gara gara) {
		this.gara = gara;
	}
	public void setTempoNuoto(Date tempoNuoto) {
		this.tempoNuoto = tempoNuoto;
	}
	public void setTempoBici(Date tempoBici) {
		this.tempoBici = tempoBici;
	}
	public void setTempoCorsa(Date tempoCorsa) {
		this.tempoCorsa = tempoCorsa;
	}
}
