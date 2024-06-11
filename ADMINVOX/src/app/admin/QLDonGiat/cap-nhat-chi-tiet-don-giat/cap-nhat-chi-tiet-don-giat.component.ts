import { Component, inject } from '@angular/core';
import { BillDetail } from '../../../models/billdetail';
import { BilldetailsService } from '../../../service/billdetails.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Bill } from '../../../models/bill';
import { HttpErrorResponse } from '@angular/common/http';
import { WashingmethodService } from '../../../service/washingmethod.service';
import { Wash } from '../../../models/wash_method';
import { UpdateBillDetailDTO } from '../../../dtos/billdetail/update.billdetail.dtos';
import { ApiResponse } from '../../../responses/api.response';

@Component({
  selector: 'app-cap-nhat-chi-tiet-don-giat',
  templateUrl: './cap-nhat-chi-tiet-don-giat.component.html',
  styleUrl: './cap-nhat-chi-tiet-don-giat.component.css'
})
export class CapNhatChiTietDonGiatComponent {
  currentPage: number = 0;
  itemsPerPage: number = 12;
  pages: number[] = [];
  totalPages: number = 0;
  visiblePages: number[] = [];
  
  billDetailID: number = 0;
  billDetail: BillDetail;
  updatedBillDetail: BillDetail;
  washes: Wash[] = [];

  private billDetailsService = inject(BilldetailsService);
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private washService: WashingmethodService,
  ) {
    this.billDetail = {} as BillDetail;
    this.updatedBillDetail = {} as BillDetail;
  }

  ngOnInit() {
    this.getBillDetails();
    this.getWashes(this.currentPage, this.itemsPerPage);
  }

  getBillDetails() {
    debugger
    this.billDetailID = Number(this.route.snapshot.paramMap.get('id'));
    this.billDetailsService.getBillDetailByID(this.billDetailID).subscribe({
      next: (apiResponse: any) => {  
        this.billDetail = apiResponse;
        this.updatedBillDetail = { ...apiResponse }; 
        console.log(this.billDetail);              
        // this.updatedBill.product_images.forEach((product_image:ProductImage) => {
        //   product_image.image_url = `${environment.apiBaseUrl}/products/images/${product_image.image_url}`;
        // });
      },
      complete: () => {
        
      },
      error: (error: HttpErrorResponse) => {
        debugger;
        console.error(error?.error?.message ?? '');
      } 
    });
  }   

  saveBillDetail(): void {   
    
    const updateBillDetailDTO: UpdateBillDetailDTO = {
      billDetailID: this.updatedBillDetail.billDetailID,
      billID: this.updatedBillDetail.billID,
      washID: this.updatedBillDetail.washID,

      description: this.updatedBillDetail.description,
      weight: this.updatedBillDetail.weight,
      price: this.updatedBillDetail.price,
      billDetailStatus: this.updatedBillDetail.billDetailStatus,
    }; 
    debugger        
    this.billDetailsService.updateBillDetail(this.billDetail.billDetailID, updateBillDetailDTO).subscribe({
      next: (apiResponse: ApiResponse) => {  
        debugger        
      },
      complete: () => {
        debugger;
        this.router.navigate([`/admin/qldg/view/${this.updatedBillDetail.billID}`]);        
      },
      error: (error: HttpErrorResponse) => {
        debugger;
        console.error(error?.error?.message ?? '');
        this.router.navigate([`/admin/qldg/view/${this.updatedBillDetail.billID}`]);
      } 
    });  
  }

  getWashes(page: number, limit: number) {
    debugger
    this.washService.getAllWashes(page, limit).subscribe({
      next: (apiResponse: any) => {
        debugger;
        this.washes = apiResponse.washingMethodResponeList;
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
}
