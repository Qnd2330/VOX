import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { MainIndexComponent } from './app/main-index/main-index.component';
import { LoginComponent } from './app/login/login.component';
import { AppComponent } from './app/app.component';


platformBrowserDynamic().bootstrapModule(AppComponent)
  .catch(err => console.error(err));

