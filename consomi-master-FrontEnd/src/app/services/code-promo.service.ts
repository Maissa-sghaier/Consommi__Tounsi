import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CodePromoService {

  
  private baseUrlgetList = 'http://localhost:8082/api/v7/registration/retrieve-all-codePromo';
  private baseUrldelete = 'http://localhost:8082/api/v7/registration/remove-codePromo';
  private baseUrlpost='http://localhost:8082/api/v7/registration/add-codePromo';
  private baseUrlactivate = 'http://localhost:8082/api/v7/registration/activate-codePromo';
  private baseUrldesactivate = 'http://localhost:8082/api/v7/registration/desactivate-codePromo';
  private baseUrlAutodesactivate = 'http://localhost:8082/api/v7/registration/desactivate-codePromo-unavailable';

  constructor(private http: HttpClient) { }

  deleteCodePromo(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrldelete}/${id}`, { responseType: 'text' });
  }

  getCodePromoList(): Observable<any> {
    return this.http.get(`${this.baseUrlgetList}`);
  }
  
  createCodePromo(codePromo: Object): Observable<Object> {
    return this.http.post(`${this.baseUrlpost}`, codePromo);
  }
  activateCodePromo(id: number): Observable<any> {
    return this.http.put(`${this.baseUrlactivate}/${id}`, { responseType: 'text' });
  }
  desactivateCodePromo(id: number): Observable<any> {
    return this.http.put(`${this.baseUrldesactivate}/${id}`, { responseType: 'text' });
  }
  autodesactivateCodePromo(): Observable<any> {
    return this.http.put(`${this.baseUrlAutodesactivate}`, { responseType: 'text' });
  }
}