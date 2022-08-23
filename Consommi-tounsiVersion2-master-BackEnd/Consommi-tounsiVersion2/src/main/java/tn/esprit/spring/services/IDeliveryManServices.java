package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entity.DeliveryMan;
import tn.esprit.spring.entity.UserErrors;

public interface IDeliveryManServices {
	public DeliveryMan updateDeliveryMan(DeliveryMan d);
	//public void deletDeliveryMan(int id);
	public DeliveryMan retrieveDeliveryMan(int id);
	public List<DeliveryMan> retrieveAllDeliveryMans();
	public UserErrors addDeliveryMan(DeliveryMan d);
	void deleteDeliveryMan(int id);
	DeliveryMan getDeliveryManByEmail(String email);
	DeliveryMan getDeliveryManByUserName(String username);
	DeliveryMan authenticate(String login, String password);
	

}
