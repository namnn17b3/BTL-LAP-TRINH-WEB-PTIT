var body = document.querySelector('body');
var ten = document.querySelector('#ten');
var email = document.querySelector('#email');
var diaChiNguoiNhan = document.querySelector('#dia-chi-nguoi-nhan');
var soDienThoaiNguoiNhan = document.querySelector('#so-dien-thoai-nguoi-nhan');
var soTaiKhoanNguoiChuyen = document.querySelector('#so-tai-khoan-nguoi-chuyen');
var nganHangNguoiChuyen = document.querySelector('#ngan-hang-nguoi-chuyen');
var ngayChuyenKhoan = document.querySelector('#ngay-chuyen-khoan');

var phanTuHienTai = null;
body.onclick = (e) => {
    if (e.target == email ||
    	e.target == diaChiNguoiNhan ||
    	e.target == ten ||
    	e.target == soDienThoaiNguoiNhan ||
    	e.target == soTaiKhoanNguoiChuyen ||
    	e.target == nganHangNguoiChuyen ||
    	e.target == ngayChuyenKhoan) {
		
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

function namNhuan(nam) {
	if (nam % 400 == 0) {
		return true;
	}
	if (nam % 100 == 0) {
		return false;
	}
	if (nam % 4 == 0) {
		return true;
	}
	return false;
}

function checkDateFormat(dateString, regxString) {
	var thangTrongNam = [0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
	var namHienTai = new Date().getFullYear();
	var res = dateString.match(regxString);
	
	var ngay = parseInt(res.groups.ngay);
	var thang = parseInt(res.groups.thang);
	var nam = parseInt(res.groups.nam);
	
	var gio = parseInt(res.groups.gio);
	var phut = parseInt(res.groups.phut);
	var giay = parseInt(res.groups.giay);
	
	if (thang > 12 || thang == 0 || ngay == 0 || namHienTai - nam > 1) {
		return false;
	}
	if (thang != 2 && ngay > thangTrongNam[thang]) {
		return false;
	}
	if (thang == 2 && namNhuan(nam) == true && ngay > 29) {
		return false;
	}
	if (thang == 2 && namNhuan(nam) == false && ngay > 28) {
		return false;
	}
	
	if (gio > 23 || phut > 59 || giay > 59) {
		return false;
	}
	return true;
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
		document.querySelector('.dia-chi-nguoi-nhan'),
		document.querySelector('.so-dien-thoai-nguoi-nhan'),
		document.querySelector('.so-tai-khoan-nguoi-chuyen'),
		document.querySelector('.ngan-hang-nguoi-chuyen'),
		document.querySelector('.ngay-chuyen-khoan')
	];
    var listString = [ten.value, email.value, diaChiNguoiNhan.value, soDienThoaiNguoiNhan.value, soTaiKhoanNguoiChuyen.value, nganHangNguoiChuyen.value, ngayChuyenKhoan.value];
    var listRegex = [/.{1,50}/, /([a-zA-Z0-9\.]+)@([a-zA-H0-9\.].+)/, /.{1,50}/, /\d{10,15}/, /\d{8,15}/, /.{1,50}/, /(?<ngay>\d{2})\/(?<thang>\d{2})\/(?<nam>\d{4}) (?<gio>\d{2}):(?<phut>\d{2}):(?<giay>\d{2})/];
    var length = chuyenKhoan.style.display == '' ? 4 : listString.length;
    for (var i = 0; i < length; i++) {
        if (listRegex[i].exec(listString[i]) == null || listRegex[i].exec(listString[i])[0] != listString[i] || (i == 6 && checkDateFormat(listString[i], listRegex[i]) == false)) {
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
			setTimeout(() => {
				chuyenKhoan.style.overflow = 'visible';
			}, 500);
		}
		else {
			chuyenKhoan.style.overflow = 'hidden';
			chuyenKhoan.style.animation = 'chayTuDuoiLen ease 0.5s forwards';
			setTimeout(() => {				
				chuyenKhoan.style.display = '';
			}, 500);
		}
	}
});

var thanhDemThoiGian = document.querySelector('.thanh-dem-thoi-gian');
var noiDungThongBaoMini2 = document.querySelector('.noi-dung-thong-bao-mini-2');