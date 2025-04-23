import { Injectable } from '@angular/core';
// import { ActivatedRouteSnapshot, CanActivate, CanActivateFn, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
// import { Observable, map, tap } from 'rxjs';
// import { AuthServiceService } from './service/auth-service.service';

// export const authGuardGuard: CanActivateFn = (route, state,router:Router,) => {
//   return this.authService.userSub.pipe(map(user=>{
//     return user ? true :false;
//   }),tap(auth=>{
//     if(!auth){
//       this.router.navigate(['/login']);
//     }
//   }));
// };
import { ActivatedRouteSnapshot, CanActivate, CanActivateFn, Route, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthServiceService } from '../service/auth-service.service';
@Injectable()
export class AuthGuard implements CanActivate{
  constructor(private authService:AuthServiceService,private router:Router){}
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
    const token=JSON.stringify(localStorage.getItem('token'));
    console.log(token);
    if(token===null){
      alert("You are not authorized..please login to continue..");
      this.router.navigate(["/login"])
      return false;
    }
    return true;
  }

}
