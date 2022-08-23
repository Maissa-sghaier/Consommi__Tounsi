package tn.esprit.spring.repository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entity.Delivery;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery ,Integer>  {
	@Query(value="select * FROM  `delivery` d , `orderm` o where d.id_delivery =o.id_delivery and  o.client_id_user = ?1 ",nativeQuery = true)
	Optional <List<Delivery>> findDeliveryByClient(@Param("id_user")Integer id_user);
	
	
	@Query("select dl.actual_adress FROM Delivery dl WHERE dl.id_delivery = :id_delivery")
	List<String>FindDeliveryAdress(@Param("id_delivery") Integer id_delivery);
	@Query("select  dl.status_order FROM Delivery dl WHERE dl.id_delivery = :id_delivery")
	List<String>FindDeliveryStatus(@Param("id_delivery") Integer id_delivery);
	
	
	
	

}
