import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { LoginComponentComponent } from '../login/login-component.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-trainee-registration-component',
  templateUrl: './trainee-registration-component.component.html',
  styleUrls: ['./trainee-registration-component.component.scss'],providers:[LoginComponentComponent]
})
export class TraineeRegistrationComponentComponent implements OnInit {
  traineeRegistrationForm!: FormGroup;
  errorOccured: boolean = false;
  constructor(private httpClient: HttpClient,private router:Router) {
  }
  ngOnInit(): void {
    this.traineeRegistrationForm = new FormGroup({
      firstname: new FormControl('', Validators.required),
      lastname: new FormControl('', Validators.required),
      dateOfBirth: new FormControl('', Validators.required),
      address: new FormControl('', Validators.required),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required, Validators.pattern("^[A-Za-z0-9@$&*^%!#]{8,15}$")])
    });
  }
  traineeRegistration() {
    this.httpClient.post('/api/gym/trainee/registration', this.traineeRegistrationForm.value).subscribe(response => {
      this.errorOccured = false;
      this.router.navigateByUrl("/login");
    }, error => this.errorOccured = true);
    this.traineeRegistrationForm.reset();
    
  }
  reset() {
    if (this.traineeRegistrationForm.valid) {
      this.traineeRegistrationForm.reset();
    }
  }
}
