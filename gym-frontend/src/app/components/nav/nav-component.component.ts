import { AuthServiceService } from 'src/app/service/auth-service.service';
import { LoginComponentComponent } from '../login/login-component.component';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-nav-component',
  templateUrl: './nav-component.component.html',
  styleUrls: ['./nav-component.component.scss'],
  providers: [LoginComponentComponent]
})
export class NavComponentComponent implements OnInit {
  isLoggedIn = this.authService.isLoggedIn;
  constructor(private authService: AuthServiceService) {
  }
  ngOnInit() {
    this.authService.userSub.subscribe(user => {
      this.isLoggedIn = user ? true : false;
    });
    const token=localStorage.getItem('token');
    if(token!==null){
      this.isLoggedIn=true;
    }
  }
  onLogout() {
    this.authService.logout();
  }
}
