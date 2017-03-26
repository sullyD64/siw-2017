package it.uniroma3.db.products2;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Provider {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private String name;
	private String email;
	@OneToOne
	private Address address;
	@ManyToMany
	private List<Product> products;
	
	public Provider(String name, String email, Address address) {
		this.name = name;
		this.email = email;
		this.address = address;
		this.products = new ArrayList<>();
	}
	
	//	Getters & Setters

	public Long getId() { return this.id; }
	public String getName() { return this.name; }
	public String getEmail() { return this.email; }
	public Address getAddress() { return this.address; }
	public List<Product> getProducts() { return this.products; }

	public void setId(Long id) { this.id = id; }
	public void setName(String name) { this.name = name; }
	public void setEmail(String email) { this.email = email; }
	public void setAddress(Address address) { this.address = address; }
	public void setProducts(List<Product> products) { this.products = products; }

	
	//	Hashcode, Equals, Tostring
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((products == null) ? 0 : products.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Provider other = (Provider) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (products == null) {
			if (other.products != null)
				return false;
		} else if (!products.equals(other.products))
			return false;
		return true;
	}	

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Product");
		sb.append("{id=").append(id);
		sb.append(", name='").append(name);
		sb.append(", email='").append(email);
		sb.append(", address='").append(address.toString());
		//sb.append(", products='").append(products.toString());
		sb.append("'}\n");
		return sb.toString();
	}

}
