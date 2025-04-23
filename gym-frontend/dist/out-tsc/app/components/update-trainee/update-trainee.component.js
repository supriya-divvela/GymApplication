import { __decorate } from "tslib";
import { Component } from '@angular/core';
import { TraineeProfileComponent } from '../trainee-profile/trainee-profile.component';
import { map } from 'rxjs';
import { FormControl, FormGroup, Validators } from '@angular/forms';
let UpdateTraineeComponent = class UpdateTraineeComponent {
    constructor(traineeProfile, route, httpClient) {
        this.traineeProfile = traineeProfile;
        this.route = route;
        this.httpClient = httpClient;
        this.username = '';
        this.trainee = {};
        this.updateForm = {};
        this.traineeForm = {};
    }
    ngOnInit() {
        this.updateForm = new FormGroup({
            firstname: new FormControl(null, Validators.required),
            lastname: new FormControl(null, Validators.required),
            dateOfBirth: new FormControl(null, Validators.required),
            address: new FormControl(null, Validators.required),
            isActive: new FormControl(null, Validators.required),
        });
        this.traineeForm = new FormGroup({
            listOfTrainers: new FormControl(null, Validators.required),
            username: new FormControl(null, Validators.required)
        });
        this.username = this.route.snapshot.params['username'];
        this.getTrainee(this.route.snapshot.params['username']);
        console.log(this.trainee.listOfTrainers);
    }
    getTrainee(traineeusername) {
        return this.httpClient.get('http://localhost:2000/gym/trainee/' + traineeusername).pipe(map(response => {
            this.trainee = response;
            return this.trainee;
        })).
            subscribe(response => console.log(response));
    }
    updateTrainee() {
        console.log(this.updateForm.value);
        this.httpClient.put('http://localhost:2000/gym/trainee', this.updateForm.value).subscribe(response => {
            console.log(response);
        });
        this.httpClient.put('http://localhost:2000/gym/trainee/updatetraineetrainers', this.traineeForm.value).subscribe(response => {
            console.log(response);
        });
    }
};
UpdateTraineeComponent = __decorate([
    Component({
        selector: 'app-update-trainee',
        templateUrl: './update-trainee.component.html',
        styleUrls: ['./update-trainee.component.scss'],
        providers: [TraineeProfileComponent]
    })
], UpdateTraineeComponent);
export { UpdateTraineeComponent };
//# sourceMappingURL=update-trainee.component.js.map