package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Collections;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
//import lombok.NoArgsConstructor;
import tn.esprit.spring.entity.Role;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@EqualsAndHashCode 
//@NoArgsConstructor

public class User implements Serializable, UserDetails {
   
	
	@SequenceGenerator(
		name="user_squence",
		sequenceName="user_sequence",
		allocationSize=1
			)
	@Id
	@GeneratedValue(
		strategy=GenerationType.SEQUENCE,
	    generator="user_sequence"          
			)
    private int id_user;
	@OneToMany(mappedBy="user")
    List<Complaint> complaints;
  
    private String first_name ;
    private String last_name ;
    private int phone_number;
    private String email ;
    private String username;
    private String password;
    private String gender ;
    @Temporal (TemporalType.DATE)
	private Date date ;
    @Enumerated(EnumType.STRING)
	protected Role role;
    protected boolean locked=false ;
    protected boolean enabled=false ;
    
    
	
    
	public List<Complaint> getComplaints() {
		return complaints;
	}
	public void setComplaints(List<Complaint> complaints) {
		this.complaints = complaints;
	}
	public String getUsername() {
		return email;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}

	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String lastName) {
		this.last_name = lastName;
	}
	public int getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(int phone_number) {
		this.phone_number = phone_number;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return username;
	}
	public void setUserName(String login) {
		this.username = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	
	public boolean isLocked() {
		return locked;
	}
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
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
    
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	    public User(List<Complaint> complaints, 
	    		String first_name, 
	    		String last_name,
	    		int phone_number, 
	    		String email,
				String username, 
				String password,
				String gender, 
				Date date,
				Role role
		) {
			
			this.complaints = complaints;
			this.first_name = first_name;
			this.last_name = last_name;
			this.phone_number = phone_number;
			this.email = email;
			this.username = username;
			this.password = password;
			this.gender = gender;
			this.date = date;
			this.role = role;
			
	    }
   
     
	

}
