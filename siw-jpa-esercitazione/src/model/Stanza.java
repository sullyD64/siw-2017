package model;

import javax.persistence.*;
import java.util.*;

@Entity
public class Stanza {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="stanza_id")
	private List<Opera> opere;
	@ManyToOne
	private Curatore curatore;
	
	public Stanza() {
		this.opere = new ArrayList<>();
	}
	
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public List<Opera> getOpere() {
		return opere;
	}
	public Curatore getCuratore() {
		return curatore;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setOpere(List<Opera> opere) {
		this.opere = opere;
	}
	public void setCuratore(Curatore curatore) {
		this.curatore = curatore;
	}
}
