import { TrainerProfileComponent } from './components/trainer-profile/trainer-profile.component';
import { TraineeRegistrationComponentComponent } from './components/trainee-registration/trainee-registration-component.component';
import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponentComponent } from './components/login/login-component.component';
import { AppComponent } from './app.component';
import { TrainerRegistrationComponentComponent } from './components/trainer-registration/trainer-registration-component.component';
import { HomeComponentComponent } from './components/home/home-component.component';
import { ForgotPasswordComponent } from './components/forgot-password/forgot-password.component';
import { TraineeProfileComponent } from './components/trainee-profile/trainee-profile.component';
import { AddTrainingComponent } from './components/add-training/add-training.component';
import { UpdateTraineeComponent } from './components/update-trainee/update-trainee.component';
import { TraineeTrainingsComponent } from './components/trainee-trainings/trainee-trainings.component';
import { TrainerTrainingsComponent } from './components/trainer-trainings/trainer-trainings.component';
import { UpdateTrainerComponent } from './components/update-trainer/update-trainer.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { AuthGuard } from './guards/auth-guard.guard';
import { traineeGuardGuard } from './guards/trainee-guard.guard';
import { trainerGuardGuard } from './guards/trainer-guard.guard';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'registration', component: RegistrationComponent },
  { path: 'home', component: HomeComponentComponent },
  { path: 'login', component: LoginComponentComponent },
  { path: 'registration', component: RegistrationComponent },
  { path: 'forgot-password', component: ForgotPasswordComponent,canActivate:[AuthGuard] },
  { path: 'forgot-password/:username', component: ForgotPasswordComponent },
  { path: 'logout', redirectTo: '/login', pathMatch: 'full' },
  { path: 'trainee-profile/:username', component: TraineeProfileComponent ,canActivate:[AuthGuard,traineeGuardGuard]},
  { path: 'trainer-profile/:username', component: TrainerProfileComponent ,canActivate:[AuthGuard,trainerGuardGuard]},
  { path: 'add-training/:username', component: AddTrainingComponent ,canActivate:[AuthGuard,traineeGuardGuard]},
  { path: 'update-trainee/:username', component: UpdateTraineeComponent ,canActivate:[AuthGuard,traineeGuardGuard]},
  { path: 'update-trainer/:username', component: UpdateTrainerComponent ,canActivate:[AuthGuard,trainerGuardGuard]},
  { path: 'trainee-trainings', component: TraineeTrainingsComponent, children: [{ path: 'trainee-profile/:traineeusername', component: TraineeTrainingsComponent}],canActivate:[AuthGuard,traineeGuardGuard] },
  { path: 'trainer-trainings', component: TrainerTrainingsComponent ,canActivate:[AuthGuard,trainerGuardGuard]},
  { path: '**', redirectTo: '/home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
