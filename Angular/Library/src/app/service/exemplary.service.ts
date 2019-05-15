import { Exemplary } from './../model/exemplary';
import { BorrowedBook } from './../model/borrowedbook';
import { ConfigService } from './../Config/config.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable()
export class ExemplaryService {

  constructor(private http: HttpClient, private configService : ConfigService) { 
    this.baseUrl = configService._apiURI;
    this.exemplaryUrl = this.baseUrl + "exemplary/";
  }

  baseUrl;
  exemplaryUrl;

  public addExemplary(id: number){
    return this.http.get(this.exemplaryUrl + "add/" + id,{responseType: 'text'});
  }

  public getAvExemp(): Observable<Exemplary[]>{
    return this.http.get<Exemplary[]>(this.exemplaryUrl + "getAvailableExemp");
  }

  public getSelectedExemplary(){
    return this.http.get<Exemplary>(this.exemplaryUrl + "getSelectedExemplary");
  }

  public getExemplaryDetails(id: number){
    return this.http.get(this.exemplaryUrl + "saveExemplary/"+id,{responseType: 'text'});
  }

  public getExemplariesByName(name: String): Observable<Exemplary[]>{
    return this.http.get<Exemplary[]>(this.exemplaryUrl + "getByName/" + name);
  }
}
