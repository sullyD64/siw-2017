package it.uniroma3.db.products2;

import javax.persistence.*;



@Entity
@Table(name="order_line")
public class OrderLine {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String item;
	private int quantity;
	private Float price;
	
	public OrderLine(Long id, String item, int quantity, Float price) {
		this.id = id;
		this.item = item;
		this.quantity = quantity;
		this.price = price;
	}
	
	// Getters & Setters	
	
	public Long getId() { return this.id; }
	public String getItem() { return this.item; }
	public int getQuantity() { return this.quantity; } 
	public Float getPrice() { return this.price; }
	
	public void setId(Long id) { this.id = id; }
	public void setItem(String item) { this.item = item; }
	public void setQuantity(int quantity) { this.quantity = quantity; }
	public void setprice(Float price) { this.price = price; }

	// Hashcode, Equals, Tostring
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((item == null) ? 0 : item.hashCode());
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
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
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
		sb.append(", item='").append(item);
		sb.append(", quantity='").append(quantity);
		sb.append(", price='").append(price);
		sb.append("'}\n");
		return sb.toString();
	}
}
