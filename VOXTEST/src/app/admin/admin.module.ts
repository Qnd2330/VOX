import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { ChiTietDonGiatFormComponent } from './QLDonGiat/chi-tiet-don-giat-form/chi-tiet-don-giat-form.component';
import { DonGiatFormComponent } from './QLDonGiat/don-giat-form/don-giat-form.component';
import { ListChiTietDonGiatComponent } from './QLDonGiat/list-chi-tiet-don-giat/list-chi-tiet-don-giat.component';
import { ListDonGiatComponent } from './QLDonGiat/list-don-giat/list-don-giat.component';
import { FormGiatLaComponent } from './QLGiatLa/form-giat-la/form-giat-la.component';
import { ListGiatLaComponent } from './QLGiatLa/list-giat-la/list-giat-la.component';
import { FormKhoComponent } from './QLKho/form-kho/form-kho.component';
import { ListKhoComponent } from './QLKho/list-kho/list-kho.component';
import { FormNhanVienComponent } from './QLNhanVien/form-nhan-vien/form-nhan-vien.component';
import { ListNhanVienComponent } from './QLNhanVien/list-nhan-vien/list-nhan-vien.component';
import { FormTaiKhoanComponent } from './QLTaiKhoan/form-tai-khoan/form-tai-khoan.component';
import { ListTaiKhoanComponent } from './QLTaiKhoan/list-tai-khoan/list-tai-khoan.component';
import { FormTTKHComponent } from './QLThongTinKH/form-ttkh/form-ttkh.component';
import { ListTTKHComponent } from './QLThongTinKH/list-ttkh/list-ttkh.component';
import { ListLichLamComponent } from './QLViecLam/list-lich-lam/list-lich-lam.component';
import { AdminRoutingModule } from './admin-routing.module';
import { AdminHeaderComponent } from './static/admin-header/admin-header.component';
import { AdminPreloaderComponent } from './static/admin-preloader/admin-preloader.component';
import { AdminSidebarComponent } from './static/admin-sidebar/admin-sidebar.component';



@NgModule({
  declarations: [
    AdminHomeComponent,
    ListDonGiatComponent,
    DonGiatFormComponent,
    ListChiTietDonGiatComponent,
    ChiTietDonGiatFormComponent,
    ListGiatLaComponent,
    FormGiatLaComponent,
    ListKhoComponent,
    FormKhoComponent,
    ListNhanVienComponent,
    FormNhanVienComponent,
    ListTaiKhoanComponent,
    FormTaiKhoanComponent,
    ListTTKHComponent,
    FormTTKHComponent,
    ListLichLamComponent,
    AdminHeaderComponent,
    AdminPreloaderComponent,
    AdminSidebarComponent,
  ],
  imports: [
    CommonModule,
    AdminRoutingModule
  ]
})
export class AdminModule { }
