package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Accounting;
import tn.esprit.spring.repository.IAccountingRepository;
import tn.esprit.spring.services.IAccountingService;

@RestController
public class AccountingRestController {

	@Autowired
	IAccountingService accser;
	@Autowired
	IAccountingRepository accRep;
	 
	@GetMapping("/accountingList")
	public List<Accounting> getAccounting(){
		return accRep.findAll();
	}
	@GetMapping("/Income")
	public double getIncome(){
		return accser.getTotalIncome();
	}
	@GetMapping("/outcome")
	public double getOutcome(){
		return accser.getTotalOutcome();
	}
	@GetMapping("/getlast")
	public Accounting updateAccounting(){
		return accser.updateAccounting();
	}
	@GetMapping("/totalInc")
	public double totalInc(){
		return accser.totalInc();
	}
	@GetMapping("/totalOut")
	public double totalOut(){
		return accser.totalOut();
	}
}
