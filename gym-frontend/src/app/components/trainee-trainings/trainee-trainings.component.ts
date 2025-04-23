import { Component, OnInit } from '@angular/core';
import { map } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { TraineeServiceService } from 'src/app/service/trainee-service.service';
import { TraineeTrainings } from 'src/app/model/TraineeTrainings';

@Component({
  selector: 'app-trainee-trainings',
  templateUrl: './trainee-trainings.component.html',
  styleUrls: ['./trainee-trainings.component.scss']
})
export class TraineeTrainingsComponent implements OnInit {
  searchText: string = "";
  searchStartDate: Date = new Date(2001, 1, 1);
  searchEndDate: Date = new Date(2090,1,1);
  searchSpecialization = '';
  traineeTrainings: TraineeTrainings[] = [];
  username:string='';
  person:string='trainee';
  constructor(
    private route: ActivatedRoute,
    private traineeService: TraineeServiceService) { }
  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.username=params['traineeusername'];
      this.getTraineeTrainings(params['traineeusername']);
    });
  }

  getTraineeTrainings(traineeusername: string) {
    return this.traineeService.getTraineeTrainings(traineeusername).pipe(map(response => {
      this.traineeTrainings = response;
      return this.traineeTrainings;
    })).
      subscribe(response => console.log(response));
  }

  onSelected(value: string) {
    this.searchSpecialization = value;
  }
}