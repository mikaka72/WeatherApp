import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/throw';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/map';
import {AuthResponse} from './AuthResponse'

@Injectable()
export class LoginService {
  private AuthenticationService = 'http://localhost:8080/oauth/token';  

  private TOKEN_AUTH_USERNAME = 'weatherApiClient';
  private TOKEN_AUTH_PASSWORD = 'th3whe4ther4p1';
  
  private authResponse: string;

  constructor(private _http: HttpClient) {}


  login(username: string, password: string): string {

    console.log("Log in as user: " + username);
   
    const body = `username=${encodeURIComponent(username)}&password=${encodeURIComponent(password)}&grant_type=password`;
    
    this._http.post<AuthResponse>(this.AuthenticationService, body, {
            headers: new HttpHeaders().set('Authorization',  'Basic ' + btoa(this.TOKEN_AUTH_USERNAME + ':' + this.TOKEN_AUTH_PASSWORD))
            .set('Content-Type', 'application/x-www-form-urlencoded') , 
            withCredentials:true
           

          
      }   ).map(res => res.access_token)
          .subscribe(data =>{
              //this.authResponse = data;
              localStorage.setItem("id_token", data);
              console.log("token: " + data),
              error => localStorage.clear()
             
            }
          );
    // localStorage.setItem("id_token", this.authResponse);
     return this.authResponse;    
  }


  
 
}

