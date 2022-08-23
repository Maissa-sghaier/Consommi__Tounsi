package tn.esprit.spring.repository;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Complaint;
import tn.esprit.spring.entity.Product;
import tn.esprit.spring.entity.Status;


@Repository

public interface IComplaintRepository extends CrudRepository<Complaint, Long>{

	List<Complaint> findByStatus(Status status);
	//void selectResponce(Complaint c);
	
	
    @Query("SELECT c FROM Complaint c WHERE c.date_complaint BETWEEN :d1 AND :d2")
	public List<Complaint> getByDate(@Param ( "d1") Date d1, @Param ("d2") Date d2);
    
    
   @Query(value="Select * FROM Product p ORDER BY p.nbr_complaint", nativeQuery=true)
  // @Query(value="Select Max(mycount) FROM (Select  product_id_product ,COUNT(product_id_product)  mycount From Complaint GROUP BY product_id_product)", nativeQuery=true)
    public List<Product> mostClaimed();

}
