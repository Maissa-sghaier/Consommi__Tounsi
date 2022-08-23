import { Injectable } from '@angular/core';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Client } from '../models/client';
import { ClientService } from '../services/client.service';
@Injectable({
  providedIn: 'root'
})


export class JwtClientService {
 

  constructor(private httpClient: HttpClient, private clientService: ClientService) { }


  public generateToken(request) {
    return this.httpClient.post<Client>("http://localhost:8082/api/v3/registration/authenticate", request, {  responseType: 'text' as 'json' });
  }


  public welcome(token, s:string) {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
   
    return this.httpClient.get<string>("http://localhost:8082/api/v3/registration/find-by-username/"+ s, {headers, responseType: 'text' as 'json' });
  }
}


