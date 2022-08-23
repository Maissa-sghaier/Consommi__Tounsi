package tn.esprit.spring.controller;



import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import tn.esprit.spring.entity.Ad;
import tn.esprit.spring.exeptions.ResourceNotFoundException;
import tn.esprit.spring.services.AdService;
import tn.esprit.spring.repository.AdRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController

public class AdRestController {
	@Autowired
	
	AdService AdService;
    @Autowired
	
	AdRepository AdRepository;
    //afficher tout les ads 
    @GetMapping("/ad")
    public List<Ad> getAllAds() {
		return AdRepository.findAll();
	}
    
	//creation ad
	@PostMapping("/create-ad/{idproduct}")
	
	@ResponseBody
	public void createAd(@RequestBody Ad ad,@PathVariable(value = "idproduct") int idproduct)
			throws ResourceNotFoundException
	{
		System.out.println("createAd");
		  AdService.createAd(ad, idproduct);
				
		
	}
	//rechercher l'ad avec l'id
	@GetMapping("/findAd/{id_ad}")
	@ResponseBody
	public ResponseEntity<Ad> getAdById(@PathVariable(value = "id_ad") int id_ad)throws ResourceNotFoundException{
		System.out.println("getAdById");
		Ad ad = AdService.getByIdAd(id_ad)
				.orElseThrow(() -> new ResourceNotFoundException("Ad not found for this id :: " + id_ad));
		return ResponseEntity.ok().body(ad);
		
	}
	//mise a jour 
	@PutMapping("/update_ad/{id}")
	@ResponseBody
	 public ResponseEntity <Ad> updateAd(@PathVariable(value = "id") int id_ad,@RequestBody Ad adv)throws ResourceNotFoundException {
		System.out.println("updateAd");
		Ad ad = AdRepository.findById(id_ad).orElseThrow(() -> new ResourceNotFoundException("Ad not found for this id :: " + id_ad));
		Ad updated_ad=AdService.updateAd( ad);
		return ResponseEntity.ok(updated_ad);
		
		
}
	
	
	
//supression
	@DeleteMapping("/ad/{id}")
	public Map<String, Boolean> deleteAd(@PathVariable(value = "id") int id_ad)
			throws ResourceNotFoundException {
		System.out.println("deleteAd");
		Ad ad = AdRepository.findById(id_ad)
				.orElseThrow(() -> new ResourceNotFoundException("Ad not found for this id :: " + id_ad));
		AdService.deleteAd(ad);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	@GetMapping("/findByProductId/{id_product}")
	@ResponseBody
	public List<Ad> getAdByProduct(@PathVariable("id_product")int id_product)throws ResourceNotFoundException {
		System.out.println("getAdByProduct");
		return AdService.getAdByProduct(id_product);
	}
}
	


