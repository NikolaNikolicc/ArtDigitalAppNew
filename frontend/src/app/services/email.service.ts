import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EmailService {

  private server = "http://localhost:8080"

  constructor(private http: HttpClient) { }

  sendMail(orderID: number){
    return this.http.post<number>(`${this.server}/email/send`, orderID);
  }
  
}
