import { __decorate } from "tslib";
import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
let TraineeRegistrationComponentComponent = class TraineeRegistrationComponentComponent {
    constructor(httpClient) {
        this.httpClient = httpClient;
    }
    ngOnInit() {
        this.postForm = new FormGroup({
            firstname: new FormControl(null, Validators.required),
            lastname: new FormControl(null, Validators.required),
            dateOfBirth: new FormControl(null, Validators.required),
            address: new FormControl(null, Validators.required),
            email: new FormControl(null, Validators.required),
            password: new FormControl(null, Validators.required)
        });
    }
    traineeRegistration() {
        console.log(this.postForm.value);
        this.httpClient.post('http://localhost:2000/gym/trainee/registration', this.postForm.value).subscribe(response => {
            console.log(response);
        });
    }
};
TraineeRegistrationComponentComponent = __decorate([
    Component({
        selector: 'app-trainee-registration-component',
        templateUrl: './trainee-registration-component.component.html',
        styleUrls: ['./trainee-registration-component.component.scss']
    })
], TraineeRegistrationComponentComponent);
export { TraineeRegistrationComponentComponent };
//# sourceMappingURL=trainee-registration-component.component.js.map