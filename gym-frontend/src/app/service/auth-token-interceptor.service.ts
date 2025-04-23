import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, exhaustAll, exhaustMap, take } from 'rxjs';
import { AuthServiceService } from './auth-service.service';

@Injectable()
export class AuthTokenInterceptorService implements HttpInterceptor {
  urlsToNotUse: Array<string>;

  constructor(
  ) {

    this.urlsToNotUse = [
      'api/gym/login'
      
    ];
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let token = localStorage.getItem('token');
    // console.log(this.isValidRequestForInterceptor(req.url));
    // if (this.isValidRequestForInterceptor(req.url)) {
      let modifiedRequest = req.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      })
      return next.handle(modifiedRequest);
    // }
    // return next.handle(req);
  }
  private isValidRequestForInterceptor(requestUrl: string): boolean {
    let positionIndicator: string = 'api/';
    let position = requestUrl.indexOf(positionIndicator);
    if (position > 0) {
      let destination: string = requestUrl.substr(position + positionIndicator.length);
      for (let address of this.urlsToNotUse) {
        if (new RegExp(address).test(destination)) {
          return false;
        }
      }
    }
    return true;
  }
}
