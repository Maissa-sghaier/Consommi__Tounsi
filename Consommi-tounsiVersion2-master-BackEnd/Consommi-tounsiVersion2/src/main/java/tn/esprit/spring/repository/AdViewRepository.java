package tn.esprit.spring.repository;
import tn.esprit.spring.entity.AdView;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface AdViewRepository extends JpaRepository<AdView, Integer> {
	
	
	//find adviws according to the user id and ad id
	
	@Query("SELECT adv FROM AdView adv WHERE adv.id_ad=:id_ad AND adv.id_user=:id_user")
	AdView findViewByUserNdAd(@Param("id_ad")Integer id_ad,@Param("id_user")Integer id_user);
//find adview by id_ad 
	@Query("select av.id_user from AdView av where av.id_ad = :id_ad")
	List<Integer> findByAdId(@Param("id_ad") Integer id_ad);
}
