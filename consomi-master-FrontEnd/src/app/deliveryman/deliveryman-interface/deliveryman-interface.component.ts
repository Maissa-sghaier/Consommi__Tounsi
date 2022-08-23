import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { deliveryman } from 'src/app/models/deliveryman';
import { DeliverymanService } from 'src/app/services/deliveryman.service';

@Component({
  selector: 'app-deliveryman-interface',
  templateUrl: './deliveryman-interface.component.html',
  styleUrls: ['./deliveryman-interface.component.css']
})
export class DeliverymanInterfaceComponent implements OnInit {

  Deliveryman: deliveryman= new deliveryman();
  id:number;
 constructor(private deliverymanservice:DeliverymanService, private router:Router,private modalService:NgbModal,private route:ActivatedRoute) { }

 ngOnInit(): void {
 
   this.id=this.route.snapshot.params['id'];
   this.deliverymanservice.getDeliverymanById(this.id).subscribe(data => {this.Deliveryman=data});
   console.log(this.Deliveryman);
 }
 onsubmit(){
  console.log(this.Deliveryman);
   this.deliverymanservice.updateDeliveryman(this.Deliveryman.id_user,this.Deliveryman).subscribe((resp)=>{console.log(resp);},(err)=>{console.log(err);});
  
 }

}
