package it.uniroma3.model;

public class Prodotto {
	private String nome;
	private String desc;
	private String codice;
	private Float prezzo;
	
	public Prodotto() {
	}
	
	public String getNome() {
		return nome;
	}
	public String getDesc() {
		return desc;
	}
	public String getCodice() {
		return codice;
	}
	public Float getPrezzo() {
		return prezzo;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
	public void setPrezzo(Float prezzo) {
		this.prezzo = prezzo;
	}
	
}
