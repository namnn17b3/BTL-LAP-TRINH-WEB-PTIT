var nutChuyenSlide = document.querySelectorAll('.nut-chuyen-slide');
for (var i = 0; i < nutChuyenSlide.length; i++) {
    nutChuyenSlide[i].onmousemove = (e) => {
        e.stopPropagation();
        for (var j = 0; j < nutChuyenSlide.length; j++) {
            nutChuyenSlide[j].classList.add('hien-nut-chuyen-slide');
        }
    }
    nutChuyenSlide[i].onmouseleave = (e) => {
        e.stopPropagation();
        for (var j = 0; j < nutChuyenSlide.length; j++) {
            nutChuyenSlide[j].classList.remove('hien-nut-chuyen-slide');
        }
    }
}

var danhSachLinkAnhTruot = ['./img/dau_tay_slide_show.jpg', './img/cam_slide_show.jpg', './img/tao_slide_show.jpg'];
var danhSachNutNho = document.querySelectorAll('.nut-nho');
var daiAnhTruot = document.querySelector('.dai-anh-truot');
var chiMucHienTai = 0;

danhSachNutNho[0].style.backgroundColor = '#ffd333';
danhSachNutNho[0].style.opacity = 0.8;

function thucHienHieuUngTruotAnh(hieuUng) {
    var danhSachAnhTruot = document.querySelectorAll('.anh-truot-hien-tai');
    danhSachAnhTruot.forEach((item) => {
        item.style.animation = hieuUng;
    });
}

nutChuyenSlide.forEach((item, index) => {
    item.onclick = (e) => {
        if (dangNhapTenSanPham == false) {
            e.stopPropagation();
            danhSachNutNho[chiMucHienTai].style.backgroundColor = '#e0e0e0';
            danhSachNutNho[chiMucHienTai].style.opacity = 0.5;
            var anhtruot = document.createElement('img');
            anhtruot.className = 'anh-truot-hien-tai';
            if (index == 1) {
                chiMucHienTai++;
                chiMucHienTai %= danhSachNutNho.length;
                anhtruot.src = danhSachLinkAnhTruot[chiMucHienTai];
                daiAnhTruot.insertAdjacentElement('beforeend', anhtruot);
                thucHienHieuUngTruotAnh('truotTuPhaiQua ease-in 0.8s forwards');
            }
            else {
                chiMucHienTai = chiMucHienTai + danhSachNutNho.length - 1;
                chiMucHienTai %= danhSachNutNho.length;
                anhtruot.src = danhSachLinkAnhTruot[chiMucHienTai];
                daiAnhTruot.insertAdjacentElement('afterbegin', anhtruot);
                thucHienHieuUngTruotAnh('truotTuTraiQua ease-in 0.9s forwards');
            }
            danhSachNutNho[chiMucHienTai].style.backgroundColor = '#ffd333';
            danhSachNutNho[chiMucHienTai].style.opacity = 0.9;
            setTimeout(() => {
                daiAnhTruot.innerHTML = '';
                anhtruot.style.animation = '';
                daiAnhTruot.appendChild(anhtruot);
            }, 900);
        }
    }
});

setInterval(() => {
    danhSachNutNho[chiMucHienTai].style.backgroundColor = '#e0e0e0';
    danhSachNutNho[chiMucHienTai].style.opacity = 0.5;
    var anhtruot = document.createElement('img');
    anhtruot.className = 'anh-truot-hien-tai';

    chiMucHienTai++;
    chiMucHienTai %= danhSachNutNho.length;
    anhtruot.src = danhSachLinkAnhTruot[chiMucHienTai];
    daiAnhTruot.insertAdjacentElement('beforeend', anhtruot);
    thucHienHieuUngTruotAnh('truotTuPhaiQua ease-in 0.8s forwards');

    danhSachNutNho[chiMucHienTai].style.backgroundColor = '#ffd333';
    danhSachNutNho[chiMucHienTai].style.opacity = 0.9;
    setTimeout(() => {
        daiAnhTruot.innerHTML = '';
        anhtruot.style.animation = '';
        daiAnhTruot.appendChild(anhtruot);
    }, 900);
}, 5000);

// Xu ly load truoc khi vao trang
setTimeout(() => {
	document.querySelector('.load-truoc-khi-vao-trang').style.display = 'none';
	document.querySelector('.phan-duoc-hien-thi').style.display = 'block';
}, 1200);