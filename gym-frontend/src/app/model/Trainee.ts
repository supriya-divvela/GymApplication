import {Trainer} from './Trainer';

export interface Trainee{
    firstname:string,
    lastname:string,
    username:string,
    dateOfBirth:Date,
    address:string,
    active:boolean,
    listOfTrainers:Trainer[],
}