import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private baseUrlgetList = 'http://localhost:8082/api/v7/registration/retrieve-all-categories';
  private baseUrldelete = 'http://localhost:8082/api/v7/registration/remove-category';
  private baseUrlpost='http://localhost:8082/api/v7/registration/add-category';

  constructor(private http: HttpClient) { }



  deleteCategory(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrldelete}/${id}`, { responseType: 'text' });
  }

  getCategoryList(): Observable<any> {
    return this.http.get(`${this.baseUrlgetList}`);
  }
  
  createCategory(category: Object): Observable<Object> {
    return this.http.post(`${this.baseUrlpost}`, category);
  }
}