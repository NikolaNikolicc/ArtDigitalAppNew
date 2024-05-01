import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Order } from '../models/order';

@Injectable({
  providedIn: 'root'
})
export class SessionService {
  saveUser(o: Order) {
    return this.http.post<number>(`${this.server}/database/saveUserDetails`, o);
  }
  
  getSessionID() {
    return this.http.post<number>(`${this.server}/database/insert`, null);
  }

  private server = "http://localhost:8080"

  constructor(private http: HttpClient){

  }
}
