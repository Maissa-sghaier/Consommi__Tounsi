package tn.esprit.spring.services;




import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Client;
import tn.esprit.spring.entity.UserErrors;
import tn.esprit.spring.registration.ConfirmationToken;
import tn.esprit.spring.registration.ConfirmationTokenService;
import tn.esprit.spring.repository.IClientRepository;

@Service

public class ClientServicesImpl implements IClientServices , UserDetailsService 
{  private final static String CLIENT_NOT_FOUND_MSG=
		   "client with email %s not found";
	@Autowired
	IClientRepository clientrep ;
	@Autowired
 	BCryptPasswordEncoder bCryptPasswordEncoder;
 	@Autowired
 	ConfirmationTokenService confirmationTokenService;
	
	@Override
	public List<Client> retrieveAllClients ()
	{
		List<Client> client =(List<Client>) clientrep.findAll();
		return client ;	
	}
	
	@Override
		public void deleteClient (int id) {
			clientrep.deleteById(id);
				}
	@Override
		public Client retrieveClient (int id) {
		   
	        Client client=clientrep.findById(id).get();
			return client;
		
			}
	
	 @Override
		public Client updateClient(Client c) {
			
			Client c1= clientrep.save(c);
			return c1;
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
	 public Optional<Client> getclientByEmail(String email)
	 {
	  return clientrep.findByEmail( email);	 
	 }

	@Override
	    public UserErrors addClient( Client c) {
	        if (clientrep.findByUsername(c.getUserName())!=null)
	            return UserErrors.USERNAME_ALREADY_EXISTS;
	        if (clientrep.findByEmail(c.getEmail())!=null)
	            return UserErrors.EMAIL_ALREADY_EXISTS;
	        else clientrep.save(c);
		   return  UserErrors.SUCCESS;
}
	    @Override
	    public Client getclientByUserName(String username)
		 {
		  return clientrep.findByUsername( username);	 
		 }

	    @Override
		public Client authenticate(String login, String password) {
			return clientrep.findByEmailAndPassword(login, password);
}

		@Override
		public UserDetails loadUserByUsername(String email) 
				throws UsernameNotFoundException {
			
			
			return clientrep.findByEmail(email)
					.orElseThrow(()  -> new  UsernameNotFoundException(
							String.format(CLIENT_NOT_FOUND_MSG,email)));
		}
		
		
		
	 	public String signUpClient (Client client) {
	 		boolean userExists =clientrep
	                 .findByEmail(client.getEmail())
	                 .isPresent();

	         if (userExists) {
	             throw new IllegalStateException("email already taken");
	         }
	   String encodedPassword= bCryptPasswordEncoder
	 		  .encode(client.getPassword());
	   client.setPassword(encodedPassword);
	   clientrep.save(client);
	  String token =UUID.randomUUID().toString();
	   //TODO: Send confirmation token
	   ConfirmationToken confirmationToken= new ConfirmationToken(
	 		  token,
	 		  LocalDateTime.now(),
	 		  LocalDateTime.now().plusMinutes(15),
	 		  client
	 		  
	 		  );
	   
	   confirmationTokenService.saveConfirmationToken(
	 		  confirmationToken);
	   //TODO: SEND EMAIL
	         return token ;
	 		
	 	}
	 	
	 	
	 	 public int enableUser(String email) {
	 	        return clientrep.enableUser(email);
	 	    }

		@Override
		public Client updateClient(int id){
			Client client=clientrep.findById(id).orElse(null);
			client.setAlerte(1);
			return clientrep.save(client);
		}






		}
