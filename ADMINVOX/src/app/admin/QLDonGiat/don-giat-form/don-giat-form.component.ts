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
  billID :number = 0;
  bill: Bill;
  updatedBill: Bill;
  // billRespones: BillRespones = {
  //   billID: 0, // Hoặc bất kỳ giá trị số nào bạn muốn
  //   userID: 0,
  //   userName: '',
  //   billDescription: '',
  //   sumWeight: 0,
  //   cost: 0,
  //   billCreateDate: new Date(),
  //   billStatus: true,
  //   billPayDate: new Date(),
  //   image: '', 
  //   billDetail: [],
  // };  
  private billService = inject(BillService);
  constructor(    
    private route: ActivatedRoute,
    private router: Router
    ) {
      this.bill = {} as Bill;
      this.updatedBill = {} as Bill;
    }

  ngOnInit() {
    this.getBillDetails(this.currentPage, this.itemsPerPage);
    
  }
  
  getBillDetails(page: number, limit: number) {
    debugger
    this.billID = Number(this.route.snapshot.paramMap.get('id'));
    this.billService.getBillById(this.billID, page, limit).subscribe({
      next: (apiResponse: any) => {  
        this.bill = apiResponse.billRespones;
        this.updatedBill = { ...apiResponse.billRespones };                
        // this.updatedBill.product_images.forEach((product_image:ProductImage) => {
        //   product_image.image_url = `${environment.apiBaseUrl}/products/images/${product_image.image_url}`;
        // });
      },
      complete: () => {
        
      },
      error: (error: HttpErrorResponse) => {
        debugger;
        console.error(error?.error?.message ?? '');
        // const response = apiResponse
        // if (response) {
        //   this.bill = response.billRespones;
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
        // debugger;      
        // this.bill.billDetail = response.billDetail
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
    //     debugger
    //   } else {
    //       console.error("Response data is undefined or null.");
    //       // Handle the error or set default values
    //   }
           
    //   },      
    //   complete: () => {
    //     debugger;        
    //   },
    //   error: (error: HttpErrorResponse) => {
    //     debugger;
    //     console.error(error?.error?.message ?? '');
      } 
    });
  }    
  
  saveBill(): void {   
    
    const updateBillDTO: UpdateBillDTO = {
      billID: this.updatedBill.billID,
      userID: this.updatedBill.userID,
      userName: this.updatedBill.userName,
      billDescription: this.updatedBill.billDescription,
      sumWeight: this.updatedBill.sumWeight,
      cost: this.updatedBill.cost,
      billCreateDate: this.updatedBill.billCreateDate,
      billStatus: this.updatedBill.billStatus,
      billPayDate: this.updatedBill.billPayDate,
      image: this.updatedBill.image,
    }; 
    debugger        
    this.billService.updateBill(this.bill.billID, updateBillDTO).subscribe({
      next: (apiResponse: ApiResponse) => {  
        debugger        
      },
      complete: () => {
        debugger;
        this.router.navigate(['/admin/qldg']);        
      },
      error: (error: HttpErrorResponse) => {
        debugger;
        console.error(error?.error?.message ?? '');
      } 
    });  
  }
}
