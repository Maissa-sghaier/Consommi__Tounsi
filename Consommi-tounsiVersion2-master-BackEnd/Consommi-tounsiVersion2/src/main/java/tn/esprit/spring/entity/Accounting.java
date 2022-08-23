package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
@Table(name="accounting")
public class Accounting implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idaccounting;
	
	private double income_permonth;
	private double outcome_permonth;
	
	@Column(name="total_exp")
	private double total_exp;
	
	@Column(name="total_inc")
	private double total_inc;
	
	@Column(name="gain_permonth")
	private double gain_permonth;

	@OneToMany
	@Cascade(CascadeType.ALL)
	private List<Product> productList;
	
	@OneToMany
	@Cascade(CascadeType.ALL)
	private List<Ordering> orderList;
	
	@OneToMany
	@Cascade(CascadeType.ALL)
	private List<Ad> adList;
	

	@OneToMany
	@Cascade(CascadeType.ALL)
	private List<Fund> fundList;
	

	@OneToMany
	@Cascade(CascadeType.ALL)
	private List<Event> eventList;
	

	@OneToMany
	@Cascade(CascadeType.ALL)
	private List<Delivery> dList;
	

	@OneToMany
	@Cascade(CascadeType.ALL)
	private List<DeliveryMan> dManList;
	

//	
	public Long getId_accounting() {
		return idaccounting;
	}

	public void setId_accounting(Long id_accounting) {
		this.idaccounting = id_accounting;
	}



	public void setIncome_permonth(double income_permonth) {
		this.income_permonth =income_permonth;
	}

	public double getTotal_exp() {
		return total_exp;
	}

	public void setTotal_exp(double total_exp) {
		this.total_exp = total_exp;
	}

	public double getTotal_inc() {
		return total_inc;
	}

	public void setTotal_inc(Long total_inc) {
		this.total_inc = total_inc;
	}

	public double getGain_permonth() {
		return gain_permonth;
	}

	public void setGain_permonth(double gain_permonth) {
		this.gain_permonth = gain_permonth;
	}

	public Accounting() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Accounting(double income_permonth,double gain_permonth,double total_inc,double total_exp, double outcome_permonth) {
		this.gain_permonth=gain_permonth;
		this.total_exp=total_exp;
		this.total_inc=total_inc;
	this.income_permonth=income_permonth;
		this.outcome_permonth=outcome_permonth;
	
	}


	public double getIncome_permonth() {
		return income_permonth;
	}

	public double getOutcome_permonth() {
		return outcome_permonth;
	}

	public void setTotal_inc(double total_inc) {
		this.total_inc = total_inc;
	}

	public void setOutcome_permonth(double outcome_permonth) {
		this.outcome_permonth = outcome_permonth;
	}
}
