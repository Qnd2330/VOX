import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { ListDonGiatComponent } from './QLDonGiat/list-don-giat/list-don-giat.component';
import { ListGiatLaComponent } from './QLGiatLa/list-giat-la/list-giat-la.component';
import { ListKhoComponent } from './QLKho/list-kho/list-kho.component';
import { FormNhanVienComponent } from './QLNhanVien/form-nhan-vien/form-nhan-vien.component';
import { ListNhanVienComponent } from './QLNhanVien/list-nhan-vien/list-nhan-vien.component';
import { ListTaiKhoanComponent } from './QLTaiKhoan/list-tai-khoan/list-tai-khoan.component';
import { ListTTKHComponent } from './QLThongTinKH/list-ttkh/list-ttkh.component';
import { ListLichLamComponent } from './QLViecLam/list-lich-lam/list-lich-lam.component';
import { AdminRoutingModule } from './admin-routing.module';
import { AdminHeaderComponent } from './static/admin-header/admin-header.component';
import { AdminPreloaderComponent } from './static/admin-preloader/admin-preloader.component';
import { AdminSidebarComponent } from './static/admin-sidebar/admin-sidebar.component';
import { AdminFooterComponent } from './static/admin-footer/admin-footer.component';
import { UserService } from '../service/user.service';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { ThemDonGiatComponent } from './QLDonGiat/them-don-giat/them-don-giat.component';
import { CapNhatDonGiatComponent } from './QLDonGiat/cap-nhat-don-giat/cap-nhat-don-giat.component';
import { CapNhatChiTietDonGiatComponent } from './QLDonGiat/cap-nhat-chi-tiet-don-giat/cap-nhat-chi-tiet-don-giat.component';
import { ThemChiTietDonGiatComponent } from './QLDonGiat/them-chi-tiet-don-giat/them-chi-tiet-don-giat.component';
import { ThemGiatLaComponent } from './QLGiatLa/them-giat-la/them-giat-la.component';
import { CapNhatGiatLaComponent } from './QLGiatLa/cap-nhat-giat-la/cap-nhat-giat-la.component';
import { ThemTaiKhoanComponent } from './QLTaiKhoan/them-tai-khoan/them-tai-khoan.component';
import { CapNhatTaiKhoanComponent } from './QLTaiKhoan/cap-nhat-tai-khoan/cap-nhat-tai-khoan.component';
import { ThemKhoComponent } from './QLKho/them-kho/them-kho.component';
import { SuaKhoComponent } from './QLKho/sua-kho/sua-kho.component';
import { ListKhoKHComponent } from './QLKhoKhachHang/list-kho-kh/list-kho-kh.component';
import { ThemKhoKHComponent } from './QLKhoKhachHang/them-kho-kh/them-kho-kh.component';
import { CapNhatKhoKhComponent } from './QLKhoKhachHang/cap-nhat-kho-kh/cap-nhat-kho-kh.component';



@NgModule({
  declarations: [
    AdminHomeComponent,
    ListDonGiatComponent,
    ThemDonGiatComponent,
    CapNhatDonGiatComponent,
    ThemChiTietDonGiatComponent,
    CapNhatChiTietDonGiatComponent,
    ListGiatLaComponent,
    ThemGiatLaComponent,
    CapNhatGiatLaComponent,
    ListKhoComponent,
    ListNhanVienComponent,
    FormNhanVienComponent,
    ListTaiKhoanComponent,
    ListTTKHComponent,
    ListLichLamComponent,
    AdminHeaderComponent,
    AdminPreloaderComponent,
    AdminSidebarComponent,
    AdminFooterComponent,
    ThemTaiKhoanComponent,
    CapNhatTaiKhoanComponent,
    ThemKhoComponent,
    SuaKhoComponent,
    ListKhoKHComponent,
    ThemKhoKHComponent,
    CapNhatKhoKhComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    HttpClientModule,
    FormsModule 
  ],
  providers: [
    UserService,
    // Các nhà cung cấp khác của bạn
  ],
})
export class AdminModule { }
