import { Component, OnInit } from '@angular/core';
import { deliveryman } from '../../models/deliveryman';
import { DeliverymanService } from '../../services/deliveryman.service';
import { Router } from '@angular/router';
import {Observable} from "rxjs/index";
import {ModalDismissReasons, NgbModal} from '@ng-bootstrap/ng-bootstrap'
@Component({
  selector: 'app-retrieve-all-deliveryman',
  templateUrl: './retrieve-all-deliveryman.component.html',
  styleUrls: ['./retrieve-all-deliveryman.component.css']
})
export class RetrieveAllDeliverymanComponent implements OnInit {

  listDeliveryman:Observable<deliveryman[]>;
  deliveryMan:deliveryman= new deliveryman();
  submitted= false;
  deliverymanToupload : deliveryman=new deliveryman();
  closeResult:String;

  constructor(private deliverymanservice:DeliverymanService, private router:Router,private modalService:NgbModal) { }

  ngOnInit(): void {
    this.listdeliveryman();
  }
  //retrieve deliveryman
  listdeliveryman(){
    this.listDeliveryman=this.deliverymanservice.getDeliveryman();
    }
    //delete DeliveryMan
    DeleteDeliveryman(id:number){
      this.deliverymanservice.deleteDeliveryman(id).subscribe(data => {console.log(data);}
      ,error => console.log(error));
      this.ngOnInit();
      this.router.navigate(['/listdeliveryman']);
    }
    //add deliveryman
      newDeliveryman():void{
        this.submitted = false;
          this.deliveryMan = new  deliveryman();
    
      }
      save() {
        this.deliverymanservice
        .createDeliveryman(this.deliveryMan).subscribe(data => {
          console.log(data)
          this.deliveryMan = new deliveryman();
       
        }, 
        error => console.log(error));
      }
    
    
       
       
      
        onSubmit() {
          this.submitted = true;
          this.save();   
          this.router.navigate(['/listdeliveryman']);
          
        }
      
//update deliveryman



}

  
  


  