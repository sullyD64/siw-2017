package it.uniroma3.db.products2;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private String name;
	private Float price;
	@Column(length = 2000)
	private String description;
	@Column(unique = true, nullable = false)
	private String code;
	@ManyToMany(mappedBy = "products")
	private List<Provider> providers;
	
	public Product(String name, Float price, String description, String code) {
		this.providers = new ArrayList<>();
	}
	
	//	Getters & Setters

	public Long getId() {return this.id; }
	public String getName() {return this.name; }
	public Float getPrice() {return this.price; }
	public String getDescription() {return this.description; }
	public String getCode() {return this.code; }
	public List<Provider> getProviders() { return this.providers; }

	public void setId(Long id) {this.id = id; }
	public void setName(String name) {this.name = name; }
	public void setPrice(Float price) {this.price = price; }
	public void setDescription(String description) {this.description = description; }
	public void setCode(String code) {this.code = code;}
	public void setProviders(List<Provider> providers) { this.providers = providers; }
	
	//	Hashcode, Equals, Tostring
	
	@Override
	public int hashCode() {
		return this.code.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		Product product = (Product)obj;
		return this.getCode().equals(product.getCode());
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Product");
		sb.append("{id=").append(id);
		sb.append(", name='").append(name);
		sb.append(", price='").append(price);
		sb.append(", description='").append(description);
		sb.append(", code='").append(code);
		//sb.append(", providers='").append(providers.toString());
		sb.append("'}\n");
		return sb.toString();
	}	
}

