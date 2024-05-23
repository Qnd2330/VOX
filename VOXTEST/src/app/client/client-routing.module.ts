import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { IndexLayoutComponent } from './index-layout/index-layout.component';
import { HomeLayoutComponent } from './home-layout/home-layout.component';
import { AboutUsComponent } from './about-us/about-us.component';
import { BillCheckoutComponent } from './bill-checkout/bill-checkout.component';
import { LoginComponent } from './login/login.component';
import { RegisterationComponent } from './registeration/registeration.component';


const routes: Routes = [
  {path:'', pathMatch:'full', redirectTo:''},
  {
    path: '', component: IndexLayoutComponent,
    children: [
      { path: '', pathMatch: 'full', redirectTo: 'home' },
      { path: 'home', component: HomeLayoutComponent, title: "Trang chủ" },
      { path: 'login', component: LoginComponent, title: "Đăng nhập" },
      { path: 'registeration', component: RegisterationComponent, title: "Đăng ký" },
      { path: 'about-us', component: AboutUsComponent, title: "Thông tin" },
      { path: 'bill-checkout', component: BillCheckoutComponent, title: "Đặt đơn" },
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientRoutingModule { }
