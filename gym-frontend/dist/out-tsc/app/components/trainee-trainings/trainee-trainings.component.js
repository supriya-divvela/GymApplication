import { __decorate } from "tslib";
import { Component } from '@angular/core';
import { map } from 'rxjs';
let TraineeTrainingsComponent = class TraineeTrainingsComponent {
    constructor(httpClient, route) {
        this.httpClient = httpClient;
        this.route = route;
        this.searchText = "";
        this.searchStartDate = new Date(2001, 1, 1);
        this.searchEndDate = new Date(2001, 1, 1);
        this.traineeTrainings = [];
    }
    ngOnInit() {
        this.route.queryParams.subscribe(params => {
            this.getTraineeTrainings(params['traineeusername']);
        });
    }
    getTraineeTrainings(traineeusername) {
        return this.httpClient.get('http://localhost:2000/gym/training/traineelist', { params: { "traineeusername": traineeusername } }).pipe(map(response => {
            for (let key in response) {
                this.traineeTrainings.push(response[key]);
            }
            return this.traineeTrainings;
        })).
            subscribe(response => console.log(response));
    }
};
TraineeTrainingsComponent = __decorate([
    Component({
        selector: 'app-trainee-trainings',
        templateUrl: './trainee-trainings.component.html',
        styleUrls: ['./trainee-trainings.component.scss']
    })
], TraineeTrainingsComponent);
export { TraineeTrainingsComponent };
//# sourceMappingURL=trainee-trainings.component.js.map