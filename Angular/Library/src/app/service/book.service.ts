import { BorrowedBook } from './../model/borrowedbook';
import { Book } from './../model/book';
import { ConfigService } from './../Config/config.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Exemplary } from '../model/exemplary';

@Injectable()
export class BookService {

  constructor(private http: HttpClient, private configService : ConfigService) { 
    this.baseUrl = configService._apiURI;
    this.BookUrl = this.baseUrl + "book/";
  }

  baseUrl;
  BookUrl;

  public getBorExemp(): Observable<BorrowedBook[]>{
    return this.http.get<BorrowedBook[]>(this.BookUrl + "getBorrowedBook");
  }

  public getBorExempByEmail(email: string): Observable<BorrowedBook[]>{
    return this.http.get<BorrowedBook[]>(this.BookUrl + "getBorrowedBookByEmail/" + email);
  }

  public getBooks(): Observable<Book[]>{
    return this.http.get<Book[]>(this.BookUrl + "getAll");
  }

  public getBooksByName(name: String): Observable<Book[]>{
    return this.http.get<Book[]>(this.BookUrl + "getByName/" + name);
  }

  public getSelectedBook(){
    return this.http.get<Book>(this.BookUrl + "getSelected");
  }

  public getBookDetails(id: number){
    return this.http.get(this.BookUrl + "save/"+id,{responseType: 'text'});
  }

  public getExemplaryDetails(id: number){
    return this.http.get(this.BookUrl + "saveExemplary/"+id,{responseType: 'text'});
  }

  public borrowBook(id: number, email: String, date: String){
    return this.http.get(this.BookUrl+ "borrow/"+id+"/"+email+"/"+date,{responseType: 'text'});
  }

  public returnBook(id: number){
    return this.http.get(this.BookUrl+ "return/"+id,{responseType: 'text'});
  }

  public addBook(name: String, author: String, type: String){
    return this.http.get(this.BookUrl+ "add/"+name+"/"+author+"/"+type,{responseType: 'text'});
  }

  public updateBook(name: String, author: String, type: String){
    return this.http.get(this.BookUrl+ "update/"+name+"/"+author+"/"+type,{responseType: 'text'});
  }

  public deleteBook(id: number){
    return this.http.get(this.BookUrl+ "delete/"+id,{responseType: 'text'});
  }
}
