import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/throw';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/map';

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
        return this._http.get<Weather[]>(this._serviceUrl+searchValue+"/"+days)
            .do(data => console.log('All: ' + JSON.stringify(data)))
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
