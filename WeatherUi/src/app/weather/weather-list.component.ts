import { Component, OnInit } from '@angular/core';
import { WeatherService} from './weather.service';
import { LoginService } from '../login/login.service';
import { tokenNotExpired } from 'angular2-jwt';

@Component({
    selector: 'weather-weathers',
    templateUrl: './weather-list.component.html',
    styleUrls: ['./weather-list.component.css'],
    providers: [WeatherService, LoginService ]
})

export class WeatherListComponent{
 
    searchValue : string = "Oulu";
  
    weathers : any[] = [];

    errorMessage : string;

    username : string = "user";

    password : string = "jwtpass";

    loggedIn : boolean = tokenNotExpired(localStorage.getItem('id_token'));

    constructor(private _weatherService: WeatherService, private _loginService: LoginService) {
        
    }
    
    search():void{
        console.log("Search " + this.searchValue);

        this._weatherService.getWeather(this.searchValue)
        .subscribe(weathers => {
            this.weathers = weathers;
        }, error => this.errorMessage = <any>error);
    }

    nextTenDays():void{
      //  this.doLogin();
        console.log("Search next 10 days for " + this.searchValue);
        this._weatherService.getWeatherForNextDays(this.searchValue, 10)
        .subscribe(weathers => {
            this.weathers = weathers;
        }, error => this.errorMessage = <any>error);
        
    }
    
    ngOnInit(): void {
        this._weatherService.getWeather(this.searchValue)
                .subscribe(weathers => {
                    this.weathers = weathers;
                   
                },
        error => this.errorMessage = <any>error);
    }

    doLogin():void{
    
        console.log("login");
        this._loginService.login(this.username, this.password);
        console.log("Token from localStorage: "+ localStorage.getItem('id_token'));
          
       

    }

    public logout() {
        localStorage.removeItem('id_token');
     }
   

}