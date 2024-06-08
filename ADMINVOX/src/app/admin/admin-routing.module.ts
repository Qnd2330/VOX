import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { ListTaiKhoanComponent } from './QLTaiKhoan/list-tai-khoan/list-tai-khoan.component';
import { ListNhanVienComponent } from './QLNhanVien/list-nhan-vien/list-nhan-vien.component';
import { ListLichLamComponent } from './QLViecLam/list-lich-lam/list-lich-lam.component';
import { ListDonGiatComponent } from './QLDonGiat/list-don-giat/list-don-giat.component';
import { ListTTKHComponent } from './QLThongTinKH/list-ttkh/list-ttkh.component';
import { ListKhoComponent } from './QLKho/list-kho/list-kho.component';
import { ListGiatLaComponent } from './QLGiatLa/list-giat-la/list-giat-la.component';
import { DonGiatFormComponent } from './QLDonGiat/don-giat-form/don-giat-form.component';
import { ChiTietDonGiatFormComponent } from './QLDonGiat/chi-tiet-don-giat-form/chi-tiet-don-giat-form.component';


const routes: Routes = [
  {path:'', pathMatch:'full', redirectTo:''},
  {
    path: '', component: AdminHomeComponent,
    children: [
      { path: '', pathMatch: 'full', redirectTo: 'qltk' },
      { path: 'qltk', component: ListTaiKhoanComponent, title: "QL Tài Khoản" },
      { path: 'qlnv', component: ListNhanVienComponent, title: "QL Nhân Viên" },
      { path: 'qlvl', component: ListLichLamComponent, title: "QL Lịch Làm" },
      { path: 'qldg', component: ListDonGiatComponent, title: "QL Đơn Giặt" },
      { path: 'qldg/view/:id', component: DonGiatFormComponent, title: "Chi Tiết Đơn Giặt" },
      { path: 'qldg/them', component: ChiTietDonGiatFormComponent, title: "Thêm mới Đơn Giặt" },
      { path: 'qlttkh', component: ListTTKHComponent, title: "QL Thông Tin KH" },
      { path: 'qlk', component: ListKhoComponent, title: "QL Kho" },
      { path: 'qlgl', component: ListGiatLaComponent, title: "QL Giặt Là" },
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
