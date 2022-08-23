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

import tn.esprit.spring.entity.Admin;
import tn.esprit.spring.entity.UserErrors;
import tn.esprit.spring.services.IAdminServices;

@RestController
public class AdminController {
	@Autowired
	IAdminServices adminServices ;
//
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
//	public IAdminServices getadminServices() {
//		return adminServices;
//	}
//
//
//	public void setIAdminServices(IAdminServices adminServices) {
//		this.adminServices = adminServices;
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

//	public String getPassword() {
//		return password;
//	}
//
//
//	public void setPassword(String password) {
//		this.password = password;
//	}


@PostMapping("/add-admin")
@ResponseBody
	public  UserErrors addAdmint(@RequestBody Admin a)
	{   UserErrors a1 = adminServices.addAdmin(a);
		return  a1;
	 }
	
	
@GetMapping("/find-admin-by-username/{username}")
@ResponseBody
public Admin getAdminByUsername(@PathVariable("username")String username) {
 return adminServices.getadminByUsername(username);
}
@GetMapping("/find-admin-by-email/{email}")
@ResponseBody
public Admin getAdminByEmail(@PathVariable("email")String email) {
return  adminServices.getadminByEmail(email);
	}
@GetMapping("/retrieve-admin/{admin-id}")
@ResponseBody
public Admin retrieveAdmin(@PathVariable("admin-id") int Id) {
	return adminServices.retrieveAdmin(Id);
}
	
@DeleteMapping("/remove-Admin/{admin-id}")
@ResponseBody
   public String removeAdmin(@PathVariable("admin-id") int id_admin) {
	adminServices.deleteAdmin(id_admin);
	return ("done");
	}
	
	@GetMapping("/retrieve-all-admins")
	@ResponseBody
	public List<Admin> getAdmins() {
	List<Admin> list = adminServices.retrieveAllAdmins();
	return list;
}
	
	
@PutMapping("/modify-admin") 
@ResponseBody
public String modifyAdmin (@RequestBody Admin admin) {
Admin a1 = adminServices.updateAdmin(admin);
return "Admin modifié " ;
}
	

}
