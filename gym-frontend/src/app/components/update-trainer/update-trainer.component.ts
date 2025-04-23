import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { map } from 'rxjs';
import { Trainer } from '../../model/Trainer';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { UpdateTrainer } from 'src/app/model/UpdateTrainer';

@Component({
  selector: 'app-update-trainer',
  templateUrl: './update-trainer.component.html',
  styleUrls: ['./update-trainer.component.scss']
})
export class UpdateTrainerComponent implements OnInit {
  constructor(private route: ActivatedRoute, private httpClient: HttpClient) { }
  updateTrainerForm: FormGroup = {} as any;
  username: string = '';
  trainer: Trainer = {} as any;
  person:string='trainer';
  ngOnInit(): void {
    this.username = this.route.snapshot.params['username'];
    this.updateTrainerForm = new FormGroup({
      username: new FormControl(this.username, Validators.required),
      firstname: new FormControl('', Validators.required),
      lastname: new FormControl('', Validators.required),
      isActive: new FormControl(false, Validators.required),
      specialization: new FormControl('', Validators.required),
      listOfTrainees: new FormControl('', Validators.required)
    });
    this.getTrainer(this.route.snapshot.params['username']);
    localStorage.setItem('trainer',JSON.stringify(this.trainer));
  }

  getTrainer(trainerusername: string) {
    return this.httpClient.get<Trainer>('api/gym/trainer/' + trainerusername).pipe(map(response => {
      this.trainer = response;
      this.updateTrainerForm.patchValue({
        firstname: this.trainer.firstname,
        lastname: this.trainer.lastname,
        isActive: this.trainer.active,
        specialization: this.trainer.specialization,
        listOfTrainees: this.trainer.listOfTrainees,
        username: this.username
      });
      return this.trainer;
    })).subscribe({ next: (response: Trainer) => console.log(response.active)});
  }

  updateTrainer() {
    const updateTrainer = new UpdateTrainer(this.username,
      this.updateTrainerForm.controls['firstname'].value,
      this.updateTrainerForm.controls['lastname'].value,
      this.updateTrainerForm.controls['isActive'].value, this.updateTrainerForm.controls['specialization'].value)
    this.httpClient.put('/api/gym/trainer', updateTrainer).subscribe(response => {
      alert("Updated Trainer Successfully");
    });
    
  }
}
