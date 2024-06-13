import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IndexLayoutComponent } from './index-layout/index-layout.component';
import { HomeLayoutComponent } from './home-layout/home-layout.component';
import { AboutUsComponent } from './about-us/about-us.component';
import { BillCheckoutComponent } from './bill-checkout/bill-checkout.component';
import { LoginComponent } from './login/login.component';
import { RegisterationComponent } from './registeration/registeration.component';

const routes: Routes = [
  {
    path: '',
    component: IndexLayoutComponent,
    children: [
      { path: '', pathMatch: 'full', redirectTo: 'home' },
      { path: 'home', component: HomeLayoutComponent, data: { title: 'Trang chủ' } },
      { path: 'login', component: LoginComponent, data: { title: 'Đăng nhập' } },
      { path: 'registeration', component: RegisterationComponent, data: { title: 'Đăng ký' } },
      { path: 'about-us', component: AboutUsComponent, data: { title: 'Thông tin' } },
      { path: 'bill-checkout', component: BillCheckoutComponent, data: { title: 'Đặt đơn' } },
      // { path: '**', redirectTo: '/login' } // Chuyển hướng mặc định cho các URL không hợp lệ
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientRoutingModule { }
