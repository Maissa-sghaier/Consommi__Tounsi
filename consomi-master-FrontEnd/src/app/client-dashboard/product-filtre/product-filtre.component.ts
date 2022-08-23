import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs';
import { Product } from 'src/app/models/product';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product-filtre',
  templateUrl: './product-filtre.component.html',
  styleUrls: ['./product-filtre.component.css']
})
export class ProductFiltreComponent implements OnInit {
  idC: number;
  idP: number;
  r:number;
  promo:number;
  minS: number;
  maxS: number;
  products :Observable<Product[]>;
  selectedRate:number =0;
  closeResult : String;
  p: Product= new Product();

  constructor(private route: ActivatedRoute,
    private router: Router,public productService: ProductService,private modalService: NgbModal) { }


  ngOnInit(): void {
    this.reloadData();
  }
  reloadData() {

     
    this.idC = this.route.snapshot.params['idC'];
    this.idP = this.route.snapshot.params['idP'];
    this.r = this.route.snapshot.params['r'];
   
    this.promo = this.route.snapshot.params['promo'];
    this.minS = this.route.snapshot.params['minS'];
    this.maxS = this.route.snapshot.params['maxS'];

    this.products = this.productService.getProductListFiltred(this.idC,this.idP,this.r,this.promo,this.minS,this.maxS);
  //  this.products = this.productService
}

radioChangeHandler(event : any){
  this.selectedRate= event.target.value;
}


open(content, product: Product) {
this.p=product;
  this.modalService.open(content, {size: 'lg', backdrop: 'static'}).result.then((result) => {
    this.closeResult = `Closed with: ${result}`;
  }, (reason) => {
    this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
  });
}

private getDismissReason(reason: any): string {
  if (reason === ModalDismissReasons.ESC) {
    return 'by pressing ESC';
  } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
    return 'by clicking on a backdrop';
  } else {
    return `with: ${reason}`;
  }
}
rate(id: number, i:number){
  this.productService.rateAProduct(id,i).subscribe(data => {
    console.log(data)}, 
    error => console.log(error));
 
  this.selectedRate=0;
  alert('Product rated successfully!');
  
}

  }
