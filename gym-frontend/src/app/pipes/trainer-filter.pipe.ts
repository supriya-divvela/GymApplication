import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'trainerFilter'
})
export class TrainerFilterPipe implements PipeTransform {

  transform(value: any,searchText:string ) {
    if(value.length===0 || searchText.trim().length===0){
      return value;
    }
    const trainings=[];
    for(const training of value){
      if(new RegExp(`.*${searchText}.*`, 'g').test(training.traineeUsername)){
        trainings.push(training);
      }
    }
    return trainings;
  }
}