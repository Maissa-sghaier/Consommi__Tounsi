package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Accounting;



@Repository
public interface IAccountingRepository extends JpaRepository<Accounting, Long >{


//	Accounting findTopOrderByIdaccountingDesc();

	Accounting findTopByOrderByIdaccountingDesc();
	@Scheduled(cron =" 59 23 31 12 *")
	@Query("SELECT sum(a.income_permonth) FROM Accounting a ")
	public double totalInc();
	@Query("SELECT sum(a.outcome_permonth) FROM Accounting a ")
	public double totalOut();
}
