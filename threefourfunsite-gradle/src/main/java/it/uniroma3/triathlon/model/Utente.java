package it.uniroma3.triathlon.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="utenti")
public class Utente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(unique = true)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@NotNull
	@Column(nullable = false)
	private boolean enabled;
	
	@OneToOne(cascade = {CascadeType.PERSIST})
	private Atleta atletaGestito;
	@OneToOne(cascade = {CascadeType.PERSIST})
	private Societa societaGestita;

	public boolean isEnabled(){
		return enabled;
	}
	
	public void setEnabled(boolean enabled){
		this.enabled = enabled;
	}
	
	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Atleta getAtletaGestito() {
		return atletaGestito;
	}

	public void setAtletaGestito(Atleta atleta) {
		this.atletaGestito = atleta;
	}
	
	public Societa getSocietaGestita() {
		return societaGestita;
	}
	
	public void setSocietaGestita(Societa societaGestita) {
		this.societaGestita = societaGestita;
	}
	
	public boolean hasAtletaGestito() {
		return this.atletaGestito!=null;
	}
	
	public boolean hasSocietaGestita() {
		return this.societaGestita!=null;
	}

	@Override
	public String toString() {
		return "Utente [id=" + id + ", username=" + username + ", password=" + password + ", enabled=" + enabled
				+ ", atletaGestito=" + atletaGestito + ", societaGestita=" + societaGestita + "]";
	}
}
