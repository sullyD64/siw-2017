package it.uniroma3.triathlon.classifiche;

import java.time.LocalTime;

import it.uniroma3.triathlon.model.Risultato;

public class RigaClassifica implements Comparable<RigaClassifica> {

	private Integer posizione;
	// private Integer punteggio;
	
	private String nomeCognomeAtleta;
	private String categoriaAtleta;
	private String nomeSocietaAtleta;
	private String regioneSocietaAtleta;
	
	private LocalTime tempoTot;
	private LocalTime tempoSwim;
	private LocalTime tempoBike;
	private LocalTime tempoRun;
	
	public RigaClassifica(Risultato risultato) {
		this.nomeCognomeAtleta = risultato.getAtletaPartecipante().getNomeCompleto();
		this.categoriaAtleta = risultato.getAtletaPartecipante().getCategoria();
		this.nomeSocietaAtleta = risultato.getAtletaPartecipante().getSocieta().getNome();
		this.regioneSocietaAtleta = risultato.getAtletaPartecipante().getSocieta().getRegione();
		
		this.tempoTot = risultato.getTempoTot();
		this.tempoSwim = risultato.getTempoSwim();
		this.tempoBike = risultato.getTempoBike();
		this.tempoRun = risultato.getTempoRun();
	}

	public Integer getPosizione() {
		return posizione;
	}

	public String getNomeCognomeAtleta() {
		return nomeCognomeAtleta;
	}

	public String getCategoriaAtleta() {
		return categoriaAtleta;
	}

	public String getNomeSocietaAtleta() {
		return nomeSocietaAtleta;
	}

	public String getRegioneSocietaAtleta() {
		return regioneSocietaAtleta;
	}
	
	public LocalTime getTempoTot() {
		return tempoTot;
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

	public void setPosizione(Integer posizione) {
		this.posizione = posizione;
	}

	public void setNomeCognomeAtleta(String nomeCognomeAtleta) {
		this.nomeCognomeAtleta = nomeCognomeAtleta;
	}

	public void setCategoriaAtleta(String categoriaAtleta) {
		this.categoriaAtleta = categoriaAtleta;
	}

	public void setNomeSocietaAtleta(String nomeSocietaAtleta) {
		this.nomeSocietaAtleta = nomeSocietaAtleta;
	}
	
	public void setRegioneSocietaAtleta(String regioneSocietaAtleta) {
		this.regioneSocietaAtleta = regioneSocietaAtleta;
	}

	public void setTempoTot(LocalTime tempoTot) {
		this.tempoTot = tempoTot;
	}

	public void setTempoSwim(LocalTime tempoSwim) {
		this.tempoSwim = tempoSwim;
	}

	public void setTempoBike(LocalTime tempoBike) {
		this.tempoBike = tempoBike;
	}

	public void setTempoRun(LocalTime tempoRun) {
		this.tempoRun = tempoRun;
	}

	@Override
	public int compareTo(RigaClassifica that) {
		return this.getTempoTot().compareTo(that.getTempoTot());
	}

}
