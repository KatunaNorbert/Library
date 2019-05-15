import { User } from '../model/user';
import { ConfigService } from '../Config/config.service';
import { Injectable, Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class UserService {

  constructor(private http: HttpClient, private configService : ConfigService) { 
    this.baseUrl = configService._apiURI;
    this.userUrl = this.baseUrl + "user/";
  }

  baseUrl: string = "";
  userUrl;

  public getUserType(){
    return this.http.get(this.userUrl + "getType", {responseType: 'text'});
  }

  public getUsers(): Observable<User[]>{
    return this.http.get<User[]>(this.userUrl + "getAll");
  }

  public getUsersByEmail(email: string): Observable<User[]>{
    return this.http.get<User[]>(this.userUrl + "getByEmail/"+email);
  }

  public getSelected(){
    return this.http.get<User>(this.userUrl + "getSelected");
  }

  public logStatus(){
    return this.http.get(this.userUrl + "logStatus", {responseType: 'text'});
  }

  public logIn(email: string, password: string){
    return this.http.get(this.userUrl + "logIn/" + email + "/" + password, {responseType: 'text'});
  }

  public logOut(){
    return this.http.get(this.userUrl + "logout", {responseType: 'text'});
  }

  public addUser(cnp: number, email: string, password: string, name: string, adress: string, phone: number, type: string){
    return this.http.get(this.userUrl + "add/" + cnp + "/" + email + "/" + password + "/" + name + "/" + adress + "/" + phone + "/" + type, {responseType: 'text'});
  }

  public delete(email: string){
    return this.http.get(this.userUrl + "delete/" + email, {responseType: 'text'});
  }

  public update(cnp: number, email: string, password: string, name: string, adress: string, phone: number, type: string){
    return this.http.get(this.userUrl + "update/" + cnp + "/" + email + "/" + password + "/" + name + "/" + adress + "/" + phone + "/" + type, {responseType: 'text'});
  }
}
