package tn.esprit.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import tn.esprit.spring.entity.Client;
import tn.esprit.spring.entity.UserErrors;

public interface IClientServices{

	Optional<Client> getclientByEmail(String email);

	Client getclientByUserName(String username);

	List<Client> retrieveAllClients();

	void deleteClient(int id);

	Client retrieveClient(int id);

	Client updateClient(Client c);

	UserErrors addClient(Client c);

	Client authenticate(String login, String password);
	public Client updateClient(int id);


	

}
