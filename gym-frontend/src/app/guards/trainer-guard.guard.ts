import { ActivatedRouteSnapshot, CanActivate, CanActivateFn, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { AuthServiceService } from '../service/auth-service.service';
import { Observable } from 'rxjs';
import { User } from '../model/User';
import { Injectable } from '@angular/core';
@Injectable()
export class trainerGuardGuard implements CanActivate {
  constructor(private authService: AuthServiceService, private router: Router) { }
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
    const user: User = JSON.parse(localStorage.getItem('user') || '{}');
    console.log(user);
    if (user.type !== 'trainer') {
      alert("You are not authorized..please login  as trainer to continue..");
      this.authService.autoLogout();
      this.router.navigate(["/login"])
      return false;
    }
    return true;
  }

}
