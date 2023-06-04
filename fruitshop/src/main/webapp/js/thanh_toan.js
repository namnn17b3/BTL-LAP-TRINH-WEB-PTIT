var body = document.querySelector('body');
var ten = document.querySelector('#ten');
var email = document.querySelector('#email');
var matKhau = document.querySelector('#mat-khau');
var nhapLaiMatKhau = document.querySelector('#nhap-lai-mat-khau');
var soTaiKhoanNguoiChuyen = document.querySelector('#so-tai-khoan-nguoi-chuyen');
var nganHangNguoiChuyen = document.querySelector('#ngan-hang-nguoi-chuyen');
var ngayChuyenKhoan = document.querySelector('#ngay-chuyen-khoan');

var phanTuHienTai = null;
body.onclick = (e) => {
    if (e.target == email || e.target == matKhau || e.target == ten || e.target == nhapLaiMatKhau || e.target == soTaiKhoanNguoiChuyen || e.target == nganHangNguoiChuyen || e.target == ngayChuyenKhoan) {
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

var button = document.querySelector('.nut-dang-ki');
button.onclick = (e) => {
	// console.log('line 64');
	// themCookie('done', 0, 3600000 * 24);
	// cho trường hợp update profile
	themCookie('update_success', 0, 3600000 * 24, 60, '/fruitshop/profile');
	// e.preventDefault();
    var listItem = [
		document.querySelector('.ten'),
		document.querySelector('.email'),
		document.querySelector('.mat-khau'),
		document.querySelector('.nhap-lai-mat-khau'),
		document.querySelector('.so-tai-khoan-nguoi-chuyen'),
		document.querySelector('.ngan-hang-nguoi-chuyen'),
		document.querySelector('.ngay-chuyen-khoan')
	];
    var listString = [ten.value, email.value, matKhau.value, nhapLaiMatKhau.value, soTaiKhoanNguoiChuyen.value, nganHangNguoiChuyen.value, ngayChuyenKhoan.value];
    var listRegex = [/.+/, /([a-zA-Z0-9\.]+)@([a-zA-H0-9\.].+)/, /.+/, /\d+/, /\d{8,15}/, /.+/, /\d{2}\/\d{2}\/\d{4} \d{2}:\d{2}:\d{2}/];
    for (var i = 0; i < listString.length; i++) {
        if (listRegex[i].exec(listString[i]) == null || listRegex[i].exec(listString[i])[0] != listString[i]) {
            e.preventDefault();
            listItem[i].insertAdjacentHTML('beforeend', 
                `<div class="canh-bao-invalid">
                    <div class="canh-bao">
                        <i class="fa-solid fa-circle-exclamation"></i>
                        <p class="doan-van-canh-bao">Invalid ${listItem[i].querySelector('.ten-input').innerText.split(" ")[0]} !</p>
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

document.querySelector('#tien-mat').click();
var chuyenKhoan = document.querySelector('.hinh-thuc-thanh-toan-chuyen-khoan');
var hinhThucThanhToan = document.querySelectorAll('.hinh-thuc-thanh-toan');
hinhThucThanhToan[0].firstElementChild.style.display = 'block';
hinhThucThanhToan.forEach((item, index) => {
	item.onclick = () => {
		item.firstElementChild.style.display = 'block';
		
		var idx = (index == 0) ? index + 1 : index - 1;
		hinhThucThanhToan[idx].firstElementChild.style.display = 'none';
		
		if (index == 1) {
			chuyenKhoan.style.display = 'block';
			chuyenKhoan.style.animation = 'chayTuTrenXuong ease 0.5s forwards';
		}
		else {
			chuyenKhoan.style.animation = 'chayTuDuoiLen ease 0.5s forwards';
			setTimeout(() => {				
				chuyenKhoan.style.display = 'none';
			}, 500);
		}
	}
});

var thanhDemThoiGian = document.querySelector('.thanh-dem-thoi-gian');
var noiDungThongBaoMini2 = document.querySelector('.noi-dung-thong-bao-mini-2');