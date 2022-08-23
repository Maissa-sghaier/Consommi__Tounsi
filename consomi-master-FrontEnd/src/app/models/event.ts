import { Client } from "./client";

export class Event {
    idEvent:number;
    nameEvent:string;
    descriptionEvent:string;
    placeEvent:string;
    costEvent:number;
    nbPlaceDisponible:number;
    fund:number;
    clients: Client;
    startDateEvent:Date;
    endDateEvent:Date;
   

}