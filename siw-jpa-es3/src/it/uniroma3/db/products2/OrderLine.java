package it.uniroma3.db.products2;

import javax.persistence.*;

@Entity
@Table(name = "order_line")
public class OrderLine {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@OneToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_product_id"))
	private Product product;
	private int quantity;
	private Float price;
	
	public OrderLine(Product product, int quantity, Float price) {
		this.product = product;
		this.quantity = quantity;
		this.price = price;
	}
	
	// Getters & Setters	
	
	public Long getId() { return this.id; }
	public Product getProduct() { return this.product; }
	public int getQuantity() { return this.quantity; } 
	public Float getPrice() { return this.price; }
	
	public void setId(Long id) { this.id = id; }
	public void setItem(Product product) { this.product = product; }
	public void setQuantity(int quantity) { this.quantity = quantity; }
	public void setprice(Float price) { this.price = price; }

	// Hashcode, Equals, Tostring
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + quantity;
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
		OrderLine other = (OrderLine) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("OrderLine");
		sb.append("{id=").append(id);
		sb.append(", product='").append(product.toString());
		sb.append(", quantity='").append(quantity);
		sb.append(", price='").append(price);
		sb.append("'}\n");
		return sb.toString();
	}
}
