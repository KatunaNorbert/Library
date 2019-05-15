import { ExemplaryService } from './../../service/exemplary.service';
import { BookService } from './../../service/book.service';
import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/service/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public exemplaries=[];
  searchText: String;

  constructor(private userService: UserService,private exemplaryService: ExemplaryService,private router: Router) { }

  giveBookDetails(exemplaryId){
      this.exemplaryService.getExemplaryDetails(exemplaryId).subscribe(data=>{
        if(data=="done"){
          this.router.navigate(['/userBook'])
        }
      })
  }

  searchByName(form){
    this.exemplaries=this.exemplaries.find(x => x.book.name === this.searchText);
  }

  ngOnInit() {
    this.userService.logStatus().subscribe(data =>{
      if(data=="false"){
        this.router.navigate(['/login']);
      }else{
        this.exemplaryService.getAvExemp().subscribe(data=>this.exemplaries=data);
      }
    })
  }

}
