import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  private server = "http://localhost:8080"

  constructor(private http: HttpClient){

  }
  
  getSessionID() {
    return this.http.post<number>(`${this.server}/database/insert`, null);
  }

}
