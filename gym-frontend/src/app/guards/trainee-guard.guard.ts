import { ActivatedRouteSnapshot, CanActivate, CanActivateFn, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { AuthServiceService } from '../service/auth-service.service';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { User } from '../model/User';
@Injectable()
export class traineeGuardGuard implements CanActivate{
  constructor(private authService:AuthServiceService,private router:Router){
  }
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
    const user:User=JSON.parse(localStorage.getItem('user') || '{}');
    if(user.type!=='trainee'){
      alert("You are not authorized..please login  as trainee to continue..");
      this.authService.autoLogout();
      this.router.navigate(["/login"])
      return false;
    }
    return true;
  }

}
