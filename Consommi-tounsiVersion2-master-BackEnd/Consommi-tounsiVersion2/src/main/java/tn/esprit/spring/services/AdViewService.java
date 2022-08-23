package tn.esprit.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.repository.AdViewRepository;
@Service

public class AdViewService implements IAdViewService {
	
	@Autowired
	AdViewRepository adviewrep;

	@Override
	public List<Integer> getUsersByAd(int id_ad) {
		// TODO Auto-generated method stub
		List<Integer> usersId=adviewrep.findByAdId(id_ad);
		
		return usersId ;
	}

}
