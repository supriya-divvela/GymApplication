import { Injectable } from '@angular/core';
import { User } from 'src/app/model/User';
import { Subject } from 'rxjs';
import { Router } from '@angular/router';
import { Trainer } from '../model/Trainer';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {
  constructor(private router: Router, private httpClient: HttpClient) { }
  isLoggedIn: boolean = false;
  userSub = new Subject<User | null>();
  login() {
    this.isLoggedIn = true;
  }
  logout() {
    this.userSub.next(null);
    localStorage.removeItem('user');
    localStorage.removeItem('token');
    this.isLoggedIn = false;
    this.router.navigateByUrl("/logout");
  }
  autoLogin() {
    console.log("auto login");
    let userData: { username: string, expirationDate: string,type:string } = JSON.parse(localStorage.getItem('user') || '{}');
    const token = localStorage.getItem('token');
    console.log(token,this.isLoggedIn);
    if (token !== null) {
      this.isLoggedIn=true;
      let user = new User(userData.username, parseInt(userData.expirationDate),userData.type);
      this.userSub.next(user);
      
      // this.httpClient.get<Trainer>('/api/gym/trainer/' + userData.username).subscribe(
      //   {
      //     next: (trainer: Trainer) => {
      //       if (trainer.specialization !== undefined) {
      //         this.router.navigate(["/trainer-profile/" + user.username]);
      //       } else {
      //         this.router.navigate(["/trainee-profile/" + user.username]);
      //       }
      //     }
      //   }
      // );
    }
  }
  autoLogout() {
    let userData: { username: string, expirationDate: string } = JSON.parse(localStorage.getItem('user') || '{}');
    let date = new Date().getTime();
    let expirationDate = new Date(userData.expirationDate).getTime();
    if (expirationDate < date) {
      setTimeout(() => {
        // this.logout();
      }, expirationDate);
    }
  }
}
