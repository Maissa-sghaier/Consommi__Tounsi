package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.List;
//import java.util.Set;
//import java.util.Set;

import javax.persistence.CascadeType;
//import javax.persistence.CascadeType;
//import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import javax.persistence.Table;


@Entity
@Table(name = "DeliveryMethod")
public class DeliveryMethod implements Serializable {

	

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_deliverym;
	
	private double fees;
	
	private String type;

	
	
	//association delivery and order 
	@OneToMany(mappedBy="deliverymethod",cascade=CascadeType.REMOVE)
	 private List<Ordering> orders;
	

	public List<Ordering> getOrders() {
		return orders;
	}

	public void setOrders(List<Ordering> orders) {
		this.orders = orders;
	}


	public DeliveryMethod(int id_deliverym, double fees, String type) {


	

		super();
		this.id_deliverym = id_deliverym;
		this.fees = fees;
		this.type = type;
	}
	
	public DeliveryMethod() {
		super();
	}

	public int getId() {
		return id_deliverym;
	}

	public void setId(int id_delivery) {
		this.id_deliverym = id_delivery;
	}

	public double getFees() {
		return fees;
	}

	public void setFees(double fees) {
		this.fees = fees;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	/*@OneToMany(cascade = CascadeType.ALL, mappedBy="delivery")
	private Set<Order> Orders;*/

}
