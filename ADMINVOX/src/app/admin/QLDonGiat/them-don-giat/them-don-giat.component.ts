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
export class ThemDonGiatComponent implements OnInit {
  addBillDTO: InsertBillDTO = {
    userID: 1,
    billDescription: 'Hãy viết những ghi chú',
    image: null,
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
    debugger;
  
    // Gọi service để gửi yêu cầu HTTP với insertBillDTO
    this.billService.insertBill(this.addBillDTO).subscribe({
      next: () => {
        debugger;
        this.router.navigate(['/admin/qldg']);        
      },
      error: (error) => {
        debugger;
        console.error(error);
        this.router.navigate(['/admin/qldg']); 
        // alert(error);
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
    this.addBillDTO.image = files;
  }
}