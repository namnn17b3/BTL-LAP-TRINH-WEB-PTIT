var body = document.querySelector('body');
var ten = document.querySelector('#ten');
var email = document.querySelector('#email');
var matKhau = document.querySelector('#mat-khau');
var nhapLaiMatKhau = document.querySelector('#nhap-lai-mat-khau');
var conMat = [document.querySelector('#con-mat-1'), document.querySelector('#con-mat-2')];
conMat.forEach((item) => {
    item.addEventListener('click', () => {
        var kieu = '';
        if (item.classList.contains('fa-eye-slash') == false) {
            item.classList.add('fa-eye-slash');
            item.classList.remove('fa-eye');
            kieu = 'text';
        }
        else {
            item.classList.remove('fa-eye-slash');
            item.classList.add('fa-eye');
            kieu = 'password';
        }
        if (item.parentElement == document.querySelector('.mat-khau')) {
            matKhau.type = kieu;
        }
        else {
            nhapLaiMatKhau.type = kieu;
        }
    });
});

var phanTuHienTai = null;
body.onclick = (e) => {
    if (e.target == email || e.target == matKhau || e.target == ten || e.target == nhapLaiMatKhau) {
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

function hasUnicode (str) {
    for (var i = 0; i < str.length; i++) {
        if (str.charCodeAt(i) > 127) return true;
    }
    return false;
}

var button = document.querySelector('.nut-dang-ki');
button.onclick = (e) => {
    var listItem = [document.querySelector('.ten'), document.querySelector('.email'), document.querySelector('.mat-khau'), document.querySelector('.nhap-lai-mat-khau')];
    var listString = [ten.value, email.value, matKhau.value, nhapLaiMatKhau.value];
    var listRegex = [/[a-zA-Z0-9]/, /([a-zA-Z0-9\.]+)@([a-zA-H0-9\.].+)/, /.{6,}/, new RegExp(matKhau.value)];
    for (var i = 0; i < 4; i++) {
        if (listRegex[i].test(listString[i]) == false || (i != 0 && hasUnicode(listString[i]) == true)) {
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
    };
}