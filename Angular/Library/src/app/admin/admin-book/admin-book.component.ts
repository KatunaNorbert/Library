import { ExemplaryService } from './../../service/exemplary.service';
import { element } from 'protractor';
import { Book } from './../../model/book';
import { BookService } from './../../service/book.service';
import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/service/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-book',
  templateUrl: './admin-book.component.html',
  styleUrls: ['./admin-book.component.css']
})
export class AdminBookComponent implements OnInit {

  book: Book=new Book();
  uEmail: String;

  constructor(private bookService: BookService,private userService: UserService,private router: Router,private exempService: ExemplaryService) { }

  add(form){
      this.bookService.addBook(this.book.name,this.book.author,this.book.type).subscribe(data =>{
        if(data="done"){
          this.router.navigate(['/adminHome']);
        }
      })
  }

  update(form){
    this.bookService.updateBook(this.book.name,this.book.author,this.book.type).subscribe(data =>{
      if(data="done"){
        this.router.navigate(['/adminHome']);
      }
    })
}

delete(){
  this.bookService.deleteBook(this.book.id).subscribe(data =>{
    if(data="done"){
      this.router.navigate(['/adminHome']);
    }
  })
}

  addExemplary(){
      this.exempService.addExemplary(this.book.id).subscribe(data =>{
          if(data="done"){
            this.router.navigate(['/adminHome']);
          }
      })
  }

  ngOnInit() {
    this.userService.logStatus().subscribe(data =>{
      if(data=="false"){
        this.router.navigate(['/login']);
      }else{
        this.uEmail=data;
        this.bookService.getSelectedBook().subscribe((data:Book)=>{
          this.book.id=data.id;
          this.book.name=data.name;
          this.book.author=data.author;
          this.book.type=data.type;
      });
      }
    })
  }

}
