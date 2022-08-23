package tn.esprit.spring.repository;

//import java.util.Date;
//import java.util.List;
//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.DeliveryMan;
//import tn.esprit.esponline.entity.User;
@Repository
public interface IDeliveryManRepository extends JpaRepository<DeliveryMan, Integer> {
	@Query("SELECT d FROM DeliveryMan d WHERE d.username = :username")
	DeliveryMan findByUsername(@Param("username")String username);
	@Query("SELECT d FROM DeliveryMan d WHERE d.email = :email")
	DeliveryMan findByEmail(@Param("email")String email);
	DeliveryMan findByEmailAndPassword(String login, String password);	
	

}
