package it.tricup.model;

import javax.persistence.*;
import java.util.*;

@Entity
public class Race {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private Date date;
	
	private String region;
	
	private List<Athlete> athletes;
	
	

}
