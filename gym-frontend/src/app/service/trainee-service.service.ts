import { catchError, map, throwError } from 'rxjs';
import { Injectable } from '@angular/core';
import { Trainee } from '../model/Trainee';
import { HttpClient } from '@angular/common/http';
import { TraineeTrainings } from '../model/TraineeTrainings';
import { Router } from '@angular/router';
import { NonActiveTrainer } from '../model/NonActiveTrainer';
import { Role } from '../model/Role';

@Injectable({
  providedIn: 'root'
})
export class TraineeServiceService {

  constructor(private httpClient: HttpClient, private router: Router) { }
  trainee: Trainee = {} as any;
  getTrainee(traineeusername: string): Trainee | any {
    // return this.httpClient.get<Trainee>('/api/gym/trainee/' + traineeusername).pipe(catchError(error=>{
    //   console.log("error occured"+error);
    //   return throwError("Error occured");
    // }))
    return this.httpClient.get<Trainee>('/api/gym/trainee/' + traineeusername);
    // return this.trainee;
    // error:(err:Error)=>{
    //   throw Error(err.message);
    // }});
    // console.log(this.trainee)
    // return this.trainee;
    // return this.traineeService.getTrainee(traineeusername).subscribe({next:
    //   (trainee: Trainee) => {
    //     console.log(trainee);
    //     if(trainee.firstname===undefined){
    //       throw Error();
    //     }

    //     this.trainee=trainee;
    //   }
  }
  getTraineeTrainings(traineeusername: string) {
    return this.httpClient.get<TraineeTrainings[]>('/api/gym/training/traineelist', { params: { "traineeusername": traineeusername } });
  }
  deleteTrainee(traineeusername: string) {
    console.log("delete trainee");
    this.httpClient.delete('/api/gym/trainee/' + traineeusername).subscribe(response => {
      console.log(response);
    });
  }
  getNonActiveTrainers(traineeusername: string) {
    return this.httpClient.get<NonActiveTrainer[]>('/api/gym/trainee/nonactivetrainer/' + traineeusername);
  }
}
