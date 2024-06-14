import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AboutUsComponent } from './about-us/about-us.component';
import { BillCheckoutComponent } from './bill-checkout/bill-checkout.component';
import { HomeLayoutComponent } from './home-layout/home-layout.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterationComponent } from './registeration/registeration.component';
import { HeaderComponent } from './static/header/header.component';
import { LoaderComponent } from './static/loader/loader.component';
import { OverlayMenuComponent } from './static/overlay-menu/overlay-menu.component';
import { SlidebarComponent } from './static/slidebar/slidebar.component';
import { ClientRoutingModule } from './client-routing.module';
import { IndexLayoutComponent } from './index-layout/index-layout.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { UserService } from '../service/user.service';



@NgModule({
  declarations: [
    HomeComponent,
    HomeLayoutComponent,
    AboutUsComponent,
    BillCheckoutComponent,
    LoginComponent,
    RegisterationComponent,
    LoaderComponent,
    OverlayMenuComponent,
    SlidebarComponent,
    HeaderComponent,
    IndexLayoutComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    ClientRoutingModule,
    HttpClientModule,
  ],
  providers: [
    UserService,
    // Các nhà cung cấp khác của bạn
  ],
})
export class ClientModule { }
