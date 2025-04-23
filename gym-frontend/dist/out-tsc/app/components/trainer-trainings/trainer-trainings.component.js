import { __decorate } from "tslib";
import { Component } from '@angular/core';
import { map } from 'rxjs';
let TrainerTrainingsComponent = class TrainerTrainingsComponent {
    constructor(httpClient, route) {
        this.httpClient = httpClient;
        this.route = route;
        this.trainerTrainings = [];
        this.searchText = '';
        this.searchSpecialization = '';
    }
    ngOnInit() {
        this.route.queryParams.subscribe(params => {
            console.log(this.getTrainerTrainings(params['trainerusername']));
        });
    }
    getTrainerTrainings(trainerusername) {
        return this.httpClient.get('http://localhost:2000/gym/training/trainerlist', { params: { "trainerusername": trainerusername } }).pipe(map(response => {
            for (let key in response) {
                this.trainerTrainings.push(response[key]);
            }
            return this.trainerTrainings;
        })).
            subscribe(response => console.log(response));
    }
};
TrainerTrainingsComponent = __decorate([
    Component({
        selector: 'app-trainer-trainings',
        templateUrl: './trainer-trainings.component.html',
        styleUrls: ['./trainer-trainings.component.scss']
    })
], TrainerTrainingsComponent);
export { TrainerTrainingsComponent };
//# sourceMappingURL=trainer-trainings.component.js.map