package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.DeliveryMan;
import tn.esprit.spring.entity.UserErrors;
import tn.esprit.spring.services.IDeliveryManServices;


@RestController
public class DeliveryManController {
	@Autowired
	IDeliveryManServices deliveryManServices ;

	
//	private String login; 
//	private String password;
//	private Boolean loggedIn;
//	
//	
//	
//	public Boolean getLoggedIn() {
//		return loggedIn;
//	}
//
//
//	public void setLoggedIn(Boolean loggedIn) {
//		this.loggedIn = loggedIn;
//	}
//
//
//	public IDeliveryManServices getdeliveryManServices() {
//		return deliveryManServices;
//	}
//
//
//	public void IDeliveryManServices(IDeliveryManServices deliveryManServices) {
//		this.deliveryManServices = deliveryManServices;
//	}
//
//
//	public String getLogin() {
//		return login;
//	}
//
//
//	public void setLogin(String login) {
//		this.login = login;
//	}
//
//
//	public String getPassword() {
//		return password;
//	}
//
//
//	public void setPassword(String password) {
//		this.password = password;
//	}


@PostMapping("/add-deliveryMan")
	@ResponseBody
	public  UserErrors addDeliveryMan(@RequestBody DeliveryMan d)
	{   UserErrors d1 = deliveryManServices.addDeliveryMan(d);
		return  d1;
	 }
	
	
@GetMapping("/find-deliveryman-by-username/{username}")
@ResponseBody
public DeliveryMan getDeliveryManByUsername(@PathVariable("username")String username) {
 return deliveryManServices.getDeliveryManByUserName(username);
}
	
	
	
	@GetMapping("/find1-by-email/{email}")
    @ResponseBody
   public DeliveryMan getDeliveryManByEmail(@PathVariable("email")String email) {
   return deliveryManServices.getDeliveryManByEmail(email);
	}
@GetMapping("/retrieve-DeliveryMan/{deliveryMan-id}")
@ResponseBody
public DeliveryMan retrieveDeliveryMan(@PathVariable("deliveryMan-id") int Id) {
	return deliveryManServices.retrieveDeliveryMan(Id);
}
	
@DeleteMapping("/remove-DeliveryMan/{deliveryMan-id}")
@ResponseBody
   public String removeDeliveryMan(@PathVariable("deliveryMan-id") int id_deliveryMan) {
	deliveryManServices.deleteDeliveryMan(id_deliveryMan);
	return ("done");
	}
	
	@GetMapping("/retrieve-all-DeliveryMans")
	@ResponseBody
	public List<DeliveryMan> getUsers() {
	List<DeliveryMan> list = deliveryManServices.retrieveAllDeliveryMans();
	return list;
}
	
	
@PutMapping("/modify-DeliveryMan") 
@ResponseBody
public DeliveryMan modifyDeliveryMan(@RequestBody DeliveryMan deliveryMan) {
	DeliveryMan d1 = deliveryManServices.updateDeliveryMan(deliveryMan);
return d1 ;
}
	

}
