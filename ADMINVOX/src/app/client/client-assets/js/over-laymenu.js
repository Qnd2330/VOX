document.addEventListener('DOMContentLoaded', function() {
    // Lắng nghe sự kiện click vào các phần tử dẫn chuyển trang
    var links = document.querySelectorAll('.nav-link');
    links.forEach(function(link) {
        link.addEventListener('click', function() {
            // Đóng overlay menu
            closeOverlayMenu();
        });
    });
});

function closeOverlayMenu() {
    // Lấy phần tử overlay menu
    var overlayMenu = document.querySelector('.side-menu-nav');
    // Đóng menu bằng cách thêm hoặc xóa lớp hiển thị
    overlayMenu.classList.remove('show');
}
