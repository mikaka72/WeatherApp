import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { WeatherListComponent } from './weather/weather-list.component'
import { WeatherService } from './weather/weather.service'
import { HttpClientModule } from '@angular/common/http'
 

@NgModule({
  declarations: [
    AppComponent,
    WeatherListComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [WeatherService],
  bootstrap: [AppComponent]
})
export class AppModule { }
