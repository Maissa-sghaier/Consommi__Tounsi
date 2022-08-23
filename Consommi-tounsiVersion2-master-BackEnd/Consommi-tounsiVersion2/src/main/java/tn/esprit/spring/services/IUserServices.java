package tn.esprit.spring.services;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.registration.ConfirmationToken;
import tn.esprit.spring.registration.ConfirmationTokenService;
import tn.esprit.spring.repository.IUserRepository;

@Service
@AllArgsConstructor 
public class IUserServices implements UserDetailsService {
   private final static String USER_NOT_FOUND_MSG=
		   "user with email %s not found";
	@Autowired 
	IUserRepository iuserrep;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	ConfirmationTokenService confirmationTokenService;
	@Override
	public UserDetails loadUserByUsername(String email) 
			throws UsernameNotFoundException {
		
		
		return iuserrep.findByEmail(email)
				.orElseThrow(()  -> new  UsernameNotFoundException(
						String.format(USER_NOT_FOUND_MSG,email)));
	}
	
	public String signUpUser (User user) {
		boolean userExists =iuserrep
                .findByEmail(user.getEmail())
                .isPresent();

        if (userExists) {
            throw new IllegalStateException("email already taken");
        }
  String encodedPassword= bCryptPasswordEncoder
		  .encode(user.getPassword());
  user.setPassword(encodedPassword);
  iuserrep.save(user);
 String token =UUID.randomUUID().toString();
  //TODO: Send confirmation token
  ConfirmationToken confirmationToken= new ConfirmationToken(
		  token,
		  LocalDateTime.now(),
		  LocalDateTime.now().plusMinutes(15),
		  user
		  
		  );
  
  confirmationTokenService.saveConfirmationToken(
		  confirmationToken);
  //TODO: SEND EMAIL
        return token ;
		
	}
	 public int enableUser(String email) {
	        return iuserrep.enableUser(email);
	    }

}
