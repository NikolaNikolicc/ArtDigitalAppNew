import { Time } from "@angular/common";
import { Timestamp } from "rxjs";

export class Order{
    orderID: number = 0;
    name: string = "";
    surname: string = "";
    mail: string = "";
    phone: string = "";
    comment: string = "";
    date: Date = new Date();
    postal: string = "";
    city: string = "";
    address: string = "";
    paperBacking: string = "";
    promoCode: string = "";
    payment: string = "";
}