import { HttpClient, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  public dataForm:  FormGroup; 
  host :string = "http://localhost:8082";


  //host :string = "http://localhost:8081";
  //headers = new HttpHeaders().set('access-control-allow-origin','http://localhost:8081'); 
  private baseUrlgetList = 'http://localhost:8082/api/v7/registration/retrieve-all-products';
  private baseUrlgetListByCat = 'http://localhost:8082/api/v7/registration/retrieve-all-products-by-cat';
  private baseUrlgetProductImg = 'http://localhost:8082/api/v7/registration/Imgarticles';
  private baseUrlgetListFiltred ='http://localhost:8082/api/v7/registration/filtre';
  private baseUrlrate='http://localhost:8082/api/v7/registration/rate-a-product';
  private baseUrlpost='http://localhost:8082/api/v7/registration/add-product';
  private baseUrldelete='http://localhost:8082/api/v7/registration/remove-product';
  private baseUrlupdate='http://localhost:8082/api/v7/registration/update-product';
  private baseUrluploadImg2='http://localhost:8082/api/v7/registration/uploadImageToProduct';

  private baseUrluploadImg='http://localhost:8082/api/v7/registration/uploadImageToProduct3';
  private baseUrldesactivate='http://localhost:8082/api/v7/registration/desactivate-promotion';

  constructor(private http: HttpClient) { }

  getProductList(): Observable<any> {
    return this.http.get(`${this.baseUrlgetList}`);
  }
  getProductListByCategory(id: number): Observable<any> {
    return this.http.get(`${this.baseUrlgetListByCat}/${id}`);
  }
  getProductImg(id: number): Observable<any> {
    return this.http.get(`${this.baseUrlgetProductImg}/${id}`);
  }
  getProductListFiltred(idC: number,idP: number,r:number,promo:number,minS:number,maxS:number):Observable<any>{
    return this.http.get(`${this.baseUrlgetListFiltred}/${idC}/${idP}/${r}/${promo}/${minS}/${maxS}`);

  }
  rateAProduct(id: number, i: number): Observable<any> {
    return this.http.put(`${this.baseUrlrate}/${id}/${i}`,{ responseType: 'text' });
  }
  
  createProduct(product: Object): Observable<Object> {
    return this.http.post(`${this.baseUrlpost}`, product);
  }

  
  updateProduct(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrlupdate}/${id}`, value);
  }
  deleteProduct(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrldelete}/${id}`, { responseType: 'text' });
  }
  desactivatePromotion(id: number): Observable<any> {
    return this.http.put(`${this.baseUrldesactivate}/${id}`,  { responseType: 'text' });
  }
  

  
  uploadImg2(id: number, s: string): Observable<any> {
    return this.http.put(`${this.baseUrluploadImg2}/${id}/${s}`, { responseType: 'text' });
  }
 

  uploadFile(file: File): Observable<any> {
		const formdata: FormData = new FormData();
    //formdata.append('codeB',s);

		formdata.append('file', file);
		const req = new HttpRequest('PUT', 'http://localhost:8082/api/v7/registration/uploadImageToProduct3/', formdata, {
			  reportProgress: true,
			  responseType: 'text'
		});

		return this.http.request(req);
 // return this.http.put<String>(`${this.baseUrluploadImg}/${formdata}`,  { responseType: 'text' });

   }


  // createData(formData: FormData): Observable<any> {
  //  return this.http.post(`${this.baseUrlpost}`, formData);
 // }
  
  //updatedata(id: number, value: any): Observable<Object> {
  //  return this.http.put(`${this.baseUrl}/${id}`, value);
  //}

}
