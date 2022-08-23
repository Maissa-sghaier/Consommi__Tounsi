package tn.esprit.spring.repository;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.DeliveryMethod;

@Repository

public interface DeliveryMethodRepository extends JpaRepository <DeliveryMethod,Integer> {

}
