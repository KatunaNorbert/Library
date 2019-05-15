import { BookService } from './../../service/book.service';
import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/service/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  users=[];

  constructor(private bookService: BookService,private userService: UserService,private router: Router) { }

  searchByEmail(form){
    this.userService.getUsersByEmail(form.value.email).subscribe(data=>this.users=data);
  }

  saveUser(email,password){
      this.userService.logIn(email,password).subscribe(data=>{
        if(data=="user" || data=="admin"){
          this.router.navigate(['/register'])
        }
      });
  }

  ngOnInit() {
    this.userService.logStatus().subscribe(data =>{
      if(data=="false"){
        this.router.navigate(['/login']);
      }else{
        this.userService.getUsers().subscribe(data=>this.users=data);
      }
    })
  }

}
