import { Product } from './../../models/product';
import { codePromo } from './../../models/codePromo';
import { Subject, Observable } from 'rxjs';
import { CartitemsService } from './../../services/cartitems.service';
import { CoupleCP } from './../../models/CoupleCP';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { address } from './../../models/address';
import { map } from 'rxjs/operators';
import { stringify } from '@angular/compiler/src/util';

@Component({
  selector: 'app-cart-management',
  templateUrl: './cart-management.component.html',
  styleUrls: ['./cart-management.component.css']
})
export class CartManagementComponent implements OnInit {
  totalfees:number=0;
  cartId:number=0;
  deliverySubmitted=false;
  codePromo:String="";
  paymentmethod:string='';
  code:codePromo={
  id_codePromo :0,
	code_promo :"",
	status_CodePromo:false,
 
    available_nbr :0,
  }
  provinces=['Tunis','Ariana','Ben Arous','Bizert','Gabes','Kebili','Sidi Bouzid','Mehdia','Sousse','Hammamet','Sfax','Touzer','Gafsa','Medenine','Kef','Beja','Nabeul','Jendouba','Kairouene','Zaghouan','Mannouba','Mounastir','Kasrine','Tataouine']
  theAddress= new address();
  constructor(private cartservice: CartitemsService) {
    }
  
  cartitems: CoupleCP[]=[];
  avalaibility :number=0;
  cartstatus=false;
  ngOnInit(): void {
    this.invokeStripe();

    this.cartId=JSON.parse(localStorage.getItem("cart"));
      if (this.cartitems==null){
        this.cartstatus=false;
        
      
      }
      else{
        this.cartstatus=true;
        this.cartservice.displayCart(this.cartId).subscribe(data=>{
          this.cartitems=data;
          this.cartservice.sendMessage(this.cartitems);
        })
        
          
       this.cartservice.displayFees(this.cartId,0,0).subscribe(
          fee=>{
            this.totalfees=fee;
            console.log("what the fuck"+this.totalfees);
            }
        );
      }
  }
  
  showProducts(val){
    this.cartservice.displayCart(val).subscribe(data=>{
      this.cartitems=data;
      this.cartservice.sendMessage(this.cartitems);
      this.cartservice.displayFees(this.cartId,0,0).subscribe(
        fee=>{
          this.totalfees=fee;
          console.log("what the fuck"+this.totalfees);
          }
      );
      
    })
  
  }
 deletingProduct(val){
   if (confirm("are you sure?")){
    this.cartservice.deleteProduct(val).subscribe(
      ()=> {this.showProducts(this.cartId);}
    );
    
  }
 }
 updateProductQuantity(itemID:any, productName: any, quantity:any,originalQuantity:any){
   if ((quantity==(-1))&&(originalQuantity==1)){
     this.deletingProduct(itemID);
   }
   else{
   this.cartservice.updateProduct(itemID,productName,quantity).subscribe(data=>{
    this.avalaibility= data;
    if (this.avalaibility>0){
    this.showProducts(this.cartId);
  }
  else{
    alert('We are sorry , it is out of stock');
  }
  });
 }
}
errMsg;
onCodeChange($event){
  this.cartservice.getcode(this.codePromo).subscribe(
    data=>{
      this.code=data;
      if (this.code==null){
        this.errMsg='Wrong code'
      }
      else{
        this.errMsg='You got a discount'
      }
     //console.log('the status '+ this.code.status_CodePromo);
    }, 
    error => {
      this.errMsg=error;
    }
  )
  console.log('the name'+this.codePromo);
  
}
cityError =true;
validCity(val){
  if (val ==='default'){ this.cityError=true}
  else {this.cityError=false}
}
distanceMap = new Map([
  ['Tunis',10],
  ['Ariana',13],
  ['Ben Arous',13] ,
  ['Bizert',65],
  ['Gabes',426],
  ['Kebili',524],
  ['Sidi Bouzid',283],
  ['Mehdia',223],
  ['Sousse',158],
  ['Hammamet',77],
  ['Sfax',278],
  ['Touzer',465],
  ['Gafsa',374],
  ['Medenine',500],
  ['Kef',177],
  ['Beja',120],
  ['Nabeul',79],
  ['Jendouba',165],
  ['Kairouene',172],
  ['Zaghouan',68],
  ['Mannouba',19],
  ['Mounastir',180],
  ['Kasrine',309],
  ['Tataouine',553]]);
prod:Product=new Product();
weight:number=0;
orderCall(){
  new Promise<void>((resolve) => {
    setTimeout(()=>{
      this.deliverySubmit();
      resolve();
    }
    ,100);

  })
  .then(_=> new Promise<void>(resolve => {
    setTimeout(()=>{
      this.www();
      resolve();
    }
    ,100);
    })
  )
  .then(_=> new Promise<void>(resolve => {
    setTimeout(()=>{
      this.deliverySubmitted=true;

    }
    ,50);
    })
  )
  ;
}

deliverySubmit(){
  
  localStorage.setItem("weight",stringify(0));
  for (let items of this.cartitems){
    this.weight=JSON.parse(localStorage.getItem("weight"));
    this.cartservice.getProduct(items.productid).subscribe(
      data=>{
        this.prod=data;
        console.log(this.prod.poids);
        console.log(items.productname);
        this.weight+=this.prod.poids;
        localStorage.setItem("weight",stringify(this.weight));
        
      }
    );
    
  }

  
}
dist:number;
deliveryFees:number;
www(){
  this.dist=this.distanceMap.get(this.theAddress.state);
  this.weight=JSON.parse(localStorage.getItem("weight"));
  this.cartservice.getDeliveryFees(this.dist,this.weight).subscribe(
    data=>{
      this.deliveryFees=data;
      console.log(this.deliveryFees)
    }
  )

}
pay(){
  if (this.paymentmethod=="stripe"){
    this.makePayment(this.totalfees);
  }
}
paymentHandler: any = null;
makePayment(amount: any) {
  const paymentHandler = (<any>window).StripeCheckout.configure({
    key:
      'pk_test_51IfNm2HeiGizEWFJmGxk1Jv6VJbQaArIX8nzqnB0TwlyVMpNS2tl4E9ZaD1YeonlJCULZHTfbLtO5IpnY5R0ehQL00e2oaLTov',

    locale: 'auto',
    token: function (stripeToken: any) {
      console.log(stripeToken.card);
      document.location.href = 'https://youtube.com';
      
    },
    
  });

  paymentHandler.open({
    name: 'Consommi Tounsi',
    description: 'Products Added',
    amount: amount ,
    currency: 'tnd',
  });
  
}
invokeStripe() {
  if (!window.document.getElementById('stripe-script')) {
    const script = window.document.createElement('script');
    script.id = 'stripe-script';
    script.type = 'text/javascript';
    script.src = 'https://checkout.stripe.com/checkout.js';
    window.document.body.appendChild(script);
  }
}
}
