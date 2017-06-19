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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="gare")
public class Gara implements Comparable<Gara>{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Size(min = 2, max = 50)
	@Column(nullable = false)
	private String nomeLuogo;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(nullable = false)
	private LocalDate dataSvolgimento;
	
	@Column(nullable = false)
	private boolean completata;
	
	@OneToMany(mappedBy = "gara", cascade={ CascadeType.MERGE, CascadeType.REMOVE})
	private List<Risultato> risultati;
	
	public Gara(String nomeLuogo, LocalDate dataSvolgimento) {
		this.nomeLuogo = nomeLuogo;
		this.dataSvolgimento = dataSvolgimento;
		this.completata = false;
		this.risultati = new LinkedList<>();
	}
	
	public Gara() {
		this.risultati = new LinkedList<>();
	}
	
	public Long getId() {
		return id;
	}
	
	public boolean isCompletata() {
		return completata;
	}
	public void setCompletata(boolean effettuata) {
		this.completata = effettuata;
	}	
	public String getNomeLuogo() {
		return nomeLuogo;
	}
	public LocalDate getDataSvolgimento() {
		return dataSvolgimento;
	}
	public List<Risultato> getRisultati() {
		return risultati;
	}
	public void setNomeLuogo(String luogo) {
		this.nomeLuogo = luogo;
	}
	public void setDataSvolgimento(LocalDate dataSvolgimento) {
		this.dataSvolgimento = dataSvolgimento;
	}
	public void setRisultati(List<Risultato> risultati) {
		this.risultati = risultati;
	}

	@Override
	public int compareTo(Gara o) {
		return this.getDataSvolgimento().compareTo(o.getDataSvolgimento());
	}
	
	
	
}
