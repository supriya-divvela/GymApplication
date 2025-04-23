import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'specializationFilter'
})
export class SpecializationFilterPipe implements PipeTransform {

  transform(value: any,searchSpecialization:string ): any {
    if(value.length===0 || searchSpecialization.trim().length===0){
      return value;
    }
    const trainings=[];
    for(const training of value){
      if(new RegExp(`.*${searchSpecialization}.*`, 'g').test(training.trainingType)){
        trainings.push(training);
      }
    }
    return trainings;
  }

}
