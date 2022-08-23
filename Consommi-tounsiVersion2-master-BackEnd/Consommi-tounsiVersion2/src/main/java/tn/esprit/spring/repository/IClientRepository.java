package tn.esprit.spring.repository;

import java.util.Optional;

//import java.util.Date;
//import java.util.List;
//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entity.Client;
//import tn.esprit.esponline.entity.User;
@Repository
public interface IClientRepository extends JpaRepository<Client, Integer> {
	@Query("SELECT c FROM Client c WHERE c.username = :username")
	   Client findByUsername(@Param("username")String username);
//		@Query("SELECT c FROM Client c WHERE c.email = :email")
//		Client  findClientByEmail(@Param("email")String email);
		Client  findByEmailAndPassword(String login, String password);
		
		Optional<Client> findByEmail(String email);

		@Transactional
		@Modifying
		@Query("UPDATE Client c " + 
		       "SET c.enabled = TRUE WHERE c.email = ?1")
		int enableUser(String email);
		

}
