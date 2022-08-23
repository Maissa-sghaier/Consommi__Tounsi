package tn.esprit.spring.repository;

import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Client;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;
import tn.esprit.spring.entity.User;

@Repository
@Transactional(readOnly = true)
public interface IUserRepository extends JpaRepository<User, Integer> {
//	@Query("SELECT u FROM User u WHERE u.username = :username")
//    User findByUsername(@Param("username")String username);
//	@Query("SELECT u FROM User u WHERE u.email = :email")
//    User findByEmail(@Param("email")String email);
//	User findByEmailAndPassword(String login, String password);	

Optional<User> findByEmail(String email);

@Transactional
@Modifying
@Query("UPDATE User u " +
        "SET u.enabled = TRUE WHERE u.email = ?1")
int enableUser(String email);

//void save(Optional<Client> getclientByEmail);

}

	  


