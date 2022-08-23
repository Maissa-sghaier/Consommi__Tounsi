package tn.esprit.spring.repository;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.CodePromo;;
@Repository

public interface CodePromoRepository extends CrudRepository <CodePromo,Integer>{
	@Query("SELECT c FROM CodePromo c WHERE c.code_promo = ?1")
	public CodePromo retrievingCode(String code);

}
