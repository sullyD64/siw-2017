package it.uniroma3.db.products2;

import javax.persistence.*;



@Entity
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private String street;
	@Column(nullable = false)
	private String city;
	private String state;
	@Column(length = 8, nullable = false)
	private String zipcode;
	@Column(nullable = false)
	private String country;
	
	public Address(Long id, String street, String city, String state, String zipcode, String country) {
		super();
		this.id = id;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.country = country;
	}
	
	//	Getters & Setters
	
	public Long getId() { return this.id; }
	public String getStreet() { return this.street; }
	public String getCity() { return this.city; }
	public String getState() { return this.state; }
	public String getZipcode() { return this.zipcode; }
	public String getCountry() { return this.country; }
	
	public void setId(Long id) {this.id = id;}
	public void setStreet(String street) {this.street = street;}
	public void setCity(String price) {this.city = price;}
	public void setState(String state) {this.state = state;}
	public void setZipcode(String zipcode) {this.zipcode = zipcode;}
	public void setCountry(String country) {this.country = country;}
	
	//	Hashcode, Equals, Tostring
	
	@Override
	public int hashCode() {
		return this.street.hashCode() + this.zipcode.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		if (zipcode == null) {
			if (other.zipcode != null)
				return false;
		} else if (!zipcode.equals(other.zipcode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Address");
		sb.append("{id=").append(id);
		sb.append(", street='").append(street);
		sb.append(", city='").append(city);
		sb.append(", state='").append(state);
		sb.append(", zipcode='").append(zipcode);
		sb.append(", country='").append(country);
		sb.append("'}\n");
		return sb.toString();
	}	
}

