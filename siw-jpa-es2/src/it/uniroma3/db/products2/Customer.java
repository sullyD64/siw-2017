package it.uniroma3.db.products2;

import javax.persistence.*;
import java.util.List;
import java.util.Date;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"firstName","lastName"}, name = ("uc_name")))
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String lastName;
	private String email;
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	@OneToOne
	private Address address;
	@OneToMany(mappedBy ="customer")
	@OrderBy("creationTime asc")
	private List<Order> orders;
	
	public Customer(String firstName, String lastName, String email, Date dateOfBirth, Address address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
	}
	
	//	Getters & Setters

	public Long getId() { return this.id; }
	public String getFirstName() { return this.firstName; }
	public String getLastName() { return this.lastName; }
	public String getEmail() { return this.email; }
	public Date getDateOfBirth() { return this.dateOfBirth; }
	public Address getAddress() { return this.address; }
	public List<Order> getOrders() { return this.orders; }
	
	public void setId(Long id) {this.id = id;}
	public void setFirstName(String firstName) {this.firstName = firstName;}
	public void setLastName(String price) {this.lastName = price;}
	public void setEmail(String email) {this.email = email;}
	public void setDateOfBirth(Date dateOfBirth) {this.dateOfBirth = dateOfBirth;}
	public void setAddress(Address address) {this.address = address;}
	public void setOrders(List<Order> orders) {this.orders = orders;}
	
	//	Hashcode, Equals, Tostring

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((orders == null) ? 0 : orders.hashCode());
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
		Customer other = (Customer) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (orders == null) {
			if (other.orders != null)
				return false;
		} else if (!orders.equals(other.orders))
			return false;
		return true;
	}	
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Address");
		sb.append("{id=").append(id);
		sb.append(", firstName='").append(firstName);
		sb.append(", lastName='").append(lastName);
		sb.append(", email='").append(email);
		sb.append(", dateOfBirth='").append(dateOfBirth.toString());
		sb.append(", address='").append(address.toString());
		sb.append("'}\n");
		return sb.toString();
	}
}
