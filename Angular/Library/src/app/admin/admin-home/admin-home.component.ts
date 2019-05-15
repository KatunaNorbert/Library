import { BookService } from './../../service/book.service';
import { Book } from './../../model/book';
import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/service/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.css']
})
export class AdminHomeComponent implements OnInit {

  books=[];

  constructor(private userService: UserService,private bookService: BookService,private router: Router) { }

  giveBookDetails(bookId){
    this.bookService.getBookDetails(bookId).subscribe(data=>{
      if(data=="done"){
        this.router.navigate(['/adminBook'])
      }
    })
}

searchByName(form){
  this.bookService.getBooksByName(form.value.name).subscribe(data =>this.books=data);
}

  ngOnInit() {
    this.userService.logStatus().subscribe(data =>{
      if(data=="false"){
        this.router.navigate(['/login']);
      }else{
        this.bookService.getBooks().subscribe(data=>this.books=data);
      }
    })
  }
}


