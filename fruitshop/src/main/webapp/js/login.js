var body = document.querySelector('body');
var email = document.querySelector('#email');
var matKhau = document.querySelector('#mat-khau');
var conMat = document.querySelector('#con-mat');
var kieu = '';
conMat.addEventListener('click', () => {
    if (conMat.classList.contains('fa-eye-slash') == false) {
        conMat.classList.add('fa-eye-slash');
        conMat.classList.remove('fa-eye');
        kieu = 'text';
    }
    else {
        conMat.classList.remove('fa-eye-slash');
        conMat.classList.add('fa-eye');
        kieu = 'password';
    }
    matKhau.type = kieu;
});

var phanTuHienTai = null;
body.onclick = (e) => {
    if (e.target == email || e.target == matKhau) {
        if (phanTuHienTai == null || phanTuHienTai == e.target) {
            e.target.style.border = '3px solid rgba(26, 102, 255, 0.5)';
            phanTuHienTai = e.target;
        }
        else if (phanTuHienTai != null && phanTuHienTai != e.target) {
            phanTuHienTai.style.border = '';
            e.target.style.border = '3px solid rgba(26, 102, 255, 0.5)';
            phanTuHienTai = e.target;
        }
    }
    else if (phanTuHienTai != null) {
        phanTuHienTai.style.border = '';
    }
}

function hasUnicode (str) {
    for (var i = 0; i < str.length; i++) {
        if (str.charCodeAt(i) > 127) return true;
    }
    return false;
}

var listItem = [document.querySelector('.email'), document.querySelector('.mat-khau')];
var listInput = [document.querySelector('#email'), document.querySelector('#mat-khau')];
var nutDangNhap = document.querySelector('.nut-dang-nhap').onclick = (e) => {
    for (var i = 0; i < 2; i++) {
        if (hasUnicode(listInput[i].value) == true) {
			e.preventDefault();
            listItem[i].insertAdjacentHTML('beforeend', 
                `<div class="canh-bao-invalid">
                    <div class="canh-bao">
                        <i class="fa-solid fa-circle-exclamation"></i>
                        <p class="doan-van-canh-bao">Invalid ${listItem[i].querySelector('.ten-input').innerText} !</p>
                    </div>
                </div>`
            );
            setTimeout(() => {
                listItem[i].removeChild(listItem[i].querySelector('.canh-bao-invalid'));
            }, 4000);
            break;
        }
    }
}