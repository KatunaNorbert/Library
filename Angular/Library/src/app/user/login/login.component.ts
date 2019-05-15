import { UserService } from './../../service/user.service';
import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  LogError=false;

  constructor(private userService: UserService,private router: Router) { }

  login(form) {
    this.userService.logIn(form.value.email,form.value.password).subscribe(data =>{
      if(data=="user"){
        this.router.navigate(['/userHome']);
      }else if(data=="admin"){
        this.router.navigate(['/adminHome']);
      }else{
        this.LogError=true;
        this.router.navigate(['/login']);
      }
    })
  }

  ngOnInit() {
  }

}
