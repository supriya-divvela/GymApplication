import { __decorate } from "tslib";
import { Component } from '@angular/core';
let LoginComponentComponent = class LoginComponentComponent {
    generate() {
        let captcha;
        captcha = document.getElementById("image");
        let uniquechar = "";
        const randomchar = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        for (let i = 1; i < 5; i++) {
            uniquechar += randomchar.charAt(Math.random() * randomchar.length);
        }
        return uniquechar;
    }
};
LoginComponentComponent = __decorate([
    Component({
        selector: 'app-login-component',
        templateUrl: './login-component.component.html',
        styleUrls: ['./login-component.component.scss']
    })
], LoginComponentComponent);
export { LoginComponentComponent };
//# sourceMappingURL=login-component.component.js.map