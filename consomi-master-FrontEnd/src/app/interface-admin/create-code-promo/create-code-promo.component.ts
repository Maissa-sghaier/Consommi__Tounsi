import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { codePromo } from 'src/app/models/codePromo';
import { CodePromoService } from 'src/app/services/code-promo.service';

@Component({
  selector: 'app-create-code-promo',
  templateUrl: './create-code-promo.component.html',
  styleUrls: ['./create-code-promo.component.css']
})
export class CreateCodePromoComponent implements OnInit {

  codePromo: codePromo = new codePromo();
  submitted = false;
  constructor( private codePromoService : CodePromoService, private router: Router) { }

  ngOnInit(): void {
  }
  newProvider():void{
    this.submitted = false;
    this.codePromo = new codePromo();
  }
  save() {
    this.codePromoService
    .createCodePromo(this.codePromo).subscribe(data => {
      console.log(data)
      this.codePromo = new codePromo();
   
    }, 
    error => console.log(error));
  }

  onSubmit() {
    this.submitted = true;
    this.save();   
    this.router.navigate(['/categoryList']);
 
  }

}

