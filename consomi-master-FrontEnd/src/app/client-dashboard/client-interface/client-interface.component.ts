import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Client } from '../../models/client';
import { ClientService } from '../../services/client.service';

@Component({
  selector: 'app-client-interface',
  templateUrl: './client-interface.component.html',
  styleUrls: ['./client-interface.component.css']
})
export class ClientInterfaceComponent implements OnInit {
client: Client= new Client();
id:number;
  constructor(private clientservice:ClientService, private route:ActivatedRoute) 
  
  {

   }

  ngOnInit(): void {
    
    this.id=this.route.snapshot.params['id'];
    this.clientservice.getClientById(this.id).subscribe(data => {this.client=data});
    console.log(this.client);

  }
  onsubmit(){
    console.log(this.client);
    this.clientservice.updateClient(this.client.id_user,this.client).subscribe((resp)=>{console.log(resp);},(err)=>{console.log(err);});
  }


}
