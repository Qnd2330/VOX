import { Component, OnInit } from '@angular/core';
import { InsertBillDTO } from '../../../dtos/bill/insert.bill.dtos';
import { BillService } from '../../../service/bill.service';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { ApiResponse } from '../../../responses/api.response';

@Component({
  selector: 'app-chi-tiet-don-giat-form',
  templateUrl: './chi-tiet-don-giat-form.component.html',
  styleUrl: './chi-tiet-don-giat-form.component.css'
})
export class ChiTietDonGiatFormComponent implements OnInit {
  insertBillDTO: InsertBillDTO = {
    userID: 0,
    billDescription: '',
    sumWeight: 0,
    cost: 0,
    billStatus: false,
    image: '',
    billPayDate: new Date(),
  };
  constructor(    
    private route: ActivatedRoute,
    private router: Router,   
    private billService: BillService,    
  ) {
  }
  ngOnInit() {
    
  } 
  insertBill() {    
    debugger
    this.billService.insertBill(this.insertBillDTO).subscribe({
      next: (apiResponse: ApiResponse) => {
        debugger
        this.router.navigate(['/admin/qldg']);        
      },
      error: (error: HttpErrorResponse) => {
        debugger;
        console.error(error?.error?.message ?? 'Loi rui');
      }        
    });    
  }
  onFileChange(event: any) {
    // Retrieve selected files from input element
    const files = event.target.files;
    // Limit the number of selected files to 5
    if (files.length > 5) {
      console.error('Please select a maximum of 5 images.');
      return;
    }
    // Store the selected files in the newProduct object
    this.insertBillDTO.image = files;
  }
}