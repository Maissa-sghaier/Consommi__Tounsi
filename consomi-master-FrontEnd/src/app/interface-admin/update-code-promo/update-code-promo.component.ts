import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { codePromo } from 'src/app/models/codePromo';
import { CodePromoService } from 'src/app/services/code-promo.service';

@Component({
  selector: 'app-update-code-promo',
  templateUrl: './update-code-promo.component.html',
  styleUrls: ['./update-code-promo.component.css']
})
export class UpdateCodePromoComponent implements OnInit {
  codePromos :Observable<codePromo[]>;

  constructor(private route: ActivatedRoute,
    private router: Router,public codePromoService: CodePromoService) { }

    ngOnInit() {

      this.reloadData();
       
      }
    
      reloadData() {
        this.codePromos = this.codePromoService.getCodePromoList();

      }
      goToList() {
        this.reloadData();
        this.router.navigate(['/updatecodepromo']);
      }
      deleteCodePromo(id: number) {
        this.codePromoService.deleteCodePromo(id)
          .subscribe(
            data => {
              console.log(data);
              this.goToList();
            },
            error => console.log(error));
      }

      activateCode(id : number){
        this.codePromoService.activateCodePromo(id)
        .subscribe(
          data => {
            console.log(data);
            this.goToList();
          },
          error => console.log(error));
      }
      
      desactivateCode(id : number){
        this.codePromoService.desactivateCodePromo(id)
        .subscribe(
          data => {
            console.log(data);
            this.goToList();
          },
          error => console.log(error));
      }

      
}
