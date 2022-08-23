import { Role } from "../models/Role"
export class Client{
    id_user:number;
    email:String;
    username:String;
    phone_number:number;
    password:String;
    role:Role ;
    date:Date;
   // enabled:boolean;
    first_name:String;
    gender:String;
    last_name:String;
    //locked:boolean;
    city:String;
    address:String;
    
}