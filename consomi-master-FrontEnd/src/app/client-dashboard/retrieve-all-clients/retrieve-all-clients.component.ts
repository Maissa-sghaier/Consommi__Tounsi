import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Client } from 'src/app/models/client';
import { ClientService } from 'src/app/services/client.service';

@Component({
  selector: 'app-retrieve-all-clients',
  templateUrl: './retrieve-all-clients.component.html',
  styleUrls: ['./retrieve-all-clients.component.css']
})
export class RetrieveAllClientsComponent implements OnInit {
  listClient:Observable<Client[]>;
  client:Client= new Client();
  constructor( private clientservice:ClientService) { }

  ngOnInit(): void {
    this.listclient();
  }
  //retrieve deliveryman
  listclient(){
    this.listClient=this.clientservice.getClient();
    }
}
