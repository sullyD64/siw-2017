package it.uniroma3.triathlon.util;

import java.time.LocalDate;
import java.time.Period;

import javax.annotation.ManagedBean;

import static it.uniroma3.triathlon.util.CostantiCategorie.CATEGORIE;

@ManagedBean
public class Calcolatore {
	  
    public static boolean convalidaEta(LocalDate date) {
    	return (Period.between(date, LocalDate.now()).getYears() < 14);
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
    
	public static String calcolaCategoria(int age) {
		return CATEGORIE.floorEntry(age).getValue();
	}
}
