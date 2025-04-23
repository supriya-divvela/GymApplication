import { Trainee } from "./Trainee";

export interface Trainer{
    firstname:string,
    lastname:string,
    username:string,
    specialization:string,
    active:boolean,
    listOfTrainees:Trainee[]
}