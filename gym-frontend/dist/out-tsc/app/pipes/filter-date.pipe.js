import { __decorate } from "tslib";
import { Pipe } from '@angular/core';
let FilterDatePipe = class FilterDatePipe {
    transform(value, searchStartDate, searchEndDate) {
        if (value.length === 0) {
            return value;
        }
        // const trainings=[];
        // for(const training of value){
        //   if(searchStartDate<=training.trainingDate){
        //     trainings.push(training);
        //   }
        // }
        // return trainings;
        let filteredData = value;
        if (searchStartDate) {
            filteredData = filteredData.filter(row => row.trainingDate >= new Date(searchStartDate));
        }
        if (searchEndDate) {
            filteredData = filteredData.filter(row => row.trainingDate <= new Date(searchStartDate));
        }
        filteredData.sort((a, b) => a.searchEndDate - b.searchStartDate);
        console.log(filteredData);
        return filteredData;
    }
};
FilterDatePipe = __decorate([
    Pipe({
        name: 'filterDate'
    })
], FilterDatePipe);
export { FilterDatePipe };
//# sourceMappingURL=filter-date.pipe.js.map