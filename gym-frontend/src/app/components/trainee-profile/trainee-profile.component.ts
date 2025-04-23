import { LoginComponentComponent } from './../login/login-component.component';
import { Component, OnInit } from '@angular/core';
import { Trainee } from '../../model/Trainee';
import { ActivatedRoute } from '@angular/router';
import { TraineeServiceService } from 'src/app/service/trainee-service.service';
import { AuthServiceService } from 'src/app/service/auth-service.service';

@Component({
  selector: 'app-trainee-profile',
  templateUrl: './trainee-profile.component.html',
  styleUrls: ['./trainee-profile.component.scss']
  // providers:[LoginComponentComponent]
})
export class TraineeProfileComponent implements OnInit {
  trainee: Trainee= {} as any;
  person:string='trainee';
  noOfTrainers:number=0;
  constructor(private route: ActivatedRoute, private traineeService: TraineeServiceService,private authService:AuthServiceService) { }
  ngOnInit(): void {
    this.username = this.route.snapshot.params['username'];
    this.getTrainee(this.username);
  }
  username: string = '';

  getTrainee(traineeusername: string) {
    return this.traineeService.getTrainee(traineeusername).subscribe({next:(response:Trainee) => {
      this.trainee = response;
      this.noOfTrainers=this.trainee.listOfTrainers.length;
      return this.trainee;
    }}
    );
  }

  deleteTrainee(traineeusername: string) {
    this.traineeService.deleteTrainee(traineeusername);
    this.authService.logout();
  }
  clickMethod(traineeusername: string) {
    if(confirm("Are you sure to delete "+name)) {
      this.traineeService.deleteTrainee(traineeusername);
      this.authService.logout();
    }
  }
  
}
