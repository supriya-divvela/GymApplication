import { Component } from '@angular/core';
import { Trainer } from '../../model/Trainer';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { TrainerServiceService } from 'src/app/service/trainer-service.service';

@Component({
  selector: 'app-trainer-profile',
  templateUrl: './trainer-profile.component.html',
  styleUrls: ['./trainer-profile.component.scss']
})
export class TrainerProfileComponent {
  noOftrainees:number=0;
  constructor(private route: ActivatedRoute, private trainerService: TrainerServiceService) { }
  ngOnInit(): void {
    this.username = this.route.snapshot.params['username'];
    this.getTrainer(this.route.snapshot.params['username']);
  }
  username: string = '';
  trainer: Trainer = {} as any;
  person:string='trainer';
  getTrainer(trainerusername: string) {
    return this.trainerService.getTrainer(trainerusername).subscribe({next:(response:Trainer) => {
      this.trainer = response;
      this.noOftrainees=this.trainer.listOfTrainees.length
      return this.trainer;
    }}
    );
  }
}
