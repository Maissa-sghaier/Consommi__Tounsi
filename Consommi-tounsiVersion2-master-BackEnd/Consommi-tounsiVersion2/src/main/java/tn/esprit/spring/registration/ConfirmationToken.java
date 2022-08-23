package tn.esprit.spring.registration;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import tn.esprit.spring.entity.User;
@Entity
public class ConfirmationToken {

	@SequenceGenerator(
		name="confirmation_token_squence",
		sequenceName="confirmation_token_sequence",
		allocationSize=1
			)
	@Id
	@GeneratedValue(
		strategy=GenerationType.SEQUENCE,
	    generator="user_sequence"          
			)
	private Long id;
	@Column(nullable=false)
	private String token;
	@Column(nullable=false)
	private LocalDateTime createdAt;
	@Column(nullable=false)
	private LocalDateTime expiredAt;
	private LocalDateTime confirmedAt;
	@ManyToOne
	@JoinColumn(
			nullable=false,
			name="user_id"
			)
	private User user;
	
	public Long getId() {
		return id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getExpiredAt() {
		return expiredAt;
	}
	public void setExpiredAt(LocalDateTime expiredAt) {
		this.expiredAt = expiredAt;
	}
   public LocalDateTime getConfirmedAt() {
      	return confirmedAt;
	}
	public void setConfirmedAt(LocalDateTime confirmedAt) {
		this.confirmedAt = confirmedAt;
	}
	public ConfirmationToken()
	{
		super();
	}
	
	public ConfirmationToken(String token,
			LocalDateTime createdAt, 
			LocalDateTime expiredAt,
			//LocalDateTime confirmedAt,
			User user) {
		
		this.token = token;
		this.createdAt = createdAt;
		this.expiredAt = expiredAt;
		//this.confirmedAt = confirmedAt;
	    this.user=user;
	}
    
}