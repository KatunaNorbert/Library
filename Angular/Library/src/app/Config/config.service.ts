import { Injectable } from '@angular/core';
 
@Injectable()
export class ConfigService {
     
    _apiURI : string;
 
    constructor() {
        this._apiURI = 'api/Library/';
     }
 
     getApiURI() {
         return this._apiURI;
     }    
}
