package it.uniroma3.triathlon.classifiche;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.uniroma3.triathlon.model.Gara;
import it.uniroma3.triathlon.model.Risultato;

public class Classifica {

	private List<RigaClassifica> righeClassifica;
	
	public Classifica (Gara gara) {
		this.righeClassifica = new ArrayList<>();
		
		for (Risultato risultato : gara.getRisultati()) {
			this.righeClassifica.add(new RigaClassifica(risultato));
		}
	}
	
	public void ordinaClassifica() {
		List<RigaClassifica> righe = this.getRigheClassifica();
		Collections.sort(righe);
		
		for (RigaClassifica riga : righe) 
			riga.setPosizione(righe.indexOf(riga) + 1);
	}
	
	public List<RigaClassifica> getRigheClassifica() {
		return righeClassifica;
	}

	public void setRigheClassifica(List<RigaClassifica> righeClassifica) {
		this.righeClassifica = righeClassifica;
	}
}
