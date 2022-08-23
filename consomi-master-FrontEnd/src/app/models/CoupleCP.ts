import { Product } from './product';
export class CoupleCP{
    id: number;
    quantity: number;
    price: number;
    productname: string;
    productid:number;
    constructor( id: number, name: string, qq: number, price: number,product:number){
        this.id= id;
        this.quantity=qq;
        this.price= price
        this.productname=name;
        this.productid=product;
    }
}