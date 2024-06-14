import { Injectable, Inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class DynamicScriptLoaderService {
  constructor(@Inject(PLATFORM_ID) private platformId: Object) {}

  loadScripts(scripts: string[]): Promise<void[]> {
    if (isPlatformBrowser(this.platformId)) {
      const promises = scripts.map(script => this.loadScript(script));
      return Promise.all(promises);
    } else {
      return Promise.resolve([]);
    }
  }

  loadScriptsSequentially(scripts: string[]): Promise<void> {
    if (isPlatformBrowser(this.platformId)) {
      return scripts.reduce((promise, script) => {
        return promise.then(() => this.loadScript(script));
      }, Promise.resolve());
    } else {
      return Promise.resolve();
    }
  }

  private loadScript(src: string): Promise<void> {
    if (isPlatformBrowser(this.platformId)) {
      return new Promise((resolve, reject) => {
        const script = document.createElement('script');
        script.src = src;
        script.onload = () => resolve();
        script.onerror = () => reject(`Script load error for ${src}`);
        document.body.appendChild(script);
      });
    } else {
      return Promise.resolve();
    }
  }

  loadStyles(styles: string[]): void {
    if (isPlatformBrowser(this.platformId)) {
      styles.forEach(style => {
        const link = document.createElement('link');
        link.href = style;
        link.rel = 'stylesheet';
        document.head.appendChild(link);
      });
    }
  }
}
