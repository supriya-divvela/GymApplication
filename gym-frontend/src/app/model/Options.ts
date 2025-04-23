// export interface Employee {
//     Id: number;
//     Code: string;
//     Name: string;
//     Job: string;
//     Salary: number;
//     Department: string;
// }

import { TraineeTrainings } from "./TraineeTrainings";

export interface Response {
    records: TraineeTrainings[];
    filtered: number;
    total: number;
}

export interface Options {
    orderBy: string;
    orderDir: 'ASC' | 'DESC';
    search: string,
    size: number,
    page: number;
}