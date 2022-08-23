package tn.esprit.spring.repository;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entity.*;


@Repository
public interface EventRepository extends CrudRepository <Event,Integer> {
	public List<Event> findByNameEvent(String name);
	
	@Query("SELECT ev FROM Event ev WHERE ev.costEvent=:costevent")
	public List<Event> filterByCostEvent(@Param ("costevent") double costevent);

	@Query("SELECT ev FROM Event ev where ev.startDateEvent >= CURRENT_DATE()")
	public List<Event> upcomingEvents();
	
	@Query("SELECT ev FROM Event ev where ev.endDateEvent  < CURRENT_DATE()")
	List<Event> passedEvents();
	
	
}

