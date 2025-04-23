import { LoginService } from './../../service/login.service';
import { Component, OnInit } from '@angular/core';
import { AbstractControlOptions, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CaptchaValidator } from '../../model/PasswordValidator';
import { Subject } from 'rxjs';
import { AuthServiceService } from 'src/app/service/auth-service.service';
import { Token } from 'src/app/model/Token';
import { Credentials } from 'src/app/model/CredentialsDetails';
import { User } from 'src/app/model/User';
import { Role } from './../../model/Role'

@Component({
  selector: 'app-login-component',
  templateUrl: './login-component.component.html',
  styleUrls: ['./login-component.component.scss']
})
export class LoginComponentComponent implements OnInit {
  loginForm: FormGroup = {} as any;
  isLoading: boolean = false;
  error: string = '';
  errorOccured: boolean = false;
  user: User = {} as any;
  role: string = '';
  token: string = '';
  constructor(
    private authService: AuthServiceService,
    private router: Router,
    private loginService: LoginService,
  ) {
  }
  ngOnInit(): void {
    this.loginForm = new FormGroup({
      username: new FormControl('', Validators.required),
      password: new FormControl('', [Validators.required]),//,Validators.pattern("^[A-Za-z0-9@$&*^%!#]{8,15}$")
      captcha: new FormControl({ value: '', disabled: true }, Validators.required),
      reentercaptcha: new FormControl('', Validators.required),
      rememberme: new FormControl(true, Validators.required),
      person: new FormControl('', Validators.required)
    }, { validators: CaptchaValidator } as AbstractControlOptions);
    this.generate();
    if (this.authService.isLoggedIn) {
      const response = JSON.parse(localStorage.getItem('user') || '{}');
      this.user = response;
      if (response.type === 'trainee') {
        this.router.navigate(["/trainee-profile/" + this.user.username]);
      } else {
        this.router.navigate(["/trainer-profile/" + this.user.username]);
      }
    }
  }

  onLogin() {
    if (!this.loginForm.valid) {
      return;
    }
    this.isLoading = true;
    const credentail: Credentials = { username: this.loginForm.controls['username'].value, password: this.loginForm.controls['password'].value };
    this.loginService.getToken(credentail).subscribe({
      next: (response: Token) => {
        this.user = new User(this.loginForm.controls['username'].value, new Date().getTime() + 20000 * 20, this.loginForm.controls['person'].value);
        localStorage.setItem('token', response.token);
        this.getRole();
        this.token = response.token;
      }, error: (error: Error) => {
        this.getError("username doesnot exist.. or password is incorrect");
      }
    });
  }

  generate = () => {
    const randomchar = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    const uniquechar = Array.from({ length: 4 }, () => randomchar[Math.floor(Math.random() * randomchar.length)]).join('');
    this.loginForm.controls['captcha'].setValue(uniquechar);
  }

  traineeOrTrainerProfile(token: string) {
    this.getProfile(token, this.user);
    if (this.loginForm.controls['person'].value === 'trainer' && this.role === "trainer") {
      console.log("hell");
      this.router.navigate(["/trainer-profile/" + this.user.username]);
    } else if (this.loginForm.controls['person'].value === 'trainee' && this.role === "trainee") {
      this.router.navigate(["/trainee-profile/" + this.user.username]);
    }
    else {
      this.getError(this.loginForm.controls['person'].value + " does not exist..");
    }
    this.authService.autoLogout();
  }

  getProfile(token: string, user: User) {
    this.authService.userSub.next(user);
    this.error = '';
    this.errorOccured = false;
    this.isLoading = true;
    localStorage.setItem('user', JSON.stringify(user));
    this.authService.isLoggedIn = true;
    this.authService.autoLogout();
  }
  getError(message: string) {
    this.isLoading = false;
    this.error = message;
    this.errorOccured = true;
    this.authService.isLoggedIn = false;
    this.router.navigateByUrl("/login");
    this.loginForm.reset({ rememberme: true });
    this.generate();
  }
  getRole() {
    console.log(this.role);
    this.loginService.getRole(this.user.username).subscribe({
      next: (response: Role) => {
        this.role = response.role;
        this.traineeOrTrainerProfile(this.token);
      }
    });
  }
}