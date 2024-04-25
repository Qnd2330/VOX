import { NgModule } from '@angular/core';
import { ServerModule } from '@angular/platform-server';

import { AppModule } from './app.module';
import { MainIndexComponent } from './main-index/main-index.component';
import { RegisterationComponent } from './registeration/registeration.component';
import { AboutUsComponent } from './about-us/about-us.component';
import { BillCheckoutComponent } from './bill-checkout/bill-checkout.component';
import { LoginComponent } from './login/login.component';

@NgModule({
  imports: [
    AppModule,
    ServerModule,
  ],
  bootstrap: [MainIndexComponent],
})
export class AppServerModule {}
