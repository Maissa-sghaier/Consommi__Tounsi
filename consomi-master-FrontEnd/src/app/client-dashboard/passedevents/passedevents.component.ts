import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Client } from 'src/app/models/client';
import { EventService } from 'src/app/services/event.service.service';

@Component({
  selector: 'app-passedevents',
  templateUrl: './passedevents.component.html',
  styleUrls: ['./passedevents.component.css']
})
export class PassedeventsComponent implements OnInit {
  events: Observable<Event[]>;
  idEvent:number;
  id_user:number; 
  costEvent:number;
  event:Event;
  client:Client;
  clients:Client[];
  constructor(private route: ActivatedRoute,private eventService:EventService,private router:Router) { }
  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.events = this.eventService.getPassedEvent();
  }
  
  getEventDetail(idEvent:number){
    this.router.navigate(['client/getEventDetail',idEvent])
  }

}
