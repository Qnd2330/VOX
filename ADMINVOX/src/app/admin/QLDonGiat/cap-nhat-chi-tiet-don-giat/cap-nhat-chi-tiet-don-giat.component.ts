import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BillDetail } from '../../../models/billdetail';
import { BilldetailsService } from '../../../service/billdetails.service';
import { WashingmethodService } from '../../../service/washingmethod.service';
import { Wash } from '../../../models/wash_method';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-cap-nhat-chi-tiet-don-giat',
  templateUrl: './cap-nhat-chi-tiet-don-giat.component.html',
  styleUrls: ['./cap-nhat-chi-tiet-don-giat.component.css']
})
export class CapNhatChiTietDonGiatComponent implements OnInit {
  currentPage: number = 0;
  itemsPerPage: number = 12;
  billDetailID: number = 0;
  updatedBillDetail: BillDetail = {} as BillDetail;
  washes: Wash[] = [];
  dataLoaded: boolean = false; // Add a flag to track data loading

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private billDetailService: BilldetailsService,
    private washService: WashingmethodService
  ) {}

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.billDetailID = Number(params.get('id'));
      this.getBillDetails();
    });
    this.getWashes(this.currentPage, this.itemsPerPage);
  }

  getBillDetails() {
    this.billDetailService.getBillDetailByID(this.billDetailID).subscribe({
      next: (data: BillDetail) => {
        this.updatedBillDetail = data;
        this.dataLoaded = true; // Set the flag to true when data is loaded
        this.updateWashID();
      },
      error: (error: HttpErrorResponse) => {
        console.error('Error fetching bill details:', error.message);
      }
    });
  }

  updateWashID() {
    // Find the corresponding Wash object based on washName
    const correspondingWash = this.washes.find(wash => wash.washName === this.updatedBillDetail.washName);
    if (correspondingWash) {
      this.updatedBillDetail.washID = correspondingWash.washID;
    }
  }

  saveBillDetail() {
    this.billDetailService.updateBillDetail(this.billDetailID, this.updatedBillDetail).subscribe({
      next: () => {
        this.router.navigate([`/admin/qldg/view/${this.updatedBillDetail.billID}`]);
      },
      error: (error: HttpErrorResponse) => {
        console.error('Error updating bill detail:', error.message);
        this.router.navigate([`/admin/qldg/view/${this.updatedBillDetail.billID}`]);
      }
    });
  }

  getWashes(page: number, limit: number) {
    this.washService.getAllWashes(page, limit).subscribe({
      next: (apiResponse: any) => {
        this.washes = apiResponse.washingMethodResponeList;
      },
      error: (error: HttpErrorResponse) => {
        console.error('Error fetching washes:', error.message);
      }
    });
  }

  onWashChange(washID: number): void {
    this.updatedBillDetail.washID = washID;
  }
}
