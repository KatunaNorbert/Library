import { BookService } from './../../service/book.service';
import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/service/user.service';
import { Router } from '@angular/router';
import { User } from 'src/app/model/user';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  user: User=new User();
  constructor(private userService: UserService,private bookService: BookService,private router: Router) { }

  addUser(){
    this.userService.addUser(this.user.cnp,this.user.email,this.user.password,this.user.name,this.user.adress,this.user.phone,this.user.type).subscribe(data =>{
      if(data=="error"){
        console.log("error");
      }else{
        this.router.navigate(['/adminHome']);
      }
    });
  }

  update(){
    console.log(this.user.name);
    this.userService.update(this.user.cnp,this.user.email,this.user.password,this.user.name,this.user.adress,this.user.phone,this.user.type).subscribe(data =>{
      if(data=="error"){
        console.log("error");
      }else{
        this.router.navigate(['/adminHome']);
      }
    });
  }

  delete(){
    this.userService.delete(this.user.email).subscribe(data =>{
      if(data=="error"){
        console.log("error");
      }else{
        this.router.navigate(['/adminHome']);
      }
    });
  }

  ngOnInit() {
    this.userService.logStatus().subscribe(data =>{
      if(data=="false"){
        this.router.navigate(['/login']);
      }else{
          this.userService.getSelected().subscribe((data:User)=>{
                  this.user.phone=data.phone,
                  this.user.cnp=data.cnp,
                  this.user.name=data.name,
                  this.user.email=data.email,
                  this.user.password=data.password,
                  this.user.adress=data.adress,
                  this.user.type=data.type
          });
      }
    })
  }

}
