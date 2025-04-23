import { __decorate } from "tslib";
import { Component } from '@angular/core';
import { map } from 'rxjs';
let TraineeProfileComponent = class TraineeProfileComponent {
    constructor(route, router, httpClient) {
        this.route = route;
        this.router = router;
        this.httpClient = httpClient;
        this.username = '';
        this.trainee = {};
    }
    ngOnInit() {
        this.username = this.route.snapshot.params['username'];
        this.getTrainee(this.route.snapshot.params['username']);
    }
    getTrainee(traineeusername) {
        return this.httpClient.get('http://localhost:2000/gym/trainee/' + traineeusername).pipe(map(response => {
            this.trainee = response;
            return this.trainee;
        })).
            subscribe(response => console.log(response));
    }
};
TraineeProfileComponent = __decorate([
    Component({
        selector: 'app-trainee-profile',
        templateUrl: './trainee-profile.component.html',
        styleUrls: ['./trainee-profile.component.scss']
    })
], TraineeProfileComponent);
export { TraineeProfileComponent };
//# sourceMappingURL=trainee-profile.component.js.map