import { Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { Provider } from 'src/app/models/provider';
import { ProviderService } from 'src/app/services/provider.service';

@Component({
  selector: 'app-create-provider',
  templateUrl: './create-provider.component.html',
  styleUrls: ['./create-provider.component.css']
})
export class CreateProviderComponent implements OnInit {
  provider: Provider = new Provider();
   submitted = false;
   constructor( private providerService : ProviderService, private router: Router) { }
 
   ngOnInit(): void {
   }
   newProvider():void{
     this.submitted = false;
     this.provider = new Provider();
   }
   save() {
     this.providerService
     .createProvider(this.provider).subscribe(data => {
       console.log(data)
       this.provider = new Provider();
    
     }, 
     error => console.log(error));
   }
 
   onSubmit() {
     this.submitted = true;
     this.save();   
     this.router.navigate(['/client/updateProvider']);
  
   }
 
 
 }
 