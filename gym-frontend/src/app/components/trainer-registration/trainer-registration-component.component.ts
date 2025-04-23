import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-trainer-registration-component',
  templateUrl: './trainer-registration-component.component.html',
  styleUrls: ['./trainer-registration-component.component.scss']
})
export class TrainerRegistrationComponentComponent {
  trainerRegistrationForm!: FormGroup;
  errorOccured: boolean = false;
  constructor(private httpClient: HttpClient,private router:Router) {
  }
  ngOnInit(): void {
    this.trainerRegistrationForm = new FormGroup({
      firstname: new FormControl('', Validators.required),
      lastname: new FormControl('', Validators.required),
      specialization: new FormControl('', Validators.required),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required, Validators.pattern("^[A-Za-z0-9@$&*^%!#]{8,15}$")])
    });
  }
  trainerRegistration() {
    this.httpClient.post('/api/gym/trainer/registration', this.trainerRegistrationForm.value).subscribe(response => {
      this.errorOccured = false;
      this.router.navigateByUrl("/login");
    }, error => this.errorOccured = true);
    this.trainerRegistrationForm.reset();
  }
  reset() {
    if (this.trainerRegistrationForm.valid) {
      this.trainerRegistrationForm.reset();
    }
  }
}
