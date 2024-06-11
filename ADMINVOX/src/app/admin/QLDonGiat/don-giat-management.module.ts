import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { ThemDonGiatComponent } from './them-don-giat/them-don-giat.component';
import { CapNhatDonGiatComponent } from './cap-nhat-don-giat/cap-nhat-don-giat.component';
import { ThemChiTietDonGiatComponent } from './them-chi-tiet-don-giat/them-chi-tiet-don-giat.component';
import { CapNhatChiTietDonGiatComponent } from './cap-nhat-chi-tiet-don-giat/cap-nhat-chi-tiet-don-giat.component';



@NgModule({
  declarations: [
  ],
  imports: [
    CommonModule,
    HttpClientModule
  ]
})
export class DonGiatManagementModule { }
