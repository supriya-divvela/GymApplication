import { __decorate } from "tslib";
import { Pipe } from '@angular/core';
let TraineeFilterPipe = class TraineeFilterPipe {
    transform(value, searchText) {
        if (value.length === 0 || searchText.trim().length === 0) {
            return value;
        }
        const trainings = [];
        for (const training of value) {
            if (new RegExp(`.*${searchText}.*`, 'g').test(training.trainerUsername)) {
                trainings.push(training);
            }
        }
        return trainings;
    }
};
TraineeFilterPipe = __decorate([
    Pipe({
        name: 'traineeFilter'
    })
], TraineeFilterPipe);
export { TraineeFilterPipe };
//# sourceMappingURL=trainee-filter.pipe.js.map