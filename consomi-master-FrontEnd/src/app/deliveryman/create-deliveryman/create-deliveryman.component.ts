import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { deliveryman} from 'src/app/models/deliveryman';
import { DeliverymanService } from 'src/app/services/deliveryman.service';

@Component({
  selector: 'app-create-deliveryman',
  templateUrl: './create-deliveryman.component.html',
  styleUrls: ['./create-deliveryman.component.css']
})
export class CreateDeliverymanComponent implements OnInit {

  deliveryman:deliveryman= new deliveryman();
 submitted= false;
  constructor(private deliverymanservice:DeliverymanService , private router:Router) { }

  ngOnInit(): void {
    this.deliverymanservice.getDeliverymanById(1).subscribe(data => {
      console.log(data)
    }, 
    error => console.log(error));
  
  }
  newDeliveryman():void{
    this.submitted = false;
      this.deliveryman = new  deliveryman();

  }
  save() {
    this.deliverymanservice
    .createDeliveryman(this.deliveryman).subscribe(data => {
      console.log(data)
      this.deliveryman = new deliveryman();
   
    }, 
    error => console.log(error));
  }

onSubmit() {
      this.submitted = true;
      this.save();   
      this.router.navigate(['/deliveryman']);
   
    }




  

}