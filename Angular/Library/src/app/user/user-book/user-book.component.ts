import { ExemplaryService } from './../../service/exemplary.service';
import { Router } from '@angular/router';
import { UserService } from './../../service/user.service';
import { Book } from './../../model/book';
import { BookService } from './../../service/book.service';
import { Component, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { Exemplary } from 'src/app/model/exemplary';

@Component({
  selector: 'app-user-book',
  templateUrl: './user-book.component.html',
  styleUrls: ['./user-book.component.css']
})
export class UserBookComponent implements OnInit {

  date : Date = new Date();
  e: Exemplary=new Exemplary();
  uEmail: String;

  constructor(private bookService: BookService,private exemplaryService:ExemplaryService,private userService: UserService,private router: Router,private datePipe: DatePipe) { }


  borrow(){
      this.date.setDate(this.date.getDate());
      var d=this.datePipe.transform(this.date, 'yyyy-MM-dd');
      this.bookService.borrowBook(this.e.id,this.uEmail,d).subscribe(data =>{
        if(data=="done"){
          this.router.navigate(['/userHome']);
        }
      })
  }

  ngOnInit() {

    this.userService.logStatus().subscribe(data =>{
      if(data=="false"){
        this.router.navigate(['/login']);
      }else{
        this.uEmail=data;
        this.exemplaryService.getSelectedExemplary().subscribe((data:Exemplary)=>{
          this.e.id=data.id;
          this.e.state=data.state;
          this.e.book=data.book;
      });
      }
    })
  }

}
