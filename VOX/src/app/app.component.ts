import { Component } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'TEST';
  // showHeader = true; // Default to show header

  // constructor(private router: Router) {}

  // ngOnInit() {
  //   this.router.events.subscribe(event => {
  //     if (event instanceof NavigationEnd) {
  //       this.showHeader = event.urlAfterRedirects !== '/home-layout';
  //     }
  //   });
  // }
}
