import { Component, OnInit, inject } from '@angular/core';
import { Bill } from '../../../models/bill';
import { BillService } from '../../../service/bill.service';
import { HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { UpdateBillDTO } from '../../../dtos/bill/update.bill.dtos';
import { ApiResponse } from '../../../responses/api.response';
import { environment } from '../../../../environments/environment';
import { BillRespones} from '../../../responses/bill/bill.response';
import { BillDTO } from '../../../dtos/bill/bill.dtos';

@Component({
  selector: 'app-don-giat-form',
  templateUrl: './don-giat-form.component.html',
  styleUrl: './don-giat-form.component.css',
})
export class DonGiatFormComponent implements OnInit{
  currentPage: number = 0;
  itemsPerPage: number = 12;
  pages: number[] = [];
  totalPages: number = 0;
  visiblePages: number[] = [];
  bill:number = 0;
  billRespones: BillRespones = {
    billID: 0, // Hoặc bất kỳ giá trị số nào bạn muốn
    userID: 0,
    userName: '',
    billDescription: '',
    sumWeight: 0,
    cost: 0,
    billCreateDate: new Date(),
    billStatus: true,
    billPayDate: new Date(),
    image: '', 
    billDetail: [],
  };  
  private billService = inject(BillService);
  constructor(    
    private route: ActivatedRoute,
    private router: Router
    ) {}

  ngOnInit() {
    this.getBillDetails(this.currentPage, this.itemsPerPage);
  }
  
  getBillDetails(page: number, limit: number) {
    debugger
    this.bill = Number(this.route.snapshot.paramMap.get('id'));
    this.billService.getBillById(this.bill, page, limit).subscribe({
      next: (apiResponse: any) => {              
        const response = apiResponse
        if (response) {
          this.billRespones = response.billRespones;
        // this.billRespones.billID = response.billID;
        // this.billRespones.userID = response.userID;
        // this.billRespones.userName = response.userName;
        // this.billRespones.billDescription = response.billDescription;
        // this.billRespones.sumWeight = response.sumWeight;
        // this.billRespones.cost = response.cost; 
        // if (response.billCreateDate) {
        //   this.billRespones.billCreateDate = new Date(
        //     response.billCreateDate[0], 
        //     response.billCreateDate[1] - 1, 
        //     response.billCreateDate[2]
        //   );        
        // } 
        // if (response.billPayDate) {
        //   this.billRespones.billPayDate = new Date(
        //     response.billPayDate[0], 
        //     response.billPayDate[1] - 1, 
        //     response.billPayDate[2]
        //   );        
        // }   
        debugger;      
        this.billRespones.billDetail = response.billDetail
        // .map((billDetails:any) => {
        //   return {
        //     billDetailID: billDetails.billDetailID,
        //     wash: billDetails.wash,
        //     weight: billDetails.weight,
        //     price: billDetails.price,
        //     billDetailStatus: billDetails.billDetailStatus
        //   };
        // });    
        // if (response.image) {
        //   this.billResponse.image = `${environment.apiBaseUrl}/bill/image/${response.image}`;
        // }    
        // this.billRespones.image = response.image;
        // this.billRespones.billStatus = response.billStatus;   
        // console.log('Bill detail:', response.billDetail);
        debugger
      } else {
          console.error("Response data is undefined or null.");
          // Handle the error or set default values
      }
           
      },      
      complete: () => {
        debugger;        
      },
      error: (error: HttpErrorResponse) => {
        debugger;
        console.error(error?.error?.message ?? '');
      } 
    });
  }    
  
  saveBill(): void {    
    debugger        
    this.billService
      .updateBill(this.bill, new BillDTO(this.billRespones))
      .subscribe({
      next: (response: ApiResponse) => {
        debugger
        // Handle the successful update
        //console.log('Order updated successfully:', response);
        // Navigate back to the previous page
        //this.router.navigate(['/admin/orders']);       
        this.router.navigate(['../'], { relativeTo: this.route });
      },
      complete: () => {
        debugger;        
      },
      error: (error: HttpErrorResponse) => {
        debugger;
        console.error(error?.error?.message ?? '');
        this.router.navigate(['../'], { relativeTo: this.route });
      }       
    });   
  } 
}
