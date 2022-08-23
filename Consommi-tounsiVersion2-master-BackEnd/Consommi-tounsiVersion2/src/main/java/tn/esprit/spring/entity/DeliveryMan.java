package tn.esprit.spring.entity;

import java.util.List;
//import java.util.Set;

//import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class DeliveryMan extends User  {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id_user;

	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	private int rating_deliveryMan;
	private int cin;
	@Enumerated(EnumType.STRING)
	private DeliveryZone Address;
	public DeliveryZone getAddress() {
		return Address;
	}
	public void setAddress(DeliveryZone address) {
		Address = address;
	}
	private float salary;
	private float bonus;
	private String city;
	@Enumerated(EnumType.STRING)
	
	private StatusDeliveryman status_delivery_man;

	

	@OneToMany(mappedBy = "deliveryman")
	private List<Delivery> delivery;
	
	
	public List<Delivery> getDelivery() {
		return delivery;
	}
	public void setDelivery(List<Delivery> delivery) {
		this.delivery = delivery;
	}
	
	public StatusDeliveryman getStatus_delivery_man() {
		return status_delivery_man;
	}
	public void setStatus_delivery_man(StatusDeliveryman status_delivery_man) {
		this.status_delivery_man = status_delivery_man;
	}
	
	
	
	
	public int getRating_deliveryMan() {
		return rating_deliveryMan;
	}
	public void setRating_deliveryMan(int rating_deliveryMan) {
		this.rating_deliveryMan = rating_deliveryMan;
	}
	public int getCin() {
		return cin;
	}
	public void setCin(int cin) {
		this.cin = cin;
	}
	public DeliveryMan() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public float getSalary() {
		return salary;
	}
	public void setSalary(float salary) {
		this.salary = salary;
	}
	public float getBonus() {
		return bonus;
	}
	public void setBonus(float bonus) {
		this.bonus = bonus;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

}
