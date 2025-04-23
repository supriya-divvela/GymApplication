import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AbstractControlOptions, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { PasswordValidator } from '../../model/PasswordValidator';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.scss']
})
export class ForgotPasswordComponent implements OnInit {
  forgotPasswordForm: FormGroup = {} as any;
  error = '';
  person: string = JSON.parse(localStorage.getItem('user') || '{}').type
  constructor(private route: ActivatedRoute, private httpClient: HttpClient) { }
  ngOnInit(): void {
    this.username = this.route.snapshot.params['username'];
    this.forgotPasswordForm = new FormGroup({
      username: new FormControl(this.username, [Validators.required, Validators.minLength(4)]),
      oldPassword: new FormControl('', Validators.required),
      newPassword: new FormControl('', [Validators.required, Validators.pattern("^[A-Za-z0-9@$&*^%!#]{8,15}$")]),
      confirmPassword: new FormControl('', Validators.required)
    }, { validators: PasswordValidator } as AbstractControlOptions);
  }
  username: string = '';
  forgotPassword() {
    console.log(this.forgotPasswordForm.value);
    this.httpClient.post('/api/gym/login/changepassword', this.forgotPasswordForm.value).subscribe({
      next: (response: any) => {
        alert("Updated password succesfully");
      }
    })
    this.forgotPasswordForm.reset({ username: this.username });
  }
  reset() {
    if (this.forgotPasswordForm.valid) {
      this.forgotPasswordForm.reset({ username: this.username });
    }
  }

}
