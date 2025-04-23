import { __decorate } from "tslib";
import { Component } from '@angular/core';
import { map } from 'rxjs';
let TrainerProfileComponent = class TrainerProfileComponent {
    constructor(route, router, httpClient) {
        this.route = route;
        this.router = router;
        this.httpClient = httpClient;
        this.username = '';
        this.trainer = {};
    }
    ngOnInit() {
        this.username = this.route.snapshot.params['username'];
        this.getTrainer(this.route.snapshot.params['username']);
    }
    getTrainer(trainerusername) {
        return this.httpClient.get('http://localhost:2000/gym/trainer/' + trainerusername).pipe(map(response => {
            this.trainer = response;
            return this.trainer;
        })).
            subscribe(response => console.log(response));
    }
};
TrainerProfileComponent = __decorate([
    Component({
        selector: 'app-trainer-profile',
        templateUrl: './trainer-profile.component.html',
        styleUrls: ['./trainer-profile.component.scss']
    })
], TrainerProfileComponent);
export { TrainerProfileComponent };
//# sourceMappingURL=trainer-profile.component.js.map