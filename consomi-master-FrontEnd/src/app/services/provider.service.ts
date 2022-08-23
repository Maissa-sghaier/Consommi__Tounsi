import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Provider } from '../models/provider';

@Injectable({
  providedIn: 'root'
})
export class ProviderService {
  private baseUrlgetList = 'http://localhost:8082/api/v7/registration/retrieve-all-providers';
  private baseUrldelete = 'http://localhost:8082/api/v7/registration/remove-provider';
  private baseUrlpost='http://localhost:8082/api/v7/registration/add-provider';
private baseUrlget='http://localhost:8082/api/v7/registration/retrieve-provider';
private baseUrlupdate='http://localhost:8082/api/v7/registration/update-provider';
  constructor(private http: HttpClient) { }

  deleteProvider(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrldelete}/${id}`, { responseType: 'text' });
  }

  getProviderList(): Observable<any> {
    return this.http.get(`${this.baseUrlgetList}`);
  }
  
  createProvider(provider: Object): Observable<Object> {
    return this.http.post(`${this.baseUrlpost}`, provider);
  }

  getProviderByID(id : number): Observable<Provider> {
    return this.http.get<Provider>(`${this.baseUrlget}/${id}`);
  }
  updateProvider(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrlupdate}/${id}`, value);
  }

}
