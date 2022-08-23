package tn.esprit.spring.entity;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.SequenceGenerator;

import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@EqualsAndHashCode 
//@NoArgsConstructor

public class Client extends User implements UserDetails{
	
	@SequenceGenerator(
			name="client_squence",
			sequenceName="client_sequence",
			allocationSize=1
				)
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(
			strategy=GenerationType.SEQUENCE,
		    generator="client_sequence"          
				)
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
  private int id_user;
	@ManyToMany(mappedBy="listclient")
	private List<Event> listevent;
	
	@ManyToMany(mappedBy="listclient")
	private List<SubjectForum> listsubjectforum;
	private  int alerte;

	
	public int getAlerte() {
		return alerte;
	}
	public void setAlerte(int alerte) {
		this.alerte = alerte;
	}
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
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
	public List<SubjectForum> getListsubjectforum() {
		return listsubjectforum;
	}
	public void setListsubjectforum(List<SubjectForum> listsubjectforum) {
		this.listsubjectforum = listsubjectforum;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@OneToMany(mappedBy="client")
	private List<Comment> comment;

	@OneToMany(cascade = CascadeType.ALL, mappedBy="client")

	private List<Ordering>orderm ;
	
	
	
	public List<Ordering> getOrderm() {
		return orderm;
	}
	public void setOrderm(List<Ordering> orderm) {
		this.orderm = orderm;

	}
	
	private String address;
	private String city;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
     SimpleGrantedAuthority authority =
    		 new SimpleGrantedAuthority(role.name()) ;
		return Collections.singletonList(authority);
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return !locked ;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Client(List<Complaint> complaints,
			String first_name,
			String last_name, 
			int phone_number,
			String email,
		    String username,
		    String password,
		    String gender, 
		    Date date, 
	      	Role role, 
	       List<Event> listevent,
		List<Comment> comment, 
		List<Ordering> orderm,
		List<SubjectForum> listsubjectforum,
		String address,
		String city) {
	super(complaints, first_name, last_name, phone_number, email, username, password, gender, date, role);
	this.listevent = listevent;
	this.comment = comment;
	this.orderm = orderm;
	this.listsubjectforum = listsubjectforum;
	this.address = address;
	this.city = city;
}

	
}
