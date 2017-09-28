# WeatherUI

Application has main view with text field where to enter city. When Get Current Weather is pressed, app gets current weather info from Related Rest API

https://github.com/mikaka72/WeatherApp/tree/master/WeatherApi

When 10 days weather In xxx button is pressed, application gets JWT token from WeatherAPi and then tries to fetch detailed "next 10 days" forecast from API

Currently however the Token request is ok, but the actual request with token to get the weather info is failing. 


This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 1.2.4.

## Development server

First run npm install

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. 

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `-prod` flag for a production build.


To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI README](https://github.com/angular/angular-cli/blob/master/README.md).
