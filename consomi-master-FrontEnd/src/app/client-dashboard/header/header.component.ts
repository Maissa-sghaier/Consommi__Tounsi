import { CoupleCP } from './../../models/CoupleCP';
import { Component, OnInit } from '@angular/core';
import { CartitemsService} from 'src/app/services/cartitems.service';
import { stringify } from '@angular/compiler/src/util';
import { Category } from 'src/app/models/categoy';
import { Observable } from 'rxjs';
import { CategoryService } from 'src/app/services/category.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private cartservice: CartitemsService,private categoryService: CategoryService) { }
  cartitems: CoupleCP[]=[];
  itemsCount: number=0;
  cartID:number=0;
  categories :Observable<Category[]>;
  ngOnInit(): void {
    this.categories = this.categoryService.getCategoryList();

    this.cartID=JSON.parse(localStorage.getItem("cart"));
    
    console.log('bitch get it together'+this.cartID);
    this.cartservice.CartitemsMessage$.subscribe(
      message =>{
        this.cartitems=message;
        this.itemsCount=this.cartitems.length;
        localStorage.setItem("itemscount",stringify(this.itemsCount));
        console.log('counting items'+this.itemsCount)
      }
    );
    if (this.cartitems!=null){
    this.itemsCount=this.cartitems.length;
  }
  this.itemsCount=JSON.parse(localStorage.getItem("itemscount")); 
  }
  
  hovering(){
    this.cartID=JSON.parse(localStorage.getItem("cart"));
    if (this.cartID==null){
     this.cartservice.createCart().subscribe(
       data=>{
         this.cartID=data;
         this.cartservice.sendID(data);
         console.log('heyy'+ this.cartID);
         localStorage.setItem("cart",stringify(this.cartID));
       } 
     );
     
    }
    

    /*else{
     this.cartservice.sendID(this.cartID);
     console.log('hey2222'+ this.cartID);
    }*/
    console.log('heyy3333'+ this.cartID);

  }

}
