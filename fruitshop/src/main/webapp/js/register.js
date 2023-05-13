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

var anhUpload = document.querySelector('.anh-dai-dien');
anhUpload.style.height = `${document.querySelector('.anh-dai-dien').offsetWidth}px`;
anhUpload.onclick = () => {
	anhUpload.firstElementChild.click();
}

var inputFileUpLoad = document.querySelector('#upload-file');
inputFileUpLoad.onchange = () => {
	var cacFileDuocUpLoad = inputFileUpLoad.files;
	var fileDauTienDuocUpLoad = cacFileDuocUpLoad[0];
	var fileReader = new FileReader();
	if (cacFileDuocUpLoad[0]) {
		fileReader.onload = (e) => {
			var src = e.target.result;
			document.querySelector('#anh-upload').src = src;
		}
	}
	fileReader.readAsDataURL(fileDauTienDuocUpLoad);	
}

// Vô hiệu hóa khi có thông báo
function voHieuHoaCuonChuot(e) {
    e.preventDefault();
}

//window.addEventListener('wheel', voHieuHoaCuonChuot, { passive: false });
//setTimeout(() => {
//    document.querySelector('.thong-bao-ve-viec').style.display = 'flex';
//}, 600);
//
//var time = 59;
//var timeCounter = document.querySelector('.thoi-gian')
//var nutOk = document.querySelector('#nut-ok');
//
//var interval = setInterval(() => {
//	if (time < 0) {
//		timeCounter.innerText = 'Hết thời gian! Vui lòng đăng kí lại tài khoản';
//		clearInterval(interval);
//		nutOk.style.cursor = 'not-allowed';
//		nutOk.onclick = (e) => {
//			e.preventDefault();
//		}
//		nutOk.onmousemove = () => {
//			nutOk.style.backgroundColor = '#ffd333';
//			nutOk.style.color = '#3d464d';
//		}
//		nutOk.style.opacity = '0.5';
//	}
//	else {
//		phut = parseInt(time / 60);
//		giay = `${time % 60}`;
//		if (giay.length < 2) {
//			giay = '0' + giay;
//		}
//		timeString = 'Thời gian còn lại: ' + `${phut}` + ':' + giay;
//		timeCounter.innerText = timeString;
//		time--;
//	}
//}, 1000);
//
//document.querySelector('#nut-huy-bo').onclick = () => {
//    document.querySelector('.thong-bao').style.animation = 'truotTuDuoiLen linear 0.5s forwards';
//    document.querySelector('html').style.scrollBehavior = 'smooth';
//    window.removeEventListener('wheel', voHieuHoaCuonChuot, { passive: false });
//    document.querySelector('body').style.setProperty('--scrollbar-width', '15px');
//    setTimeout(() => {
//        document.querySelector('.thong-bao').style.display = 'none';
//    }, 500);
//}