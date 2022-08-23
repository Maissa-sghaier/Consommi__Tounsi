package tn.esprit.spring.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Category;
import tn.esprit.spring.entity.Product;

@Repository

public interface ProductRepository extends JpaRepository <Product,Integer>{
	@Query("SELECT p FROM Product p WHERE p.name= :name")
	Product retrieveProductByName(@Param("name") String name);
	
	List<Product> findByName(String name);
	
	@Query(value = "select p.* from Product p join Category c on p.category_id=c.id  where c.name_category = :category", nativeQuery = true)
	public List<Product> getByCategoryName(@Param("category") String category);
	

	@Query("SELECT p FROM Product p WHERE p.status_promotion= ?1")
	List<Product> retrieveProductByPromotion(Boolean b);
	
	@Query("SELECT p FROM Product p WHERE p.category= :c")
	List<Product> retrieveProductsByCategory(@Param("c") Category c);

	@Query(value = "select * from Product p  where  p.selling_price between :binf and :bsup", nativeQuery = true)
	public List<Product> getByPriceBetween(@Param("binf") float binf, @Param("bsup") float bsup);

	@Query("SELECT p FROM Product p WHERE p.bar_code= :bar_code")
	public List<Product> getProductByBarCode(long bar_code);
}
