import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Client } from 'src/app/models/client';
import { ClientService } from 'src/app/services/client.service';

@Component({
  selector: 'app-create-client',
  templateUrl: './create-client.component.html',
  styleUrls: ['./create-client.component.css']
})
export class CreateClientComponent implements OnInit {
 client:Client= new Client();
 submitted= false;
  constructor(private clientservice:ClientService , private router:Router) { }

  ngOnInit(): void {
    
    }



//add client
newClient():void{
  this.submitted = false;
    this.client = new  Client();

}
save() {
  this.clientservice
  .createClient(this.client).subscribe(data => {
    console.log(data)
    this.client = new Client();
 
  }, 
  error => console.log(error));
}


 
 

  onSubmit() {
    this.submitted = true;
    this.save();   
    this.router.navigate(['/createClient']);
 
  }


}
