import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/User';

@Component({
  selector: 'app-home-component',
  templateUrl: './home-component.component.html',
  styleUrls: ['./home-component.component.scss']
})
export class HomeComponentComponent implements OnInit{
  username:string=''
  person:string=''
  isLoggedIn=false;
  ngOnInit(): void {
    const user:User=JSON.parse(localStorage.getItem('user') || '{}')
    if(user.type!==undefined){
    this.username=user.username;
    this.person=user.type;
    this.isLoggedIn=true;
    }
  }
}
