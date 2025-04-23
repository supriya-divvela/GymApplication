import { Component, OnInit } from '@angular/core';
import { TraineeProfileComponent } from '../trainee-profile/trainee-profile.component';
import { ActivatedRoute, Router } from '@angular/router';
import { Trainee } from '../../model/Trainee';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { UpdateTrainee } from 'src/app/model/UpdateTrainee';
import { TraineeServiceService } from 'src/app/service/trainee-service.service';
import { NonActiveTrainer } from 'src/app/model/NonActiveTrainer';

@Component({
  selector: 'app-update-trainee',
  templateUrl: './update-trainee.component.html',
  styleUrls: ['./update-trainee.component.scss'],
  providers: [TraineeProfileComponent]
})
export class UpdateTraineeComponent implements OnInit {
  constructor(private traineeService: TraineeServiceService, private route: ActivatedRoute, private httpClient: HttpClient,private router:Router) { }
  updateTraineeForm: FormGroup = {} as any;
  username: string = '';
  trainee: Trainee = {} as any;
  trainers: NonActiveTrainer[] = [];
  person:string='trainee';
  ngOnInit() {
    this.updateTraineeForm = new FormGroup({
      firstname: new FormControl('', Validators.required),
      lastname: new FormControl('', Validators.required),
      dateOfBirth: new FormControl('', Validators.required),
      address: new FormControl('', Validators.required),
      isActive: new FormControl('', Validators.required),
      listOfTrainers: new FormControl('', Validators.required),
      username: new FormControl(this.username, Validators.required)
    });
    this.username = this.route.snapshot.params['username'];
    this.getTrainee(this.route.snapshot.params['username']);
    this.getNonActiveTrainers(this.username);
    console.log(this.trainee);
    localStorage.setItem('trainee',JSON.stringify(this.trainee));
  }

  getTrainee(traineeusername: string) {
    return this.httpClient.get<Trainee>('api/gym/trainee/' + traineeusername).pipe(map(response => {
      this.trainee = response;
      this.updateTraineeForm.patchValue({
        firstname: this.trainee.firstname,
        lastname: this.trainee.lastname,
        dateOfBirth: this.trainee.dateOfBirth,
        address: this.trainee.address,
        isActive: this.trainee.active,
        listOfTrainers: this.trainee.listOfTrainers,
        username: this.trainee.username
      });
      return this.trainee;
    })).
      subscribe(response => console.log(response));
  }

  updateTrainee() {
    const updateTrainee = new UpdateTrainee(this.username,
      this.updateTraineeForm.controls['firstname'].value,
      this.updateTraineeForm.controls['lastname'].value,
      this.updateTraineeForm.controls['dateOfBirth'].value,
      this.updateTraineeForm.controls['address'].value,
      this.updateTraineeForm.controls['isActive'].value)
    this.httpClient.put('/api/gym/trainee', updateTrainee).subscribe(response => {
      console.log(response);
      
    });
    const t: string[] = this.updateTraineeForm.controls['listOfTrainers'].value;
    this.httpClient.put('/api/gym/trainee/updatetraineetrainers', { traineeUsername: this.username, listOfTrainerUsernames: t }).subscribe(response => {
      alert("Updated Trainee Successfully");
      this.router.navigateByUrl("/trainee-profile/"+this.username);
    });
    
  }
  getNonActiveTrainers(traineeusername: string) {
    return this.traineeService.getNonActiveTrainers(traineeusername).
      subscribe({
        next: (response: NonActiveTrainer[]) => {
          this.trainers = response;
        }
      });
  }
}
