package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Delivery")

public class Delivery implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_delivery;
	@Enumerated(EnumType.STRING)
	private DeliveryZone delivery_zone;
	private Date date_delivery;
	@Enumerated(EnumType.STRING)
	private StatusOrder status_order;
	private String actual_adress;
	
	
	public String getActual_adress() {
		return actual_adress;
	}


	public void setActual_adress(String actual_adress) {
		this.actual_adress = actual_adress;
	}


	public StatusOrder getStatus_order() {
		return status_order;
	}


	public void setStatus_order(StatusOrder status_order) {
		this.status_order = status_order;
	}


	public Date getDate_delivery() {
		return date_delivery;
	}


	public void setDate_delivery(Date date_delivery) {
		this.date_delivery = date_delivery;
	}


	public DeliveryZone getDelivery_zone() {
		return delivery_zone;
	}


	public void setDelivery_zone(DeliveryZone delivery_zone) {
		this.delivery_zone = delivery_zone;
	}
	@ManyToOne
	private DeliveryMan deliveryman;
	@JsonIgnore
	
	
	public DeliveryMan getDeliveryman() {
		return deliveryman;
	}


	public void setDeliveryman(DeliveryMan deliveryman) {
		this.deliveryman = deliveryman;
		
	}
	@OneToOne
	@JoinColumn(name = "id_order")
	private Ordering Order;

	@JsonIgnore
	public Ordering getOrder() {
		return Order;
	}


	public void setOrder(Ordering order) {
		Order = order;
	}


	public int getId_delivery() {
		return id_delivery;
	}


	public void setId_delivery(int id_delivery) {
		this.id_delivery = id_delivery;
	}


	public Delivery() {
		super();
		
		
	}
	public Delivery(int id_delivery, DeliveryZone delivery_zone, Date date_delivery, StatusOrder status_order,String actual_adress,
			DeliveryMan deliveryman) {
		super();
		this.id_delivery = id_delivery;
		this.delivery_zone = delivery_zone;
		this.date_delivery = date_delivery;
		this.status_order = status_order;
		this.actual_adress=actual_adress;
	}

}
