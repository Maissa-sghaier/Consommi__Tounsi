import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from "rxjs/index";
import { ApiResponse } from '../models/api.response';
import { deliveryman } from '../models/deliveryman';

@Injectable({
  providedIn: 'root'
})
export class DeliverymanService {

  private baseUrl = 'http://localhost:8082/api/v4/registration/modify-DeliveryMan'
  private baseUrl1 = 'http://localhost:8082/api'
  constructor(private http: HttpClient) { }
 // private baseUrl: string = environment.baseUrl;

  getDeliveryman() : Observable<deliveryman[]> {
    return this.http.get<deliveryman[]>(this.baseUrl1+'/v4/registration/retrieve-all-DeliveryMans');
  }

  getDeliverymanById(id_user: number): Observable<deliveryman> {
    return this.http.get<deliveryman>(this.baseUrl1+'/v4/registration/retrieve-DeliveryMan/'+id_user);
  }

  createDeliveryman(deliveryman: Object): Observable<Object> {
    return this.http.post<ApiResponse>(this.baseUrl1+'/v4/registration/add-deliveryMan/',deliveryman);
  }

  updateDeliveryman(id_user:number,value:any): Observable<Object> {
    return this.http.put('http://localhost:8082/api/v4/registration/modify-DeliveryMan/' +id_user,value);
  }

  deleteDeliveryman(id_user: number): Observable<ApiResponse> {
    return this.http.delete<ApiResponse>(this.baseUrl1+'/v4/registration/remove-DeliveryMan/'+ id_user);
  }}