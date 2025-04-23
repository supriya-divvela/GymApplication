import { __decorate } from "tslib";
import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { map } from 'rxjs';
let AddTrainingComponent = class AddTrainingComponent {
    constructor(httpClient, route) {
        this.httpClient = httpClient;
        this.route = route;
        this.addTrainingForm = {};
        this.username = '';
        this.trainee = {};
    }
    ngOnInit() {
        this.username = this.route.snapshot.params['username'];
        this.getTrainee(this.route.snapshot.params['username']);
        this.addTrainingForm = new FormGroup({
            traineeUsername: new FormControl(this.username, [Validators.required, Validators.minLength(4)]),
            trainerUsername: new FormControl(null, Validators.required),
            trainingName: new FormControl(null, Validators.required),
            trainingDate: new FormControl(null, Validators.required),
            trainingType: new FormControl(null, Validators.required),
            trainingDuration: new FormControl(1, [Validators.min(1), Validators.max(10)])
        });
        this.addTrainingForm.get('trainerUsername')?.valueChanges.subscribe(x => {
            const selectedTrainer = this.trainee.listOfTrainers.find(trainer => trainer.username === x);
            if (selectedTrainer) {
                this.addTrainingForm.get('trainingType')?.setValue(selectedTrainer.specialization);
            }
            else {
                this.addTrainingForm.get('trainingType')?.setValue('');
            }
        });
    }
    getTrainee(traineeusername) {
        return this.httpClient.get('http://localhost:2000/gym/trainee/' + traineeusername).pipe(map(response => {
            this.trainee = response;
            return this.trainee;
        })).
            subscribe(response => console.log(response));
    }
    addTraining() {
        console.log(this.addTrainingForm.value);
        this.httpClient.post('http://localhost:2000/gym/training/addtraining', this.addTrainingForm.value).subscribe(response => {
            console.log(response);
        });
    }
    reset() {
        if (this.addTrainingForm.valid) {
            this.addTrainingForm.reset();
        }
    }
};
AddTrainingComponent = __decorate([
    Component({
        selector: 'app-add-training',
        templateUrl: './add-training.component.html',
        styleUrls: ['./add-training.component.scss']
    })
], AddTrainingComponent);
export { AddTrainingComponent };
//# sourceMappingURL=add-training.component.js.map