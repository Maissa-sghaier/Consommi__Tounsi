package tn.esprit.spring.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity

//@SecondaryTable(name = "User", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id_user"))
public class Complaint implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_complaint;

	@Column(name="status")
	@Enumerated(EnumType.STRING)
	private Status status;
	@Column(name="date_limit")
	private LocalDate date_limit;
	

	@Column(name="date_complaint")
	@Temporal(TemporalType.TIME)
	private Date date_complaint;
	
	@Column(name="date_response")
	@Temporal(TemporalType.DATE)
	private Date date_response;
	
	@Column(name="response")
	@Enumerated(EnumType.STRING)
	private Response response;
	
	@Column(name="specific_subject")
	@Enumerated(EnumType.STRING)
	private Specific specific_subject;
	
	@Column(name="general_subject")
	private String general_subject;
	@Transient
	private int TotalNumberOfComplaints;
	@Transient
	private int NumberOfNewComplaints;
	@Transient
	private int NumberOfDoneComplaints;
	@Transient
	private int NumberOfInProcessComplaints;

	@ManyToOne
	private User user;
	
	@ManyToOne
	
	private Product product;
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId_complaint() {
		return id_complaint;
	}

	public void setId_complaint(Long id_complaint) {
		this.id_complaint = id_complaint;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}


	public LocalDate getDate_limit() {
		return date_limit;
	}

	public void setDate_limit(LocalDate date_limit) {
		this.date_limit = date_limit;
	}


	public Date getDate_response() {
		return date_response;
	}


	

	public Date getDate_complaint() {
		return date_complaint;
	}

	public void setDate_complaint(Date date_complaint) {
		this.date_complaint = date_complaint;
	}

	public void setDate_response(Date date_response) {
		this.date_response = date_response;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public Specific getSpecific_subject() {
		return specific_subject;
	}

	public void setSpecific_subject(Specific specific_subject) {
		this.specific_subject = specific_subject;
	}

	public String getGeneral_subject() {
		return general_subject;
	}

	public void setGeneral_subject(String general_subject) {
		this.general_subject = general_subject;
	}

	public Complaint() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getTotalNumberOfComplaints() {
		return TotalNumberOfComplaints;
	}

	public void setTotalNumberOfComplaints(int totalNumberOfComplaints) {
		TotalNumberOfComplaints = totalNumberOfComplaints;
	}

	public int getNumberOfNewComplaints() {
		return NumberOfNewComplaints;
	}

	public void setNumberOfNewComplaints(int numberOfNewComplaints) {
		NumberOfNewComplaints = numberOfNewComplaints;
	}

	public int getNumberOfDoneComplaints() {
		return NumberOfDoneComplaints;
	}

	public void setNumberOfDoneComplaints(int numberOfDoneComplaints) {
		NumberOfDoneComplaints = numberOfDoneComplaints;
	}

	public int getNumberOfInProcessComplaints() {
		return NumberOfInProcessComplaints;
	}

	public void setNumberOfInProcessComplaints(int numberOfInProcessComplaints) {
		NumberOfInProcessComplaints = numberOfInProcessComplaints;
	}
	
	public Complaint (Long id_complaint, Date date_complaint , Date date_response , Response response,Status status,Specific specific,General general_subject ,Product product){
		this.id_complaint= id_complaint;
		this.date_complaint=date_complaint;
		this.date_response=date_response;
		this.status=status;
		this.product=product;
		
	}
	
}
