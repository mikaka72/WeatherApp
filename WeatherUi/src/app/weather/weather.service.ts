import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/throw';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/map';
import { AuthHttp } from 'angular2-jwt';
import { Weather } from './weather';


@Injectable()
export class WeatherService {
    private _serviceUrl = 'http://localhost:8080/api/v1/weather/';
  
    constructor(private _http: HttpClient) { }

    getWeather(searchValue : string): Observable<Weather[]> {
        return this._http.get<Weather[]>(this._serviceUrl+searchValue)
            .do(data => console.log('All: ' + JSON.stringify(data)))
            .catch(this.handleError);
    }

    getWeatherForNextDays(searchValue : string, days : number): Observable<Weather[]> {
        
        let authToken = localStorage.getItem('id_token');
        console.log("getWeatherForNextDays authToken " + authToken);
        const headers = new HttpHeaders().set('Authorization', `Bearer ${authToken}`);
        return this._http.get<Weather[]>(this._serviceUrl+"forecast/"+searchValue+"/"+days,
        {
            headers
        }).do(data => console.log('All: ' + JSON.stringify(data)))
         .catch(this.handleError);

        
    } 

    private handleError(err: HttpErrorResponse) {
        let errorMessage = '';
        if (err.error instanceof Error) {
            errorMessage = `An error occurred: ${err.error.message}`;
        } else {
            errorMessage = `Server returned code: ${err.status}, error message is: ${err.message}`;
        }
        console.error(errorMessage);
        return Observable.throw(errorMessage);
    }
}
