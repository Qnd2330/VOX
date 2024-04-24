import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { LoginComponent } from './app/login/login.component';
import { AppModule } from './app/app.module';


platformBrowserDynamic().bootstrapModule(LoginComponent)
  .catch(err => console.error(err));
