package tn.esprit.spring.services;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import tn.esprit.spring.entity.Complaint;
import tn.esprit.spring.entity.Status;



public interface IComplaintService {

	Complaint addComplaint(Complaint c,int id);
	void deleteComplaint(String id);
	Complaint updateComplaint(Complaint c);
	Optional<Complaint> getByIdComplaint(Long id);
	Iterable<Complaint> getListComplaint();
	List<Complaint> getByStatus(Status status);
//	void selectResponce(Complaint c);
	List<Complaint> getByDate(Date d1, Date d2);
	Complaint JPQLQuery1();
	Complaint JPQLQuery3();
	Complaint JPQLQuery2();
	//void mostClaimedProduct();
//	List<Product> mostClaimed();
	//Complaint addComplaint(Complaint c, int id);
	public String decision(Long id_c);

}
