import { UserService } from 'src/app/service/user.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-menu',
  templateUrl: './admin-menu.component.html',
  styleUrls: ['./admin-menu.component.css']
})
export class AdminMenuComponent implements OnInit {

  constructor(private userService:UserService, private router: Router) { }

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

editUser(){
  this.router.navigate(["/usersList"]);
}

return(){
  this.router.navigate(["/returnExem"]);
}

  ngOnInit() {
  }

}
