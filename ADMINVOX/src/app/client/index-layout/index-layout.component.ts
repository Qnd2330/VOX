import { Component, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import $ from "jquery";


@Component({
  selector: 'app-index-layout',
  templateUrl: './index-layout.component.html',
  styleUrl: './index-layout.component.css',
})
export class IndexLayoutComponent {
  showHeader: boolean = true;

  constructor(private router: Router) { }

  ngOnInit() {
    this.checkHeaderVisibility();
    // Lắng nghe sự kiện thay đổi URL từ Router
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.checkHeaderVisibility(); // Kiểm tra lại tính hiển thị của header khi URL thay đổi
      }
    });

    $("#loader").fadeOut(1000);

    /*===================================
//Side Menu
======================================*/
$("#sidemenu_toggle").on("click", function () {

  $(".side-menu-nav").removeClass('opacity-control');
  $(".side-bar").removeClass("remove_side_bar");
  $(".side-menu-nav").addClass("open-side-menu");
  $(".side-menu-nav nav").addClass("nav-styling");

});

/*===================================
//Side Menu on Mobile
======================================*/
$("#sidemenu_toggle1").on("click", function () {
  $(".side-menu-nav").removeClass('opacity-control');
  $(".side-bar").removeClass("remove_side_bar");
  $(".side-menu-nav").addClass("open-side-menu");
  $(".side-menu-nav nav").addClass("nav-styling");

});



/*===================================
//Side Menu Close
======================================*/
$("#close-side-menu-nav").on("click", function () {

  $(".open-side-menu").addClass('opacity-control');
  setTimeout(function () {
      $(".side-bar").addClass("remove_side_bar");
      $(".side-menu-nav").removeClass("open-side-menu");
      // remove class of visability hidden of main menu
      $(".nav-appear").removeClass("main-nav-hide");

  }, 100);

  //animation remove sub menu
  $(".sub-menu-nav-appear").removeClass("submenu-disappear");

  //remove class of visiabilty of sub menu
  $(".submenu-overlay").removeClass("Demo-submenu-visible");


});

/*===================================
//Đóng overlay menu khi người dùng click vào một đường link trong menu
======================================*/
$(".side-bar .nav-item .nav-link").on("click", function (event) {
  // Đóng overlay menu
  $(".open-side-menu").addClass('opacity-control');
  setTimeout(function () {
      $(".side-bar").addClass("remove_side_bar");
      $(".side-menu-nav").removeClass("open-side-menu");
      // remove class of visability hidden of main menu
      $(".nav-appear").removeClass("main-nav-hide");

  }, 100);

  //animation remove sub menu
  $(".sub-menu-nav-appear").removeClass("submenu-disappear");

  //remove class of visiabilty of sub menu
  $(".submenu-overlay").removeClass("Demo-submenu-visible");
});



/*===================================
//Sub Menu Open
======================================*/
$(".side-bar .nav-item .nav-link").on("click", function (event) {

  var x = event.target.id;

  if (x == "") {

  }
  else {
      $(".side-menu-nav nav").removeClass("nav-styling");
      $(".nav-appear").addClass("nav-disappear");
      setTimeout(function () {
          $(".nav-appear").addClass("main-nav-hide");
          $("#" + x + " " + "~div.submenu-overlay").addClass("Demo-submenu-visible");
      }, 200);
      setTimeout(function () {
          $(".nav-appear").removeClass("nav-disappear");
      }, 1000);

  }

});

/*===================================
// GO back on Sub Menu
======================================*/
$('.go-back-btn').on('click', function () {


  setTimeout(function () {
      $(".nav-appear").removeClass("main-nav-hide"); // main nav show
      $(".side-menu-nav nav").addClass("nav-styling");
  }, 300);

  $(".Demo-submenu-visible .sub-menu-nav-appear").addClass("submenu-disappear"); // animation sub menu zoom out .6 sec

  $(".submenu-overlay").removeClass("Demo-submenu-visible");  // sub menu hidden

  setTimeout(function () {
      $(".sub-menu-nav-appear").removeClass("submenu-disappear");
  }, 700);

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
