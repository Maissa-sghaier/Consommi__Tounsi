import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs'
import { Client } from '../models/client';


@Injectable({
  providedIn: 'root'
})
export class EventService {
  private baseUrl1 = 'http://localhost:8082/springMVC/servlet/eventsLists';
  private baseUrl2 = 'http://localhost:8082/springMVC/servlet/addEvent';
  private baseUrl3 = 'http://localhost:8082/springMVC/servlet/updateEvent';
  private baseUrl4='http://localhost:8082/springMVC/servlet/participationsList'
  private baseUrl5='http://localhost:8082/springMVC/servlet/deleteEvent';
  private baseUrl6='http://localhost:8082/springMVC/servlet/findByIdEvent';
  private baseUrl7='http://localhost:8082/springMVC/servlet/affecterClientAEvent';
  private baseUrl8='http://localhost:8082/springMVC/servlet/viewAllSubject';
  private baseUrl9='http://localhost:8082/springMVC/servlet/commentairesList';
  private baseUrl20='http://localhost:8082/springMVC/servlet/?';
  private baseUrl10='http://localhost:8082/springMVC/servlet//passedEvents';
  constructor(private http: HttpClient) { }
  getChat() {
    return this.http.get(`${this.baseUrl20}`);
  }

  getListParticipants(idEvent: number) {
    return this.http.get <Client[]>(`${this.baseUrl4}/${idEvent}`);
  }
  getSubjectForumList():Observable<any>{
    return this.http.get(`${this.baseUrl8}`);
  }

  createEvent(event: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl2}`,event);
  }

  getEventUpdated(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl3}/${id}`, value);
  }

  deleteEvent(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl5}/${id}`, { responseType: 'text' });
  }

  getEventsList(): Observable<any> {
    return this.http.get(`${this.baseUrl1}`);
  }
  getPassedEvent():Observable<any> {
    return this.http.get(`${this.baseUrl10}`);
  }
  getEventDetail(idEvent:number):Observable<any>{
  return this.http.get(`${this.baseUrl6}/${idEvent}`);}

  getCommentaireSubject(id_subject:number):Observable<any>{
    return this.http.get(`${this.baseUrl9}/${id_subject}`);}
  

  getEventId(idEvent:number):Observable<any>{
    return this.http.get(`${this.baseUrl6}/${idEvent}`);}

    register(id_user:number,idEvent:number,costEvent:number){
      return this.http.get(`${this.baseUrl7}/${id_user}/${idEvent}/${costEvent}`);}
      
    }



