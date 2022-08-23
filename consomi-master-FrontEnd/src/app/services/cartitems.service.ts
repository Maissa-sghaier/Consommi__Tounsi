import { Product } from './../models/product';
import { Injectable } from '@angular/core';
import { CoupleCP } from 'src/app/models/CoupleCP';
import {HttpClient, HttpErrorResponse } from '@angular/common/http';
import {Observable, Subject, throwError} from 'rxjs';
import { codePromo } from '../models/codePromo';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CartitemsService {

  readonly createCartUrl="http://localhost:8082/api/v6/registration/createCart";
  readonly deleteCartUrl="http://localhost:8082/api/v6/registration/deleteCart";
  readonly addProdoctUrl="http://localhost:8082/api/v6/registration/addproduct";
  readonly updateProductUrl="http://localhost:8082/api/v6/registration/updateproduct";
  readonly deleteProductUrl="http://localhost:8082/api/v6/registration/delete_product_from_cart";
  readonly displayCartUrl="http://localhost:8082/api/v6/registration/showCart";
  readonly displayFeesUrl="http://localhost:8082/api/v6/registration/get_fees";
  readonly getCodeUrl="http://localhost:8082/api/v7/registration/getcodepromo";
  readonly getProductUrl= "http://localhost:8082/api/v7/registration/getproduct";
  readonly getDeliveryfeesUrl="http://localhost:8082/api/v8/registration/calculFees/0";

  constructor(private http:HttpClient) {}
  
  private _CartitemsMessageSource =new Subject<CoupleCP[]>();
  CartitemsMessage$ = this._CartitemsMessageSource.asObservable();
  sendMessage(message : CoupleCP[]){
    this._CartitemsMessageSource.next(message);
  }

  private _cartIdMessageSource = new Subject<number>();
  cartIdMessage$ =this._cartIdMessageSource.asObservable();
  sendID(IdMessage: number){
    this._cartIdMessageSource.next(IdMessage);
  }
  cartitems: CoupleCP[]=[];
  createCart():Observable<number>{
    return this.http.post<number>( `${this.createCartUrl}`,null);
  }
  
  deleteCart(cartId:any){
    return this.http.delete(`${this.deleteCartUrl}/${cartId}`);
  }

  addProdoct(cartId:any, productID: any, quantity:any):Observable<number>{
    return this.http.post<number>(`${this.addProdoctUrl}/${cartId}/${productID}/${quantity}`, null);
  } 

  updateProduct(itemID:any, productName: any, quantity:any):Observable<number>{
    return this.http.put<number>( `${this.updateProductUrl}/${itemID}/${productName}/${quantity}`, null);
  }

  deleteProduct( itemID:any){
    return this.http.delete(`${this.deleteProductUrl}/${itemID}`);
  }

  displayCart(cartId: any):Observable<CoupleCP[]>{
    return this.http.get<CoupleCP[]>(`${this.displayCartUrl}/${cartId}`);
  }

  displayFees(cartId:any, eventId:any, promoCode:any):Observable<number>{
    return this.http.get<number>(`${this.displayFeesUrl}/${cartId}/${eventId}/${promoCode}`);
  }

  CartitemCount(cartitems){
   return cartitems.length;
  }
  getcode(codename:String):Observable<codePromo>{
    return this.http.get<codePromo>(`${this.getCodeUrl}/${codename}`).pipe(
      catchError(this.errorHandle)
    );
  } 
  errorHandle(error: HttpErrorResponse){
    return throwError(error.message || 'Server error');
  }
  getProduct(prodID:number):Observable<Product>{
    return this.http.get<Product>(`${this.getProductUrl}/${prodID}`);
  }
  getDeliveryFees(distance:number, weight:number):Observable<number>{
    return this.http.get<number>(`${this.getDeliveryfeesUrl}/${distance}/${weight}`);
  }
}
