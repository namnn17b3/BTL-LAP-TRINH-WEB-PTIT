var body = document.querySelector('body');
var dangNhapTenSanPham = false;

var gioHang = document.querySelector('.gio-hang');
gioHang.onmousemove = () => {
    var gioHangHieuUngMo = document.querySelector('.gio-hang-hieu-ung-mo');
    gioHangHieuUngMo.style.backgroundColor = '#fffac2';
    gioHangHieuUngMo.style.opacity = 0.3;
}

gioHang.onmouseleave = () => {
    var gioHangHieuUngMo = document.querySelector('.gio-hang-hieu-ung-mo');
    gioHangHieuUngMo.style.backgroundColor = 'transparent';
    gioHangHieuUngMo.style.opacity = 1;
}

var timKiem = document.querySelector('.tim-kiem');
timKiem.onmousemove = () => {
    var timKiemHieuUngMo = document.querySelector('.tim-kiem-hieu-ung-mo');
    timKiemHieuUngMo.style.backgroundColor = '#fffac2';
    timKiemHieuUngMo.style.opacity = 0.3;
}

timKiem.onmouseleave = () => {
    var timKiemHieuUngMo = document.querySelector('.tim-kiem-hieu-ung-mo');
    timKiemHieuUngMo.style.backgroundColor = 'transparent';
    timKiemHieuUngMo.style.opacity = 1;
}

var soLanClick = 0;
var timKiemIcon = document.querySelector('.tim-kiem-icon');
var boTimKiemIcon = document.querySelector('.bo-tim-kiem-icon');
var nhapTenSanPham = document.querySelector('.nhap-ten-san-pham');
nhapTenSanPham.style.display = 'none';
timKiem.onclick = () => {
    if (soLanClick == 0) {
        timKiemIcon.style.display = 'none';
        boTimKiemIcon.style.display = 'block';
        nhapTenSanPham.style.display = 'flex';
        dangNhapTenSanPham = true;
        timKiemIcon.style.animation = 'moDan ease 0.5s forwards';
        boTimKiemIcon.style.animation = 'damDan ease 0.5s forwards';
    }
    else {
        timKiemIcon.style.display = 'block';
        boTimKiemIcon.style.display = 'none';
        nhapTenSanPham.style.display = 'none';
        dangNhapTenSanPham = false;
        timKiemIcon.style.animation = 'damDan ease 0.5s forwards';
        boTimKiemIcon.style.animation = 'moDan ease 0.5s forwards';

        nhapTenSanPham.value = '';
    }
    soLanClick = (soLanClick + 1) % 2;
}

nhapTenSanPham.oninput = () => {
    // Xu ly sau
    console.log(nhapTenSanPham.value);
}

function xuatHienDangNhap(tenClassCha, tenBangMenuCon) {
    var mucCha = document.querySelector(tenClassCha);
    var bangMenuCon = document.querySelector(tenBangMenuCon);
    var count = 0;
    mucCha.onclick = (e) => {
        if (dangNhapTenSanPham == true) return;
        e.stopPropagation();
        if (count % 2 == 0) {
            bangMenuCon.style.display = 'block';
        }
        else {
            bangMenuCon.style.display = 'none';
        }
        count++;
    }

    bangMenuCon.onclick = (e) => {
        e.stopPropagation();
    }

    body.addEventListener('click', (e) => {
        if (e.target !== bangMenuCon) {
            count = 0;
            bangMenuCon.style.display = 'none';
        }
    });
}

function xuatHienMenuChon(tenClassCha, tenBangMenuCon) {
    var mucCha = document.querySelector(tenClassCha);
    var bangMenuCon = document.querySelector(tenBangMenuCon);
    mucCha.onmousemove = () => {
        bangMenuCon.style.display = 'block';
    }
    mucCha.onmouseleave = (e) => {
        bangMenuCon.style.display = 'none';
    }
}

xuatHienDangNhap('.dang-nhap', '.bang-dang-nhap');
xuatHienMenuChon('.danh-muc-san-pham', '.bang-danh-muc-con');
xuatHienMenuChon('.qua-tang-cao-cap', '.bang-qua-tang-cao-cap');
xuatHienMenuChon('.trai-cay-tuoi', '.bang-trai-cay-tuoi');

document.querySelectorAll('.bang-dang-nhap').forEach(item => {
    item.onclick = () => {
        item.firstElementChild.firstElementChild.click();
    }
});

window.onscroll = () => {
    if (document.documentElement.scrollTop > 90) {
        document.querySelector('.nut-len-dau-trang').classList.add('hien-nut-len-dau-trang');
    }
    else {
        document.querySelector('.nut-len-dau-trang').classList.remove('hien-nut-len-dau-trang');
    }
}

document.querySelectorAll('.thong-tin-muc-con-ket-thuc a').forEach((item) => {
    item.onmousemove = () => {
        item.style.color = '#0084cb';
    }
    item.onmouseleave = () => {
        item.style.color = '#212529';
    }
});

try {
    document.querySelector('#link-to-trang-chu').onmousemove = (e) => {
        e.target.style.color = '#0084cb';
    }

    document.querySelector('#link-to-trang-chu').onmouseleave = (e) => {
        e.target.style.color = '#3D464D';
    }
}
catch (err) {}

function voHieuHoaCuonChuot(e) {
    e.preventDefault();
}

try {
    document.querySelectorAll('.nut-chuyen-huong-den-trang-tin-tuc').forEach(item => {
        item.onmousemove = () => {
            item.firstElementChild.style.color = '#fff';
        }
        item.onmouseleave = () => {
            item.firstElementChild.style.color = '#3D464D';
        }
    });
    document.querySelectorAll('.anh-tin-tuc').forEach(item => {
        item.onclick = () => {
            item.lastElementChild.click();
        }
    });
}
catch(exception) {}

// Xu ly buoc phai dang nhap truoc khi thao tac voi san pham 
// window.addEventListener('wheel', voHieuHoaCuonChuot, { passive: false });
// setTimeout(() => {
//     document.querySelector('.thong-bao-buoc-phai-dang-nhap').style.display = 'block';
// }, 600);

// document.querySelector('#nut-huy-bo').onclick = () => {
//     document.querySelector('.buoc-phai-dang-nhap').style.animation = 'truotTuDuoiLen linear 0.5s forwards';
//     document.querySelector('html').style.scrollBehavior = 'smooth';
//     window.removeEventListener('wheel', voHieuHoaCuonChuot, { passive: false });
//     document.querySelector('body').style.setProperty('--scrollbar-width', '15px');
//     setTimeout(() => {
//         document.querySelector('.buoc-phai-dang-nhap').style.display = 'none';
//     }, 500);
// }