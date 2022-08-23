package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entity.Donation;


public interface IDonationService {
	
	public int addDonation(Donation donation);//by admin
	public List<Donation> DonationLists();//by admin and client
	public Donation updateDonation(Donation donation);//by admin
	public void deleteDonation(int id) ;//by admin
	

}
