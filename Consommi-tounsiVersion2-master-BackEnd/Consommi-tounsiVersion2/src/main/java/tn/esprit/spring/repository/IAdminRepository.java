package tn.esprit.spring.repository;

//import java.util.Date;
//import java.util.List;
//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Admin;
import tn.esprit.spring.entity.Client;
//import tn.esprit.esponline.entity.User;
@Repository
public interface IAdminRepository extends JpaRepository<Admin, Integer> {
	@Query("SELECT a FROM Admin a WHERE a.username = :username")
   Admin findByUsername(@Param("username")String username);
	@Query("SELECT a FROM Admin a WHERE a.email = :email")
   Admin findByEmail(@Param("email")String email);
	Admin findByEmailAndPassword(String login, String password);	
	

}
