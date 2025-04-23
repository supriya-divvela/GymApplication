import { __decorate } from "tslib";
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavComponentComponent } from './components/nav/nav-component.component';
import { FooterComponentComponent } from './components/footer/footer-component.component';
import { LoginComponentComponent } from './components/login/login-component.component';
import { TraineeRegistrationComponentComponent } from './components/trainee-registration/trainee-registration-component.component';
import { TrainerRegistrationComponentComponent } from './components/trainer-registration/trainer-registration-component.component';
import { HomeComponentComponent } from './components/home/home-component.component';
import { ForgotPasswordComponent } from './components/forgot-password/forgot-password.component';
import { TraineeProfileComponent } from './components/trainee-profile/trainee-profile.component';
import { TrainerProfileComponent } from './components/trainer-profile/trainer-profile.component';
import { UpdateTraineeComponent } from './components/update-trainee/update-trainee.component';
import { UpdateTrainerComponent } from './components/update-trainer/update-trainer.component';
import { TraineeTrainingsComponent } from './components/trainee-trainings/trainee-trainings.component';
import { TrainerTrainingsComponent } from './components/trainer-trainings/trainer-trainings.component';
import { AddTrainingComponent } from './components/add-training/add-training.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { TraineeFilterPipe } from './pipes/trainee-filter.pipe';
import { TrainerFilterPipe } from './pipes/trainer-filter.pipe';
import { FilterDatePipe } from './pipes/filter-date.pipe';
let AppModule = class AppModule {
};
AppModule = __decorate([
    NgModule({
        declarations: [
            AppComponent,
            NavComponentComponent,
            FooterComponentComponent,
            LoginComponentComponent,
            TraineeRegistrationComponentComponent,
            TrainerRegistrationComponentComponent,
            HomeComponentComponent,
            ForgotPasswordComponent,
            TraineeProfileComponent,
            TrainerProfileComponent,
            UpdateTraineeComponent,
            UpdateTrainerComponent,
            TraineeTrainingsComponent,
            TrainerTrainingsComponent,
            AddTrainingComponent,
            TraineeFilterPipe,
            TrainerFilterPipe,
            FilterDatePipe
        ],
        imports: [
            BrowserModule,
            AppRoutingModule,
            FormsModule,
            NgbModule,
            ReactiveFormsModule,
            HttpClientModule
        ],
        providers: [],
        bootstrap: [AppComponent]
    })
], AppModule);
export { AppModule };
//# sourceMappingURL=app.module.js.map