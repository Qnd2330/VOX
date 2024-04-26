import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { HeaderComponent } from './static/header/header.component';
import { LoaderComponent } from './static/loader/loader.component';
import { OverlayMenuComponent } from './static/overlay-menu/overlay-menu.component';
import { SlidebarComponent } from './static/slidebar/slidebar.component';
import { LoginComponent } from './login/login.component';
import { MainIndexComponent } from './main-index/main-index.component';
import { RegisterationComponent } from './registeration/registeration.component';
import { AboutUsComponent } from './about-us/about-us.component';
import { BillCheckoutComponent } from './bill-checkout/bill-checkout.component';
import { AppComponent } from './app.component';
import { IndexComponent } from './index/index.component';

@NgModule({
  declarations: [
    HeaderComponent,
    LoaderComponent,
    OverlayMenuComponent,
    SlidebarComponent,
    LoginComponent,
    MainIndexComponent,
    RegisterationComponent,
    AboutUsComponent,
    BillCheckoutComponent,
    AppComponent,
    IndexComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
  ],
  providers: [
    provideClientHydration()
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
  // bootstrap: [RegisterationComponent]
})
export class AppModule { }
