import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from "rxjs/index";
import { Client } from '../models/client';
import { ApiResponse } from '../models/api.response';

@Injectable({
  providedIn: 'root'
})
export class ClientService {
 
  private baseUrl = 'http://localhost:8082'
  
  constructor(private http: HttpClient) { }
 // private baseUrl: string = environment.baseUrl;

 getClientByUsername(username: string):Observable<any> {
  return this.http.get(this.baseUrl+'/find-by-username/'+username);
}

  getClient() : Observable<Client[]> {
    return this.http.get<Client[]>(this.baseUrl+'/api/v1/registration/retrieve-all-clients');
  }

  getClientById(id_user: number): Observable<any> {
    return this.http.get('http://localhost:8082/api/v3/registration/retrieve-client/'+id_user);
  }

  createClient(client: Object): Observable<Object> {
    return this.http.post<ApiResponse>('http://localhost:8082/api/v1/registration/',client);
    
  }

  updateClient(id: number, value: Client): Observable<Object> {
    return this.http.put<Client>('http://localhost:8082/api/v3/registration/modify-Client/'+ id, value);
  }

  deleteClient(id: number): Observable<ApiResponse> {
    return this.http.delete<ApiResponse>(this.baseUrl + id);
  }
}
