package tn.esprit.spring.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Complaint;
import tn.esprit.spring.entity.Product;
import tn.esprit.spring.entity.Status;
import tn.esprit.spring.services.ComplaintService;
import tn.esprit.spring.services.IProductService;






@RestController

public class ComplaintRestController {

	@Autowired
	ComplaintService complaintService;
	@Autowired
	IProductService prod;
	
	@PostMapping("/addComplaint/{id}")
	@ResponseBody
	public Complaint addComplaint(@RequestBody Complaint complaint,@PathVariable ("id") int id)
	{
		complaintService.addComplaint(complaint,id );
		return complaint;
	}
	
	@DeleteMapping("/deleteComplaint/{id_complaint}")
	@ResponseBody
	public void deleteComplaint(@PathVariable("id_complaint") String id_complaint){
		complaintService.deleteComplaint(id_complaint);
	}
	
	@PutMapping("/updateComplaint")
	@ResponseBody
	public Complaint modifyComplaint(@RequestBody Complaint c){
		return complaintService.updateComplaint(c);
	}
	
	@GetMapping("/showListComplaint")
	@ResponseBody
	public Iterable<Complaint> showListComplaint(){
		return complaintService.getListComplaint();
	}
	
	@GetMapping("/findComplaint/{id_complaint}")
	@ResponseBody
	public Optional<Complaint> findComplaintById(@PathVariable("id_complaint") Long id_complaint){
		return complaintService.getByIdComplaint(id_complaint);
	}
	
	@GetMapping("/findByStatus/{status}")
	@ResponseBody
	public List<Complaint> findByStatus(@PathVariable("status") Status status){
		return complaintService.getByStatus(status);
	}
	@GetMapping("/filtrerParDate/{d1}/{d2}")
	@ResponseBody
	public List<Complaint> getByDate(@PathVariable( "d1") @DateTimeFormat(iso = ISO.DATE) Date d1, @PathVariable("d2") @DateTimeFormat(iso = ISO.DATE) Date d2){
		return complaintService.getByDate(d1,d2);
	}
	
	@GetMapping("/stat")
	@ResponseBody
	public Complaint getNbUsers() {
		return complaintService.JPQLQuery();
	}
	@GetMapping("/stat/New")
	@ResponseBody
	public Complaint getNbUsers1() {
		return complaintService.JPQLQuery1();
	}

	@GetMapping("/stat/Done")
	@ResponseBody
	public Complaint getNbUsers2() {
		return complaintService.JPQLQuery2();
	}
	@GetMapping("/stat/InProcess")
	@ResponseBody
	public Complaint getNbUsers3() {
		return complaintService.JPQLQuery3();
	}
//	@Autowired
//	EmailService emp;
//
//	@PostMapping(value="/textemail",consumes = "application/json", produces = "application/json")
//	@ResponseBody
//	public String sendMail(@RequestBody EmailTemplate empa){
//		try {
//			
//			EmailTemplate em=new EmailTemplate();
//		
//		
//			emp.sendTextEmail(empa);
//			return "Email Sent!";
//		} catch (Exception ex) {
//			return "Error in sending email: " + ex;
//		}
//	}
//	
	
//	@GetMapping("/mostClaimedComplaint")
//	@ResponseBody
//	public List<Product> mostClaimed(){
//	return prod.mostClaimed();
//	}
	
	
	@PutMapping("/decision/{id_c}")
	//@RequestMapping(value="/decision",method=RequestMethod.PUT)
	@ResponseBody String decision (@PathVariable("id_c") Long id_c){
			return complaintService.decision(id_c);
		
	}
	
}
