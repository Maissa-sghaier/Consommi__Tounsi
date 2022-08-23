package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Event;
import tn.esprit.spring.services.EventService;
import tn.esprit.spring.services.IEventService;

@RestController
public class RestControlEvent {
	@Autowired
	EventService eventservice;
	
	//http://localhost:8082/springMVC/servlet/addEvent
	@PostMapping("/addEvent")//success
	@ResponseBody
	public int addEvent(@RequestBody Event event){
		eventservice.addEvent(event);
		return event.getIdEvent();
		}
	//http://localhost:8082/springMVC/servlet/eventsLists

	@GetMapping(value="/eventsLists")//success
	@ResponseBody
	public List<Event> eventsLists(){
		return eventservice.eventsLists();
		}
	
	@PutMapping(value="/updateEvent")//success
	@ResponseBody
	public Event updateEvent(@RequestBody Event event){
		return eventservice.updateEvent(event);
		}
	
	@DeleteMapping("/deleteEvent/{id}")//success
	@ResponseBody
	public void deleteEvent(@PathVariable("id") int id) {eventservice.deleteEvent(id);}
	
	@GetMapping("/findByIdEvent/{id}")//success
	@ResponseBody
	public Event findbyId(@PathVariable("id")int id){return eventservice.findbyId(id);}
	
	@GetMapping(value="/findByNameEvent/{name}")//success
	@ResponseBody
	public List<Event> findEventByName(@PathVariable("name")String name){return eventservice.findEventByName(name);}
	
	@GetMapping("/filterEvent/{cost_event}")//success
	@ResponseBody
	public List<Event> filterEvent(@PathVariable("cost_event") double cost_event){return eventservice.filterEvent(cost_event);}
	
	@GetMapping("/upcomingEvents")//success
	@ResponseBody
	public List<Event> upcomingEvents(){return eventservice.upcomingEvents();}
	
	@GetMapping("/passedEvents")//success
	@ResponseBody
	public List<Event> passedEvents(){return eventservice.passedEvents();}
	
	@GetMapping("/displayBestEventsByfunds")//success
	@ResponseBody
	public List<String> displayBestEventsByfunds(){return eventservice.displayBestEventsByfunds();}
	
	@GetMapping("/displayBestEventsByParticipations")//success
	@ResponseBody
	public List<String> displayBestEventsByParticipations(){return eventservice.displayBestEventsByParticipations();}
	
	@GetMapping("/participationsList/{idEvent}")//success
	@ResponseBody
	public List<String> participationsList(@PathVariable("idEvent") int idEvent) {return eventservice.participationsList(idEvent);}
	
	@PostMapping(value="/affecterClientAEvent/{clientId}/{eventId}/{costEvent}") //success
	@ResponseBody
	public void affecterClientAEvent(@PathVariable("clientId")int clientId,@PathVariable("eventId") int eventId,@PathVariable("costEvent")double costEvent){
		eventservice.affecterClientAEvent(clientId,eventId,costEvent);}

}
