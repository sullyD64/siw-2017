package it.uniroma3.triathlon.util;

import java.time.LocalDate;
import java.time.Period;

import javax.annotation.ManagedBean;

import static it.uniroma3.triathlon.util.CostantiCategorie.CATEGORIE;
import static it.uniroma3.triathlon.util.CostantiCategorie.FEMMINILE;

@ManagedBean
public class Calcolatore {

	public static boolean convalidaEtaAtleta(LocalDate date) {
		return !(Period.between(date, LocalDate.now()).getYears() < 14);
	}

	public static boolean convalidaEtaSocieta(LocalDate date) {
		return (Period.between(LocalDate.of(1900,1,1), date).getDays() > 0) &&
				(Period.between(date, LocalDate.now()).getDays() > 0);
	}

	public static int calcolaEta(LocalDate birthDate, LocalDate currentDate) {
		if ((birthDate != null) && (currentDate != null)) {
			return Period.between(birthDate, currentDate).getYears();
		} else {
			return 0;
		}
	}

	public static int calcolaEta(LocalDate birthDate) {
		if (birthDate != null) {
			return Period.between(birthDate, LocalDate.now()).getYears();
		} else {
			return 0;
		}
	}

	public static String calcolaCategoria(int eta, String sesso) {
		String cat = CATEGORIE.floorEntry(eta).getValue();
		
		if (eta >= 20 && sesso.equals("F"))
			cat = FEMMINILE;

		return cat;	
	}
}
