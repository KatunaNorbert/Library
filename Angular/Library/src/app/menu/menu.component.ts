import { BookService } from './../service/book.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {


  logout(){
      this.userService.logOut().subscribe(data =>{
        if(data=="done"){
          this.router.navigate(['/login']);
        }
      })
  }

  goHome(){
    this.userService.getUserType().subscribe(data =>{
      if(data=="admin"){
        this.router.navigate(['/adminHome']);
      }else if(data=="user"){
        this.router.navigate(['/userHome']);
      }else{
        this.router.navigate(['/login']);
      }
    })
}
  constructor(private userService: UserService,private router: Router) { }

  ngOnInit() {
  }

}
