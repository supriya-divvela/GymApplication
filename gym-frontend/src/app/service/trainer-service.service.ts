import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Trainer } from '../model/Trainer';
import { TrainerTrainings } from '../model/TrainerTrainings';
import { catchError, throwError } from 'rxjs';
import { Trainee } from '../model/Trainee';

@Injectable({
  providedIn: 'root'
})
export class TrainerServiceService {
  constructor(private httpClient: HttpClient) { }
  getTrainer(trainerusername: string) {
    return this.httpClient.get<Trainer>('/api/gym/trainer/' + trainerusername);
  }
  getTrainerTrainings(trainerusername: string) {
    return this.httpClient.get<TrainerTrainings[]>('/api/gym/training/trainerlist', { params: { "trainerusername": trainerusername } });
  }

}
