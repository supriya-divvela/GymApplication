import { Component, OnInit } from '@angular/core';
import { TrainerTrainings } from '../../model/TrainerTrainings';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-trainer-trainings',
  templateUrl: './trainer-trainings.component.html',
  styleUrls: ['./trainer-trainings.component.scss']
})
export class TrainerTrainingsComponent implements OnInit {
  constructor(private httpClient: HttpClient, private route: ActivatedRoute) { }
  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.username=params['trainerusername'];
    });
    this.getTrainerTrainings(this.username);
  }
  person:string='trainer';
  trainerTrainings: TrainerTrainings[] = [];
  searchText: string = '';
  searchStartDate: Date = new Date(2001, 1, 1);
  searchEndDate: Date = new Date(2090,1,1);
  username:string='';
  getTrainerTrainings(trainerusername: string) {
    this.username=trainerusername;
    return this.httpClient.get<TrainerTrainings[]>('/api/gym/training/trainerlist', { params: { "trainerusername": trainerusername } }).pipe(map(response => {
      for (let key in response) {
        this.trainerTrainings.push(response[key]);
      }
      return this.trainerTrainings;
    })).subscribe(response => console.log(response));

  }
}
