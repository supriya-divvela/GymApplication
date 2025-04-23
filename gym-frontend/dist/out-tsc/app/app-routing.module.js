import { __decorate } from "tslib";
import { TrainerProfileComponent } from './components/trainer-profile/trainer-profile.component';
import { TraineeRegistrationComponentComponent } from './components/trainee-registration/trainee-registration-component.component';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { LoginComponentComponent } from './components/login/login-component.component';
import { TrainerRegistrationComponentComponent } from './components/trainer-registration/trainer-registration-component.component';
import { HomeComponentComponent } from './components/home/home-component.component';
import { ForgotPasswordComponent } from './components/forgot-password/forgot-password.component';
import { TraineeProfileComponent } from './components/trainee-profile/trainee-profile.component';
import { AddTrainingComponent } from './components/add-training/add-training.component';
import { UpdateTraineeComponent } from './components/update-trainee/update-trainee.component';
import { TraineeTrainingsComponent } from './components/trainee-trainings/trainee-trainings.component';
import { TrainerTrainingsComponent } from './components/trainer-trainings/trainer-trainings.component';
import { UpdateTrainerComponent } from './components/update-trainer/update-trainer.component';
const routes = [
    { path: '', redirectTo: '/home', pathMatch: 'full' },
    { path: 'home', component: HomeComponentComponent },
    { path: 'login', component: LoginComponentComponent },
    { path: 'trainer-registration', component: TrainerRegistrationComponentComponent },
    { path: 'trainee-registration', component: TraineeRegistrationComponentComponent },
    { path: 'forgot-password', component: ForgotPasswordComponent },
    { path: 'forgot-password/:username', component: ForgotPasswordComponent },
    { path: 'logout', redirectTo: '/home', pathMatch: 'full' },
    { path: 'trainee-profile', component: TraineeProfileComponent },
    { path: 'trainee-profile/:username', component: TraineeProfileComponent },
    { path: 'trainer-profile', component: TrainerProfileComponent },
    { path: 'trainer-profile/:username', component: TrainerProfileComponent },
    { path: 'add-training/:username', component: AddTrainingComponent },
    { path: 'update-trainee/:username', component: UpdateTraineeComponent },
    { path: 'update-trainer/:username', component: UpdateTrainerComponent },
    { path: 'trainee-trainings', component: TraineeTrainingsComponent, children: [{ path: 'trainee-profile/:traineeusername', component: TraineeTrainingsComponent }] },
    { path: 'trainer-trainings', component: TrainerTrainingsComponent },
    // { path: '**', redirectTo: '/home', pathMatch: 'full' }
];
let AppRoutingModule = class AppRoutingModule {
};
AppRoutingModule = __decorate([
    NgModule({
        imports: [RouterModule.forRoot(routes)],
        exports: [RouterModule]
    })
], AppRoutingModule);
export { AppRoutingModule };
//# sourceMappingURL=app-routing.module.js.map