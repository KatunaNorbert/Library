import { FilterPipe } from './user/home/my-filter';
import { ExemplaryService } from './service/exemplary.service';
import { ConfigService } from './Config/config.service';
import { HttpModule } from '@angular/http';
import { HttpClientModule } from '@angular/common/http';
import { BookService } from './service/book.service';
import { UserService } from 'src/app/service/user.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { LoginComponent } from './user/login/login.component';
import { RegisterComponent } from './user/register/register.component';
import { HomeComponent } from './user/home/home.component';
import { UserBookComponent } from './user/user-book/user-book.component';
import { RouterModule } from '@angular/router';
import { AdminHomeComponent } from './admin/admin-home/admin-home.component';
import { AdminBookComponent } from './admin/admin-book/admin-book.component';
import { MenuComponent } from './menu/menu.component';
import { AdminMenuComponent } from './admin-menu/admin-menu.component';
import { DatePipe } from '@angular/common';
import { ReturnBookComponent } from './admin/return-book/return-book.component';
import { UserListComponent } from './admin/user-list/user-list.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    UserBookComponent,
    AdminHomeComponent,
    AdminBookComponent,
    MenuComponent,
    AdminMenuComponent,
    ReturnBookComponent,
    UserListComponent,
    FilterPipe
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    HttpModule,
    RouterModule.forRoot([
      {
        path: 'login',
        component: LoginComponent
      },
      {
        path: 'menu',
        component: MenuComponent
      },
      {
        path: 'register',
        component: RegisterComponent
      },
      {
        path: 'userHome',
        component: HomeComponent
      },
      {
        path: 'userBook',
        component: UserBookComponent
      },
      {
        path: 'adminHome',
        component: AdminHomeComponent
      },
      {
        path: 'adminBook',
        component: AdminBookComponent
      },
      {
        path: 'returnExem',
        component: ReturnBookComponent
      },
      {
        path: 'usersList',
        component: UserListComponent
      },
      {
        path: '**',
        component: HomeComponent
      },
      {
        path: '',
        component: LoginComponent
      }
    ])
  ],
  providers: [UserService,BookService,ConfigService,ExemplaryService,DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
