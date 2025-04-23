import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { AuthServiceService } from './service/auth-service.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  encapsulation: ViewEncapsulation.Emulated
})
export class AppComponent implements OnInit{
  constructor(private authService:AuthServiceService){}
  ngOnInit() {
   this.authService.autoLogin();
  } 
  title='gym-app';
}