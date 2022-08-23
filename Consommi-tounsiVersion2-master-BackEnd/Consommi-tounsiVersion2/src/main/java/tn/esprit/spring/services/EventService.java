package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twilio.type.PhoneNumber;

import tn.esprit.spring.entity.Client;
import tn.esprit.spring.entity.Event;
import tn.esprit.spring.entity.Fund;
import tn.esprit.spring.entity.SmsRequest;
import tn.esprit.spring.repository.CartRepository;
import tn.esprit.spring.repository.IClientRepository;
import tn.esprit.spring.repository.EventRepository;
import tn.esprit.spring.repository.FundRepository;

@Service
public class EventService implements IEventService {
	@Autowired
	EventRepository eventrep;
	
	@Autowired
	FundRepository fundrep;
	
	@Autowired
	IClientRepository clientrep;
	
	@Autowired
	CartRepository cartrep;
	
	@Autowired
	TwilioSmsSender tw;
	
	@Autowired 
	SmsService smsService;
	
	//ADMINISTRATEUR 
	@Override
	public int addEvent(Event event) {
		
		Fund f=new Fund();
		f.setAmount(0);
		event.setFund(f);
		eventrep.save(event);
		fundrep.save(f);
		return event.getIdEvent();

	}
	//ADMINISTRATEUR et USER
	@Override
	public List<Event> eventsLists() {
		List<Event> eventLists =(List<Event>) eventrep.findAll();
		return eventLists;
		
	}
	//ADMINISTRATEUR
	@Override
	public Event updateEvent(Event event) {
		
		return eventrep.save(event);
		
	}
	//ADMINISTRATEUR
	@Override
	public void deleteEvent(int id) {
		 eventrep.deleteById(id);
		
	}
	//ADMINISTRATEUR et USER
	@Override
	public Event findbyId(int id) {
		return eventrep.findById(id).get();
		
	}
	//ADMINISTRATEUR et USER
	@Override
	public List<Event> findEventByName(String name) {
		List<Event> list=(List<Event>) eventrep.findByNameEvent(name);
		return list;
	}
	//ADMINISTRATEUR et USER
	@Override
	public List<Event> filterEvent(double costevent) {
		return eventrep.filterByCostEvent(costevent);
	}
	//ADMINISTRATEUR et USER
	@Override
	public List<Event> upcomingEvents() {
		List<Event> list= eventrep.upcomingEvents();
		return list;
		}
	
	//ADMINISTRATEUR et USER
	@Override
	public List<Event> passedEvents() {
		List<Event> list= eventrep.passedEvents();
		return list;
		
	}

	

	@Override
	public List<String> displayBestEventsByfunds() {
		List<String> list = new ArrayList<>();
		String s = "";
		List<Integer> listId = new ArrayList<>();
		List<Integer> lc= new ArrayList<>();
		List<Event> listEvent = (List<Event>) eventrep.findAll();
		
		for (Event ev : listEvent) {
			listId.add(ev.getIdEvent());
			lc.add((int)ev.getCostEvent());
		}
		
		List<Integer> sortedList = new ArrayList<>(lc);
		Collections.sort(sortedList);
		
		for (int i=0; i<3; i++) {
			int max = sortedList.get(sortedList.size()-1);// retourne le max qui a la dernière position de la liste
			Integer ind = listId.get(lc.indexOf(max));// prend le collecte et retourne id d'event
			s =(i+1)+" - Event: "+eventrep.findById(ind).get().getNameEvent()+" with "+max+"DT collected amount. ";
			list.add(s);
			sortedList.remove(sortedList.size()-1);
			lc.set(lc.indexOf(max), -1);
		}
		
		return list;
	}

	@Override
	public List<String> displayBestEventsByParticipations() {
		List<String> list = new ArrayList<>();
		String s = "";
		List<Integer> listId = new ArrayList<>();
		List<Integer> lp= new ArrayList<>();//nbre de participants
		List<Event> listEvent = (List<Event>) eventrep.findAll();
		
		for (Event ev : listEvent) {
			listId.add(ev.getIdEvent());
			lp.add(ev.getListclient().size());
		}
		
		List<Integer> sortedList = new ArrayList<>(lp);
		Collections.sort(sortedList);
		
		for (int i=0; i<3; i++) {
			int max = sortedList.get(sortedList.size()-1);// retourne le max qui a la dernière position de la liste
			int ind = listId.get(lp.indexOf(max));// prend nbre de participations et retourne id d'event corresspondant
			s =(i+1)+" - Event: "+eventrep.findById(ind).get().getNameEvent()+" with "+max+" participations ";
			list.add(s);
			sortedList.remove(sortedList.size()-1);
			lp.set(lp.indexOf(max), -1);
		}
		
		return list;
	}
	@Override
	public List<String> participationsList(int idEvent) {
		Event e =eventrep.findById(idEvent).orElse(null);
		List<Client> listcl=new ArrayList<Client>();
		listcl=e.getListclient();
		List<String> lnoms=new ArrayList<String>();
		for(Client c:listcl)
		{
			lnoms.add("NAME AND LASTNAME: "+c.getFirst_name()+" "+c.getLast_name()+" EMAIL: "+c.getEmail());
		}
		
		return lnoms;
		}
	@Override
	public void affecterClientAEvent(int ClientId, int EventId,double costEvent) {
		Client c=clientrep.findById(ClientId).orElse(null);
		Event e=eventrep.findById(EventId).orElse(null);
		if(e.getCostEvent()>=costEvent){
		e.getListclient().add(c);
		Fund f = e.getFund();
		double valeur =e.getCostEvent()-costEvent;
		e.setCostEvent(valeur);
		//la valeur est ajouté dans le fund
		double amount=f.getAmount()+costEvent;
		f.setAmount(amount);
		//le nombre de place va diminuer
		int nv_nbreDePlace=e.getNbPlaceDisponible()-1;
		e.setNbPlaceDisponible(nv_nbreDePlace);
		//un message est envoyé
		String msg="Hello,Dear Mr/Mrs "+"  "+c.getFirst_name()+" "+c.getLast_name()+"   You are registered in The event "+
		e.getNameEvent()+"  "
		+" you have chosen to donate "+costEvent+"." +" this value is already added to your shopping cart,please finish the payment ."
				+ "Consommi tounsi thanks you for your donation" +" --- CONSOMMI TOUNSI---- ";
		SmsRequest smsRequest = new SmsRequest ("+21656628701",msg);
		//send a mail sms to the client 
		smsService.sendSms(smsRequest );
		//tw.sendSms(smsRequest);
		
		eventrep.save(e);
		fundrep.save(f);
		}
		else 
		{System.out.println("The event is closed.thank you ");}
		
		
	}
	
	
}

