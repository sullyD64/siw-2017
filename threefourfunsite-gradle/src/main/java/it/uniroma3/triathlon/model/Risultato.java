package it.uniroma3.triathlon.model;

import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="risultati", uniqueConstraints = @UniqueConstraint(columnNames={"atletaPartecipante","gara"}))
public class Risultato implements Comparable<Risultato> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private boolean valido;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name = "atletaPartecipante")
	private Atleta atletaPartecipante;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name = "gara")
	private Gara gara;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalTime tempoSwim;
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalTime tempoBike;
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalTime tempoRun;
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalTime tempoTot;
	
	public Risultato(Atleta atletaPartecipante, Gara gara) {
		this.atletaPartecipante = atletaPartecipante;
		this.gara = gara;
		this.valido = false;
	}
	
	public Risultato() {
		this.valido = false;
	}
	
	public Long getId() {
		return id;
	}
	
	public boolean isValido() {
		return valido;
	}
	public Atleta getAtletaPartecipante() {
		return atletaPartecipante;
	}
	public Gara getGara() {
		return gara;
	}
	public LocalTime getTempoSwim() {
		return tempoSwim;
	}
	public LocalTime getTempoBike() {
		return tempoBike;
	}
	public LocalTime getTempoRun() {
		return tempoRun;
	}
	public LocalTime getTempoTot() {
		return tempoTot;
	}
	public void setValido(boolean valido) {
		this.valido = valido;
	}
	public void setAtletaPartecipante(Atleta atletaPartecipante) {
		this.atletaPartecipante = atletaPartecipante;
	}
	public void setGara(Gara gara) {
		this.gara = gara;
	}
	public void setTempoSwim(LocalTime tempoNuoto) {
		this.tempoSwim = tempoNuoto;
	}
	public void setTempoBike(LocalTime tempoBike) {
		this.tempoBike = tempoBike;
	}
	public void setTempoRun(LocalTime tempoRun) {
		this.tempoRun = tempoRun;
	}	
	public void setTempoTot(LocalTime tempoTot) {
		this.tempoTot = tempoTot;
	}
	
	public void resetTempi() {
		this.setTempoSwim(null);
		this.setTempoBike(null);
		this.setTempoRun(null);
		this.setTempoTot(null);
	}

	@Override
	public int compareTo(Risultato that) {
		return this.getAtletaPartecipante().getNomeCompleto().compareTo(that.getAtletaPartecipante().getNomeCompleto());
	}
}
