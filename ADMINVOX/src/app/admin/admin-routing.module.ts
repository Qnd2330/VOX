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
import { CapNhatDonGiatComponent } from './QLDonGiat/cap-nhat-don-giat/cap-nhat-don-giat.component';
import { ThemDonGiatComponent } from './QLDonGiat/them-don-giat/them-don-giat.component';
import { ThemChiTietDonGiatComponent } from './QLDonGiat/them-chi-tiet-don-giat/them-chi-tiet-don-giat.component';
import { CapNhatChiTietDonGiatComponent } from './QLDonGiat/cap-nhat-chi-tiet-don-giat/cap-nhat-chi-tiet-don-giat.component';
import { CapNhatGiatLaComponent } from './QLGiatLa/cap-nhat-giat-la/cap-nhat-giat-la.component';
import { ThemGiatLaComponent } from './QLGiatLa/them-giat-la/them-giat-la.component';
import { ThemTaiKhoanComponent } from './QLTaiKhoan/them-tai-khoan/them-tai-khoan.component';
import { CapNhatTaiKhoanComponent } from './QLTaiKhoan/cap-nhat-tai-khoan/cap-nhat-tai-khoan.component';


const routes: Routes = [
  {path:'', pathMatch:'full', redirectTo:''},
  {
    path: '', component: AdminHomeComponent,
    children: [
      { path: '', pathMatch: 'full', redirectTo: 'qltk' },
      //Tài khoản
      { path: 'qltk', component: ListTaiKhoanComponent, title: "QL Tài Khoản" },
      { path: 'qltk/insert', component: ThemTaiKhoanComponent, title: "Thêm Tài Khoản" },
      { path: 'qltk/update/:id', component: CapNhatTaiKhoanComponent, title: "Cập Nhật Tài Khoản" },
      //Nhân viên
      { path: 'qlnv', component: ListNhanVienComponent, title: "QL Nhân Viên" },
      //Lịch làm
      { path: 'qlvl', component: ListLichLamComponent, title: "QL Lịch Làm" },
      //Đơn Giặt
      { path: 'qldg', component: ListDonGiatComponent, title: "QL Đơn Giặt" },
      { path: 'qldg/view/:id', component: CapNhatDonGiatComponent, title: "Chi Tiết Đơn Giặt" },
      { path: 'qldg/them', component: ThemDonGiatComponent, title: "Thêm mới Đơn Giặt" },
      { path: 'qldg/view/:id/them', component: ThemChiTietDonGiatComponent, title: "Thêm mới Chi tiết Đơn Giặt" },
      { path: 'qldg/view/:billID/update/:id', component: CapNhatChiTietDonGiatComponent, title: "Cập Nhật Chi Tiết Đơn Giặt" },
      //Thông tin khách hàng
      { path: 'qlttkh', component: ListTTKHComponent, title: "QL Thông Tin KH" },
      //Kho
      { path: 'qlk', component: ListKhoComponent, title: "QL Kho" },
      //Kiểu giặt
      { path: 'qlgl', component: ListGiatLaComponent, title: "QL Giặt Là" },
      { path: 'qlgl/update/:id', component: CapNhatGiatLaComponent, title: "Cập Nhật Kiểu Giặt" },
      { path: 'qlgl/insert', component: ThemGiatLaComponent, title: "Thêm Mới Kiểu Giặt" },
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
