import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { AppModule } from './app/app.module';
import { provideHttpClient, withFetch } from '@angular/common/http';
import { StaticProvider } from '@angular/core';


platformBrowserDynamic().bootstrapModule(AppModule, {
  providers: [
    provideHttpClient(withFetch()) as unknown as StaticProvider // Ép kiểu sang unknown trước khi ép kiểu sang StaticProvider
  ]
}).catch(err => console.error(err));
