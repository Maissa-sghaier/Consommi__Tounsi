package tn.esprit.spring.services;

import java.util.ArrayList;
//import java.util.HashMap;
import java.util.List;
//import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import tn.esprit.spring.repository.AdRepository;
import tn.esprit.spring.repository.ProductRepository;
import tn.esprit.spring.entity.Ad;

import tn.esprit.spring.entity.Product;
import tn.esprit.spring.services.ProductService;
//import tn.esprit.spring.exeptions.ResourceNotFoundException;




@Service

public class AdService implements IAdService {
	@Autowired
	AdRepository AdRepository;
	
	@Autowired
	ProductRepository ProductRepository;
	@Autowired
	ProductService ProductService;
	

	@Override
	
	public Ad createAd(Ad ad,  int idproduct) {
		
	List<Product> product = new ArrayList<Product>();
	
	ProductRepository.findById(idproduct).orElse(null);
	
	ad.setProducts( product);
	
	return AdRepository.save(ad);
	
	
		
	}

	@Override
	public Ad updateAd(Ad adv) {
		// TODO Auto-generated method stub
		
		
		return AdRepository.save(adv);
		
		
		
	}

	@Override
	public void deleteAd(Ad ad) {
		// TODO Auto-generated method stub
		
		AdRepository.delete(ad);
		
		
	}

	@Override
	public Optional<Ad> getByIdAd(int id){
		
		return AdRepository.findById(id);
	
	}

	@Override
	public List<Ad> getAdByProduct(int id_product) {
		// TODO Auto-generated method stub
		Product p =ProductRepository.findById(id_product).orElse(null);
		
		List<Ad>listad=new ArrayList<Ad>();
		listad=p.getAds();
		return listad ;
	}

	

}
	
	

	