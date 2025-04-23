import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Trainee } from '../../model/Trainee';
import { map } from 'rxjs';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-add-training',
  templateUrl: './add-training.component.html',
  styleUrls: ['./add-training.component.scss']
})
export class AddTrainingComponent {
  addTrainingForm: FormGroup = {} as any;
  username: string = '';
  person: string = 'trainee';
  trainee: Trainee = {} as any;
  NonActiveTrainer: any;
  constructor(private httpClient: HttpClient, private route: ActivatedRoute) {
  }
  ngOnInit(): void {
    this.username = this.route.snapshot.params['username'];
    this.getTrainee(this.route.snapshot.params['username']);
    this.addTrainingForm = new FormGroup({
      traineeUsername: new FormControl(this.username, [Validators.required]),
      trainerUsername: new FormControl(null, [Validators.required]),
      trainingName: new FormControl(null, Validators.required),
      trainingDate: new FormControl(null, Validators.required),
      trainingType: new FormControl('', Validators.required),
      trainingDuration: new FormControl(1, [Validators.min(1), Validators.max(10)])
    });
    this.addTrainingForm.get('trainerUsername')?.valueChanges.subscribe(x => {
      const selectedTrainer = this.trainee.listOfTrainers.find(trainer => trainer.username === x);
      if (selectedTrainer) {
        this.addTrainingForm.controls['trainingType'].setValue(selectedTrainer.specialization);
      } else {
        this.addTrainingForm.controls['trainingType'].setValue('');
      }
    });
  }

  getTrainee(traineeusername: string) {
    return this.httpClient.get<Trainee>('/api/gym/trainee/' + traineeusername).pipe(map(response => {
      this.trainee = response;
      return this.trainee;
    })).
      subscribe(response => console.log(response));
  }

  addTraining() {
    console.log(this.addTrainingForm.value);
    this.httpClient.post('/api/gym/training/addtraining', this.addTrainingForm.value).subscribe(response => {
      alert("Training Added Successfully..");
    });
    this.addTrainingForm.reset({ traineeUsername: this.username });
  }

  reset() {
    if (this.addTrainingForm.valid) {
      this.addTrainingForm.reset({ traineeUsername: this.username });
    }
  }

}
