import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { HeaderComponent } from './static/header/header.component';
import { LoaderComponent } from './static/loader/loader.component';
import { OverlayMenuComponent } from './static/overlay-menu/overlay-menu.component';
import { SlidebarComponent } from './static/slidebar/slidebar.component';
import { LoginComponent } from './login/login.component';

@NgModule({
  declarations: [
    HeaderComponent,
    LoaderComponent,
    OverlayMenuComponent,
    SlidebarComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [
    provideClientHydration()
  ],
  bootstrap: [LoginComponent]
})
export class AppModule { }
