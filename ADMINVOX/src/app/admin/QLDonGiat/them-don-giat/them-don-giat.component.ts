import { Component, OnInit } from '@angular/core';
import { InsertBillDTO } from '../../../dtos/bill/insert.bill.dtos';
import { BillService } from '../../../service/bill.service';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { ApiResponse } from '../../../responses/api.response';

@Component({
  selector: 'app-them-don-giat',
  templateUrl: './them-don-giat.component.html',
  styleUrl: './them-don-giat.component.css'
})
export class ThemDonGiatComponent {
  addBillDTO: any = {
    // your other bill properties here
  };
  selectedFile: File | null = null;

  constructor(private billService: BillService, private router: Router, private route: ActivatedRoute) {}

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
    console.log('Selected file:', this.selectedFile); // Debugging log
  }

  onSubmit() {
    console.log('Form data:', this.addBillDTO); // Debugging log
    this.billService.insertBill(this.addBillDTO).subscribe({
      next: (apiResponse: any) => {
        console.log('Bill created:', apiResponse); // Debugging log
        if (this.selectedFile) {
          const billID = apiResponse.billID; // Assuming the response contains the newly created bill's ID
          console.log('Uploading image for bill ID:', billID); // Debugging log
          this.billService.uploadImages(billID, this.selectedFile).subscribe({
            next: (imageResponse: any) => {
              console.log('Images uploaded successfully:', imageResponse);
              // Navigate back to the previous page
              this.router.navigate(['../'], { relativeTo: this.route });
            },
            error: (error: HttpErrorResponse) => {
              console.error('Image upload error:', error?.error?.message ?? '');
            }
          });
        } else {
          this.router.navigate(['../'], { relativeTo: this.route });
        }
      },
      error: (error) => {
        console.error('Bill creation error:', error);
        this.router.navigate(['/admin/qldg']); 
        // alert(error);
      }        
    });
  }
}