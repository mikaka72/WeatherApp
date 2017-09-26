import { Component, OnInit } from '@angular/core';
import { WeatherService} from './weather.service';

@Component({
    selector: 'weather-weathers',
    templateUrl: './weather-list.component.html',
    styleUrls: ['./weather-list.component.css']
})

export class WeatherListComponent{
 
    searchValue : string = "Oulu";
  
    weathers : any[] = [];

    errorMessage : string;

    constructor(private _weatherService: WeatherService) {
        
    }
    
    search():void{
        console.log("Search " + this.searchValue);

        this._weatherService.getWeather(this.searchValue)
        .subscribe(weathers => {
            this.weathers = weathers;
        }, error => this.errorMessage = <any>error);
    }

    nextTenDays():void{
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

}