package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.repository.AdRepository;
import tn.esprit.spring.repository.AdViewRepository;
import tn.esprit.spring.services.IAdViewService;
import tn.esprit.spring.entity.AdView;
import tn.esprit.spring.exeptions.ResourceNotFoundException;
import tn.esprit.spring.entity.Ad;


@RestController

public class AdViewRestController {
	@Autowired
	IAdViewService adviewservice;
	@Autowired  
	AdViewRepository adviewrepository;
	@Autowired
	AdRepository adrep;
	
	//aficher tout les adviews
	@GetMapping("/adView")
	public List<AdView> getAllAdsViews() {
		return adviewrepository.findAll();
	}
	
	@GetMapping("/adView/{id_ad}")
	public List<Integer> getUsersIdByAdId(@PathVariable(value = "id_ad") Integer id_ad) throws ResourceNotFoundException {
		
		return adviewservice.getUsersByAd(id_ad) ;
	}
	@PostMapping("/adViews")
	public ResponseEntity<AdView> addAdView( @RequestBody AdView adView)
    throws ResourceNotFoundException {
		System.out.println("addAdView - id_ad : "+adView.getId_ad()+" id_user : "+adView.getId_user());
		AdView adv= adviewrepository.findViewByUserNdAd(adView.getId_ad(),adView.getId_user());
		if(adv==null && adView.getId_user()!=0 ) {
			System.out.println("Test");
			adviewrepository.save(adView);
			Ad ad = adrep.findById(adView.getId_ad())
					.orElseThrow(() -> new ResourceNotFoundException("Ad not found for this id :: " + adView.getId_ad()));
			ad.setActualViewNumber(adviewrepository.findByAdId(adView.getId_ad()).size());
			adrep.save(ad);
		}
		return ResponseEntity.ok(adView);
	}

	

}
