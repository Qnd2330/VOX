import { NgModule } from '@angular/core';
import { ServerModule } from '@angular/platform-server';

import { AppModule } from './app.module';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';

@NgModule({
  imports: [
    AppModule,
    ServerModule,
    AppRoutingModule
  ],
  bootstrap: [AppComponent],
})
export class AppServerModule {}
