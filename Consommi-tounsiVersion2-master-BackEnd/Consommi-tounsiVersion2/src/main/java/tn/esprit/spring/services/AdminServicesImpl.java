package tn.esprit.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Admin;
import tn.esprit.spring.entity.Client;

import tn.esprit.spring.entity.UserErrors;
import tn.esprit.spring.repository.IAdminRepository;
@Service
public class AdminServicesImpl implements IAdminServices{
	@Autowired
	IAdminRepository adminrep;
	@Override
	public void deleteAdmin (int id) {
		adminrep.deleteById(id);
			}
@Override
	public Admin retrieveAdmin (int id) {
	
		Admin admin=adminrep.findById(id).get();
		return admin;
	
		}
@Override
public List<Admin> retrieveAllAdmins ()
{
	List<Admin> admin =(List<Admin>) adminrep.findAll();
	return admin ;	
}

 @Override
	public Admin updateAdmin(Admin a) {
		
		Admin a1= adminrep.save(a);
		return a1;
		}
 @Override
public Admin getadminByUsername(String username)
{
 return adminrep.findByUsername( username);	 
}
 
 @Override
 public Admin getadminByEmail(String email)
 {
  return adminrep.findByEmail( email);	 
 }

 @Override
	public UserErrors addAdmin(Admin a) {
     if (adminrep.findByUsername(a.getUserName())!=null)
         return UserErrors.USERNAME_ALREADY_EXISTS;
     if (adminrep.findByEmail(a.getEmail())!=null)
         return UserErrors.EMAIL_ALREADY_EXISTS;
     else adminrep.save(a);
	   return  UserErrors.SUCCESS;

}
 @Override
	public Admin authenticate(String login, String password) {
		return adminrep.findByEmailAndPassword(login, password);
		
 
 
 }}
