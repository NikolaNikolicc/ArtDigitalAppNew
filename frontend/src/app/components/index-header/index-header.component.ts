import { Component, ElementRef, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { UploadService } from 'src/app/services/upload.service';

declare var bootstrap: any; // This is for Bootstrap's JavaScript

@Component({
  selector: 'app-index-header',
  templateUrl: './index-header.component.html',
  styleUrls: ['./index-header.component.css']
})
export class IndexHeaderComponent{
  @ViewChild('priceModal') priceModalRef!: ElementRef;
  @ViewChild('offcanvasNavbar') offCanvasRef!: ElementRef;
  private offcanvas: any;

  constructor(private router: Router, private fileService: UploadService){

  }

  goToSite(){
    window.location.href = 'https://fotostudioart.rs/';
  }

  downloadFolder(foldername: string) {
    this.fileService.download(foldername).subscribe(blob => {
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(blob);
      link.download = foldername + '.zip';
      link.click();
    });
  }

  ngAfterViewInit() {
    // Initialize the offcanvas instance after view initialization
    const offcanvasNative: HTMLElement = this.offCanvasRef.nativeElement;
    this.offcanvas = new bootstrap.Offcanvas(offcanvasNative);
  }

  startAgain(){
    this.offcanvas.hide();
    localStorage.clear();
    this.router.navigate([""]);
  }

  openPriceModal() {
    this.offcanvas.hide();
    this.showPriceModal();
  }
  
  showPriceModal() {
    // Now show the modal for prices
    const modalNative: HTMLElement = this.priceModalRef.nativeElement;
    const modal = new bootstrap.Modal(modalNative, {
      // backdrop: 'static',
      // keyboard: false,
    });
    modal.show();
  }

  
}
