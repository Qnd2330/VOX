import { AfterViewInit, Component, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { DynamicScriptLoaderService } from '../../service/dynamicscriptloader.service';


@Component({
  selector: 'app-index-layout',
  templateUrl: './index-layout.component.html',
  styleUrl: './index-layout.component.css',
})
export class IndexLayoutComponent implements AfterViewInit {
  showHeader: boolean = true;

  constructor(private router: Router, private dynamicScriptLoader: DynamicScriptLoaderService) { }

  ngAfterViewInit(): void {
    const styles = [
      'assets/vendor/css/bundle.min.css',
      'assets/vendor/css/jquery.fancybox.min.css',
      'assets/vendor/css/owl.carousel.min.css',
      'assets/vendor/css/swiper.min.css',
      'assets/vendor/css/cubeportfolio.min.css',
      'assets/vendor/css/wow.css',
      'assets/vendor/css/LineIcons.min.css',
      'assets/css/style.css'
    ];

    const scripts = [
      'https://code.jquery.com/jquery-3.6.0.min.js', // Đảm bảo jQuery được tải trước
      'assets/vendor/js/owl.carousel.min.js', // Owl Carousel cần được tải sau jQuery
      'assets/vendor/js/jquery.fancybox.min.js', // FancyBox cần được tải sau jQuery
      'assets/js/script.js',
      'assets/js/over-laymenu.js',
      'assets/vendor/js/swiper.min.js',
      'assets/vendor/js/jquery.cubeportfolio.min.js',
      'assets/vendor/js/parallaxie.min.js',
      'assets/vendor/js/wow.min.js',
      'assets/vendor/js/bundle.min.js',
    ];

    this.dynamicScriptLoader.loadStyles(styles);
    this.dynamicScriptLoader.loadScriptsSequentially(scripts).then(() => {
      console.log('All scripts loaded');
      // this.initializeExternalScripts();
    }).catch(error => console.error(error));
  }

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
