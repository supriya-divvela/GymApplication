import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
export interface INavLink {
  id: number;
  pathLink: string;
  label: string;
}
@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {

  registrationForm: FormGroup = {} as any;
  person: boolean = true;
  constructor() { }
  ngOnInit(): void {
    this.registrationForm = new FormGroup({
      person: new FormControl(true, Validators.required),
    })
  }
  onSelected(value: string) {
    if (value === 'Trainer') {
      this.person = false;
    } else {
      this.person = true;
    }
  }
}
