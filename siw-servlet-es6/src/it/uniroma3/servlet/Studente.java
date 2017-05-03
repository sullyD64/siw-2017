package it.uniroma3.servlet;

import java.util.Date;

public class Studente {
	private String nome;
	private String cognome;
	private Integer matricola;
	private Date dataNascita;
	
	public Studente(String nome, String cognome, Integer matricola, Date dataNascita) {
		this.nome = nome;
		this.cognome = cognome;
		this.matricola = matricola;
		this.dataNascita = dataNascita;
	}
	
	public String getNome() {
		return nome;
	}
	public String getCognome() {
		return cognome;
	}
	public Integer getMatricola() {
		return matricola;
	}
	public Date getDataNascita() {
		return dataNascita;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public void setMatricola(Integer matricola) {
		this.matricola = matricola;
	}
	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}
	
	
}
