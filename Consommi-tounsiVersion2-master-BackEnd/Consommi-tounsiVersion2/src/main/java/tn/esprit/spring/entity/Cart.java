package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.TemporalType;

@Entity
public class Cart implements Serializable {
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id_cart;

	private double total;
	private String status_cart;
	

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId_cart() {
		return id_cart;
	}
	public void setId_cart(int id_cart) {
		this.id_cart = id_cart;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getStatus_cart() {
		return status_cart;
	}
	public void setStatus_cart(String status_cart) {
		this.status_cart = status_cart;
	}
	public Donation getDonation() {
		return donation;
	}
	public void setDonation(Donation donation) {
		this.donation = donation;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	//By lamis 
	@OneToOne(cascade=CascadeType.REMOVE)
	private Donation donation;
	//added by nour

		
		@ManyToOne
		private CodePromo codePromo;
		
		public CodePromo getCodePromo() {
			return codePromo;
		}
		public void setCodePromo(CodePromo codePromo) {
			this.codePromo = codePromo;
		}
		//end 


		@JsonManagedReference(value="coupleCP-cart")
		@OneToMany(mappedBy="cart", cascade =CascadeType.REMOVE)
		private List<CoupleCP> listCartPoducts;

		
		public List<CoupleCP> getListCartPoducts() {
			return listCartPoducts;
		}
		public void setListCartPoducts(List<CoupleCP> listCartPoducts) {
			this.listCartPoducts = listCartPoducts;
		}
		
		
}

