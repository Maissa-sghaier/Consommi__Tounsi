package tn.esprit.spring.registration;

import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
//import lombok.Getter;
import lombok.ToString;
import tn.esprit.spring.entity.Comment;
import tn.esprit.spring.entity.Complaint;
import tn.esprit.spring.entity.Event;
import tn.esprit.spring.entity.Ordering;
//import tn.esprit.spring.entity.OrderMouna;
import tn.esprit.spring.entity.SubjectForum;


@AllArgsConstructor
@EqualsAndHashCode
@ToString

public class RegistrationRequest {
	
	public List<Complaint> getComplaints() {
		return complaints;
	}
	public void setComplaints(List<Complaint> complaints) {
		this.complaints = complaints;
	}
	List<Complaint> complaints;
	private  String first_name;
	private  String last_name;
	private  String email;
	private  String password;
	private int phone_number;
    private String gender ;
    private String username;
    private String adresse;
    private List<Event> listevent;
    private List<Comment> comment;
   private List<Ordering> orderm;
    private List<SubjectForum> listsubjectforum;
    private String city;
    
    
    
    
 public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public List<Event> getListevent() {
		return listevent;
	}
	public void setListevent(List<Event> listevent) {
		this.listevent = listevent;
	}
	public List<Comment> getComment() {
		return comment;
	}
	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}
	public List<Ordering> getOrdering() {
		return orderm;
	}
	public void setOrdering(List<Ordering> orderm) {
		this.orderm = orderm;
	}
	public List<SubjectForum> getListsubjectforum() {
		return listsubjectforum;
	}
	public void setListsubjectforum(List<SubjectForum> listsubjectforum) {
		this.listsubjectforum = listsubjectforum;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Temporal (TemporalType.DATE)
	private Date date ;
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String firstName) {
		this.first_name = firstName;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String lastName) {
		this.last_name = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(int phone_number) {
		this.phone_number = phone_number;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	}
	
