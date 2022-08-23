package tn.esprit.spring.services;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Complaint;
import tn.esprit.spring.entity.Product;
import tn.esprit.spring.entity.Specific;
import tn.esprit.spring.entity.Status;
import tn.esprit.spring.repository.IComplaintRepository;

@Service
@Transactional
public class ComplaintService implements IComplaintService{

	@Autowired
	IComplaintRepository complaintRepository;
//	@Autowired
//	EmailService emailservice;
	@Autowired
	ProductService productser;
	@Autowired
	ComplaintService complaintser;
	@Override
	public Complaint addComplaint(Complaint c,int id){
		Product p = new Product();
		//c.setDate_complaint(LocalDate.now());
		ZoneId defaultZoneId = ZoneId.of("Africa/Tunis");
		LocalDate localDate = LocalDate.now();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
	    c.setDate_complaint(date);
	    c.setStatus(Status.New);
	   
		c.setProduct(productser.getProductById(id));
	    complaintRepository.save(c);
    if((c.getSpecific_subject().equals(Specific.Product))){
	    	p= c.getProduct();
	    	p.setNbr_complaint(p.getNbr_complaint()+1);
	    }
	    return c;
		}
	
	@Override
	public void deleteComplaint(String id){
		complaintRepository.deleteById(Long.parseLong(id));
	}

	@Override
	public Complaint updateComplaint(Complaint c) {
		// TODO Auto-generated method stub
		return complaintRepository.save(c);
	}
	
	@Override
	public Optional<Complaint> getByIdComplaint(Long id){
		return complaintRepository.findById(id);
	}
	
	@Override
	public List<Complaint> getByStatus(Status status){
		return complaintRepository.findByStatus(status);
	}
	
	@Override
	public Iterable<Complaint> getListComplaint(){
		return complaintRepository.findAll();

	}
	
	@Override
	public List<Complaint> getByDate( Date d1, Date d2){
		return complaintRepository.getByDate(d1, d2);
	}

	
	

	@Autowired
    EntityManagerFactory emf;
	public Complaint JPQLQuery()
    {
		Complaint complaint = new Complaint();
		
		EntityManager e = emf.createEntityManager();
        e = emf.createEntityManager();
        Query query;
        query = e.createQuery("Select count(c) from Complaint c");
        complaint.setTotalNumberOfComplaints(Integer.parseInt(query.getSingleResult().toString()));
        System.out.println("Res count from : Complaint"+complaint.getTotalNumberOfComplaints());
        e.close();
        return complaint;
        
    }
	@Override
	public Complaint JPQLQuery1()
    {
		Complaint complaint = new Complaint();
		Query query;
		EntityManager e1 = emf.createEntityManager();
        e1 = emf.createEntityManager();
        query = e1.createQuery("Select count(c) from Complaint c Where c.status = 'New'");
        complaint.setNumberOfNewComplaints(Integer.parseInt(query.getSingleResult().toString()));
        System.out.println("Res count from : Complaint"+complaint.getNumberOfNewComplaints());
        e1.close();
        return complaint;
    }
	@Override
	public Complaint JPQLQuery2()
    {
		Complaint complaint = new Complaint();
		Query query;
		EntityManager e2 = emf.createEntityManager();
        e2 = emf.createEntityManager();
        query = e2.createQuery("Select count(c) from Complaint c Where c.status = 'Done'");
        complaint.setNumberOfDoneComplaints(Integer.parseInt(query.getSingleResult().toString()));
        System.out.println("Res count from : Complaint"+complaint.getNumberOfDoneComplaints());
        e2.close();
        return complaint;
    }     
	@Override
	public Complaint JPQLQuery3()
    {   
		Complaint complaint = new Complaint();
		Query query;
		EntityManager e3 = emf.createEntityManager();
        e3 = emf.createEntityManager();
        query = e3.createQuery("Select count(c) from Complaint c Where c.status = 'InProcess'");
        complaint.setNumberOfInProcessComplaints(Integer.parseInt(query.getSingleResult().toString()));
        System.out.println("Res count from : Complaint"+complaint.getNumberOfInProcessComplaints());
        e3.close();
        
        return complaint;
    }
//	@Override
//	public List<Product> mostClaimed(){
//		return  productRep.m;
//	}
//	@Override
//	public void mostClaimedProduct(){
//		List<Product> products = new ArrayList<Product>();
//		Complaint complaint = new Complaint();
//		productRep.findAll().forEach(p-> products.add(p));
//		
//		
//		
//		
//		return ;
//			
//	
//	}

	@Override
	public String decision(Long id_c) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
//	public String decision(Long id_c) {
////		
////		  Optional<Complaint>  c=  Optional.ofNullable(null);
//		  //Iterable<Complaint> c = new ArrayList<>();
//		  Complaint c = new Complaint();
//		 c= complaintser.getByIdComplaint(id_c).orElse(null);
//		  
//		if(c.getResponse().equals(Response.Refund)) {
//			ZoneId defaultZoneId = ZoneId.of("Africa/Tunis");
//			LocalDate localDate = LocalDate.now();
//			Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
//			c.setDate_response(date);
//			c.setStatus(c.getStatus().Done);
//			EmailTemplate emp=new EmailTemplate();
//			//emp.setSendTo(c.getUser().getEmail());
//			emp.setBody("Our Desicion is : Refund");
//			emailservice.sendTextEmail(emp);
//			return "Refund";
//		}
//		if(c.getResponse().equals(Response.Trade)) {
//					return  trade(c,couponValue);
//				}
//		if(c.getResponse().equals(Response.Repair)) {
//			return repair(c,typePanne,prixReparation,idProduct);
//		}
//		if(c.getResponse().equals(Response.Refused)) {
//			
//			//c.setUsers(userRepository.getOne(idClient));
//		    complaintRepository.save(c);
//			return"Complaint Refused";
//		}
//		return "";
//	}
//	private String repair(Complaint c,String typePanne,float prixReparation,Long idProduct) {
//		Complaint complaint = new Complaint();
//		complaint.setDate_limit(LocalDate.now().plusDays(15));
//		//complaint.setDateReparation(LocalDate.now().plusDays(15));
//	//	complaint.setTypePanne(typePanne);
//		complaint.setStatus(c.getStatus().InProcess);
//		//complaint.setPrixReparation(prixReparation);
//		//Product p = productRep.getOne(idProduct);
//		//reparation.setProduct(p);
//		//reparationRepository.save(reparation);
//		//p.setReparation(reparation);
//		//productRepository.save(p);
//		
//		return "{done}";
//	}
//	
//	private String trade(Complaint c,float couponValue) {
//		// TODO Auto-generated method stub
//		//Exchange exchange = new Exchange();
//		//exchange.setCouponValue(couponValue);
//		//exchange.setState("En cours");
//		//LocalDateTime today =  LocalDateTime.now();     //Today
//		//LocalDateTime tomorrow = today.plusDays(15); 
//		//exchange.setDateLimite(LocalDate.now().plusDays(15));
//		//exchange.setNumCoupon(numCoupon); LocalDate.now().plusDays(15)
//		//Reclamation reclamation = reclamationRepository.getOne(r.getId());
//		//reclamation.setDecision(r.getDecision());
//		//reclamationRepository.save(reclamation);
//		//System.out.println("====================>"+ reclamation.getUsers().getId());
//		
//		  //exchange.setUsers(reclamation.getUsers());
//		  //exchangeRepository.save(exchange);
//		  //affecter coupon au user 
//		  //User u=userRepository.getOne(reclamation.getUsers().getId());
//		  //u.getExchanges().add(exchange); 
//		  //userRepository.save(u);
//		 
//		c.setDate_limit(LocalDate.now().plusDays(15));
//		c.setStatus(c.getStatus().InProcess);
//		Product p=new Product();
//		double x=c.getProduct().getPrice();
//	//	productser.getProductBetweenXY(x-10,x+10);
//		
//		return 		"{done}";
//
//	}
//	
	
}
