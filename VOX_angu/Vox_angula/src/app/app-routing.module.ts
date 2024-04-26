import { NgModule } from '@angular/core';
import { RouterModule, Routes, RouterLink } from '@angular/router';
import { MainIndexComponent } from './main-index/main-index.component';
import { LoginComponent } from './login/login.component';
import { RegisterationComponent } from './registeration/registeration.component';
import { AboutUsComponent } from './about-us/about-us.component';
import { BillCheckoutComponent } from './bill-checkout/bill-checkout.component';
import { title } from 'process';
import { IndexComponent } from './index/index.component';

const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: 'index'},
  {path: 'index', component: IndexComponent, title: "Trang chủ" },
  {path: 'login', component: LoginComponent, title:"Đăng nhập"},
  {path: 'registeration', component: RegisterationComponent, title:"Đăng ký"},
  {path: 'about-us', component: AboutUsComponent, title:"Thông tin"},
  {path: 'bill-checkout', component: BillCheckoutComponent, title:"Đặt đơn"},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
