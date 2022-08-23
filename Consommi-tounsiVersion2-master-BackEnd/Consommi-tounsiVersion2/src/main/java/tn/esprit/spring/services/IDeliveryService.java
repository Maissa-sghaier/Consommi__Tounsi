package tn.esprit.spring.services;

import java.util.List;
import java.util.Optional;

//import org.springframework.web.bind.annotation.PathVariable;

import tn.esprit.spring.entity.Delivery;

//import tn.esprit.spring.entity.Client;

public interface IDeliveryService {
	Optional<List<Delivery>>GetDeliveryByClient(int id_user);//associer une livraison à un client 
	String getAdressStatusByDeliveryId( int id_delivery);
	
	

}
