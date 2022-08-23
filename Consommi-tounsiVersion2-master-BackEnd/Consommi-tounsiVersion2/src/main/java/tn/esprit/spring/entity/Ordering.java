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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import tn.esprit.spring.entity.MethodPayment;

@Entity
@Table (name = "orderm")
public class Ordering implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id_order ;
	@Temporal(TemporalType.DATE)
	private Date oder_date;
	private float delivery_fees;
	private float sum;
	@Enumerated(EnumType.STRING)
	private MethodPayment payment_method;
	private boolean status;
	
	public int getId_order() {
		return id_order;
	}
	public void setId_order(int id_order) {
		this.id_order = id_order;
	}
	public Date getOder_date() {
		return oder_date;
	}
	public void setOder_date(Date oder_date) {
		this.oder_date = oder_date;
	}
	public float getDelivery_fees() {
		return delivery_fees;
	}
	public void setDelivery_fees(float delivery_fees) {
		this.delivery_fees = delivery_fees;
	}
	public float getSum() {
		return sum;
	}
	public void setSum(float sum) {
		this.sum = sum;
	}
	public MethodPayment getPayment_method() {
		return payment_method;
	}
	public void setPayment_method(MethodPayment payment_method) {
		this.payment_method = payment_method;
	}
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@ManyToOne
	//association deliverymethod and order 
	private DeliveryMethod deliverymethod;
	
	
	public DeliveryMethod getDeliverymethod() {
		return deliverymethod;
	}
	public void setDeliverymethod(DeliveryMethod deliverymethod) {
		this.deliverymethod = deliverymethod;
	}
	//association delivery and order
	@OneToOne
	@JoinColumn(name = "id_delivery")
	private Delivery delivery;
	public Delivery getDelivery() {
		return delivery;
	}
	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}
	@ManyToOne
	private Client client;
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	
	
	@OneToOne
	private Cart cart;


	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	public Ordering(int id_order, Date oder_date, float delivery_fees, float sum) {
		super();
		this.id_order = id_order;
		this.oder_date = oder_date;
		this.delivery_fees = delivery_fees;
		this.sum = sum;
		
		
		this.status = status;
	}
	
	public Ordering( Date oder_date, float delivery_fees, float sum, 
			MethodPayment payment_methods, DeliveryMethod deliverymethod, Delivery delivery
		, Cart cart) {
		super();
		this.id_order = id_order;
		this.oder_date = oder_date;
		this.delivery_fees = delivery_fees;
		this.sum = sum;
		this.payment_method = payment_method;
		this.status = status;
		this.deliverymethod = deliverymethod;
		this.delivery = delivery;
		this.client = client;
		this.cart = cart;
	}
	
	
}
