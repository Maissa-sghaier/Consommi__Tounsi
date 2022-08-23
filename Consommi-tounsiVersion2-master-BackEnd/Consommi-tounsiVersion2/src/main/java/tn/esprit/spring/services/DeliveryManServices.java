package tn.esprit.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import tn.esprit.spring.entity.DeliveryMan;
import tn.esprit.spring.entity.UserErrors;
import tn.esprit.spring.repository.IDeliveryManRepository;

@Service
public class DeliveryManServices implements IDeliveryManServices {
	@Autowired
	IDeliveryManRepository deliveryManrep ;
	@Override
	public List<DeliveryMan> retrieveAllDeliveryMans ()
	{
		List<DeliveryMan> deliveryMan =(List<DeliveryMan>) deliveryManrep.findAll();
		return deliveryMan ;	
	}
	
	@Override
		public void deleteDeliveryMan (int id) {
		deliveryManrep.deleteById(id);
				}
	@Override
		public DeliveryMan retrieveDeliveryMan (int id) {
		   
		DeliveryMan deliveryMan=deliveryManrep.findById(id).get();
			return deliveryMan;
		
			}
	
	 @Override
		public DeliveryMan updateDeliveryMan(DeliveryMan d) {
			
		 DeliveryMan d1= deliveryManrep.save(d);
			return d1;
	 }
	 
	 
//	 @Override
//	 public UserDetails loadClientByUsername(String username)
//	 {
//		 Client client= clientrep.getClientByUsername( username);	 
//		 if (client == null)
//				throw new UsernameNotFoundException("invalid user");
//			Collection<GrantedAuthority> authorities = new ArrayList<>();
//
//			return new ClientPrincipal(client);
	 //}
	 @Override
	 public DeliveryMan getDeliveryManByEmail(String email)
	 {
	  return deliveryManrep.findByEmail( email);	 
	 }

	
	    public UserErrors addDeliveryMan( DeliveryMan d) {
	        if (deliveryManrep.findByUsername(d.getUserName())!=null)
	            return UserErrors.USERNAME_ALREADY_EXISTS;
	        if (deliveryManrep.findByEmail(d.getEmail())!=null)
	            return UserErrors.EMAIL_ALREADY_EXISTS;
	        else deliveryManrep.save(d);
		   return  UserErrors.SUCCESS;
}
	    @Override
	    public DeliveryMan getDeliveryManByUserName(String username)
		 {
		  return deliveryManrep.findByUsername( username);	 
		 }

	    @Override
		public DeliveryMan authenticate(String login, String password) {
			return deliveryManrep.findByEmailAndPassword(login, password);

	


}} 


