package it.uniroma3.triathlon.util;

import java.util.NavigableMap;
import java.util.TreeMap;

public class CostantiCategorie {
	
	private static final String YOUTH_A = "YA";
	private static final String YOUTH_B = "YB";
	private static final String JUNIOR = "JU";
	private static final String SENIOR_1 = "S1";
	private static final String SENIOR_2 = "S2";
	private static final String SENIOR_3 = "S3";
	private static final String SENIOR_4 = "S4";
	private static final String MASTER_1 = "M1";
	private static final String MASTER_2 = "M2";
	private static final String MASTER_3 = "M3";
	private static final String MASTER_4 = "M4";
	private static final String MASTER_5 = "M5";
	private static final String MASTER_6 = "M6";
	private static final String MASTER_7 = "M7";
	private static final String MASTER_8 = "M8";
	
	public static final String FEMMINILE = "F";
	public static final NavigableMap<Integer, String> CATEGORIE = getCategorie();
	
	public static NavigableMap<Integer, String> getCategorie(){
		NavigableMap<Integer, String> categorie = new TreeMap<>();
		
		categorie.put(Integer.MIN_VALUE, null);
		categorie.put(14, YOUTH_A);
		categorie.put(16, YOUTH_B);
		categorie.put(18, JUNIOR);
		categorie.put(20, SENIOR_1);
		categorie.put(25, SENIOR_2);
		categorie.put(30, SENIOR_3);
		categorie.put(35, SENIOR_4);
		categorie.put(40, MASTER_1);
		categorie.put(45, MASTER_2);
		categorie.put(50, MASTER_3);
		categorie.put(55, MASTER_4);
		categorie.put(60, MASTER_5);
		categorie.put(65, MASTER_6);
		categorie.put(70, MASTER_7);
		categorie.put(75, MASTER_8);

		return categorie;
	}
}
