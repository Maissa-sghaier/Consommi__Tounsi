package tn.esprit.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Donation;
import tn.esprit.spring.entity.Event;
import tn.esprit.spring.repository.DonationRepository;

@Service
public class DonationService implements IDonationService{

	@Autowired
	DonationRepository donationrep; 
	
	@Override
	public int addDonation(Donation donation) {
		donationrep.save(donation);
		return donation.getId_donation();
	}

	@Override
	public List<Donation> DonationLists() {
		List<Donation> donationLists =(List<Donation>) donationrep.findAll();
		return donationLists;
	}

	@Override
	public Donation updateDonation(Donation donation) {
		
		return donationrep.save(donation);
	}

	@Override
	public void deleteDonation(int id) {
		donationrep.deleteById(id);
		
	}

}
