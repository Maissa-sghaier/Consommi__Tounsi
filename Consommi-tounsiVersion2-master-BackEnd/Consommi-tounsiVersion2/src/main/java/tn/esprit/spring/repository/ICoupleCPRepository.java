package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Cart;
import tn.esprit.spring.entity.CoupleCP;
import tn.esprit.spring.entity.Product;
@Repository
public interface ICoupleCPRepository extends CrudRepository<CoupleCP, Integer> {
	public CoupleCP findByCartAndProduct(Cart cart, Product product);
	public List<CoupleCP> findByCart(Cart cart);
	

}
