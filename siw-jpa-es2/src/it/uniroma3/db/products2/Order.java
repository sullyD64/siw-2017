package it.uniroma3.db.products2;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;
    @Temporal(TemporalType.DATE)
    private Date deliveryDate;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name=("fk_customer_id")))
    private Customer customer;
    @OneToMany
    @JoinColumn(name="orders_id", foreignKey = @ForeignKey(name="fk_orders_id"))
    private List<OrderLine> orderLines;
   
	public Order() {
		this.orderLines = new ArrayList<>();
	}
	
	// Getters & Setters	
	
	public Long getId() { return this.id; }
	public Date getCreationTime() { return this.creationTime; }
	public Date getDeliveryDate() { return this.deliveryDate; } 
	public Customer getCustomer() { return this.customer; }
	public List<OrderLine> getOrderLines() { return this.orderLines; }
	
	public void setId(Long id) { this.id = id; }
	public void setCreationTime(Date creationTime) { this.creationTime = creationTime; }
	public void setDeliveryDate(Date deliveryDate) { this.deliveryDate = deliveryDate; }
	public void setCustomer(Customer customer) { this.customer = customer; }
	public void setOrderLines(List<OrderLine> orderLines) { this.orderLines = orderLines; }

	// Hashcode, Equals, Tostring

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creationTime == null) ? 0 : creationTime.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((deliveryDate == null) ? 0 : deliveryDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((orderLines == null) ? 0 : orderLines.hashCode());
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
		Order other = (Order) obj;
		if (creationTime == null) {
			if (other.creationTime != null)
				return false;
		} else if (!creationTime.equals(other.creationTime))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (deliveryDate == null) {
			if (other.deliveryDate != null)
				return false;
		} else if (!deliveryDate.equals(other.deliveryDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (orderLines == null) {
			if (other.orderLines != null)
				return false;
		} else if (!orderLines.equals(other.orderLines))
			return false;
		return true;
	}
		
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Address");
		sb.append("{id=").append(id);
		sb.append(", creationTime='").append(creationTime.toString());
		sb.append(", deliveryDate='").append(deliveryDate.toString());
		sb.append(", customer='").append(customer.toString());
		//sb.append(", orderLines='").append(orderLines.toString());
		sb.append("'}\n");
		return sb.toString();
	}
}

