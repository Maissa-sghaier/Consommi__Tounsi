package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Donation;
import tn.esprit.spring.services.DonationService;

@RestController
public class RestControlDonation {
	
	@Autowired
	DonationService donationservice;
	
	//http://localhost:8082/springMVC/servlet/addDonation
	@PostMapping("/addDonation")//success
	@ResponseBody
	public int addDonation(@RequestBody Donation donation){return donationservice.addDonation(donation);}
	
	//http://localhost:8082/springMVC/servlet/DonationLists
		@GetMapping(value="/DonationLists")//success
		@ResponseBody
	public List<Donation> DonationLists(){return donationservice.DonationLists();}
	
		//http://localhost:8082/springMVC/servlet/updateDonation
		@PutMapping(value="/updateDonation")//success
		@ResponseBody
		public Donation updateDonation(@RequestBody Donation donation){return donationservice.updateDonation(donation);}
	
		
		@DeleteMapping("/deleteDonation/{id}")//success
		@ResponseBody
		public void deleteDonation(@PathVariable("id") int id){donationservice.deleteDonation(id);}
	

}
