var thanhGachChan = document.querySelector('.thanh-gach-chan-item');
var itemMoTa = document.querySelector('.item-mo-ta');
var itemDanhGia = document.querySelector('.item-danh-gia');
var moTaChiTietSanPham = document.querySelector('.mo-ta-chi-tiet-san-pham');
var danhGiaSanPham = document.querySelector('.danh-gia-san-pham');
var hienTaiDuocClick = 0;
var nutPhanLoais = document.querySelectorAll('.nut-phan-loai');

function xetGiaTriChoKeyframes(element, keyFramesName) {
	var styleSheets = document.styleSheets;
	for (var i = 0; i < styleSheets.length; i++) {
		var rules = styleSheets[i].cssRules || styleSheets.rules;
		for (var j = 0; j < rules.length; j++) {
			if (rules[j].type === CSSRule.KEYFRAMES_RULE && rules[j].name === keyFramesName) {
				var keyframes = rules[j].cssRules;
				for (var k = 0; k < keyframes.length; k++) {
					if (keyframes[k].keyText === '100%') {
//						keyframes[k].cssText = `100% { height: ${element.offsetHeight}px; }`;
						keyframes[k].style.height = `${element.offsetHeight}px`;
//						console.log(keyframes[k], keyframes[k].cssText, `100% { height: ${element.offsetHeight}px; }`);
					}
				}
			}
		}
	}
}

//xetGiaTriChoKeyframes(moTaChiTietSanPham, 'keoDai1');
//xetGiaTriChoKeyframes(danhGiaSanPham, 'keoDai2');

itemMoTa.onclick = () => {
	if (hienTaiDuocClick != 0) {
		thanhGachChan.style.animation = 'sangTrai 0.2s ease forwards';
		danhGiaSanPham.style.display = 'none';
		danhGiaSanPham.style.animation = '';
		moTaChiTietSanPham.style.display = 'block';
//		console.log(moTaChiTietSanPham.offsetHeight);
		xetGiaTriChoKeyframes(moTaChiTietSanPham, 'keoDai1');
		moTaChiTietSanPham.style.animation = 'keoDai1 1.2s ease';
	}
	hienTaiDuocClick = 1;
}

itemDanhGia.onclick = () => {
	thanhGachChan.style.animation = 'sangPhai 0.2s ease forwards';
	moTaChiTietSanPham.style.display = 'none';
	moTaChiTietSanPham.style.animation = '';
	danhGiaSanPham.style.display = 'block';
//	console.log(danhGiaSanPham.offsetHeight);
	xetGiaTriChoKeyframes(danhGiaSanPham, 'keoDai2');
	danhGiaSanPham.style.animation = 'keoDai2 1.2s ease';
	hienTaiDuocClick = 1;
}

document.querySelectorAll('.thanh-phan-phan-trang').forEach(item => {
	item.onclick = () => {
		item.firstElementChild.click();
	}
});

nutPhanLoais.forEach(item => {
	item.onclick = () => {
		item.firstElementChild.click();
	}
});

//console.log(moTaChiTietSanPham.offsetHeight);

var nutTru = document.querySelector('.tru');
var nutCong = document.querySelector('.cong');
var hienThiSoLuong = document.querySelector('#so-luong-hien-thi p');
var soLuongHidden = document.querySelector('.so-luong');

nutTru.onmouseover = () => {
	var preSoLuong = parseInt(soLuongHidden.innerText);
	var soLuong = preSoLuong - 1;
	if (soLuong <= 0) {
		nutTru.style.cursor = 'not-allowed';
	}
	else {
		nutTru.style.cursor = 'pointer';
	}
}

nutTru.onclick = () => {
	var preSoLuong = parseInt(soLuongHidden.innerText);
	var soLuong = preSoLuong - 1;
	if (soLuong > 0) {
		hienThiSoLuong.innerText = soLuong.toString();
		soLuongHidden.innerText = soLuong.toString();
		hienThiSoLuong.title = soLuong.toString();
		hienThiSoLuong.parentElement.title = soLuong.toString();
	}
	else {
		nutTru.style.cursor = 'not-allowed';
	}
}

nutCong.onclick = () => {
	nutTru.style.cursor = 'pointer';
	var preSoLuong = parseInt(soLuongHidden.innerText);
	var soLuong = preSoLuong + 1;
	if (soLuong > 99) {
		hienThiSoLuong.innerText = '99+';
	}
	else {
		hienThiSoLuong.innerText = soLuong.toString();
	}
	soLuongHidden.innerText = soLuong.toString();
	hienThiSoLuong.title = soLuong.toString();
	hienThiSoLuong.parentElement.title = soLuong.toString();
}