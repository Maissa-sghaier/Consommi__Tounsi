import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { EventService } from 'src/app/services/event.service.service';
import {Event} from 'src/app/models/event'
@Component({
  selector: 'app-event-details',
  templateUrl: './event-details.component.html',
  styleUrls: ['./event-details.component.css']
})
export class EventDetailsComponent implements OnInit {
  idEvent:number;
  event:Event;
  constructor(private route: ActivatedRoute,private router: Router,
    private eventService: EventService) { }

  ngOnInit(){
    this.event=new Event();

    this.idEvent=this.route.snapshot.params['idEvent'];
    this.eventService.getEventDetail(this.idEvent).subscribe(data => {
      console.log(data)
      this.event = data;
    }, error => console.log(error));
  }
  list(){
    this.router.navigate(['client/events'])
  }
  getListParticipants(idEvent:number){
    this.router.navigate(['client/ListParticipants',idEvent])
  }
  getEventDetail(idEvent:number){
    this.router.navigate(['client/getEventDetail',idEvent])
  }
  key='idEvent';
  reverse:boolean=false;
  sort(key){
    this.key=key;
    this.reverse=!this.reverse;

}
}
