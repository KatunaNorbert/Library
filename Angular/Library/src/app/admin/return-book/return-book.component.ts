import { BookService } from './../../service/book.service';
import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/service/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-return-book',
  templateUrl: './return-book.component.html',
  styleUrls: ['./return-book.component.css']
})
export class ReturnBookComponent implements OnInit {

  books=[];

  constructor(private bookService: BookService,private userService: UserService,private router: Router) { }

  
  searchByEmail(form){
    this.bookService.getBorExempByEmail(form.value.email).subscribe(data => this.books=data);
  }

return(id){
  this.bookService.returnBook(id).subscribe(data =>{
    if(data=="done"){
      this.router.navigate(['/adminHome'])
    }
  })
}

  ngOnInit() {
    this.userService.logStatus().subscribe(data =>{
      if(data=="false"){
        this.router.navigate(['/login']);
      }else{
        this.bookService.getBorExemp().subscribe(data=>this.books=data);
      }
    })
  }

}
