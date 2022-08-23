package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.Ordering;
@Repository

public interface OrderingRepository extends JpaRepository <Ordering,Integer> {

}
