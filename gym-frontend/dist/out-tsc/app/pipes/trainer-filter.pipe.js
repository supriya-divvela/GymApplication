import { __decorate } from "tslib";
import { Pipe } from '@angular/core';
let TrainerFilterPipe = class TrainerFilterPipe {
    transform(value, searchText) {
        if (value.length === 0 || searchText.trim().length === 0) {
            return value;
        }
        const trainings = [];
        for (const training of value) {
            if (new RegExp(`.*${searchText}.*`, 'g').test(training.traineeUsername)) {
                trainings.push(training);
            }
        }
        return trainings;
    }
};
TrainerFilterPipe = __decorate([
    Pipe({
        name: 'trainerFilter'
    })
], TrainerFilterPipe);
export { TrainerFilterPipe };
//# sourceMappingURL=trainer-filter.pipe.js.map