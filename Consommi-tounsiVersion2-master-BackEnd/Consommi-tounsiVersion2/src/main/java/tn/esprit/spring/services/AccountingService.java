package tn.esprit.spring.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Accounting;
import tn.esprit.spring.entity.Ad;
import tn.esprit.spring.entity.DeliveryMan;
import tn.esprit.spring.entity.Event;
import tn.esprit.spring.entity.Fund;
import tn.esprit.spring.entity.Ordering;
import tn.esprit.spring.repository.AdRepository;
import tn.esprit.spring.repository.EventRepository;
import tn.esprit.spring.repository.FundRepository;
import tn.esprit.spring.repository.IAccountingRepository;
import tn.esprit.spring.repository.IDeliveryManRepository;
import tn.esprit.spring.repository.OrderingRepository;



@Service
public class AccountingService implements IAccountingService{

	@Autowired
	IAccountingRepository accrepository;
	@Autowired
	AdRepository adrepository;
	@Autowired
	IDeliveryManRepository dmrep;
	@Autowired
	EventRepository evenrep;
	@Autowired
	OrderingRepository orderep;
	@Autowired
	FundRepository fundrep;
	@Autowired
	AccountingService accser;
	
	@Override
	
	public double getOutcomeAd(){
		
		List<Ad> adList= (List<Ad>) adrepository.findAll();
		double amount=0D;
		for(Ad ad:adList){
			 
			Date date = ad.getEndDate();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			Date date2 = new Date();
			Calendar calendar2 = Calendar.getInstance();
			calendar2.setTime(date2);
			if(calendar.get(Calendar.MONTH)==calendar2.get(Calendar.MONTH) )
			amount+= ad.getCost();
		}
		return amount;
	}
	@Override
	public double getOutcomeDelivaryMan(){
		List<DeliveryMan> dManList= (List<DeliveryMan>) dmrep.findAll(); 
		
		double amount=0D;
		for(DeliveryMan dm:dManList){
			amount+= dm.getSalary();
		}
		return amount;
		
	}
	
	@Override
	public double getOutcomeEvent(){
		
	
		List<Event> eventList= (List<Event>) evenrep.findAll();
		double amount=0D;
		for(Event event:eventList){
			        Date date= event.getEndDateEvent();
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					Date date2 = new Date();
					Calendar calendar2 = Calendar.getInstance();
					calendar2.setTime(date2);
			if(calendar.get(Calendar.MONTH)==calendar2.get(Calendar.MONTH) )
			amount+= event.getCostEvent();
		}
		return amount;
	}
	
	@Override
	public double getIncomeOrder(){
		
		List<Ordering> order=(List<Ordering>) orderep.findAll();
		double amount=0D;
		for(Ordering or:order){
			Date date= or.getOder_date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			Date date2 = new Date();
			Calendar calendar2 = Calendar.getInstance();
			calendar2.setTime(date2);
	if(calendar.get(Calendar.MONTH)==calendar2.get(Calendar.MONTH) )
			amount+= or.getSum();
		}
		return amount;
	}
	
	@Override
	public double getIncomeFund(){
		List<Fund> fundList=(List<Fund>) fundrep.findAll();
		double amount=0D;
		for(Fund fund:fundList){
			Date date= fund.getDate_fund();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			Date date2 = new Date();
			Calendar calendar2 = Calendar.getInstance();
			calendar2.setTime(date2);
	if(calendar.get(Calendar.MONTH)==calendar2.get(Calendar.MONTH) )
			amount+= fund.getAmount();
		}
		return amount;
	}
	
	@Override
	public double getTotalIncome(){
		Accounting acc=new Accounting();
		double x=0D;
		x+= accser.getIncomeFund()+accser.getIncomeOrder();
		acc.setIncome_permonth(x);;
		return x;
	}
	@Override
	public double getTotalOutcome(){
		Accounting acc=new Accounting();
		double x=0D;
		x+= accser.getOutcomeAd()+ accser.getOutcomeDelivaryMan()+ accser.getOutcomeEvent();
		acc.setOutcome_permonth(x);
		return x;
	}
	@Override
	@Scheduled(cron="0 0 1 * * ")
	public Accounting addAccounting(){
		Accounting acc=new Accounting();
		accrepository.save(acc);
		return acc;
	}
	@Override
	public double gain_permonth(){
		double x=0D;
		x=accser.getTotalIncome()-accser.getTotalOutcome();
		return x;
	}
	
	@Override
	@Scheduled(cron =" 59 23 31 12 *")
	public double totalInc(){
		return accrepository.totalInc();
	}
	@Override
	@Scheduled(cron =" 59 23 31 12 *")
	public double totalOut(){
		return accrepository.totalOut();
	}
		
@Override
	public Accounting updateAccounting(){
	
		
		Accounting acc=new Accounting();

		acc=accrepository.findTopByOrderByIdaccountingDesc();
		acc.setOutcome_permonth(accser.getTotalOutcome());
		acc.setIncome_permonth(accser.getTotalIncome());
		acc.setGain_permonth(accser.gain_permonth());
		acc.setTotal_inc(accser.totalInc());
		acc.setTotal_exp(accser.totalOut());
		accrepository.save(acc);
		
		
		return acc;
		
	}
		
	
	
}
