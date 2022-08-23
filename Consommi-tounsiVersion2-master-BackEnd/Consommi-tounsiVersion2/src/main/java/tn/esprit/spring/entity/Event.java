package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Event implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int idEvent;
	private String nameEvent;
	private String descriptionEvent;
	@OneToOne(cascade=CascadeType.ALL)
	private Fund fund;
	 
	@ManyToOne
	private Admin admin;
	
	@JsonIgnore
	@ManyToMany(fetch=FetchType.EAGER)
	private List<Client>listclient = new ArrayList<>();
	
	@Temporal(TemporalType.DATE)
	private Date startDateEvent;
	@Temporal(TemporalType.DATE)
	private Date endDateEvent;
	private String placeEvent;
	private double costEvent;
	private int nbPlaceDisponible;
	public int getIdEvent() {
		return idEvent;
	}
	public void setIdEvent(int idEvent) {
		this.idEvent = idEvent;
	}
	public String getNameEvent() {
		return nameEvent;
	}
	public void setNameEvent(String nameEvent) {
		this.nameEvent = nameEvent;
	}
	public String getDescriptionEvent() {
		return descriptionEvent;
	}
	public void setDescriptionEvent(String descriptionEvent) {
		this.descriptionEvent = descriptionEvent;
	}
	public Fund getFund() {
		return fund;
	}
	public void setFund(Fund fund) {
		this.fund = fund;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public List<Client> getListclient() {
		return listclient;
	}
	public void setListclient(List<Client> listclient) {
		this.listclient = listclient;
	}
	public Date getStartDateEvent() {
		return startDateEvent;
	}
	public void setStart_date_event(Date startDateEvent) {
		this.startDateEvent = startDateEvent;
	}
	public Date getEndDateEvent() {
		return endDateEvent;
	}
	public void setEndDateEvent(Date endDateEvent) {
		this.endDateEvent = endDateEvent;
	}
	public String getPlaceEvent() {
		return placeEvent;
	}
	public void setPlaceEvent(String placeEvent) {
		this.placeEvent = placeEvent;
	}
	public double getCostEvent() {
		return costEvent;
	}
	public void setCostEvent(double costEvent) {
		this.costEvent = costEvent;
	}
	public int getNbPlaceDisponible() {
		return nbPlaceDisponible;
	}
	public void setNbPlaceDisponible(int nbPlaceDisponible) {
		this.nbPlaceDisponible = nbPlaceDisponible;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idEvent;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (idEvent != other.idEvent)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Event [idEvent=" + idEvent + ", nameEvent=" + nameEvent + ", descriptionEvent=" + descriptionEvent
				+ ", fund=" + fund + ", admin=" + admin + ", listclient=" + listclient + ", startDateEvent="
				+ startDateEvent+ ", endDateEvent=" + endDateEvent + ", placeEvent=" + placeEvent + ", costEvent="
				+ costEvent + ", nbPlaceDisponible=" + nbPlaceDisponible + "]";
	}
	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Event(int idEvent, String nameEvent, String descriptionEvent, Fund fund, Date startDateEvent,
			Date endDateEvent, String placeEvent, double costEvent, int nbPlaceDisponible) {
		super();
		this.idEvent = idEvent;
		this.nameEvent = nameEvent;
		this.descriptionEvent = descriptionEvent;
		this.fund = fund;
		this.startDateEvent = startDateEvent;
		this.endDateEvent = endDateEvent;
		this.placeEvent = placeEvent;
		this.costEvent = costEvent;
		this.nbPlaceDisponible = nbPlaceDisponible;
	}
	public Event(String nameEvent, String descriptionEvent, Fund fund, Date startDateEvent,
			Date endDateEvent, String placeEvent, double costEvent, int nbPlaceDisponible) {
		super();
		this.nameEvent = nameEvent;
		this.descriptionEvent = descriptionEvent;
		this.fund = fund;
		this.startDateEvent= startDateEvent;
		this.endDateEvent = endDateEvent;
		this.placeEvent = placeEvent;
		this.costEvent = costEvent;
		this.nbPlaceDisponible = nbPlaceDisponible;
	}
	public Event(String nameEvent, String descriptionEvent) {
		super();
		this.nameEvent = nameEvent;
		this.descriptionEvent = descriptionEvent;
	}
	
	
	
	
	
	
	
}
