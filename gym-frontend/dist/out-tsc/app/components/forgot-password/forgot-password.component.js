import { __decorate } from "tslib";
import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { PasswordValidator } from './PasswordValidator';
let ForgotPasswordComponent = class ForgotPasswordComponent {
    constructor(route, router, httpClient) {
        this.route = route;
        this.router = router;
        this.httpClient = httpClient;
        this.forgotPasswordForm = {};
        this.error = '';
        this.username = '';
    }
    ngOnInit() {
        this.username = this.route.snapshot.params['username'];
        this.forgotPasswordForm = new FormGroup({
            username: new FormControl(this.username, [Validators.required, Validators.minLength(4)]),
            oldPassword: new FormControl('', Validators.required),
            newPassword: new FormControl('', [Validators.required, Validators.pattern('/^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})/')]),
            confirmPassword: new FormControl('', Validators.required)
        }, { validators: PasswordValidator });
    }
    forgotPassword() {
        this.httpClient.post('http://localhost:2000/gym/login/changepassword', this.forgotPasswordForm.value);
    }
    reset() {
        if (this.forgotPasswordForm.valid) {
            this.forgotPasswordForm.reset();
        }
    }
};
ForgotPasswordComponent = __decorate([
    Component({
        selector: 'app-forgot-password',
        templateUrl: './forgot-password.component.html',
        styleUrls: ['./forgot-password.component.scss']
    })
], ForgotPasswordComponent);
export { ForgotPasswordComponent };
//# sourceMappingURL=forgot-password.component.js.map