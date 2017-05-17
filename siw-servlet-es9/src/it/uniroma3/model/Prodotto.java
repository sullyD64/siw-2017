package it.uniroma3.model;

import java.util.Date;

public class Prodotto {
	private String nome;
	private String desc;
	private Float prezzo;
	private Date dataScadenza;
	
	public Prodotto() {
	}
	
	public String getNome() {
		return nome;
	}
	public String getDesc() {
		return desc;
	}
	public Float getPrezzo() {
		return prezzo;
	}
	public Date getDataScadenza() {
		return dataScadenza;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public void setPrezzo(Float prezzo) {
		this.prezzo = prezzo;
	}
	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}
	
}
