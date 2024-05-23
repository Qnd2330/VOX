import { Component, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';

@Component({
  selector: 'app-index-layout',
  templateUrl: './index-layout.component.html',
  styleUrl: './index-layout.component.css',
})
export class IndexLayoutComponent {
  showHeader: boolean = true;

  constructor(private router: Router) {}

  ngOnInit() {
    this.checkHeaderVisibility();
    // Lắng nghe sự kiện thay đổi URL từ Router
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.checkHeaderVisibility(); // Kiểm tra lại tính hiển thị của header khi URL thay đổi
      }
    });
  }


  private checkHeaderVisibility() {
    if (this.router.url === '/home') {
      this.showHeader = false;
    } else {
      this.showHeader = true;
    }
  }
}
