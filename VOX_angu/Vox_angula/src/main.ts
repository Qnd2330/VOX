import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { MainIndexComponent } from './app/main-index/main-index.component';


platformBrowserDynamic().bootstrapModule(MainIndexComponent)
  .catch(err => console.error(err));
