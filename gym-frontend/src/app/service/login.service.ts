import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Role } from '../model/Role';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  constructor(private httpClient: HttpClient) { }
  getToken(credential: any): any {
    return this.httpClient.post('/api/auth/token', credential, { headers: { skip: "true" } });
  }
  getRole(username: string) {
    return this.httpClient.get<Role>("/api/gym/role/" + username);
  }
}
