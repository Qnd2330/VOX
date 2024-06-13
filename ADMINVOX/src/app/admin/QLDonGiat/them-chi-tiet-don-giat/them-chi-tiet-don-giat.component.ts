import { Component, OnInit } from '@angular/core';
import { InsertBillDetailDTO } from '../../../dtos/billdetail/insert.billdetail.dtos';
import { ActivatedRoute, Router } from '@angular/router';
import { BilldetailsService } from '../../../service/billdetails.service';
import { Wash } from '../../../models/wash_method';
import { WashingmethodService } from '../../../service/washingmethod.service';
import { HttpErrorResponse } from '@angular/common/http';
import { BillDetail } from '../../../models/billdetail';

@Component({
  selector: 'app-them-chi-tiet-don-giat',
  templateUrl: './them-chi-tiet-don-giat.component.html',
  styleUrl: './them-chi-tiet-don-giat.component.css',
})
export class ThemChiTietDonGiatComponent implements OnInit {
  currentPage: number = 0;
  itemsPerPage: number = 12;
  billID: number = 0;
  insertBillDetailDTO: InsertBillDetailDTO = {
    billID: 0,
    washID: 1,
    description: 'Hãy viết những ghi chú',
    weight: 0,
    billDetailStatus: false,
  };
  billDetail: BillDetail[] = [];
  washes: Wash[] = [];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private billDetailService: BilldetailsService,
    private washService: WashingmethodService,
  ) {
  }
  ngOnInit() {
    debugger
    if (typeof localStorage !== 'undefined') {
      this.currentPage = Number(localStorage.getItem('currentBillAdminPage')) || 0;
    }
    this.getWashes(this.currentPage, this.itemsPerPage); // Gọi hàm loadWashes khi component được khởi tạo
  }
  insertBillDetail(): void {
    debugger;
    this.billID = Number(this.route.snapshot.paramMap.get('id'));
    debugger;

    // Gọi service để gửi yêu cầu HTTP với insertBillDTO
    this.billDetailService.insertBillDetail(this.billID, this.insertBillDetailDTO).subscribe({
      next: () => {
        debugger;
        this.router.navigate([`/admin/qldg/view/${this.billID}`]);
      },
      error: (error) => {
        debugger;
        console.error(error);
        this.router.navigate([`/admin/qldg/view/${this.billID}`]);
        // alert(error);
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
  extractBillIDFromURL() {
    this.route.params.subscribe(params => {
      const billID = params['id']; // Lấy billID từ route parameters
      if (billID) {
        this.insertBillDetailDTO.billID = billID; // Gán billID vào insertBillDetailDTO
      }
    });
  }
}
