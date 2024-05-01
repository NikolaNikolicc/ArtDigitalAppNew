import { Component, ElementRef, HostListener, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { Order } from 'src/app/models/order';
import { SessionService } from 'src/app/services/session.service';

declare var bootstrap: any; // This is for Bootstrap's JavaScript

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.css']
})
export class OrderDetailsComponent implements OnInit {

  @ViewChild('errorModal') modalError!: ElementRef;
  pickupMethod: string = "licno";
  name: string = "";
  surname: string = "";
  phone: string = "";
  email: string = "";
  error: string = "";
  showError: boolean = false;
  postal: string = "";
  city: string = "";
  address: string = "";
  location: string = "Terazije 5";
  comment: string = "";
  promo: string = "";
  numberOfPictures: number = 0;
  numberOfExtras: number = 0;
  totalPrice: number = 0;
  extrasChosen: string = "";
  // session info
  sessionID: number = 0;

  constructor(private databaseService: SessionService,private router: Router, private _eref: ElementRef) {

  }
  ngOnInit(): void {
    window.scrollTo(0, 0);
    let sid = localStorage.getItem("sessionID");
    let nop = localStorage.getItem("imageBlobsLength");
    let noe = localStorage.getItem("extrasLength");
    let ec = localStorage.getItem("extrasChosen");
    let tp = localStorage.getItem("totalPrice")
    if (nop != null && noe != null && ec != null && sid != null && tp != null) {
      this.numberOfPictures = JSON.parse(nop);
      this.numberOfExtras = JSON.parse(noe) / 6;
      this.extrasChosen = JSON.parse(ec);
      this.sessionID = JSON.parse(sid);
      this.totalPrice = JSON.parse(tp);
    }
  }

  // slide panel
  @HostListener('document:click', ['$event'])
  public onDocumentClick(event: MouseEvent): void {
    const slidePanelContent = this._eref.nativeElement.querySelector('#slidePanelContent');

    if (slidePanelContent && !slidePanelContent.contains(event.target)) {
      // Check if the slide panel is currently shown
      if (slidePanelContent.classList.contains('show')) {
        // Toggle the panel to hide it
        slidePanelContent.classList.remove('show');
      }
    }
  }

  validateEmail(): boolean {
    const regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
    return regex.test(this.email);
  }

  validatePhone(): boolean {
    const regex = /^[+]{0,1}[0-9-]+$/;
    return regex.test(this.phone);
  }

  validatePostal(): boolean {
    const regex = /^[0-9]{5}$/;
    return regex.test(this.postal);
  }

  validations() {
    if (this.name == "") {
      this.error = "Polje sa imenom mora biti popunjeno.";
      this.showError = true;
      return;
    }
    if (this.surname == "") {
      this.error = "Polje sa prezimenom mora biti popunjeno.";
      this.showError = true;
      return;
    }
    if (this.phone == "") {
      this.error = "Polje sa brojem telefona mora biti popunjeno.";
      this.showError = true;
      return;
    }
    if (this.email == "") {
      this.error = "Polje sa mejlom mora biti popunjeno.";
      this.showError = true;
      return;
    }
    // if(!this.validateEmail()){
    //   this.error = "Polje mejl nije u odgovarajućem formatu.";
    //   this.showError = true;
    //   return;
    // }
    // if(!this.validatePhone()){
    //   this.error = "Polje telefon nije u odgovarajućem formatu.";
    //   this.showError = true;
    //   return;
    // }
    if (this.pickupMethod == "kurir") {
      if (this.postal == "") {
        this.error = "Polje sa poštanskim brojem mora biti popunjeno.";
        this.showError = true;
        return;
      }
      if (this.city == "") {
        this.error = "Polje sa gradom mora biti popunjeno.";
        this.showError = true;
        return;
      }
      if (this.address == "") {
        this.error = "Polje adresa mora biti popunjeno.";
        this.showError = true;
        return;
      }
      // if(!this.validatePostal()){
      //   this.error = "Polje sa poštanskim brojem mora biti popunjeno u odgovarajućem formatu (pet cifara).";
      //   this.showError = true;
      //   return;
      // }
    }
  }

  next() {
    this.error = "";
    this.showError = false;
    this.validations();
    if (this.showError) {
      const modalNative: HTMLElement = this.modalError.nativeElement;
      const modal = new bootstrap.Modal(modalNative, {
        backdrop: 'static', // Prevents closing when clicking outside
        keyboard: false // Prevents closing with the escape key
      });
      modal.show();
      return;
    }
    const o = new Order();
    o.name = this.name;
    o.surname = this.surname;
    o.mail = this.email;
    o.phone = this.phone;
    switch(this.pickupMethod){
      case "licno":
        o.postal = "licno preuzimanje";
        o.city = "licno preuzimanje";
        o.address = this.location;
        break;
      case "kurir":
        o.postal = this.postal;
        o.city = this.city;
        o.address = this.address;
        break;
      default:
        o.postal = "licno preuzimanje";
        o.city = "licno preuzimanje";
        o.address = "Terazije 5";
        break;
    }
    o.comment = this.comment;
    o.orderID = this.sessionID;
    o.promoCode = this.promo;
    let pb = localStorage.getItem("paperBacking");
    if(pb == null)return;
    o.paperBacking = pb;
    this.databaseService.saveUser(o).subscribe(
      data=>{
        if(data){
          if(data == -1){
            alert("Doslo je do greske.");
          }
          else{
            alert("Uspesno izvrsena posiljka. Vas broj posiljke: " + data);
          }
          
        }
      }
    );
  }

}
