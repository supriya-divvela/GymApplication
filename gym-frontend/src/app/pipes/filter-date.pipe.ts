import { formatDate } from '@angular/common';
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filterDate'
})
export class FilterDatePipe implements PipeTransform {

  transform(value: any, searchStartDate?: Date, searchEndDate?: Date) {
    if (value.length === 0) {
      return value;
    }
    var filteredData: any[] = value;
    if (searchStartDate !== undefined) {
      filteredData = filteredData.filter(row =>
        new Date(row.trainingDate) >= new Date(searchStartDate));
    }
    if (searchEndDate) {
      filteredData = filteredData.filter(row => new Date(row.trainingDate) <= new Date(searchEndDate));
    }
    if (searchStartDate !== undefined && searchEndDate !== undefined) {
      if (new Date(searchStartDate) >= new Date(searchEndDate)) {
        return value;
      }
    }
    return filteredData;
  }
}
