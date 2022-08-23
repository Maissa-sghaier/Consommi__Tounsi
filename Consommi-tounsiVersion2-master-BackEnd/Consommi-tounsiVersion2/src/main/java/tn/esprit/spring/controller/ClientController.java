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

import tn.esprit.spring.entity.Client;
import tn.esprit.spring.entity.UserErrors;
import tn.esprit.spring.services.IClientServices;

@RestController
public class ClientController {
	@Autowired
	IClientServices clientServices ;

//	
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
//	public IClientServices getclientServices() {
//		return clientServices;
//	}
//
//
//	public void setIClientServices(IClientServices clientServices) {
//		this.clientServices = clientServices;
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
//

@PostMapping("/add-client")
	@ResponseBody
	public  UserErrors addClient(@RequestBody Client c)
	{   UserErrors c1 = clientServices.addClient(c);
		return  c1;
	 }
	
	
@GetMapping("/find-by-username/{username}")
@ResponseBody
public Client getClientByUsername(@PathVariable("username")String username) {
 return clientServices.getclientByUserName(username);
}
	
	
	
//	@GetMapping("/find-by-email/{email}")
//    @ResponseBody
//   public Client getClientByEmail(@PathVariable("email")String email) {
//   return    clientServices.getclientByEmail(email);
//	}
@GetMapping("/retrieve-client/{client-id}")
@ResponseBody
public Client retrieveClient(@PathVariable("client-id") int Id) {
	return clientServices.retrieveClient(Id);
}
	
@DeleteMapping("/remove-Client/{client-id}")
@ResponseBody
   public String removeClient(@PathVariable("client-id") int id_client) {
	clientServices.deleteClient(id_client);
	return ("done");
	}
	
	@GetMapping("/retrieve-all-clients")
	@ResponseBody
	public List<Client> getClients() {
	List<Client> list = clientServices.retrieveAllClients();
	return list;
}
	
	
@PutMapping("/modify-client") 
@ResponseBody
public Client modifyClient (@RequestBody Client client) {
Client c1 = clientServices.updateClient(client);
return c1 ;
}
	

}
