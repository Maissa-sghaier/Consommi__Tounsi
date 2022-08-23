import { CartitemsService } from './../../services/cartitems.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Category } from 'src/app/models/categoy';
import { Product } from 'src/app/models/product';
import { Provider } from 'src/app/models/provider';
import { CategoryService } from 'src/app/services/category.service';
import { ProductService } from 'src/app/services/product.service';
import { ProviderService } from 'src/app/services/provider.service';
import {ModalDismissReasons, NgbModal} from '@ng-bootstrap/ng-bootstrap'
import { stringify } from '@angular/compiler/src/util';
import { CoupleCP } from 'src/app/models/CoupleCP';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  id: number;
  selectedRate:number =0;
  selectedRate2:number =0;
  cartStatus=false;
  products :Observable<Product[]>;
  categories: Observable<Category[]>;
  providers: Observable<Provider[]>;
  closeResult : String;
  p: Product= new Product();
  cartId:number=0;
  cartitems:CoupleCP[]=[];
  itemsCount: number;
  constructor(private modalService: NgbModal,private route: ActivatedRoute,
    private router: Router,public productService: ProductService, private categoryService: CategoryService, private providerService : ProviderService, private cartservice: CartitemsService) { }

  ngOnInit() {
    this.cartId=JSON.parse(localStorage.getItem("cart"));

    if (this.cartitems!=null){

     this.cartservice.displayCart(this.cartId).subscribe(data=>{
        this.cartitems=data;
        this.cartservice.sendMessage(this.cartitems);});
      
  }
    this.reloadData();
  
  }

  reloadData() {
   
    this.products = this.productService.getProductList();
    this.categories = this.categoryService.getCategoryList();
    this.providers=this.providerService.getProviderList();
  }

getImg(id : number){
this.productService.getProductImg(id)
.subscribe(data => {
  console.log(data)});
}

filtre(idC : number,idP : number,r: number,promo: number,minS: number,maxS : number)

  {
this.router.navigate(['/client/Productfitred',idC,idP,r,promo,minS,maxS]);
  
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
productStatus=0;
addProductToCart(val){
  console.log('hello from product list'+this.cartId);
  if (this.cartId==null){
    this.cartservice.createCart().subscribe(data=>{
      this.cartId=data;
      localStorage.setItem("cart",stringify(this.cartId));
      this.cartservice.addProdoct(this.cartId,val,1).subscribe(
        data=>{
         this.productStatus=data;
         this.cartservice.displayCart(this.cartId).subscribe(
           data=>{
             this.cartitems=data;
             console.log('display cart'+ this.cartId);
             this.cartservice.sendMessage(this.cartitems)
           }
         );
      }
      );
      console.log('if close********'+this.cartId);
    });
  }
  else{
    this.cartservice.addProdoct(this.cartId,val,1).subscribe(
      data=>{
       this.productStatus=data;
       this.cartservice.displayCart(this.cartId).subscribe(
         data=>{
           this.cartitems=data;
           console.log('display cart'+ this.cartId);
           this.cartservice.sendMessage(this.cartitems)
         }
       );
    }
    );
    console.log('else *********'+this.cartId);
  }
  console.log('outside********'+this.cartId);
}
 
}

