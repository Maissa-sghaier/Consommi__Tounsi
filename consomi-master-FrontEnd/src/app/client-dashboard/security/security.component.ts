import { Component, OnInit } from '@angular/core';

import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { JwtClientService } from '../../services/jwt-client.service';
//import { Client } from '../models/';
import { ClientService } from '../../services/client.service';
@Component({
  selector: 'app-security',
  templateUrl: './security.component.html',
  styleUrls: ['./security.component.css']
})
export class SecurityComponent implements OnInit {


 idActif: number; 
authRequest:any;
c:any;  
response:any;


  constructor(private service:JwtClientService, private clientservice:ClientService, private router : Router) { }
 onSubmit(f:NgForm) {
    console.log(f);
    const username = f.value['username'];
    const password= f.value['password'];
   this.authRequest={
      "username":username,
      "password":password
        };
        this.getAccessToken(this.authRequest);
      }
  
 
  ngOnInit() {}
public getAccessToken(authRequest){
let resp=this.service.generateToken(authRequest);
resp.subscribe(data=>this.accessApi(data));

//console.log(this.c);

  }


  public accessApi(token){
let resp=this.service.welcome(token,this.authRequest.username);
resp.subscribe(data=>{this.response=data.substring(11,data.indexOf(','))
 console.log(this.response)
 this.router.navigate(['/clientinterface',this.response]);})
//console.log(data.substring(11,data.indexOf(','))





//this.router.navigate(['/listdeliveryman',this.response]);
//public retrieveClient(id_user ,token){
  //let resp=this.service.welcome(token);
  //resp.subscribe(data=>this.clientservice.getClientById(id_user))
  
//}




}




}
