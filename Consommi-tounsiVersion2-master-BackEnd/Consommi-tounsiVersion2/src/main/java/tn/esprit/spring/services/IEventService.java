package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entity.Event;

public interface IEventService {
	
	public int addEvent(Event event);//by admin
	public List<Event> eventsLists();//by admin and client
	public Event updateEvent(Event event);//by admin
	public void deleteEvent(int id) ;//by admin
	public Event findbyId(int id);//by admin and client
	public List<Event> findEventByName(String name);//by admin and client
	public List<Event> filterEvent(double cost_event);//by admin and client
	public List<Event> upcomingEvents();//by admin and client
	public List<Event> passedEvents();//by admin and client
	public List<String> displayBestEventsByfunds();//by admin and client
	public List<String> displayBestEventsByParticipations();//by admin and client
	public List<String> participationsList(int idEvent) ;
	public void affecterClientAEvent(int ClientId, int EventId,double costEvent);

}
